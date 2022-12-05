import 'package:bloc/bloc.dart';
import 'package:bloodify_front_end/shared/Constatnt/nationalIDValidator.dart';
import 'package:geolocator/geolocator.dart';
import 'package:intl/intl.dart';
import 'package:meta/meta.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../models/sign_up.dart';
import '../../../models/user.dart';
import '../../../shared/network/remote/dio_helper.dart';

part 'sign_up_state.dart';

class SignUpCubit extends Cubit<SignUpStates> {
  IconData PasswordSuffix = Icons.visibility_outlined;
  bool PasswordIsPassword = true;
  DateTime supposedDateOfBirth = DateTime.now();
  IconData PasswordConfirmSuffix = Icons.visibility_outlined;
  bool PasswordConfirmIsPassword = true;

  UserData user = UserData();
  // bool serviceEnabled = false;

  /// Determine the current position of the device.
  ///
  /// When the location services are not enabled or permissions
  /// are denied the `Future` will return an error.
  // Future<Position> _determinePosition() async {
  //   LocationPermission permission;

  //   // Test if location services are enabled.
  //   serviceEnabled = await Geolocator.isLocationServiceEnabled();
  //   if (!serviceEnabled) {
  //     // Location services are not enabled don't continue
  //     // accessing the position and request users of the
  //     // App to enable the location services.
  //     return Future.error('Location services are disabled.');
  //   }

  //   permission = await Geolocator.checkPermission();
  //   if (permission == LocationPermission.denied) {
  //     permission = await Geolocator.requestPermission();
  //     if (permission == LocationPermission.denied) {
  //       // Permissions are denied, next time you could try
  //       // requesting permissions again (this is also where
  //       // Android's shouldShowRequestPermissionRationale
  //       // returned true. According to Android guidelines
  //       // your App should show an explanatory UI now.
  //       return Future.error('Location permissions are denied');
  //     }
  //   }

  //   if (permission == LocationPermission.deniedForever) {
  //     // Permissions are denied forever, handle appropriately.
  //     return Future.error(
  //         'Location permissions are permanently denied, we cannot request permissions.');
  //   }

  //   // When we reach here, permissions are granted and we can
  //   // continue accessing the position of the device.
  //   return await Geolocator.getCurrentPosition();
  // }

  SignUpCubit() : super(SignUpInitial());

  static SignUpCubit get(context) => BlocProvider.of(context);

  void changePassWordVisibilityPass() {
    PasswordIsPassword = !PasswordIsPassword;
    PasswordSuffix = PasswordIsPassword
        ? Icons.visibility_outlined
        : Icons.visibility_off_outlined;
    emit(ChangePasswordVisibilityState());
  }

  void changePassWordVisibilityConfirm() {
    PasswordConfirmIsPassword = !PasswordConfirmIsPassword;
    PasswordConfirmSuffix = PasswordConfirmIsPassword
        ? Icons.visibility_outlined
        : Icons.visibility_off_outlined;
    emit(ChangePasswordVisibilityState());
  }

  void changeDateOfBirth(DateTime time) {
    user.dOB = time;
    emit(ChangeDate());
  }

  void changeLastDonated(DateTime time) {
    user.last_donated_time = time;
    print(user.last_donated_time);
    emit(lastDonated());
  }

  void changeBloodType(String type) {
    user.bloodType = type;
    emit(ChangeBloodType());
  }

  void changeIsPatient(bool value) {
    user.isPatient = value;
    emit(ChangeIsPatient());
  }

  // void changeLocation(String location) {
  //   user.location = location;
  //   emit(ChangeLocation());
  // }

  void setSupposedDateOfBirth(NationalIDParser parser) {
    supposedDateOfBirth = parser.getDOB();
    emit(GetSupposedDateOfBirth());
  }

  // void getLocation() {
  //   emit(GetLocationsLoading());
  //   _determinePosition().then((value) {
  //     print(value);
  //     // user.location = value;
  //     emit(GetLocationsSucces());
  //   }).onError((error, stackTrace) {
  //     print("error $error");
  //     emit(GetLocationsError(error.toString()));
  //   });
  // }

  void userSignUp() {
    emit(SignUpLoading());
    var last_time_donated = user.last_donated_time != null
        ? new DateFormat("dd-MM-yyyy").format(user.last_donated_time!)
        : null;
    var dof = new DateFormat("dd-MM-yyyy").format(user.dOB);
    print("${last_time_donated} date ${dof}");
    DioHelper.postData(url: 'user',
        // data: {
        //   'name': name,
        //   'email': email,
        //   'password': password,
        //   'phone': phone,
        // },
        data: {
          'firstName': user.fName,
          'lastName': user.lName,
          'nationalID': user.nationalID,
          'email': user.email,
          'password': user.password,
          // 'location': user.location,
          'lastTimeDonated': last_time_donated,
          'hasDiseases': user.isPatient,
          'bloodType': user.bloodType,
        }).then((value) {
      print(value.data);
      // loginModel = user.fromJson(value.data);
      emit(SiqnUpApiSucces(SignUpResponse.fromJson(value.data)));
    }).catchError((error) {
      if (error.response.statusCode == 409) {
        print(error.response);
        emit(SiqnUpApiSucces(SignUpResponse.fromJson(error.response.data)));
      } else {
        print(error.response.body);
        emit(SiqnUpApiError(error.toString()));
      }
    });
  }
}

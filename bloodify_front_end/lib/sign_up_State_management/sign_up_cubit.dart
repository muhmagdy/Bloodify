import 'package:bloc/bloc.dart';
import 'package:geolocator/geolocator.dart';
import 'package:meta/meta.dart';
import 'package:flutter/material.dart';
import 'package:bloodify_front_end/sign_up_State_management/sign_up_user_model.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../shared/network/remote/dio_helper.dart';

part 'sign_up_state.dart';

class SignUpCubit extends Cubit<SignUpStates> {
  IconData PasswordSuffix = Icons.visibility_outlined;
  bool PasswordIsPassword = true;

  IconData PasswordConfirmSuffix = Icons.visibility_outlined;
  bool PasswordConfirmIsPassword = true;

  UserData user = UserData();
  bool serviceEnabled = false;

  /// Determine the current position of the device.
  ///
  /// When the location services are not enabled or permissions
  /// are denied the `Future` will return an error.
  Future<Position> _determinePosition() async {
    LocationPermission permission;

    // Test if location services are enabled.
    serviceEnabled = await Geolocator.isLocationServiceEnabled();
    if (!serviceEnabled) {
      // Location services are not enabled don't continue
      // accessing the position and request users of the
      // App to enable the location services.
      return Future.error('Location services are disabled.');
    }

    permission = await Geolocator.checkPermission();
    if (permission == LocationPermission.denied) {
      permission = await Geolocator.requestPermission();
      if (permission == LocationPermission.denied) {
        // Permissions are denied, next time you could try
        // requesting permissions again (this is also where
        // Android's shouldShowRequestPermissionRationale
        // returned true. According to Android guidelines
        // your App should show an explanatory UI now.
        return Future.error('Location permissions are denied');
      }
    }

    if (permission == LocationPermission.deniedForever) {
      // Permissions are denied forever, handle appropriately.
      return Future.error(
          'Location permissions are permanently denied, we cannot request permissions.');
    }

    // When we reach here, permissions are granted and we can
    // continue accessing the position of the device.
    return await Geolocator.getCurrentPosition();
  }

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

  void changeBloodType(String type) {
    user.bloodType = type;
    emit(ChangeBloodType());
  }

  void changeIsPatient(bool value) {
    user.isPatient = value;
    emit(ChangeIsPatient());
  }

  void changeLocation(String location) {
    user.location = location;
    emit(ChangeLocation());
  }

  void getLocation() {
    emit(GetLocationsLoading());
    _determinePosition().then((value) {
      print(value);
      user.location = value;
      emit(GetLocationsSucces());
    }).onError((error, stackTrace) {
      print("error $error");
      emit(GetLocationsError(error.toString()));
    });
  }

  void userSignUp() {
    emit(SignUpLoading());
    DioHelper.postData(
        url: 'register',
        // data: {
        //   'name': name,
        //   'email': email,
        //   'password': password,
        //   'phone': phone,
        // },
        data: {
          'first_name': user.fName,
          'last_name': user.lName,
          'nationalID': user.nationalID,
          'email': user.email,
          'password': user.password,
          'location': user.location,
          'date_of_birth': user.dOB,
          'isPatient': user.isPatient,
          'bloodType': user.bloodType,
        }).then((value) {
      print(value.data);
      // loginModel = user.fromJson(value.data);
      emit(SiqnUpApiSucces(SignUpResponse.fromJson(value.data)));
    }).catchError((error) {
      print(error.toString());
      emit(SiqnUpApiError(error.toString()));
    });
  }
}

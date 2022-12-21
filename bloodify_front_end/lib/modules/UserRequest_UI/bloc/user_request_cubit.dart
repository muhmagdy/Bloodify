import 'package:bloodify_front_end/models/BloodRequest.dart';
import 'package:bloodify_front_end/models/found_institution.dart';
import 'package:bloodify_front_end/modules/UserRequest_UI/bloc/user_request_service.dart';
import 'package:bloodify_front_end/shared/Constatnt/Component.dart';
import 'package:equatable/equatable.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

part 'user_request_state.dart';

class UserRequestFormCubit extends Cubit<UserRequestFormState> {
  UserRequestFormCubit() : super(UserRequestFormState.initial());

  static UserRequestFormCubit get(context) => BlocProvider.of(context);

  void changeBloodType(String bloodType) {
    emit(state.copyWith(pickedBloodType: bloodType));
  }

  void changeBloodBagsCount(double bloodBagsCount) {
    emit(state.copyWith(bloodBagsCount: bloodBagsCount));
  }

  void changeRequestExpiryDate(DateTime expiryDate) {
    emit(state.copyWith(expiryDate: expiryDate));
  }

  void loadInstitutions() async {
    if (state.institutions.isEmpty) {
      try {
        var currState = state.copyWith();
        emit(UserRequestFormState.loading().copyWith(
            pickedBloodType: currState.pickedBloodType,
            expiryDate: currState.expiryDate,
            bloodBagsCount: currState.bloodBagsCount));

        var data = await getInstitutions();
        emit(UserRequestFormState.institutionsLoadSuccess(institutions: data)
            .copyWith(
                pickedBloodType: currState.pickedBloodType,
                expiryDate: currState.expiryDate,
                bloodBagsCount: currState.bloodBagsCount));
      } catch (e) {
        print(e);
      }
    }
  }

  dynamic changeInstitution(dynamic institution) {
    emit(state.copyWith(pickedInstitution: institution));
  }

  bool validate() {
    return state.bloodBagsCount != null &&
        state.expiryDate != null &&
        state.pickedBloodType != null &&
        state.pickedInstitution != null;
  }

  Future<void> submit() async {
    print("sending post...");
    print(state);

    BloodRequest bloodRequest = BloodRequest(
        institutionID: state.pickedInstitution!.institutionID,
        LastUpdateTime: DateTime.now(),
        expiryTime: state.expiryDate,
        requiredBags: state.bloodBagsCount.toInt(),
        bloodType: state.pickedBloodType);

    var currState = state.copyWith();
    emit(UserRequestFormState.loading().copyWith(
        pickedBloodType: currState.pickedBloodType,
        pickedInstitution: currState.pickedInstitution,
        bloodBagsCount: currState.bloodBagsCount,
        expiryDate: currState.expiryDate!.isAfter(DateTime.now())
            ? currState.expiryDate
            : null));

    bool success = await createRequest(bloodRequest);

    if (success) {
      emit(UserRequestFormState.createdSuccessfully());
    } else {
      showToast(
          text: "Error while creating the post", color: Colors.black, time: 2);
      emit(currState.copyWith());
    }
  }
}

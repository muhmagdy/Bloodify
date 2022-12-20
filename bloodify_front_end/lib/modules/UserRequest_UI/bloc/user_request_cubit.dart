import 'dart:io';

import 'package:bloodify_front_end/models/found_institution.dart';
import 'package:bloodify_front_end/shared/Constatnt/Component.dart';
import 'package:equatable/equatable.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

part 'user_request_state.dart';

class UserRequestFormCubit extends Cubit<UserRequestFormState> {
  //TODO add data repo
  //   NewCarBloc({required NewCarRepository newCarRepository})
  //     : _newCarRepository = newCarRepository,
  //       super(const NewCarState.initial()) {
  //   on<NewCarEvent>(_onEvent, transformer: sequential());
  // }
  //final InstitutionRepo

  UserRequestFormCubit() : super(UserRequestFormState.initial());

  static UserRequestFormCubit get(context) => BlocProvider.of(context);

  // void onUserRequestFormLoaded(
  //   UserRequestFormLoaded event,
  //   Emitter<UserRequestFormState> emit,
  // ) {}

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
        emit(UserRequestFormState.institutionsLoadInProgress().copyWith(
            pickedBloodType: currState.pickedBloodType,
            expiryDate: currState.expiryDate,
            bloodBagsCount: currState.bloodBagsCount));
        // emit(UserRequestFormState.institutionsLoadInProgress());

        var data = await getInstitutions();
        emit(UserRequestFormState.institutionsLoadSuccess(institutions: data)
            .copyWith(
                pickedBloodType: currState.pickedBloodType,
                expiryDate: currState.expiryDate,
                bloodBagsCount: currState.bloodBagsCount));

        // emit(UserRequestFormState.institutionsLoadSuccess(institutions: data));
      } catch (e) {
        print(e);
      }
    }
  }

  Future<List<InstitutionBrief>> getInstitutions() async {
    return await Future.delayed(
        Duration(seconds: 5),
        (() => [
              InstitutionBrief(institutionID: 1, name: "one", location: "alex"),
              InstitutionBrief(institutionID: 2, name: "two", location: "alex"),
              InstitutionBrief(
                  institutionID: 3, name: "three", location: "alex"),
              InstitutionBrief(
                  institutionID: 4, name: "four", location: "alex"),
              InstitutionBrief(
                  institutionID: 5, name: "five", location: "alex"),
              InstitutionBrief(institutionID: 6, name: "six", location: "alex"),
              InstitutionBrief(
                  institutionID: 7, name: "seven", location: "alex"),
              InstitutionBrief(
                  institutionID: 8, name: "eight", location: "alex"),
              InstitutionBrief(
                  institutionID: 9, name: "nine", location: "alex"),
            ]));
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

  void submit() {
    print("sending post...");
    print(state);
    //TODO get user location
  }
}

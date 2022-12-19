import 'dart:io';

import 'package:bloodify_front_end/modules/UserRequest_UI/user_request.dart';
import 'package:bloodify_front_end/modules/signUP_UI/sign_up_State_management/sign_up_cubit.dart';
import 'package:equatable/equatable.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:equatable/equatable.dart';

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

  void changeBloodBagsCountChan(double bloodBagsCount) {
    emit(state.copyWith(bloodBagsCount: bloodBagsCount));
  }

  void changeRequestExpiryDate(DateTime expiryDate) {
    print(expiryDate);
    emit(state.copyWith(expiryDate: expiryDate));
  }

  void loadInstitutions() async {
    if (state.institutions.isEmpty) {
      try {
        var data = await getInstitutions();
        print("yaaaaw");
        emit(state.copyWith(institutions: data));
      } catch (e) {
        print(e);
      }
    }
  }

  Future<List<String>> getInstitutions() async {
    return await Future.delayed(
        Duration(seconds: 2),
        (() => [
              "one",
              "two",
              "three",
              "one",
              "two",
              "three",
              "one",
              "two",
              "three",
              "one",
              "two",
              "three",
              "one",
              "two",
              "three",
              "one",
              "two",
              "three",
              "one",
              "two",
              "three",
              "one",
              "two",
              "three",
              "one",
              "two",
              "three",
              "one",
              "two",
              "three",
              "one",
              "two",
              "three",
              "one",
              "two",
              "three",
              "one",
              "two",
              "three"
            ]));
  }

  void changeInstitution(String institution) {
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

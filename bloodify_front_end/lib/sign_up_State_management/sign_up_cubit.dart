import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';
import 'package:flutter/material.dart';
import 'package:bloodify_front_end/sign_up_State_management/sign_up_user_model.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
part 'sign_up_state.dart';

class SignUpCubit extends Cubit<SignUpState> {
  IconData PasswordSuffix = Icons.visibility_outlined;
  bool PasswordIsPassword = true;

  IconData PasswordConfirmSuffix = Icons.visibility_outlined;
  bool PasswordConfirmIsPassword = true;

  UserData user = UserData();

  SignUpCubit() : super(SignUpInitial());

  static SignUpCubit get(context) => BlocProvider.of(context);

  void changePassWordVisibilityPass(){
    PasswordIsPassword  = !PasswordIsPassword;
    PasswordSuffix = PasswordIsPassword ? Icons.visibility_outlined : Icons.visibility_off_outlined;
    emit(ChangePasswordVisibilityState());
  }

  void changePassWordVisibilityConfirm() {
    PasswordConfirmIsPassword = !PasswordConfirmIsPassword;
    PasswordConfirmSuffix =
    PasswordConfirmIsPassword ? Icons.visibility_outlined : Icons
        .visibility_off_outlined;
    emit(ChangePasswordVisibilityState());
  }

  void changeDateOfBirth(DateTime time){
    user.dOB = time;
    emit(ChangeDate());
  }

  void changeBloodType(String type){
    user.bloodType = type;
    emit(ChangeBloodType());
  }

  void changeIsPatient(bool value){
    user.isPatient = value;
    emit(ChangeIsPatient());
  }

  void changeLocation(String location){
    user.location = location;
    emit(ChangeLocation());
  }
}

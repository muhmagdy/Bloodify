import 'package:bloc/bloc.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import 'change_password_state.dart';

class ChangePasswordCubit extends Cubit<PasswordState> {
  ChangePasswordCubit(): super(ChangePasswordVisibilityState());
  IconData PasswordSuffix = Icons.visibility_outlined;
  bool PasswordIsPassword = true;
  IconData PasswordConfirmSuffix = Icons.visibility_outlined;
  bool PasswordConfirmIsPassword = true;

  static ChangePasswordCubit get(context) => BlocProvider.of(context);


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

}

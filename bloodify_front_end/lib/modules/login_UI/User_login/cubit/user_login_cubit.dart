import 'package:bloc/bloc.dart';
import 'package:bloodify_front_end/modules/login_UI/User_login/cubit/user_states_login.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../../models/login_mode.dart';
import '../../../../shared/network/remote/dio_helper.dart';

class UserLoginCubit extends Cubit<UserLoginStates> {
  UserLoginCubit() : super(UserLoginInitialState());

  static UserLoginCubit get(context) => BlocProvider.of(context);
  late UserLoginModel loginModel;
  void userLogin({
    required String email,
    required String password,
  }) {
    emit(UserLoginLoadingState());

    DioHelper.putLogin(
      url: 'login',
      email: email,
      password: password,
    ).then((value) {
      print(value.data);
      print(value.data);
      loginModel = UserLoginModel.fromJson(value.data);
      emit(UserLoginSuccessState(loginModel));
    }).catchError((error) {
      print("api " + error.toString());
      emit(UserLoginErrorState(error.toString()));
    });
  }

  IconData suffix = Icons.visibility_outlined;
  bool isPassword = true;

  void changePasswordVisibility() {
    isPassword = !isPassword;
    suffix =
        isPassword ? Icons.visibility_outlined : Icons.visibility_off_outlined;

    emit(UserChangePasswordVisibilityState());
  }
}

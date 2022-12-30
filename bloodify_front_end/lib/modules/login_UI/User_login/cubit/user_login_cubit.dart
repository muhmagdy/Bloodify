import 'package:bloodify_front_end/modules/login_UI/User_login/cubit/user_states_login.dart';
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../../models/login_mode.dart';
import '../../../../shared/Constatnt/userInfo.dart';
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

    DioHelper.postLogin(
        url: 'user/auth',
        email: email,
        password: password,
        data: {"token": UserInfo.deviceToken}).then((Response value) {
      if (value.statusCode == 422) {
        print("422");
      }
      if (value.statusMessage == "ok") {
        print("ok");
      }
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

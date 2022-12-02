import 'package:bloc/bloc.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../../models/institution.dart';
import '../../../../shared/network/remote/dio_helper.dart';
import 'institution_states_login.dart';

class InstitutionLoginCubit extends Cubit<InstitutionLoginStates> {
  InstitutionLoginCubit() : super(InstitutionLoginInitialState());

  static InstitutionLoginCubit get(context) => BlocProvider.of(context);
  late InstitutionLoginModel loginModel;
  void institutionLogin({
    required String email,
    required String password,
  }) {
    emit(InstitutionLoginLoadingState());

    DioHelper.postData(
      url: 'login',
      data: {
        'email': email,
        'password': password,
      },
    ).then((value) {
      print(value.data);
      print(value.data);
      loginModel = InstitutionLoginModel.fromJson(value.data);
      emit(InstitutionLoginSuccessState(loginModel));
    });
    // .catchError((error) {
    //   print("api " + error.toString());
    //   emit(LoginErrorState(error.toString()));
    // });
  }

  IconData suffix = Icons.visibility_outlined;
  bool isPassword = true;

  void changePasswordVisibility() {
    isPassword = !isPassword;
    suffix =
        isPassword ? Icons.visibility_outlined : Icons.visibility_off_outlined;

    emit(InstitutionChangePasswordVisibilityState());
  }
}

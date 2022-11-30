import 'package:bloodify_front_end/models/user_model.dart';

abstract class UserLoginStates {}

class UserLoginInitialState extends UserLoginStates {}

class UserLoginLoadingState extends UserLoginStates {}

class UserLoginSuccessState extends UserLoginStates {
  final UserLoginModel loginModel;
  UserLoginSuccessState(this.loginModel);
}

class UserLoginErrorState extends UserLoginStates {
  final String error;

  UserLoginErrorState(this.error);
}

class UserChangePasswordVisibilityState extends UserLoginStates {}

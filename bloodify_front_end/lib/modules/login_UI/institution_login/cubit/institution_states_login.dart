import '../../../../models/institution.dart';

abstract class InstitutionLoginStates {}

class InstitutionLoginInitialState extends InstitutionLoginStates {}

class InstitutionLoginLoadingState extends InstitutionLoginStates {}

class InstitutionLoginSuccessState extends InstitutionLoginStates {
  final InstitutionLoginModel loginModel;
  InstitutionLoginSuccessState(this.loginModel);
}

class InstitutionLoginErrorState extends InstitutionLoginStates {
  final String error;

  InstitutionLoginErrorState(this.error);
}

class InstitutionChangePasswordVisibilityState extends InstitutionLoginStates {}

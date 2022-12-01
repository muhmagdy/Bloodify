part of 'sign_up_cubit.dart';

@immutable
abstract class SignUpStates {}

class SignUpInitial extends SignUpStates {
  SignUpInitial();
}

class SignUpLoading extends SignUpStates {
  SignUpLoading();
}

class SignUpLoaded extends SignUpStates {
  SignUpLoaded();
}

class SignUpError extends SignUpStates {
  final String error;
  SignUpError(this.error);
}

class ChangePasswordVisibilityState extends SignUpStates {
  ChangePasswordVisibilityState();
}

class ChangeDate extends SignUpStates {
  ChangeDate();
}

class ChangeIsPatient extends SignUpStates {
  ChangeIsPatient();
}

class ChangeBloodType extends SignUpStates {
  ChangeBloodType();
}

class ChangeLocation extends SignUpStates {
  ChangeLocation();
}

class GetLocationsLoading extends SignUpStates {}

class GetLocationsSucces extends SignUpStates {}

class GetLocationsError extends SignUpStates {
  final String error;
  GetLocationsError(this.error);
}

class SiqnUpApiLoading extends SignUpStates {}

class SiqnUpApiSucces extends SignUpStates {
  SignUpResponse response;
  SiqnUpApiSucces(this.response);
}

class SiqnUpApiError extends SignUpStates {
  final String error;
  SiqnUpApiError(this.error);
}

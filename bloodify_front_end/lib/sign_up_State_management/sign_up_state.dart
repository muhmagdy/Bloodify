part of 'sign_up_cubit.dart';

@immutable
abstract class SignUpState {}

class SignUpInitial extends SignUpState {
  SignUpInitial();
}

class SignUpLoading extends SignUpState {
  SignUpLoading();
}

class SignUpLoaded extends SignUpState {
  SignUpLoaded();
}

class SignUpError extends SignUpState {
  final String error;
  SignUpError(this.error);
}

class ChangePasswordVisibilityState extends SignUpState {
  ChangePasswordVisibilityState();
}

class ChangeDate extends SignUpState{
  ChangeDate();
}

class ChangeIsPatient extends SignUpState{
  ChangeIsPatient();
}

class ChangeBloodType extends SignUpState{
  ChangeBloodType();
}

class ChangeLocation extends SignUpState{
  ChangeLocation();
}
part of 'user_request_cubit.dart';

abstract class UserRequestFormEvent extends Equatable {
  const UserRequestFormEvent();

  @override
  List<Object?> get props => [];
}

class UserRequestFormLoaded extends UserRequestFormEvent {
  const UserRequestFormLoaded();
}

class BloodTypeChanged extends UserRequestFormEvent {
  const BloodTypeChanged({this.bloodType});

  final String? bloodType;

  @override
  List<Object?> get props => [bloodType];
}

class BloodBagsCountChanged extends UserRequestFormEvent {
  const BloodBagsCountChanged(double value, {this.bloodBagsCount});

  final double? bloodBagsCount;

  @override
  List<Object?> get props => [bloodBagsCount];
}

class RequestExpiryDateChanged extends UserRequestFormEvent {
  const RequestExpiryDateChanged({this.expiryDate});

  final DateTime? expiryDate;

  @override
  List<Object?> get props => [expiryDate];
}

class InstitutionChanged extends UserRequestFormEvent {
  const InstitutionChanged({this.institution});

  final String? institution;

  @override
  List<Object?> get props => [institution];
}

part of 'user_request_cubit.dart';

class UserRequestFormState extends Equatable {
  final List<String> bloodTypes;
  final String? pickedBloodType;
  final double bloodBagsCount;
  final DateTime? expiryDate;
  late List<InstitutionBrief> institutions;
  final InstitutionBrief? pickedInstitution;
  bool isLoading, isCreatedSuccessfully;

  UserRequestFormState._(
      {this.bloodTypes = const [
        'A+',
        'B+',
        'O+',
        'AB+',
        'A-',
        'B-',
        'O-',
        'AB-'
      ],
      this.pickedBloodType,
      this.bloodBagsCount = 1,
      this.expiryDate,
      this.institutions = const <InstitutionBrief>[],
      this.pickedInstitution,
      this.isLoading = false,
      this.isCreatedSuccessfully = false});

  UserRequestFormState.initial() : this._();

  UserRequestFormState.loading() : this._(isLoading: true);

  UserRequestFormState.institutionsLoadSuccess(
      {required List<InstitutionBrief> institutions})
      : this._(institutions: institutions);

  UserRequestFormState.createdSuccessfully()
      : this._(isCreatedSuccessfully: true);

  UserRequestFormState copyWith(
      {List<String>? bloodTypes,
      String? pickedBloodType,
      double? bloodBagsCount,
      DateTime? expiryDate,
      List<InstitutionBrief>? institutions,
      InstitutionBrief? pickedInstitution,
      bool? isLoading,
      bool? isCreatedSuccessfully}) {
    return UserRequestFormState._(
        bloodTypes: bloodTypes ?? this.bloodTypes,
        pickedBloodType: pickedBloodType ?? this.pickedBloodType,
        bloodBagsCount: bloodBagsCount ?? this.bloodBagsCount,
        expiryDate: expiryDate ?? this.expiryDate,
        institutions: institutions ?? this.institutions,
        pickedInstitution: pickedInstitution ?? this.pickedInstitution,
        isLoading: isLoading ?? this.isLoading,
        isCreatedSuccessfully:
            isCreatedSuccessfully ?? this.isCreatedSuccessfully);
  }

  @override
  List<Object?> get props => [
        bloodTypes,
        pickedBloodType,
        bloodBagsCount,
        expiryDate,
        institutions,
        pickedInstitution,
        isLoading,
        isCreatedSuccessfully
      ];
}

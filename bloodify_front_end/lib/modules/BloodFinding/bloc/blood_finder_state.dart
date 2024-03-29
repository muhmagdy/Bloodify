part of 'blood_finder_cubit.dart';

class BloodFinderState extends Equatable {
  late List<FoundInstitutionWithDistance> foundInstitutions;
  final List<String> bloodTypes;
  late String? pickedBloodType;
  late Position? location;
  bool isLoading, invalidForm, error;

  BloodFinderState._(
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
      this.foundInstitutions = const <FoundInstitutionWithDistance>[],
      this.pickedBloodType,
      this.location,
      this.isLoading = false,
      this.invalidForm = false,
      this.error = false});

  BloodFinderState.initial() : this._();

  BloodFinderState.noBloodTypePicked() : this._(invalidForm: true);

  BloodFinderState.findingInstitutions() : this._(isLoading: true);

  BloodFinderState.errorOccured() : this._(error: true);

  BloodFinderState.findingInstitutionsSuccess(
      {required List<FoundInstitutionWithDistance> institutions})
      : this._(foundInstitutions: institutions);

  BloodFinderState copyWith(
      {List<FoundInstitutionWithDistance>? foundInstitutions,
      List<String>? bloodTypes,
      String? pickedBloodType,
      Position? location,
      bool? isLoading,
      bool? invalidForm,
      bool? error}) {
    return BloodFinderState._(
        bloodTypes: bloodTypes ?? this.bloodTypes,
        foundInstitutions: foundInstitutions ?? this.foundInstitutions,
        pickedBloodType: pickedBloodType ?? this.pickedBloodType,
        location: location ?? this.location,
        isLoading: isLoading ?? this.isLoading,
        invalidForm: invalidForm ?? this.invalidForm,
        error: error ?? this.error);
  }

  @override
  List<Object?> get props => [
        bloodTypes,
        foundInstitutions,
        pickedBloodType,
        location,
        isLoading,
        invalidForm,
        error
      ];
}

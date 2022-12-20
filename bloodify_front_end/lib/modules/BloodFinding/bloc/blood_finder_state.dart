part of 'blood_finder_cubit.dart';

class BloodFinderState extends Equatable {
  late List<FoundInstitutionWithDistance> foundInstitutions;
  final List<String> bloodTypes;
  late String? pickedBloodType;
  late Position? location;

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
      this.location});

  BloodFinderState.initial() : this._();

  BloodFinderState.findingInstitutions() : this._();

  BloodFinderState.findingInstitutionsSuccess(
      {required List<FoundInstitutionWithDistance> institutions})
      : this._(foundInstitutions: institutions);

  BloodFinderState copyWith(
      {List<FoundInstitutionWithDistance>? foundInstitutions,
      List<String>? bloodTypes,
      String? pickedBloodType,
      Position? location}) {
    return BloodFinderState._(
        bloodTypes: bloodTypes ?? this.bloodTypes,
        foundInstitutions: foundInstitutions ?? this.foundInstitutions,
        pickedBloodType: pickedBloodType ?? this.pickedBloodType,
        location: location ?? this.location);
  }

  @override
  List<Object?> get props =>
      [bloodTypes, foundInstitutions, pickedBloodType, location];
}

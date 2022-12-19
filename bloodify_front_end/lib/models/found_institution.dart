import 'package:equatable/equatable.dart';

class FoundInstitution extends Equatable {
  late int institutionId;
  late String institutionName;
  late String institutionLocation;
  late double longitude;
  late double latitude;
  late Map<String, int> types_bags;
  late int working_hours;
  late double? distance;

  FoundInstitution(
      {required this.institutionId,
      required this.institutionName,
      required this.institutionLocation,
      required this.longitude,
      required this.latitude,
      required this.types_bags,
      required this.working_hours,
      this.distance});

  @override
  List<Object?> get props => [
        institutionId,
        institutionName,
        institutionName,
        longitude,
        latitude,
        types_bags,
        working_hours
      ];
}

class FoundInstitutionWithDistance extends Equatable {
  FoundInstitution institution;
  double distance;
  FoundInstitutionWithDistance(
      {required this.institution, required this.distance});

  @override
  List<Object?> get props => [institution, distance];
}

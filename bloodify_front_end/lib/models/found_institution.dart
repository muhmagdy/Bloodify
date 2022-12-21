import 'package:equatable/equatable.dart';

class FoundInstitution extends Equatable {
  late int institutionId;
  late String institutionName;
  late String institutionLocation;
  late double longitude;
  late double latitude;
  late Map<String, dynamic> types_bags;
  late int working_hours;

  FoundInstitution(
      {required this.institutionId,
      required this.institutionName,
      required this.institutionLocation,
      required this.longitude,
      required this.latitude,
      required this.types_bags,
      required this.working_hours});

  static FoundInstitution fromJson(Map<String, dynamic> json) {
    return FoundInstitution(
        institutionId: json["institutionId"],
        institutionName: json["institutionName"],
        institutionLocation: json["institutionLocation"],
        longitude: json["longitude"],
        latitude: json["latitude"],
        types_bags: json["types_bags"],
        working_hours: json["working_hours"]);
  }

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
  final FoundInstitution institution;
  final double distance;
  const FoundInstitutionWithDistance(
      {required this.institution, required this.distance});

  int getID() {
    return institution.institutionId;
  }

  String getName() {
    return institution.institutionName;
  }

  String getLocation() {
    return institution.institutionLocation;
  }

  Map<String, dynamic> getBloodBags() {
    return institution.types_bags;
  }

  int getWorkingHours() {
    return institution.working_hours;
  }

  double getDistance() {
    return distance;
  }

  @override
  List<Object?> get props => [institution, distance];
}

class InstitutionBrief extends Equatable {
  final int institutionID;
  final String name, location;
  const InstitutionBrief(
      {required this.institutionID,
      required this.name,
      required this.location});

  static InstitutionBrief fromJson(Map<String, dynamic> json) {
    return InstitutionBrief(
        institutionID: json["id"],
        name: json["name"],
        location: json["location"]);
  }

  @override
  String toString() {
    return '$name, $location';
  }

  @override
  List<Object?> get props => [institutionID, name, location];
}

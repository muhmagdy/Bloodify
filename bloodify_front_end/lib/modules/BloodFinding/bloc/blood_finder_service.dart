import 'dart:convert';

import 'package:bloodify_front_end/models/found_institution.dart';
import 'package:dio/dio.dart';
import 'package:geolocator/geolocator.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';

Future<List<FoundInstitutionWithDistance>> find_Institutions(
    String? bloodType) async {
  List<FoundInstitution>? foundInstitutions = await searchInstitutions(
      bloodType?.replaceAll("+", "p").replaceAll("-", "n"));
  return await processFindings(foundInstitutions);
}

Future<List<FoundInstitution>> searchInstitutions(String? bloodType) async {
  String url = "user/blood/search/$bloodType";
  Response response = await DioHelper.getData(url: url, query: {});

  if (response.statusCode == 200) {
    return List.from(response.data.map((e) => FoundInstitution.fromJson(e)));
  } else {
    throw Exception("Error Occured");
  }
  // return [
  //   FoundInstitution(
  //       institutionId: 1,
  //       institutionName: "one",
  //       institutionLocation: "loc",
  //       longitude: 250,
  //       latitude: 14,
  //       types_bags: Map.of({"A+": 1, "B+": 2}),
  //       working_hours: 24),
  //   FoundInstitution(
  //       institutionId: 2,
  //       institutionName: "two",
  //       institutionLocation: "loc",
  //       longitude: 13,
  //       latitude: 120,
  //       types_bags: Map.of({"A+": 1, "B+": 2}),
  //       working_hours: 24),
  //   FoundInstitution(
  //       institutionId: 3,
  //       institutionName: "three",
  //       institutionLocation: "loc",
  //       longitude: -40,
  //       latitude: 104,
  //       types_bags: Map.of({"A+": 0, "B+": 2}),
  //       working_hours: 24),
  //   FoundInstitution(
  //       institutionId: 4,
  //       institutionName: "four",
  //       institutionLocation: "loc",
  //       longitude: 13,
  //       latitude: 14,
  //       types_bags: Map.of({
  //         "A+": 1,
  //         "B+": 2,
  //         "Z+": -1,
  //         "X+": -2,
  //         "Q+": -1,
  //         "F+": -2,
  //         "C+": -1,
  //         "D+": -2,
  //       }),
  //       working_hours: 24),
  //   FoundInstitution(
  //       institutionId: 4,
  //       institutionName: "five",
  //       institutionLocation: "loc",
  //       longitude: -13,
  //       latitude: 104,
  //       types_bags: Map.of({"A+": 1, "B+": 0}),
  //       working_hours: 24),
  // ];
}

Future<List<FoundInstitutionWithDistance>> processFindings(
    List<FoundInstitution>? institutions) async {
  if (institutions == null) throw Exception();
  Position? position;
  try {
    position = await getLocation();
  } catch (e) {
    print(e);
  }

  List<FoundInstitutionWithDistance> processedFindings = institutions.map((e) {
    e.types_bags.removeWhere((key, value) => value <= 0);
    return FoundInstitutionWithDistance(
        institution: e,
        distance: getDistance(position, e.latitude, e.longitude));
  }).toList();

  processedFindings.sort(((a, b) => a.distance.compareTo(b.distance)));

  return processedFindings;
}

getDistance(Position? pos, double lat, double long) {
  if (pos == null) return -1.0;
  return Geolocator.distanceBetween(pos.latitude, pos.latitude, lat, long);
}

Future<Position> getLocation() async {
  bool serviceEnabled;
  LocationPermission permission;

  // Test if location services are enabled.
  serviceEnabled = await Geolocator.isLocationServiceEnabled();
  if (!serviceEnabled) {
    // Location services are not enabled don't continue
    // accessing the position and request users of the
    // App to enable the location services.
    return Future.error('Location services are disabled.');
  }

  permission = await Geolocator.checkPermission();
  if (permission == LocationPermission.denied) {
    permission = await Geolocator.requestPermission();
    if (permission == LocationPermission.denied) {
      // Permissions are denied, next time you could try
      // requesting permissions again (this is also where
      // Android's shouldShowRequestPermissionRationale
      // returned true. According to Android guidelines
      // your App should show an explanatory UI now.
      return Future.error('Location permissions are denied');
    }
  }

  if (permission == LocationPermission.deniedForever) {
    // Permissions are denied forever, handle appropriately.
    return Future.error(
        'Location permissions are permanently denied, we cannot request permissions.');
  }
  return await Geolocator.getCurrentPosition();
}

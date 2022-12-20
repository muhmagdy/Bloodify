import 'package:bloc/bloc.dart';
import 'package:bloodify_front_end/models/found_institution.dart';
import 'package:equatable/equatable.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:geolocator/geolocator.dart';

part 'blood_finder_state.dart';

class BloodFinderCubit extends Cubit<BloodFinderState> {
  BloodFinderCubit() : super(BloodFinderState.initial());
  static BloodFinderCubit get(context) => BlocProvider.of(context);

  void changedBloodType(String value) {
    emit(state.copyWith(pickedBloodType: value));
  }

  Future<void> findInstitutions() async {
    var currState = state.copyWith();
    emit(BloodFinderState.findingInstitutions());
    emit(state.copyWith(pickedBloodType: currState.pickedBloodType));

    //TODO: api call
    final List<FoundInstitution> foundInstitutions = [
      FoundInstitution(
          institutionId: 1,
          institutionName: "one",
          institutionLocation: "loc",
          longitude: 250,
          latitude: 14,
          types_bags: Map.of({"A+": 1, "B+": 2}),
          working_hours: 24),
      FoundInstitution(
          institutionId: 2,
          institutionName: "two",
          institutionLocation: "loc",
          longitude: 13,
          latitude: 120,
          types_bags: Map.of({"A+": 1, "B+": 2}),
          working_hours: 24),
      FoundInstitution(
          institutionId: 3,
          institutionName: "three",
          institutionLocation: "loc",
          longitude: -40,
          latitude: 104,
          types_bags: Map.of({"A+": 1, "B+": 2}),
          working_hours: 24),
      FoundInstitution(
          institutionId: 4,
          institutionName: "four",
          institutionLocation: "loc",
          longitude: 13,
          latitude: 14,
          types_bags: Map.of({
            "A+": 1,
            "B+": 2,
            "Z+": 1,
            "X+": 2,
            "Q+": 1,
            "F+": 2,
            "C+": 1,
            "D+": 2,
          }),
          working_hours: 24),
      FoundInstitution(
          institutionId: 4,
          institutionName: "five",
          institutionLocation: "loc",
          longitude: -13,
          latitude: 104,
          types_bags: Map.of({"A+": 1, "B+": 2}),
          working_hours: 24),
    ];
    // print(foundInstitutions);

    emit(BloodFinderState.findingInstitutionsSuccess(
            institutions: await processFindings(foundInstitutions))
        .copyWith(pickedBloodType: currState.pickedBloodType));
  }

  Future<List<FoundInstitutionWithDistance>> processFindings(
      List<FoundInstitution> institutions) async {
    try {
      await getLocation();
    } catch (e) {
      print(e);
    }

    getDistance(Position? pos, double lat, double long) {
      if (pos == null) return -1.0;
      return Geolocator.distanceBetween(pos.latitude, pos.latitude, lat, long);
    }

    List<FoundInstitutionWithDistance> processedFindings = institutions
        .map((e) => FoundInstitutionWithDistance(
            institution: e,
            distance: getDistance(state.location, e.latitude, e.longitude)))
        .toList();
    processedFindings.sort(((a, b) => a.distance.compareTo(b.distance)));
    return processedFindings;
  }

  Future<void> getLocation() async {
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
    Position location = await Geolocator.getCurrentPosition();
    emit(state.copyWith(location: location));
  }
}

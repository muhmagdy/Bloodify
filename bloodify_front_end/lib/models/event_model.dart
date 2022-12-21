// ignore_for_file: non_constant_identifier_names, camel_case_types

import 'package:geolocator/geolocator.dart';


class Event_model {
  late int event_ID;
  late String title;
  late DateTime from_date;
  late DateTime to_date;
  Position? location;
  Event_model(
      {required this.event_ID,
      required this.title,
      required this.from_date,
      required this.to_date,
      this.location});

  Event_model.fromJson(Map<String, dynamic> json) {
    event_ID = json['event_ID'];
    title = json['title'];
    from_date = json['from_date'];
    to_date = json['to_date'];
    location = json['location'];
  }
  @override
  String toString() {
    // ignore: prefer_interpolation_to_compose_strings
    return "Post id" + event_ID.toString();
  }
=======
import '../shared/Constatnt/colors.dart';

/*
  "title": "Blood donation 21th event",
    "startDate": "05-01-2025",
    "endDate": "07-01-2025",
    "startWorkingHour": "02:33 AM",
    "endWorkingHour": "02:35 PM",
    "location": "Smouha, Alexandria, Egypt",
    "longitude": -17.333333,
    "latitude": 30.1232
*/
class Event_model {
  int? event_ID;
  late String title;
  late DateTime startDate;
  late DateTime endDate;
  late DateTime startWorkingHour;
  late DateTime endWorkingHour;
  String? location;
  late double longitude;
  late double latitude;

  Event_model(
      {required this.title,
      required this.startDate,
      required this.endDate,
      required this.location,
      required this.latitude,
      required this.longitude});

  Event_model.fromJson(Map<String, dynamic> json) {
    /*
  "title": "Blood donation 21th event",
    "startDate": "05-01-2025",
    "endDate": "07-01-2025",
    "startWorkingHour": "02:33 AM",
    "endWorkingHour": "02:35 PM",
    "location": "Smouha, Alexandria, Egypt",
    "longitude": -17.333333,
    "latitude": 30.1232
*/

    event_ID = json['event_ID'];
    title = json['title'];
    startDate = dateFormat.parse(json['startDate']);
    endDate = dateFormat.parse(json['endDate']);
    startWorkingHour = dateFormat.parse(json['startWorkingHour']);
    endWorkingHour = dateFormat.parse(json['endWorkingHour']);
    longitude = json['longitude'];
    latitude = json['latitude'];
    location = json['location'];
  }
}

// ignore_for_file: non_constant_identifier_names

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
// ignore: camel_case_types
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

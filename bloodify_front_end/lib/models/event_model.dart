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
}

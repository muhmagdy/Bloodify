// ignore_for_file: non_constant_identifier_names, camel_case_types

import 'package:geolocator/geolocator.dart';

class EventCreationReponse {
  bool? state;
  String? message;
  EventCreationReponse({required state, required message});

  EventCreationReponse.fromJson(Map<String, dynamic> json) {
    state = json['state'];
    message = json['message'];
  }
}

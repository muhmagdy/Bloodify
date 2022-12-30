import 'package:geolocator/geolocator.dart';

class PostBrief {
  late int id;
  late String nationalID;
  late String name;
  late DateTime dateTime;
  late int count;
  late String bloodType;
  late double distance;
  late String hospitalName;
  late int state;
  late double longitude;
  late double latitude;
  late Position location;
  PostBrief.fromJson(Map<String, dynamic> json, this.state) {
    id = json['id'];
    nationalID = json['nationalID'];
    name = json['name'];
    print(json['dateTime']);
    if (json['dateTime'] == null)
      dateTime = DateTime.now();
    else
      dateTime = DateTime.parse(json['dateTime']) ?? DateTime.now();
    count = json['count'];
    bloodType = json['bloodType'];
    distance = json['distance'];
    hospitalName = json['hospitalName'];
    longitude = json['longitude'];
    latitude = json['latitude'];
  }

  PostBrief.fromInstJson(Map<String, dynamic> json) {
    id = json['id'];
    nationalID = json['nationalID'];
    name = json['name'];
    print(json['dateTime']);
    if (json['dateTime'] == null)
      dateTime = DateTime.now();
    else
      dateTime = DateTime.parse(json['dateTime']) ?? DateTime.now();
    count = json['count'];
    bloodType = json['bloodType'];
  }

  PostBrief.fromJsonWithLocation(
      Map<String, dynamic> json, this.state, Position position) {
    id = json['id'];
    nationalID = json['nationalID'];
    name = json['name'];
    print(json['dateTime']);
    dateTime = DateTime.parse(json['dateTime']) ?? DateTime.now();
    count = json['count'];
    bloodType = json['bloodType'];
    hospitalName = json['hospitalName'];
    longitude = json['longitude'];
    latitude = json['latitude'];
    distance = Geolocator.distanceBetween(
            position.latitude, position.longitude, latitude, longitude) /
        1000;
    location = position;
  }

  PostBrief(this.id, this.nationalID, this.name, this.dateTime, this.count,
      this.bloodType, this.distance, this.hospitalName, this.state);
}

import 'package:bloodify_front_end/models/postBrief.dart';

class UserBrief {
  late int userID;
  late String name;
  late String bloodType;
  late double distance;
  int nMessages = 0;

  UserBrief.fromJson(Map<String, dynamic> json) {
    userID = json['userID'];
    name = json['name'];
    bloodType = json['bloodType'];
    distance = json['distance'];
  }

  UserBrief(this.userID, this.name, this.distance, this.bloodType);
}

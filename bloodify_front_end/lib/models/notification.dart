import '../modules/BloodFinding/bloc/blood_finder_service.dart';

class NotificationBody {
  late String instituteName;
  late String acceptorName;
  late DateTime lastTime;
  late double longitude;
  late double latitude;
  late double distance;

  NotificationBody.fromJson(Map<String, dynamic> json) {
    instituteName = json["instituteName"];
    acceptorName = json["acceptorName"];
    lastTime = DateTime.parse(json["lastTime"]);
    longitude = json["longitude"];
    latitude = json["latitude"];
  }
}

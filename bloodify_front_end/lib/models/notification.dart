class Notification {
  late String instituteName;
  late String acceptorName;
  late DateTime lastTime;
  late double longitude;
  late double latitude;
  Notification.fromJson(Map<String, dynamic> json) {
    instituteName = json["instituteName"];
    acceptorName = json["acceptorName"];
    lastTime = DateTime.parse(json["lastTime"]);
    longitude = json["longitude"];
    latitude = json["latitude"];
  }
}

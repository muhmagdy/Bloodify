class Notification {
  late double longtitude;
  late double latitude;
  late String instituteName;
  Notification.fromJson(Map<String, dynamic> json) {
    longtitude = double.parse(json['longtitude']);
    latitude = double.parse(json['latitude']);
    instituteName = json['Institute'];
  }
}

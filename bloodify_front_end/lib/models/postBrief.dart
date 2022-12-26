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
  PostBrief.fromUserJson(Map<String, dynamic> json, int state) {
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
    this.state = state;
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

  PostBrief(this.id, this.nationalID, this.name, this.dateTime, this.count,
      this.bloodType, this.distance, this.hospitalName, this.state);
}

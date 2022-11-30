class InstitutionLoginModel {
  late bool status;
  late String message;
  InstitutionData? data;
  InstitutionLoginModel.fromJson(Map<String, dynamic> json) {
    status = json['status'];
    message = json['message'];
    data = json['data'] != null ? InstitutionData.fromJson(json['data']) : null;
  }
}

class InstitutionData {
  late int id;
  late String name;
  late String email;
  // late String bloodType;

  late String token;

  InstitutionData.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    name = json['name'];
    email = json['email'];
    // bloodType = json['bloodType'];
    token = json['token'];
  }
}

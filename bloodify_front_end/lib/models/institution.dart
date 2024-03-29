class InstitutionLoginModel {
  late bool status;
  late String message;
  InstitutionData? data;
  InstitutionLoginModel(this.status) {
    message = "Invalid user name or Password";
  }
  InstitutionLoginModel.fromJson(Map<String, dynamic> json) {
    status = json['status'];
    message = json['message'];
    data = json['data'] != null ? InstitutionData.fromJson(json['data']) : null;
  }
}

class InstitutionData {
  late String name;
  late String email;
  // late String bloodType;

  late String token;
  InstitutionData(this.name, this.email, this.token);
  InstitutionData.fromJson(Map<String, dynamic> json) {
    name = json['institutionName'];
    email = json['email'];
    // bloodType = json['bloodType'];
    token = json['token'];
  }
}

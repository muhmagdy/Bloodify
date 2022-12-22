
class UserData {
  late String fName;
  late String lName;
  late String nationalID;
  late String email;
  late String password;
  late var location;
  DateTime dOB = DateTime(
      DateTime.now().year - 20, DateTime.now().month, DateTime.now().day);
  bool isPatient = false;
  String bloodType = 'A+';
  late String token;
}

class SignUpResponse {
  late bool status;
  late String message;
  late UserData userData;
  SignUpResponse.fromJson(Map<String, dynamic> json) {
    status = json['status'];
    message = json['message'];
  }
}

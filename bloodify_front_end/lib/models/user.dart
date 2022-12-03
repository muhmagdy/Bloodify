import 'dart:convert';

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
  UserData.fromJson(Map<String, dynamic> json) {
    // fName = json['first_name'];

    // lName = json['last_name'];
    // nationalID = json['last_name'];
    // email = json['email'];
    // password = json['password'];
    // location = json['location'];
    // dOB = json['date_of_birth'];
    // isPatient = json['isPatient'];
    // bloodType = json['bloodType'];
    token = json['token'];
  }
  UserData() {
    DateTime dOB = DateTime(
        DateTime.now().year - 20, DateTime.now().month, DateTime.now().day);
    bool isPatient = false;
    String bloodType = 'A+';
  }
}
// data: {
//           'first_name': user.fName,
//           'last_name': user.lName,
//           'nationalID': user.nationalID,
//           'email': user.email,
//           'password': user.password,
//           'location': user.location,
//           'date_of_birth': user.dOB,
//           'isPatient': user.isPatient,
//           'bloodType': user.bloodType,
//         }

// ignore_for_file: non_constant_identifier_names

class UserData {
  late String fName;
  late String lName;
  late String nationalID;
  late String email;
  late String password;
  DateTime dOB = DateTime(
      DateTime.now().year - 20, DateTime.now().month, DateTime.now().day);
  DateTime? last_donated_time;
  bool isPatient = false;
  String bloodType = 'A+';
  late String token;

  late final int id;
  UserData.fromJson(Map<String, dynamic> json) {
    id = json["id"];
    fName = json['first_name'];

    lName = json['last_name'];
    nationalID = json['nationalID'];
    email = json['email'];
    // password = json['password'];
    // location = json['location'];
    last_donated_time = json['last_time_donated'];
    // dOB = json['date_of_birth'];
    isPatient = json['hasDiseases'];
    bloodType = json['bloodType'];
    token = json['token'];
  }
  UserData() {
    dOB = DateTime(
        DateTime.now().year - 20, DateTime.now().month, DateTime.now().day);
    isPatient = false;
    bloodType = 'A+';
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

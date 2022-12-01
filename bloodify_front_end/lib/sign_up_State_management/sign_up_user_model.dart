import 'package:intl/intl.dart';

class UserData {
  late String fName;
  late String lName;
  late String nationalID;
  late String email;
  late String password;
  DateTime dOB = DateTime(DateTime.now().year - 20, DateTime.now().month, DateTime.now().day);
  bool isPatient = false;
  String bloodType = 'A+';
  late String location;

  late String token;
}


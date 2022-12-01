import 'package:bloodify_front_end/models/user.dart';

class SignUpResponse {
  late bool status;
  late String message;
  late UserData userData;
  SignUpResponse.fromJson(Map<String, dynamic> json) {
    status = json['status'];
    message = json['message'];
  }
}

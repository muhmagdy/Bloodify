import 'package:bloodify_front_end/models/user.dart';

class SignUpResponse {
  late bool state;
  late String message;
  late UserData userData;
  SignUpResponse.fromJson(Map<String, dynamic> json) {
    state = json['state'];
    message = json['message'];
  }
}

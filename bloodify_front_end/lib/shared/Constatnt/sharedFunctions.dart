import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';

bool validateEmail(value) {
  bool _validateEmail = RegExp(
          r"^[a-zA-Z0-9.a-zA-Z0-9.!#$%&'*+-/=?^_`{|}~]+@[a-zA-Z0-9]+\.[a-zA-Z]+")
      .hasMatch(value);
  return _validateEmail;
}

bool validateName(value){
  String digits = '123456789';
  for(int i = 0; i < value.length; i++) {
    if(digits.contains(value[i])) return false;
  }
  return true;
}

bool validatePassword(value){
  String uppers = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', lowers = 'abcdefghijklmnopqrstuvwxyz', digits = '123456789';
  List<bool> arr = [false, false, false, false];
  for(int i = 0; i < value.length; i++) {
    if(!digits.contains(value[i]) && !lowers.contains(value[i]) && !uppers.contains(value[i]) && value[i] != '_') return false;
    arr[0] = arr[0] || digits.contains(value[i]);
    arr[1] = arr[1] || lowers.contains(value[i]);
    arr[2] = arr[2] || uppers.contains(value[i]);
    arr[3] = arr[3] || ('_' == value[i]);
  }
  return arr.reduce((acc, element) => acc = acc && element);
}

void navigateTo(context, widget) => Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => widget,
      ),
    );
void navigateAndFinish(
  context,
  widget,
) =>
    Navigator.pushAndRemoveUntil(
      context,
      MaterialPageRoute(
        builder: (context) => widget,
      ),
      (route) {
        return false;
      },
    );

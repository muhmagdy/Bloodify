import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';

bool validateEmail(value) {
  String mailAddress = "^\\w+([._-]?\\w+)*";
  String mailHost = "@\\w+([._-]?\\w+)*";
  String organisation = "(\\.\\w{2,3})+\$";
  String REGEX = "$mailAddress$mailHost$organisation";
  bool validateEmail = RegExp(REGEX).hasMatch(value.trim());
  return validateEmail;
}

bool validateName(value) {
  for (int i = 0; i < value.length; i++) {
    int ascii = value.codeUnitAt(i);
    if (!(ascii >= 65 && ascii <= 90) && !(ascii >= 97 && ascii <= 122))
      return false;
  }
  return true;
}

bool validatePassword(value) {
  List<bool> arr = [false, false, false, false];
  bool upper, lower, digit, underscore;
  for (int i = 0; i < value.length; i++) {
    int ascii = value.codeUnitAt(i);
    upper = (ascii >= 65 && ascii <= 90);
    lower = (ascii >= 97 && ascii <= 122);
    digit = (ascii >= 48 && ascii <= 57);
    underscore = value[i] == '_';
    if (!upper && !lower && !digit && !underscore) return false;
    arr[0] = arr[0] || lower;
    arr[1] = arr[1] || upper;
    arr[2] = arr[2] || digit;
    arr[3] = arr[3] || underscore;
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
AppBar defaultAppBAr(String text) {
  return AppBar(
    backgroundColor: const Color.fromARGB(255, 255, 78, 66),
    title: Row(children: [
      ClipRRect(
        borderRadius: BorderRadius.circular(70),
        child: Container(
          width: 40,
          height: 40,
          decoration: const BoxDecoration(
              color: Colors.white,
              image: DecorationImage(
                  image: AssetImage('assets/icons/blood-removebg-preview.ico'),
                  fit: BoxFit.contain)),
        ),
      ),
      const SizedBox(
        width: 10,
      ),
      Text(
        text,
        style: TextStyle(color: Colors.white),
      )
    ]),
  );
}

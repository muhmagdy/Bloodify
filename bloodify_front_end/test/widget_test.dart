// // This is a basic Flutter widget test.
// //
// // To perform an interaction with a widget in your test, use the WidgetTester
// // utility in the flutter_test package. For example, you can send tap and scroll
// // gestures. You can also use WidgetTester to find child widgets in the widget
// // tree, read text, and verify that the values of widget properties are correct.

import 'package:flutter_test/flutter_test.dart';
import 'package:bloodify_front_end/shared/Constatnt/sharedFunctions.dart';
import 'package:bloodify_front_end/shared/Constatnt/nationalIDValidator.dart';
import 'Sign_UP_INtests.dart';
// import 'package:bloodify_front_end/main.dart';

void main() {
  group('Input Data', () {
    test('valid Name', () {
      expect(validateName(generateValidName()), true);
    });
    test('Invalid Name', () {
      expect(validateName(generateInvalidName()), false);
    });
    test("valid password", () {
     expect(validatePassword(generatePassword(true, true, true, true)), true);
    });
    test("password without uppercases", () {
     expect(validatePassword(generatePassword(false, true, true, true)), false);
    });
    test("password without lowercases", () {
      expect(validatePassword(generatePassword(true, false, true, true)), false);
    });
    test("password without digits", () {
      expect(validatePassword(generatePassword(true, true, false, true)), false);
    });
    test("password without underscore", () {
      expect(validatePassword(generatePassword(true, true, true, false)), false);
    });
    test("password without upper and lower cases", () {
      expect(validatePassword(generatePassword(false, false, true, true)), false);
    });
    test("password without upper cases and digits", () {
      expect(validatePassword(generatePassword(false, true, false, true)), false);
    });
    test("password without upper and underscore", () {
      expect(validatePassword(generatePassword(false, true, true, false)), false);
    });
    test("password without lower cases and digits", () {
      expect(validatePassword(generatePassword(true, false, false, true)), false);
    });
    test("password without lower cases and underscore", () {
      expect(validatePassword(generatePassword(true, false, true, false)), false);
    });
    test("password without digits and underscore", () {
      expect(validatePassword(generatePassword(true, true, false, false)), false);
    });
    test("password without upper, lower cases and underscore", () {
      expect(validatePassword(generatePassword(false, false, true, false)), false);
    });
    test("password with underscore only", () {
      expect(validatePassword(generatePassword(false, false, false, true)), false);
    });
    test("password with digits only", () {
      expect(validatePassword(generatePassword(false, false, true, false)), false);
    });
    test("password with lowercases only", () {
      expect(validatePassword(generatePassword(false, true, false, false)), false);
    });
    test("password with uppercase only", () {
      expect(validatePassword(generatePassword(true, false, false, false)), false);
    });
    test("password with not allowed characters", (){
      expect(validatePassword(generateInvalidPassword()), false);
    });
    test("valid national id for Egypt", (){
      NationalIDParser parser = EgyptNationalIDParser();
      expect(parser.validateNationalID(generateNationalID(true, true, true)), true);
    });
    test("invalid national id in century for Egypt", (){
      int century = generateCentury(false);
      int ID = generateNationalID(true, true, true);
      int temp = 0;
      while(ID >= 10){
        temp = (temp * 10) + (ID % 10);
        ID = (ID / 10).floor();
      }
      ID = century;
      while(temp >= 10){
        ID= (ID * 10) + (temp % 10);
        temp = (temp / 10).floor();
      }
      NationalIDParser parser = EgyptNationalIDParser();
      expect(parser.validateNationalID(ID), false);
    });
    test("invalid national id in year for Egypt", (){
      NationalIDParser parser = EgyptNationalIDParser();
      expect(parser.validateNationalID(generateNationalID(true, false, true)), false);
    });
    test("invalid national id in days/month for Egypt", (){
      NationalIDParser parser = EgyptNationalIDParser();
      expect(parser.validateNationalID(generateNationalID(true, true, false)), false);
    });
    test("invalid national id in days/month and years for Egypt", (){
      NationalIDParser parser = EgyptNationalIDParser();
      expect(parser.validateNationalID(generateNationalID(true, false, false)), false);
    });
    test("invalid national id in century and year for Egypt", (){
      int century = generateCentury(false);
      int ID = generateNationalID(true, false, true);
      int temp = 0;
      while(ID >= 10){
        temp = (temp * 10) + (ID % 10);
        ID = (ID / 10).floor();
      }
      ID = century;
      while(temp >= 10){
        ID= (ID * 10) + (temp % 10);
        temp = (temp / 10).floor();
      }
      NationalIDParser parser = EgyptNationalIDParser();
      expect(parser.validateNationalID(ID), false);
    });
    test("invalid national id in century and days / month for Egypt", (){
      int century = generateCentury(false);
      int ID = generateNationalID(true, true, false);
      int temp = 0;
      while(ID >= 10){
        temp = (temp * 10) + (ID % 10);
        ID = (ID / 10).floor();
      }
      ID = century;
      while(temp >= 10){
        ID= (ID * 10) + (temp % 10);
        temp = (temp / 10).floor();
      }
      NationalIDParser parser = EgyptNationalIDParser();
      expect(parser.validateNationalID(ID), false);
    });
    test("invalid national id in century and year for Egypt", (){
      int century = generateCentury(false);
      int ID = generateNationalID(true, false, false);
      int temp = 0;
      while(ID >= 10){
        temp = (temp * 10) + (ID % 10);
        ID = (ID / 10).floor();
      }
      ID = century;
      while(temp >= 10){
        ID= (ID * 10) + (temp % 10);
        temp = (temp / 10).floor();
      }
      NationalIDParser parser = EgyptNationalIDParser();
      expect(parser.validateNationalID(ID), false);
    });
  });
}

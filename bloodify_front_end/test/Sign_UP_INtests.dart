import 'dart:math';
import 'package:bloodify_front_end/shared/Constatnt/nationalIDValidator.dart';
import 'package:flutter_test/flutter_test.dart';

/// generates a string with only upper and lower case letters
String generateValidName(){
  String result = '';
  Random random = Random();
  int length = random.nextInt(30) + 20, charCode;
  for (int i = 0; i < length; i++){
    charCode = random.nextInt(26);
    charCode += (random.nextBool())? 97 : 65;
    result += String.fromCharCode(charCode);
  }
  return result;
}

/// generate a string of at least one special character / digit
String generateInvalidName(){
  String result = '';
  Random random = Random();
  int length = random.nextInt(30) + 20, definiteInvalidIndex = random.nextInt(length), charCode = 0;
  for (int i = 0; i < length; i++){
    charCode = random.nextInt(256);
    result += String.fromCharCode(charCode);
  }
  while ((charCode >= 65 && charCode <= 90) || (charCode >= 97 && charCode <= 122)) {
    charCode = random.nextInt(256);
  }
  result = result.substring(0, definiteInvalidIndex) + String.fromCharCode(charCode) + result.substring(definiteInvalidIndex, length);
  return result;
}

///generates valid passwords if all the supplied parameters are true
///else, it generates invalid password due to absence of one of the categories
String generatePassword(bool upperReq, bool lowerReq, bool digitReq, bool _req){
 String result = "";
 Random random = Random();
 if(upperReq) result += String.fromCharCode(random.nextInt(26) + 65);
 if(lowerReq) result += String.fromCharCode(random.nextInt(26) + 97);
 if(digitReq) result += String.fromCharCode(random.nextInt(10) + 48);
 if(_req) result += '_';
 int length = random.nextInt(20) + 8, charCode;
 for(int i = 0; i < length; i++){
   charCode = random.nextInt(123);
   if((digitReq && charCode >= 48 && charCode <= 57) || (upperReq && charCode >= 65 && charCode <= 90) ||
      (lowerReq && charCode >= 97 && charCode <= 122) || (_req && charCode == 95)) {
     result += String.fromCharCode(charCode);
   }
 }
 int ind = random.nextInt(result.length + 1);
 return result.substring(ind) + result.substring(0, ind);
}

generateInvalidPassword(){
 Random random = Random();
 int charCode = random.nextInt(256), length = random.nextInt(5)+1;
 String invalidSubString = '';
 for(int i = 0; i < length; i++){
   while((charCode >= 48 && charCode <= 57) || (charCode >= 65 && charCode <= 90) ||
       (charCode >= 97 && charCode <= 122) || charCode == 95) {
     charCode = random.nextInt(256);
   }
   invalidSubString += String.fromCharCode(charCode);
 }
 String password = generatePassword(random.nextBool(), random.nextBool(), random.nextBool(), random.nextBool());
 int ind = random.nextInt(password.length + 1);
 password = password.substring(ind) + invalidSubString + password.substring(0, ind);
 ind = random.nextInt(password.length + 1);
 return password.substring(ind) + password.substring(0, ind);
}

///generates a valid ID if all parameters supplied are true
int generateNationalID(bool validCentury, bool validYear, bool validDay){
  Random random = Random();
  int centuryNumber = generateCentury(validCentury);
  int number = centuryNumber;
  int year = generateYear(validYear, centuryNumber), month = random.nextInt(12) + 1;
  number = (number * 100) + year;
  number = (number * 100) + month;
  int day = generateDay(validDay, month, year);
  number = (number * 100) + day;
  for(int i= 7; i < 14; i++) number = (number * 10) + random.nextInt(10);
  print(number);
  return number;
}

int generateCentury(bool valid){
  int minCenturyNum = (((DateTime.now().year - 60) / 100).floor()) - 19 + 2;
  int maxCenturyNum = (((DateTime.now().year - 18) / 100).floor()) - 19 + 2;
  Random random = Random();
  if (valid){
    return random.nextInt(maxCenturyNum - minCenturyNum + 1) + minCenturyNum;
  }
  else{
    return (random.nextBool())? random.nextInt(minCenturyNum) : maxCenturyNum + 1;
  }
}
int generateYear(bool valid, int century){
  int baseYear = 1900 + (century - 2) * 100;
  int minYear = (DateTime.now().year - 60), maxYear = (DateTime.now().year - 18);
  Random random = Random();
  int year = random.nextInt(100);
  if(valid){
    while((year + baseYear) > maxYear || (year + baseYear) < minYear){
      year = random.nextInt(100);
    }
  }
  else{
    while((year + baseYear) <= maxYear && (year + baseYear) >= minYear){
      year = random.nextInt(100);
    }
  }
  return year;
}
int generateDay(bool valid, int month, int year){
  Random random = Random();
  if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
    return valid? random.nextInt(31) + 1 : (random.nextBool()? 0: 32);
  }
  if(month == 4 || month == 6 || month == 9 || month == 11){
    return valid? random.nextInt(30) + 1 : (random.nextBool()? 0: 31);
  }
  if(isleapYear(year)){
    return valid? random.nextInt(29) + 1 : (random.nextBool()? 0: 30);
  }
  else{
    return valid? random.nextInt(28) + 1 : (random.nextBool()? 0: 29);
  }
}
import 'package:intl/intl.dart';

abstract class NationalIDParser{
  bool validateNationalID(int number);
  DateTime getDOB();
}

class EgyptNationalIDParser extends NationalIDParser{
  late DateTime dob;
  @override
  bool validateNationalID(int number) {
    // TODO: implement validateNationalID
    String ID = number.toString();
    if (ID.length != 14) return false; ///validating number of digits
    ///validating date of birth
    int centuryNumber = int.parse(ID.substring(0,1));
    int minCenturyNum = (((DateTime.now().year - 60) / 100).floor()) - 19 + 2;
    int maxCenturyNum = (((DateTime.now().year - 18) / 100).floor()) - 19 + 2;
    if(centuryNumber < minCenturyNum || centuryNumber > maxCenturyNum) return false;
    centuryNumber -= 2;
    int years = int.parse(ID.substring(1, 3));
    years = 1900 + (centuryNumber) * 100 + years;
    int minYear = (DateTime.now().year - 60), maxYear = (DateTime.now().year - 18);
    if(years < minYear || years > maxYear) return false;
    int months = int.parse(ID.substring(3, 5)) ;
    int days = int.parse(ID.substring(5, 7));

    bool thirty_Days_Month = Thirty_Days_Month(months, days);
    bool thirty_one_Days_Month = Thirty_one_Days_Month(months, days);
    bool february = February(years, months, days);
    if (days == 0 || !(thirty_Days_Month || thirty_one_Days_Month || february)) return false;
    dob = new DateFormat("yyyy-MM-dd").parse('$years-$months-$days');
    print(dob);
    return true;
  }

  bool Thirty_Days_Month(int months, int days){
    return (months == 9 || months == 4 || months == 6 || months == 11) && days <= 30;
  }
  bool Thirty_one_Days_Month(int months, int days){
    return (months == 1 || months == 3 || months == 5 || months == 7 || months == 8 || months == 10 || months == 12) && days <= 31;
  }
  bool February(int years, int months, int days){
    if(months == 2 && ((isLeapYear(years) && days <= 29) || (!isLeapYear(years) && days <= 28))) return true;
    return false;
  }

  bool isLeapYear(int year){
    return isleapYear(year);
  }

  @override
  DateTime getDOB() {
    return dob;
  }
}

bool isleapYear(int year){
  if(year % 4 == 0 && year % 100 != 0) return true;
  if(year % 400 == 0)  return true;
  return false;
}
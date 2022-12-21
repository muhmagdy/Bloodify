// ignore: file_names
abstract class Language {
  String showInvalidPassword();
  String showInvalidPasswordConfirm();
  String showInvalidValue(value);
  String enterPassword();
  String enterValue(value);
  String getLabel(abbrev);
  String showIncorrectDOB();
  String showIncorrectLDT();
}

class EnglishLanguage extends Language {
  var dictionary = {
    'fname': "First Name",
    'lname': "Last Name",
    'natID': "National ID",
    'mail': 'Mail Address',
    'pass': 'Password',
    'passConfirm': "Password Confirmation",
    'next': 'next',
    'do you have an account?': 'Do You Have an Account?',
    'login': "Login!",
    // 'verification': "Verifiy Your Email link sended to you inbox",
    'verification': "Signed Up successfully",
    'dob': "Birth Date",
    'having diseases': "Do You Have any Diseases?",
    'yes': "Yes",
    'no': "No",
    'blood type': "Blood Type",
    'location': 'Location',
    'back': "Back",
    'submit': "Submit"
  };
  @override
  String showInvalidPassword() =>
      "Password MUST include uppercase letters,\nlowercase letters, digits, and _";
  @override
  String showInvalidPasswordConfirm() => "Non Matching Passwords";
  @override
  String showInvalidValue(value) => "Invalid ${dictionary[value]}";
  @override
  String enterPassword() => "Password should be of > 7 characters";
  @override
  String enterValue(value) => "${dictionary[value]} musn't be embty  ";
  @override
  String getLabel(abbrev) => '${dictionary[abbrev]}';
  @override
  String showIncorrectDOB() => 'Not Correct Date of Birth';
  @override
  String showIncorrectLDT() => 'Not Correct Last Donated Time';
}

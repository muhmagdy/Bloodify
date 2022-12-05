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
  String showInvalidPassword() =>
      "Password MUST include uppercase letters,\nlowercase letters, digits, and _";
  String showInvalidPasswordConfirm() => "Non Matching Passwords";
  String showInvalidValue(value) => "Invalid ${dictionary[value]}";
  String enterPassword() => "Password should be of > 7 characters";
  String enterValue(value) => "Enter Your  ${dictionary[value]}";
  String getLabel(abbrev) => '${dictionary[abbrev]}';
  String showIncorrectDOB() => 'Not Correct Date of Birth';
  String showIncorrectLDT() => 'Not Correct Last Donated Time';
}

import 'package:bloodify_front_end/modules/settings/common/email_confirmation.dart';
import 'package:bloodify_front_end/shared/network/local/cach_helper.dart';
import 'package:flutter/material.dart';

import '../../shared/Constatnt/Component.dart';
import '../../shared/Constatnt/sharedFunctions.dart';
import '../../shared/network/remote/dio_helper.dart';

class ForgetPassword extends StatefulWidget {
  const ForgetPassword({Key? key}) : super(key: key);

  @override
  State<ForgetPassword> createState() => _ForgetPasswordState();
}

class _ForgetPasswordState extends State<ForgetPassword> {
  final _formKey = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    final mailController = TextEditingController();
    final height = MediaQuery.of(context).size.height;

    return Scaffold(
        appBar: AppBar(
          title: const Text('Forget Password'),
        ),
        body: Form(
            key: _formKey,
            child:
                Column(mainAxisAlignment: MainAxisAlignment.center, children: [
              const SizedBox(height: 10),
              const Text('Please enter your email address below',
                  textAlign: TextAlign.left),
              SizedBox(height: 0.02 * height),
              defaultInputText(
                  controller: mailController,
                  validate: (String mail) {
                    if (mail.isEmpty) {
                      return language.enterValue('mail');
                    }
                    return validateEmail(mail)
                        ? null
                        : language.showInvalidValue('mail');
                  },
                  labelText: language.getLabel('mail'),
                  type: TextInputType.emailAddress,
                  prefix: Icons.mail_outline),
              Row(
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  TextButton(
                    onPressed: () {
                      if (_formKey.currentState!.validate()) {
                        _nextTap(context, mailController.value.text);
                      }
                    },
                    child: const Text('Next'),
                  ),
                ],
              )
            ])));
  }

  void _nextTap(BuildContext context, String email) {
    DioHelper.postData(url: "password", data: {
      'email': CachHelper.getData(key: 'email'),
    }).then((value) {
      showToast(text: value.data['message'], color: Colors.blue, time: 3000);
      if (value.data['status'] == true) {
        _navigateToChangePassword(context);
      }
      Navigator.pop(context);
    }).catchError((error) => print(error));
    CachHelper.saveData(key: 'confirmationEmail', value: email);
  }

  void _navigateToChangePassword(BuildContext context) {
        Navigator.of(context).push(PageRouteBuilder(
        pageBuilder: (context, animation, secondaryAnimation) =>
            const EmailConfirmation(),
        transitionsBuilder: ((context, animation, secondaryAnimation, child) {
          const begin = Offset(0.0, 1.0);
          const end = Offset.zero;
          const curve = Curves.ease;
          var tween =
              Tween(begin: begin, end: end).chain(CurveTween(curve: curve));
          return SlideTransition(
            position: animation.drive(tween),
            child: child,
          );
        })));
  }
}

import 'dart:async';

import 'package:bloodify_front_end/shared/Constatnt/Component.dart';
import 'package:bloodify_front_end/shared/network/local/cach_helper.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
// import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
import 'package:flutter/material.dart';

import '../../../shared/Constatnt/sharedFunctions.dart';
import 'chage_password.dart';

class EmailConfirmation extends StatefulWidget {
  const EmailConfirmation({super.key});

  @override
  State<EmailConfirmation> createState() => _EmailConfirmationState();
}

class _EmailConfirmationState extends State<EmailConfirmation> {
  final _formKey = GlobalKey<FormState>();
  final _confirmationCodeController = TextEditingController();
  int _timeLeft = 90;
  late Timer _timer;
  bool _isButtonDisabled = false;

  _EmailConfirmationState() {
    DioHelper.postData(
            url: '/password',
            data: {'email': CachHelper.getData(key: 'confirmationEmail')})
        .then((value) {
      showToast(text: value.data['message'], color: Colors.blue, time: 3000);
    });
  }

  @override
  void dispose() {
    _timer.cancel();
    super.dispose();
  }

  void _startTimer() {
    DioHelper.postData(url: "password", data: {
      'email': CachHelper.getData(key: 'email'),
    }).then((value) {
      showToast(text: value.data['message'], color: Colors.blue, time: 3000);
      Navigator.pop(context);
    }).catchError((error) => print(error));

    setState(() {
      _isButtonDisabled = true;
    });
    _timer = Timer.periodic(const Duration(seconds: 1), (timer) {
      setState(() {
        _timeLeft--;
        if (_timeLeft == 0) {
          timer.cancel();
          _timeLeft = 90;
          _isButtonDisabled = false;
        }
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    final height = MediaQuery.of(context).size.height;
    return Scaffold(
      appBar: AppBar(
        title: const Text('Email Confirmation'),
      ),
      body: Form(
        key: _formKey,
        child: Column(
          children: [
            const SizedBox(height: 10),
            const Text(
                'We have sent a confirmation code to your email. Please enter the code below:'),
            SizedBox(height: 0.02 * height),
            defaultInputText(
                controller: _confirmationCodeController,
                validate: (number) {
                  if (number == null || number == '') {
                    return language.enterValue('confCode');
                  } else if (number.length != 4) {
                    return language.showInvalidConfCode();
                  }
                  return null;
                },
                labelText: language.getLabel('confCode'),
                type: TextInputType.number,
                prefix: Icons.pin),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                TextButton(
                    onPressed: _isButtonDisabled ? null : _startTimer,
                    child: _isButtonDisabled
                        ? Text('Resend code ($_timeLeft seconds)')
                        : const Text('Resend code')),
                TextButton(
                  onPressed: () {
                    // validating the input
                    if (_formKey.currentState!.validate()) {
                      _onNext(context, _confirmationCodeController.value.text);
                    }
                  },
                  child: const Text('Next'),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }

  void _onNext(BuildContext context, String confirmationCode) {
    Navigator.of(context).push(PageRouteBuilder(
        pageBuilder: (context, animation, secondaryAnimation) =>
            ChangePassword(confirmationCode),
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

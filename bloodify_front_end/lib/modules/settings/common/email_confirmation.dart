import 'dart:async';

import 'package:bloodify_front_end/shared/Constatnt/Component.dart';
// import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
import 'package:flutter/material.dart';

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

  @override
  void dispose() {
    _timer.cancel();
    super.dispose();
  }

  void _startTimer() {
    setState(() {
      _isButtonDisabled = true;
    });
    _timer = Timer.periodic(const Duration(seconds: 1), (timer) {
      setState(() {
        _timeLeft--;
        if(_timeLeft == 0) {
          timer.cancel();
          _timeLeft = 90;
          _isButtonDisabled = false;
        }
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    final double width = MediaQuery.of(context).size.width;
    final height = MediaQuery.of(context).size.height;
    return Scaffold(
      appBar: AppBar(
        title: const Text('Email Confirmation'),
      ),
      body: Form(
        key: _formKey,
        child: Column(
          children: [
            SizedBox(height: 0.02 * height),
            const Text('We have sent a confirmation code to your email. Please enter the code below:'),
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
            TextButton(
              onPressed: () {
                // validating the input
                if(_formKey.currentState!.validate()) {
                  print(_confirmationCodeController.value.text);
                  // TODO: CONTACT THE BACKEND TO VALIDATE THE CODE
                  // DioHelper.postData(url: "user/asdff", data: {
                  //   'code': _confirmationCodeController.value.text
                  // }).then((value) => value.data)
                  // .catchError((error) => print(error));
                }
              },
              child: const Text('Submit'),
            ),
            TextButton(
              // TODO: CONTACT THE BACKEND TO REGENERATE THE CODE
              onPressed: _isButtonDisabled ? null : _startTimer,
              child: _isButtonDisabled
                  ? Text('Resend code ($_timeLeft seconds)')
                  : const Text('Resend code')
            ),
          ],
        ),
      ),
    );
  }
}
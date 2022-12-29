import 'package:bloodify_front_end/shared/network/local/cach_helper.dart';
import 'package:flutter/material.dart';

import '../../../shared/Constatnt/Component.dart';

class ChangeThreshold extends StatefulWidget {
  const ChangeThreshold({super.key});

  @override
  State<ChangeThreshold> createState() => _ChangeThresholdState();
}

class _ChangeThresholdState extends State<ChangeThreshold> {
  final _formKey = GlobalKey<FormState>();
  final _thresholdController = TextEditingController();

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
            SizedBox(height: 0.02 * height),
            const Text('Select your new threshold in kilometers'
                ' (value must be between 1 and 50 km):'),
            SizedBox(height: 0.02 * height),
            defaultInputText(
                controller: _thresholdController,
                validate: (number) {
                  if (number == null || number == '') {
                    return language.enterValue('threshold');
                  } else if (int.parse(number) < 1 || int.parse(number) > 50) {
                    return language.showInvalidThreshold();
                  }
                  return null;
                },
                labelText: language.getLabel('threshold'),
                type: TextInputType.number,
                prefix: Icons.pin),
            TextButton(
              onPressed: () {
                // validating the input
                if(_formKey.currentState!.validate()) {
                  CachHelper.saveData(key: 'threshold',
                      value: int.parse(_thresholdController.value.text));
                  Navigator.of(context).pop();
                }
              },
              child: const Text('Submit'),
            ),
          ],
        ),
      ),
    );
  }
}

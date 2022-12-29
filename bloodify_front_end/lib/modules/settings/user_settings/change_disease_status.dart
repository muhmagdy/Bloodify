import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
import 'package:flutter/material.dart';

import '../../../shared/Constatnt/Component.dart';

class ChangeDiseaseStatus extends StatefulWidget {
  const ChangeDiseaseStatus({Key? key}) : super(key: key);

  @override
  State<ChangeDiseaseStatus> createState() => _ChangeDiseaseStatusState();
}

class _ChangeDiseaseStatusState extends State<ChangeDiseaseStatus> {
  final _formKey = GlobalKey<FormState>();
  String? hasDiseases = "true";

  @override
  Widget build(BuildContext context) {
    final height = MediaQuery.of(context).size.height;
    return Scaffold(
      appBar: AppBar(
        title: const Text('Change Disease Status'),
      ),
      body: Form(
        key: _formKey,
        child: Column(
          children: [
            SizedBox(height: 0.02 * height),
            RadioListTile(
              title: const Text("I have diseases"),
              value: "true",
              groupValue: hasDiseases,
              onChanged: (value) {
                setState(() {
                  hasDiseases = value;
                });
              },
            ),
            SizedBox(height: 0.02 * height),
            RadioListTile(
              title: const Text("I don't have any diseases"),
              value: "false",
              groupValue: hasDiseases,
              onChanged: (value) {
                setState(() {
                  hasDiseases = value;
                });
              },
            ),
            TextButton(
              onPressed: () {
                _onSubmit();
              },
              child: const Text('Submit'),
            ),
          ],
        ),
      ),
    );
  }

  void _onSubmit() {
    DioHelper.postData(
            url: 'user/disease', data: {'hasDiseases': hasDiseases == "true"})
        .then((value) {
      showToast(text: value.data['message'], color: Colors.blue, time: 3000);
      if (value.data['status'] == true) {
        Navigator.pop(context);
      }
    }).catchError((error) => print(error));
  }
}

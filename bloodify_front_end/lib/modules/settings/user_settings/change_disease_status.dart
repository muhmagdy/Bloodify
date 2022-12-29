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
              title: const Text("Yes"),
              value: "true",
              groupValue: hasDiseases,
              onChanged: (value){
                setState(() {
                  hasDiseases = value;
                });
              },
            ),
            SizedBox(height: 0.02 * height),
            RadioListTile(
              title: const Text("No"),
              value: "false",
              groupValue: hasDiseases,
              onChanged: (value){
                setState(() {
                  hasDiseases = value;
                });
              },
            ),
            TextButton(
              onPressed: () {
                DioHelper.postData(url: 'user/disease', data: {
                  'hasDiseases': hasDiseases == "true"
                }).then((value) {

                });
              },
              child: const Text('Submit'),
            ),
          ],
        ),
      ),
    );
  }
}

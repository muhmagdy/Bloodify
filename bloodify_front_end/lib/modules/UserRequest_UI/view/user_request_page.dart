import 'package:bloodify_front_end/modules/UserRequest_UI/bloc/user_request_bloc.dart';
import 'package:bloodify_front_end/modules/UserRequest_UI/bloc/user_request_state.dart';
import 'package:bloodify_front_end/modules/signUP_UI/sign_up_pages/Languages.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/container.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_spinbox/material.dart';
import 'package:intl/intl.dart';

class UserRequestForm extends StatelessWidget {
  // UserRequestForm({super.key});
  final _formKey = GlobalKey<FormState>();
  Language language = EnglishLanguage();
  var dateFormat = new DateFormat("dd-MM-yyyy");

  Padding makeDropDown(
      String title, List<String> list, Function(String value) changed) {
    return Padding(
        padding: EdgeInsets.all(10.0),
        child: DropdownButtonFormField<String>(
          decoration: InputDecoration(labelText: title),
          value: null,
          items: list.map<DropdownMenuItem<String>>((String value) {
            return DropdownMenuItem<String>(
              value: value,
              child: Text(value),
            );
          }).toList(),
          onChanged: (value) => {changed(value!)},
        ));
  }

  Center makeDatePicker(BuildContext context, String labelText) {
    // var labelText = "Post Expiration Date";
    var now = DateTime.now(), requestDuration = const Duration(days: 30);
    return Center(
        child: TextField(
      // controller: dateInput,
      //editing controller of this TextField
      decoration: InputDecoration(
          icon: const Icon(Icons.calendar_today), //icon of text field
          labelText: labelText //label text of field
          ),
      readOnly: true,
      //set it true, so that user will not able to edit text
      onTap: () async {
        DateTime? pickedDate = await showDatePicker(
            context: context,
            initialDate: now,
            firstDate: now,
            //DateTime.now() - not to allow to choose before today.
            lastDate: now.add(requestDuration));

        if (pickedDate != null) {
          print(
              pickedDate); //pickedDate output format => 2021-03-10 00:00:00.000
          String formattedDate = dateFormat.format(pickedDate);
          print(
              formattedDate); //formatted date output using intl package =>  2021-03-16
        } else {}
      },
    ));
  }

  @override
  Widget build(BuildContext context) {
    List<String> types = ['A+', 'B+', 'O+', 'AB+', 'A-', 'B-', 'O-', 'AB-'];
    List<String> institutions = ["one", "two", "three"];
    var postExpiration = "Post Expiration Date";
    var title = "Post Request";
    var bloodBags = "Blood bags";
    var bloodType = "Blood Type";
    var institution = "Institution";
    var buttonText = "Post";

    return BlocConsumer<UserRequestFormCubit, UserRequestFormStates>(
        builder: (context, state) {
          double width = MediaQuery.of(context).size.width;
          double height = MediaQuery.of(context).size.height;
          return Scaffold(
              backgroundColor: Colors.white,
              resizeToAvoidBottomInset: false,
              appBar: AppBar(
                backgroundColor: defaultColor,
                title: Text(
                  title,
                  style: TextStyle(color: Colors.white),
                ),
              ),
              body: SingleChildScrollView(
                padding: EdgeInsets.symmetric(
                    vertical: height * 0.01, horizontal: 8),
                child: Padding(
                  padding: const EdgeInsets.all(10.0),
                  child: Form(
                    key: _formKey,
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                          children: [
                            SizedBox(
                              width: (width - 50) / 2,
                              child: makeDropDown(
                                  bloodType, types, bloodTypeChanged),
                            ),
                            SizedBox(
                              width: (width - 50) / 2,
                              child: SpinBox(
                                decoration:
                                    InputDecoration(labelText: bloodBags),
                                min: 1,
                                max: 4,
                                value: 1,
                                onChanged: (value) =>
                                    {changedBloodCount(value)},
                              ),
                            ),
                          ],
                        ),
                        makeDatePicker(context, postExpiration),
                        makeDropDown(
                            institution, institutions, institutionChanged),
                        Padding(
                          padding: const EdgeInsets.all(15.0),
                          child: Row(
                              mainAxisAlignment: MainAxisAlignment.end,
                              children: [
                                SizedBox(
                                    width: width / 5,
                                    child: ElevatedButton(
                                        onPressed: submit,
                                        child: Text(buttonText)))
                              ]),
                        ),
                      ],
                    ),
                  ),
                ),
              ));
        },
        listener: (context, state) {});
  }

  changedBloodCount(double value) {}

  bloodTypeChanged(String value) {}

  institutionChanged(String value) {}

  void submit() {}
}

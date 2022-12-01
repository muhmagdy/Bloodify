import 'dart:io';

import 'package:bloodify_front_end/sign_up_State_management/sign_up_cubit.dart';
import 'package:bloodify_front_end/sign_up_pages/sign_up_1.dart';
import 'package:conditional_builder_null_safety/conditional_builder_null_safety.dart';
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:bloodify_front_end/shared/Constatnt/Component.dart';
import 'package:geolocator/geolocator.dart';
import 'package:intl/intl.dart';

import '../shared/Constatnt/sharedFunctions.dart';
import '../sign_up_State_management/sign_up_user_model.dart';

class SignUp2 extends StatelessWidget {
  final dateController = TextEditingController();
  List<String> types = ['A+', 'B+', 'O+', 'AB+', 'A-', 'B-', 'O-', 'AB-'];
  final _formKey = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    dateController.text =
        DateFormat('yyyy-MM-dd').format(SignUpCubit.get(context).user.dOB);

    return BlocConsumer<SignUpCubit, SignUpStates>(
      listener: (context, state) {
        // TODO: implement listener
        if (state is SiqnUpApiSucces) {
          if (state.response.status) {
            SignUpCubit.get(context).user = new UserData();
            showToast(
                text: "Verifiy Your Email link sended to you inbox",
                color: Colors.green,
                time: 5000);
            navigateAndFinish(
              context,
              SignUp1(),
            );
          } else {
            showToast(
                text: state.response.message, color: Colors.red, time: 5000);
          }
        }
      },
      builder: (context, state) {
        double width = MediaQuery.of(context).size.width;
        double height = MediaQuery.of(context).size.height;
        return Scaffold(
          backgroundColor: Colors.white,
          resizeToAvoidBottomInset: false,
          body: SingleChildScrollView(
            child: Padding(
              padding: const EdgeInsets.all(10.0),
              child: Form(
                key: _formKey,
                child: Column(
                  children: [
                    DefaultProgramPhoto(width: width, height: height),
                    TextFormField(
                        validator: (String? value) {
                          if (value!.isEmpty) {
                            return "Enter Your birth date";
                          }
                          if (new DateFormat("yyyy-MM-dd").parse(value) != SignUpCubit.get(context).supposedDateOfBirth) {
                            print("incorrect date");
                            return "Not Correct Date Of Birth";
                          }
                          return null;
                        },
                        onTap: () {
                          DateTime now = DateTime.now();
                          showDatePicker(
                            context: context,
                            initialDate: SignUpCubit.get(context).user.dOB,
                            firstDate:
                                DateTime(now.year - 60, now.month, now.day),
                            lastDate:
                                DateTime(now.year - 18, now.month, now.day),
                          ).then((value) => {
                                if (value != null)
                                  {
                                    dateController.text =
                                        DateFormat('yyyy-MM-dd').format(value),
                                    SignUpCubit.get(context)
                                        .changeDateOfBirth(value),
                                  }
                              });
                        },
                        controller: dateController,
                        keyboardType: TextInputType.datetime,
                        decoration: InputDecoration(
                          prefix: Icon(
                            Icons.calendar_month_outlined,
                            color: Color.fromARGB(255, 255, 78, 66),
                          ),
                          labelText: "Date of birth",
                          focusedBorder: OutlineInputBorder(
                              borderSide: BorderSide(color: Colors.grey)),
                          border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(10)),
                        )),
                    SizedBox(height: 10),
                    Row(
                      children: [
                        Flexible(
                            flex: 3,
                            child: Column(
                              mainAxisAlignment: MainAxisAlignment.start,
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Text("Do You Have any Diseases?",
                                    style: TextStyle(color: Colors.grey[800])),
                                SizedBox(
                                  height: 5,
                                ),
                                Row(
                                  children: [
                                    Flexible(
                                      child: Container(
                                        width: 95,
                                        decoration: BoxDecoration(
                                            borderRadius:
                                                BorderRadius.circular(10),
                                            border: Border.all(
                                                color: Colors.grey, width: 2)),
                                        child: TextButton.icon(
                                            onPressed: () {
                                              SignUpCubit.get(context)
                                                  .changeIsPatient(true);
                                            },
                                            style: ButtonStyle(
                                              elevation: MaterialStateProperty
                                                  .resolveWith((states) => 0.0),
                                            ),
                                            icon: SignUpCubit.get(context)
                                                    .user
                                                    .isPatient
                                                ? Icon(
                                                    Icons
                                                        .radio_button_checked_outlined,
                                                    color: Colors.red)
                                                : Icon(
                                                    Icons
                                                        .radio_button_off_outlined,
                                                    color: Colors.red),
                                            label: Text('Yes',
                                                style: TextStyle(
                                                    color: Colors.grey[800]))),
                                      ),
                                    ),
                                    SizedBox(
                                      width: 5,
                                    ),
                                    Flexible(
                                      child: Container(
                                        width: 95,
                                        decoration: BoxDecoration(
                                            borderRadius:
                                                BorderRadius.circular(10),
                                            border: Border.all(
                                                color: Colors.grey, width: 2)),
                                        child: TextButton.icon(
                                            onPressed: () {
                                              SignUpCubit.get(context)
                                                  .changeIsPatient(false);
                                            },
                                            style: ButtonStyle(
                                              elevation: MaterialStateProperty
                                                  .resolveWith((states) => 0.0),
                                            ),
                                            icon: !SignUpCubit.get(context)
                                                    .user
                                                    .isPatient
                                                ? Icon(
                                                    Icons
                                                        .radio_button_checked_outlined,
                                                    color: Colors.red)
                                                : Icon(
                                                    Icons
                                                        .radio_button_off_outlined,
                                                    color: Colors.red),
                                            label: Text('No',
                                                style: TextStyle(
                                                    color: Colors.grey[800]))),
                                      ),
                                    )
                                  ],
                                ),
                                SizedBox(
                                  height: 10,
                                ),
                                Text("Blood Type",
                                    textAlign: TextAlign.left,
                                    style: TextStyle(color: Colors.grey[800])),
                                DropdownButtonFormField(
                                    value:
                                        SignUpCubit.get(context).user.bloodType,
                                    items: types
                                        .map((e) => DropdownMenuItem(
                                              child: Text(
                                                "    $e",
                                                style: TextStyle(
                                                    color: Colors.red,
                                                    fontWeight: FontWeight.bold,
                                                    fontSize: 18),
                                              ),
                                              value: e,
                                            ))
                                        .toList(),
                                    icon: Icon(
                                      Icons.bloodtype_outlined,
                                      color: Colors.red,
                                    ),
                                    onChanged: (val) {
                                      SignUpCubit.get(context)
                                          .changeBloodType(val!);
                                    })
                              ],
                            )),
                        SizedBox(
                          width: 10,
                        ),
                        Flexible(
                            flex: 2,
                            child: Column(
                              mainAxisAlignment: MainAxisAlignment.start,
                              crossAxisAlignment: CrossAxisAlignment.center,
                              children: [
                                Text("Location",
                                    textAlign: TextAlign.right,
                                    style: TextStyle(
                                      color: Colors.grey[800],
                                    )),
                                SizedBox(
                                  height: 20,
                                ),
                                ConditionalBuilder(
                                  condition: state is! GetLocationsLoading,
                                  builder: (context) => Container(
                                    decoration: BoxDecoration(
                                      border: Border.all(
                                          width: 10, color: Colors.grey),
                                      color: Color.fromARGB(255, 237, 237, 237),
                                      shape: BoxShape.circle,
                                    ),
                                    child: IconButton(
                                      iconSize: 80,
                                      icon: const Icon(
                                        Icons.gps_fixed_rounded,
                                        color: Colors.pink,
                                      ),
                                      onPressed: () {
                                        SignUpCubit.get(context).getLocation();
                                      },
                                    ),
                                  ),
                                  fallback: (context) => Center(
                                      child: CircularProgressIndicator()),
                                )
                              ],
                            ))
                      ],
                    ),
                    SizedBox(height: 30),
                    Row(
                      children: [
                        Expanded(
                          child: DefaultButton(
                              onClick: () {
                                if(_formKey.currentState!.validate()) Navigator.pop(context);
                              },
                              text: "Back"),
                        ),
                        SizedBox(
                          width: 10,
                        ),
                        Expanded(
                          child: ConditionalBuilder(
                            condition: state is! SignUpLoading,
                            builder: (context) => DefaultButton(
                                onClick: () {
                                  if (SignUpCubit.get(context).serviceEnabled && _formKey.currentState!.validate()) {
                                    print("object");
                                    SignUpCubit.get(context).userSignUp();
                                  }
                                  ////////some checks on data and requests to the backend API
                                  // to decide whether to go to Home page or ask to complete the missing data
                                },
                                text: "Submit",
                                backGround: Colors.green),
                            fallback: (context) =>
                                Center(child: CircularProgressIndicator()),
                          ),
                        )
                      ],
                    )
                  ],
                ),
              ),
            ),
          ),
        );
      },
    );
  }
}

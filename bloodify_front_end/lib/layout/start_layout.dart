import 'package:bloodify_front_end/modules/login_UI/User_login/userLogin.dart';
import 'package:bloodify_front_end/modules/login_UI/institution_login/institutionLogin.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/container.dart';
import 'package:flutter/src/widgets/framework.dart';

import '../shared/Constatnt/Component.dart';
import '../shared/functions/sharedFunctions.dart';

class StartWidget extends StatelessWidget {
  Widget txb({
    required text,
    required icon,
    required onClick,
  }) =>
      Container(
        width: double.infinity,
        height: 60,
        child: TextButton.icon(
          // <-- TextButton
          onPressed: onClick,
          icon: Icon(
            icon,
            color: Colors.white,
            size: 24.0,
          ),

          style: ButtonStyle(
            // minimumSize:
            // MaterialStateProperty.all(Size(width - 40, height * .09)),
            backgroundColor: MaterialStateProperty.all<Color>(Colors.red),
            shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(10),
              side: BorderSide(color: Colors.white),
            )),
          ),
          label: Text(
            text,
            style: TextStyle(color: Colors.white, fontSize: 20),
          ),
        ),
      );
  const StartWidget({super.key});

  @override
  Widget build(BuildContext context) {
    double width = MediaQuery.of(context).size.width;
    double height = MediaQuery.of(context).size.height;
    return Scaffold(
      body: Container(
        margin: EdgeInsets.only(left: 20, right: 20),
        child: Column(
          children: [
            DefaultProgramPhoto(height: height, width: width),
            SizedBox(
              height: 40,
            ),
            // DefaultButton(
            //     onClick: () {
            //       navigateTo(context, UserLogin());
            //     },
            //     text: "Login User"),
            // SizedBox(
            //   height: 40,
            // ),
            // DefaultButton(
            //     onClick: () {
            //       navigateTo(context, InstitutionLogin());
            //     },
            //     text: "Login Institution"),
            // SizedBox(
            //   height: 40,
            // ),
            // DefaultButton(
            //     onClick: () {
            //       // navigateTo(context, SignUp());
            //     },
            //     text: "Signup"),
            SizedBox(
              height: 40,
            ),
            txb(
              text: "Login Institution",
              icon: Icons.local_hospital_rounded,
              onClick: () {
                navigateTo(context, InstitutionLogin());
              },
            ),
            SizedBox(
              height: 40,
            ),
            txb(
              text: "Login User",
              icon: Icons.person,
              onClick: () {
                navigateTo(context, UserLogin());
              },
            ),
            SizedBox(
              height: 40,
            ),
            txb(
              text: "Signup",
              icon: Icons.add,
              onClick: () {
                // navigateTo(context, SignUp());
              },
            ),
          ],
        ),
      ),
    );
  }
}
import 'package:flutter/material.dart';

Widget DefaultProgramPhoto({required width, required height}) => Center(
      child: Container(
        width: width * 3 / 4,
        height: height * 0.3 + height / 10,
        decoration: BoxDecoration(
            image: DecorationImage(
                image: AssetImage("assets/images/bloodify.png"),
                fit: BoxFit.contain)),
      ),
    );
Widget DefaultButton(
        {double width = double.infinity,
        double height = 60,
        Color backGround = Colors.red,
        required Function onClick,
        required String text,
        merginLeft = 20.0,
        merginRight = 20.0}) =>
    Container(
        width: width,
        height: height,
        margin: EdgeInsets.only(left: merginLeft, right: merginRight),
        child: TextButton(
          style: ButtonStyle(
            // minimumSize:
            // MaterialStateProperty.all(Size(width - 40, height * .09)),
            backgroundColor: MaterialStateProperty.all<Color>(backGround),
            shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(10),
              side: BorderSide(color: Color.fromARGB(255, 139, 92, 89)),
            )),
          ),
          onPressed: () {
            onClick();
          },
          child: Text(
            text = text.toUpperCase(),
            style: TextStyle(color: Colors.white, fontSize: 20),
          ),
        ));

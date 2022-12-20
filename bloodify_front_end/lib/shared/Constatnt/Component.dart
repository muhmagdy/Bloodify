import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';

import '../../modules/signUP_UI/sign_up_pages/Languages.dart';
import 'nationalIDValidator.dart';

Widget defaultProgramPhoto({required width, required height}) => Center(
      child: Container(
        width: width * 3 / 4,
        height: height * 0.3 + height / 10,
        decoration: const BoxDecoration(
            image: DecorationImage(
                image: AssetImage("assets/images/bloodify.png"),
                fit: BoxFit.contain)),
      ),
    );
Widget defaultButton(
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
              side: const BorderSide(color: Color.fromARGB(255, 139, 92, 89)),
            )),
          ),
          onPressed: () {
            onClick();
          },
          child: Text(
            text = text.toUpperCase(),
            style: const TextStyle(color: Colors.white, fontSize: 20),
          ),
        ));
Widget defaultInputText({
  required TextEditingController controller,
  TextInputType? type,
  Function? onSubmit,
  Function? onChange,
  Function? onTap,
  bool isPassword = false,
  required Function validate,
  required String labelText,
  IconData? prefix,
  IconData? suffix,
  Function? suffixPressed,
  bool isClickable = true,
}) =>
    TextFormField(
      controller: controller,
      keyboardType: type,
      obscureText: isPassword,
      enabled: isClickable,
      onFieldSubmitted: (() {
        if (onSubmit != null) {
          onSubmit.call();
        } else {
          return null;
        }
      }()),
      onChanged: (() {
        if (onChange != null) {
          onChange.call();
        } else {
          return null;
        }
      }()),
      onTap: (() {
        if (onTap != null) {
          onTap.call();
        } else {
          return null;
        }
      }()),
      validator: (value) => validate(value),
      decoration: InputDecoration(
        labelText: labelText,
        prefixIcon: Icon(
          prefix,
          color: const Color.fromARGB(255, 255, 78, 66),
        ),
        suffixIcon: (() {
          if (suffix != null) {
            return InkWell(
              onTap: () {
                suffixPressed!.call();
              },
              child: isPassword
                  ? const Icon(
                      Icons.visibility,
                      color: Color.fromARGB(255, 255, 78, 66),
                    )
                  : const Icon(
                      Icons.visibility_off,
                      color: Color.fromARGB(255, 255, 78, 66),
                    ),
            );
          } else {
            return null;
          }
        }()),
        border: const OutlineInputBorder(),
      ),
    );
NationalIDParser parser = EgyptNationalIDParser();
Language language = EnglishLanguage();
void showToast({
  required String text,
  required Color color,
  required time,
}) =>
    Fluttertoast.showToast(
      backgroundColor: color,
      msg: text,
      toastLength: Toast.LENGTH_LONG,
      gravity: ToastGravity.BOTTOM,
      timeInSecForIosWeb: time,
      textColor: Colors.white,
      fontSize: 16.0,
    );
nationalValidate(number) {
  if (number == null || number == '') {
    return language.enterValue('natID');
  }
  if (!parser.validateNationalID(int.parse(number))) {
    return language.showInvalidValue('natID');
  }
  return null;
}

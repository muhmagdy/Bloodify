import 'package:bloodify_front_end/layout/start_layout.dart';
import 'package:bloodify_front_end/modules/settings/common/email_confirmation.dart';
import 'package:bloodify_front_end/modules/settings/user_settings/change_disease_status.dart';

import 'package:bloodify_front_end/modules/settings/user_settings/change_threshold.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
import 'package:flutter/material.dart';

import '../../../shared/Constatnt/Component.dart';
import '../../../shared/Constatnt/colors.dart';
import '../../../shared/Constatnt/fonts.dart';
import '../../../shared/network/local/cach_helper.dart';

class UserSettings extends StatefulWidget {
  const UserSettings({super.key});

  @override
  State<UserSettings> createState() => _UserSettingsState();
}

class _UserSettingsState extends State<UserSettings> {
  @override
  Widget build(BuildContext context) {
    final double width = MediaQuery.of(context).size.width;
    final height = MediaQuery.of(context).size.height;
    return Scaffold(
      appBar: AppBar(
          title: Row(
        children: [
          ClipRRect(
            borderRadius: BorderRadius.circular(70),
            child: Container(
              width: 40,
              height: 40,
              decoration: const BoxDecoration(
                  color: Colors.white,
                  image: DecorationImage(
                      image:
                          AssetImage('assets/icons/blood-removebg-preview.ico'),
                      fit: BoxFit.contain)),
            ),
          ),
          SizedBox(
            width: width * .25,
          ),
          const Text(
            "Settings",
            style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold),
          ),
        ],
      )),
      body: Container(
        width: double.infinity,
        alignment: Alignment.center,
        child: Column(
          children: [
            Container(
              width: 0.15 * height,
              height: 0.15 * height,
              margin:
                  EdgeInsets.only(top: 0.02 * height, bottom: 0.02 * height),
              decoration: const BoxDecoration(
                  // color: Colors.white,
                  image: DecorationImage(
                      image: AssetImage('assets/images/user-settings-icon.png'),
                      fit: BoxFit.contain)),
            ),
            Text(
              CachHelper.getData(key: 'username'),
              style: const TextStyle(fontSize: 30),
            ),
            SizedBox(height: 0.01 * height),
            TextButton(
                onPressed: () {
                  _onChangeThresholdTap(context);
                },
                style: TextButton.styleFrom(
                    backgroundColor: red,
                    minimumSize: Size(width * 0.9, height * 0.05),
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(width / 26))),
                child: Text(
                  "Change threshold",
                  style: NormalStyle(height, Colors.white),
                )),
            SizedBox(height: 0.01 * height),
            TextButton(
                onPressed: () {
                  _onChangeDiseaseStatus(context);
                },
                style: TextButton.styleFrom(
                    backgroundColor: red,
                    minimumSize: Size(width * 0.9, height * 0.05),
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(width / 26))),
                child: Text(
                  "Change disease status",
                  style: NormalStyle(height, Colors.white),
                )),
            SizedBox(height: 0.01 * height),
            TextButton(
                onPressed: () {
                  _onChangePasswordTap(context);
                },
                style: TextButton.styleFrom(
                    backgroundColor: red,
                    minimumSize: Size(width * 0.9, height * 0.05),
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(width / 26))),
                child: Text(
                  "Change password",
                  style: NormalStyle(height, Colors.white),
                )),
            SizedBox(height: 0.01 * height),
            TextButton(
                onPressed: () async {
                  bool result = await showDialog(
                    context: context,
                    builder: (context) {
                      return AlertDialog(
                        title: const Text('Confirm log out'),
                        content:
                            const Text('Are you sure you want to log out?'),
                        actions: [
                          TextButton(
                            onPressed: () {
                              Navigator.of(context).pop(false);
                            },
                            child: const Text('Cancel'),
                          ),
                          TextButton(
                            onPressed: () {
                              Navigator.of(context).pop(true);
                            },
                            child: const Text('OK'),
                          ),
                        ],
                      );
                    },
                  );
                  if (result) {
                    await _onLogoutTap(context);
                  }
                },
                style: TextButton.styleFrom(
                    backgroundColor: red,
                    minimumSize: Size(width * 0.9, height * 0.05),
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(width / 26))),
                child: Text(
                  "Log out",
                  style: NormalStyle(height, Colors.white),
                ))
          ],
        ),
      ),
    );
  }

  void _onChangeThresholdTap(BuildContext context) {
    Navigator.of(context).push(PageRouteBuilder(
        pageBuilder: (context, animation, secondaryAnimation) =>
            const ChangeThreshold(),
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

  void _onChangeDiseaseStatus(BuildContext context) {
    Navigator.of(context).push(PageRouteBuilder(
        pageBuilder: (context, animation, secondaryAnimation) =>
            const ChangeDiseaseStatus(),
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

  void _onChangePasswordTap(BuildContext context) {
    DioHelper.postData(url: "password", data: {
      'email': CachHelper.getData(key: 'email'),
    }).then((value) {
      showToast(text: value.data['message'], color: Colors.blue, time: 3000);
    }).catchError((error) => print(error));

    CachHelper.saveData(
        key: 'confirmationEmail', value: CachHelper.getData(key: 'email'));

    Navigator.of(context).push(PageRouteBuilder(
        pageBuilder: (context, animation, secondaryAnimation) =>
            const EmailConfirmation(),
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

  Future _onLogoutTap(BuildContext context) async {
    if (await CachHelper.removeAllData()) {
      DioHelper.postData(url: "user/logout", data: {});
      Navigator.of(context, rootNavigator: true).pushReplacement(
          MaterialPageRoute(builder: (context) => const StartWidget()));
    }
  }
}

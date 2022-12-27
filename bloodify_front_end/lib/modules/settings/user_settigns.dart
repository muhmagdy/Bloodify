import 'package:bloodify_front_end/modules/login_UI/User_login/userLogin.dart';
import 'package:bloodify_front_end/modules/signUP_UI/sign_up_pages/Languages.dart';
import 'package:bloodify_front_end/modules/signUP_UI/sign_up_pages/sign_up_1.dart';
import 'package:flutter/material.dart';

import '../../shared/Constatnt/colors.dart';
import '../../shared/Constatnt/fonts.dart';

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
    const title = "Settings";
    const photoWidth = 100.0;
    const photoHeight = 100.0;
    return  Scaffold(
      appBar: AppBar(
          toolbarHeight: 0.07 * height,
          backgroundColor: blue,
          centerTitle: true,
          title: Text(title, style: AppHeaderStyle(height, Colors.white))
      ),
      body: Container(
        width: double.infinity,
        alignment: Alignment.center,
        child: Column(
          // crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Container(
              width: photoWidth,
              height: photoHeight,
              margin: EdgeInsets.only(top: 0.02*height, bottom: 0.02*height),
              decoration: const BoxDecoration(
                  color: Colors.white,
                  image: DecorationImage(
                      image: AssetImage('assets/images/user-settings-icon.png'),
                      fit: BoxFit.contain)
              ),
            ),
            const Text(
              "El Saber Dealer",
              style: TextStyle(fontSize: 30),
            ),
            SizedBox(height: 0.01*height),
            TextButton(
                onPressed: () {},
                style: TextButton.styleFrom(
                    backgroundColor: red,
                    minimumSize: Size(width * 0.9, height * 0.05),
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(width / 26)
                    )
                ),
                child: Text(
                  "Change email",
                  style: NormalStyle(height, Colors.white),
                )
            ),
            SizedBox(height: 0.01*height),
            TextButton(
                onPressed: () {},
                style: TextButton.styleFrom(
                    backgroundColor: red,
                    minimumSize: Size(width * 0.9, height * 0.05),
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(width / 26)
                    )
                ),
                child: Text(
                  "Change password",
                  style: NormalStyle(height, Colors.white),
                )
            ),
            SizedBox(height: 0.01*height),
            TextButton(
                onPressed: () async {
                  bool result = await showDialog(
                    context: context,
                    builder: (context) {
                      return AlertDialog(
                        title: const Text('Confirm log out'),
                        content: const Text('Are you sure you want to log out?'),
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
                    // print("navigating 7alan!");
                    await logout(context);
                    // send to backend token
                    // navigate to main
                  }
                },
                style: TextButton.styleFrom(
                    backgroundColor: red,
                    minimumSize: Size(width * 0.9, height * 0.05),
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(width / 26)
                    )
                ),
                child: Text(
                  "Log out",
                  style: NormalStyle(height, Colors.white),
                )
            )
          ],
        ),
      ),
    );
  }
}

Future logout(BuildContext context) async {
  Navigator.pushNamedAndRemoveUntil(context, '/', (route) => false);
}
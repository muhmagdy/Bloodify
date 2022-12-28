import 'package:flutter/material.dart';

import '../../layout/start_layout.dart';
import '../../shared/Constatnt/colors.dart';
import '../../shared/Constatnt/fonts.dart';
import '../../shared/network/local/cach_helper.dart';

class InstitutionSettings extends StatefulWidget {
  const InstitutionSettings({super.key});

  @override
  State<InstitutionSettings> createState() => _InstitutionSettingsState();
}

class _InstitutionSettingsState extends State<InstitutionSettings> {
  Future logout(BuildContext context) async {
    if (await CachHelper.removeData(key: 'isUser') &&
        await CachHelper.removeData(key: 'token')) {
      Navigator.of(context, rootNavigator: true).pushReplacement(
          MaterialPageRoute(builder: (context) => const StartWidget()));
    }
  }

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
          // crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Container(
              width: 0.15 * height,
              height: 0.15 * height,
              margin:
                  EdgeInsets.only(top: 0.02 * height, bottom: 0.02 * height),
              decoration: const BoxDecoration(
                  // color: Colors.white,
                  image: DecorationImage(
                      image: AssetImage(
                          'assets/images/institution-settings-icon.png'),
                      fit: BoxFit.contain)),
            ),
            const Text(
              "El Saber Hospital",
              style: TextStyle(fontSize: 30),
            ),
            SizedBox(height: 0.01 * height),
            TextButton(
                onPressed: () {},
                style: TextButton.styleFrom(
                    backgroundColor: red,
                    minimumSize: Size(width * 0.9, height * 0.05),
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(width / 26))),
                child: Text(
                  "Change email",
                  style: NormalStyle(height, Colors.white),
                )),
            SizedBox(height: 0.01 * height),
            TextButton(
                onPressed: () {},
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
                    await logout(context);
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
}

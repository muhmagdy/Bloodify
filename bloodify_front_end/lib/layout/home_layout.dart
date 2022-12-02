import 'package:bloodify_front_end/layout/start_layout.dart';
import 'package:bloodify_front_end/modules/login_UI/User_login/userLogin.dart';
import 'package:bloodify_front_end/shared/network/local/cach_helper.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/container.dart';
import 'package:flutter/src/widgets/framework.dart';

import '../shared/functions/sharedFunctions.dart';

class HomeLayout extends StatelessWidget {
  const HomeLayout({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Color.fromARGB(255, 255, 78, 66),
        title: Row(children: [
          ClipRRect(
            borderRadius: BorderRadius.circular(70),
            child: Container(
              width: 40,
              height: 40,
              decoration: BoxDecoration(
                  color: Colors.white,
                  image: DecorationImage(
                      image:
                          AssetImage('assets/icons/blood-removebg-preview.ico'),
                      fit: BoxFit.contain)),
            ),
          ),
          SizedBox(
            width: 10,
          ),
          Text(
            'Home',
            style: TextStyle(color: Colors.white),
          )
        ]),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          CachHelper.removeData(
            key: 'token',
          ).then((value) {
            if (value) {
              navigateAndFinish(
                context,
                StartWidget(),
              );
            }
          });
          CachHelper.removeData(
            key: 'isUser',
          ).then((value) {
            if (value) {
              navigateAndFinish(
                context,
                StartWidget(),
              );
            }
          });
        },
        backgroundColor: Colors.red,
        child: const Icon(Icons.logout_rounded),
      ),
    );
  }
}

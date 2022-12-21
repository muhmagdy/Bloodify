import 'package:bloodify_front_end/layout/start_layout.dart';
import 'package:bloodify_front_end/modules/login_UI/User_login/userLogin.dart';
import 'package:bloodify_front_end/modules/navbar/navbar.dart';
import 'package:bloodify_front_end/shared/network/local/cach_helper.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/container.dart';
import 'package:flutter/src/widgets/framework.dart';

import '../shared/Constatnt/sharedFunctions.dart';

class HomeLayout extends StatelessWidget {
  const HomeLayout({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(debugShowCheckedModeBanner: false, home: NavBar());
  }
}

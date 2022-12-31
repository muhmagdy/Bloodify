import 'package:bloodify_front_end/modules/home_UI/user_home/userHome.dart';
import 'package:bloodify_front_end/modules/navbar/institution_navbar.dart';
import 'package:bloodify_front_end/modules/navbar/navbar.dart';
import 'package:bloodify_front_end/shared/Constatnt/userInfo.dart';

import 'package:flutter/material.dart';

// ignore: must_be_immutable
class HomeLayout extends StatelessWidget {
  HomeLayout({super.key});
  String token = UserInfo.token ?? "";
  bool isUser = UserInfo.isUser ?? true;
  @override
  Widget build(BuildContext context) {
    if (isUser) {
      return const MaterialApp(
          debugShowCheckedModeBanner: false, home: NavBar());
    } else {
      return const MaterialApp(
          debugShowCheckedModeBanner: false, home: InstNavBar());
    }
  }
}

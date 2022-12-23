import 'package:bloodify_front_end/layout/start_layout.dart';

import 'package:bloodify_front_end/models/event_model.dart';
import 'package:bloodify_front_end/modules/navbar/institution_navbar.dart';
import 'package:bloodify_front_end/modules/transactions_modules/post_transaction/postTransaction.dart';

import 'package:bloodify_front_end/modules/create_event/create_event.dart';
import 'package:bloodify_front_end/modules/login_UI/User_login/userLogin.dart';
import 'package:bloodify_front_end/modules/navbar/navbar.dart';
import 'package:bloodify_front_end/shared/Constatnt/userInfo.dart';

import 'package:bloodify_front_end/shared/network/local/cach_helper.dart';
import 'package:flutter/material.dart';
import '../modules/transactions_modules/institution_tranaction/InstituteTransaction.dart';
import '../shared/Constatnt/sharedFunctions.dart';

// ignore: must_be_immutable
class HomeLayout extends StatelessWidget {
  const HomeLayout({super.key});
  String token = UserInfo.token ?? "";
  bool isUser = UserInfo.isUser ?? true;
  var post = Post(1, "3010010152045", 4, "A.con");
  @override
  Widget build(BuildContext context) {
    isUser = true;
    return MaterialApp(debugShowCheckedModeBanner: false, home: NavBar());
  }
}

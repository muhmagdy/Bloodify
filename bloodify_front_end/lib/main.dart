import 'package:bloc/bloc.dart';
import 'package:bloodify_front_end/modules/login/userLogin.dart';
import 'package:bloodify_front_end/shared/bloc_observer.dart';
import 'package:bloodify_front_end/shared/network/local/cach_helper.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
import 'package:bloodify_front_end/shared/styles/themes.dart';
import 'package:flutter/material.dart';

import 'layout/home_layout.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await CachHelper.init();
  String? token = CachHelper.getData(key: 'token');
  var widget;
  if (token != null)
    widget = HomeLayout();
  else
    widget = UserLogin();
  Bloc.observer = MyBlocObserver();
  DioHelper.init();
  runApp(MyApp(
    startWidget: widget,
  ));
}

class MyApp extends StatelessWidget {
  final Widget startWidget;
  MyApp({
    required this.startWidget,
  });

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: lightTheme,
      darkTheme: darkTheme,
      themeMode: false ? ThemeMode.dark : ThemeMode.light,
      home: startWidget,
    );
  }
}

import 'package:bloc/bloc.dart';
import 'package:bloodify_front_end/modules/login/login.dart';
import 'package:bloodify_front_end/shared/bloc_observer.dart';
import 'package:bloodify_front_end/shared/network/local/cach_helper.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
import 'package:bloodify_front_end/shared/styles/themes.dart';
import 'package:flutter/material.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await CachHelper.init();
  Bloc.observer = MyBlocObserver();
  DioHelper.init();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: lightTheme,
      darkTheme: darkTheme,
      themeMode: false ? ThemeMode.dark : ThemeMode.light,
      home: Login(),
    );
  }
}

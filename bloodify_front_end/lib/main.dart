import 'package:bloc/bloc.dart';
import 'package:bloodify_front_end/layout/start_layout.dart';
import 'package:bloodify_front_end/modules/BloodFinding/bloc/blood_finder_cubit.dart';
import 'package:bloodify_front_end/modules/BloodFinding/view/blood_finder_page.dart';
import 'package:bloodify_front_end/modules/UserRequest_UI/user_request.dart';
import 'package:bloodify_front_end/modules/UserRequest_UI/view/user_request_page.dart';
import 'package:bloodify_front_end/modules/login_UI/User_login/userLogin.dart';
import 'package:bloodify_front_end/modules/login_UI/institution_login/institutionLogin.dart';
import 'package:bloodify_front_end/shared/bloc_observer.dart';
import 'package:bloodify_front_end/shared/network/local/cach_helper.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
import 'package:bloodify_front_end/shared/styles/themes.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import 'layout/home_layout.dart';
import 'modules/login_UI/User_login/cubit/user_login_cubit.dart';
import 'modules/login_UI/institution_login/cubit/institution_login_cubit.dart';
import 'modules/signUP_UI/sign_up_State_management/sign_up_cubit.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await CachHelper.init();
  String? token = CachHelper.getData(key: 'token');
  var widget;
  if (token != null)
    widget = HomeLayout();
  else
    widget = StartWidget();
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
    return MultiBlocProvider(
        providers: [
          BlocProvider(create: (context) => SignUpCubit()),
          BlocProvider(create: (context) => UserLoginCubit()),
          BlocProvider(create: (context) => InstitutionLoginCubit()),
        ],
        child: MaterialApp(
          debugShowCheckedModeBanner: false,
          theme: lightTheme,
          darkTheme: darkTheme,
          themeMode: false ? ThemeMode.dark : ThemeMode.light,
          home: startWidget,
        ));
  }
}

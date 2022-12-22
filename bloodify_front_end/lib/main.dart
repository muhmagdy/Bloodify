import 'package:bloodify_front_end/layout/start_layout.dart';
import 'package:bloodify_front_end/modules/login_UI/User_login/userLogin.dart';
import 'package:bloodify_front_end/modules/login_UI/institution_login/institutionLogin.dart';
import 'package:bloodify_front_end/modules/navbar/institution_navbar.dart';

import 'package:bloodify_front_end/modules/BloodFinding/bloc/blood_finder_cubit.dart';
import 'package:bloodify_front_end/modules/BloodFinding/view/blood_finder_page.dart';
import 'package:bloodify_front_end/modules/UserRequest_UI/user_request.dart';
import 'package:bloodify_front_end/modules/UserRequest_UI/view/user_request_page.dart';

import 'package:bloodify_front_end/modules/transactions_modules/event_transaction/cubit/eventTransaction_cubit.dart';
import 'package:bloodify_front_end/modules/transactions_modules/post_transaction/cubit/post_transaction_cubit.dart';
import 'package:bloodify_front_end/shared/Constatnt/login.dart';
import 'package:bloodify_front_end/shared/Constatnt/userInfo.dart';

import 'package:bloodify_front_end/modules/create_event/create_event_cubit/create_event_cubit.dart';

import 'package:bloodify_front_end/modules/login_UI/User_login/userLogin.dart';
import 'package:bloodify_front_end/modules/login_UI/institution_login/institutionLogin.dart';
import 'package:bloodify_front_end/shared/bloc_observer.dart';
import 'package:bloodify_front_end/shared/network/local/cach_helper.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
import 'package:bloodify_front_end/shared/styles/themes.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import 'layout/home_layout.dart';
import 'models/institution.dart';
import 'modules/login_UI/User_login/cubit/user_login_cubit.dart';
import 'modules/login_UI/institution_login/cubit/institution_login_cubit.dart';
import 'modules/signUP_UI/sign_up_State_management/sign_up_cubit.dart';
import 'shared/Constatnt/userInfo.dart';
import 'modules/transactions_modules/institution_tranaction/cubic/institution_transaction_cubit.dart';

void main() async {
  String token = UserInfo.token;
  bool isUser = UserInfo.isUser;
  WidgetsFlutterBinding.ensureInitialized();
  await CachHelper.init();
  token = CachHelper.getData(key: 'token');
  var widget;
  if (token != null) {
    isUser = CachHelper.getData(key: 'isUser');
    widget = HomeLayout();
  } else {
    widget = const StartWidget();
  }
  Bloc.observer = MyBlocObserver();
  DioHelper.init();
  runApp(MyApp(
    startWidget: widget,
  ));
}

class MyApp extends StatelessWidget {
  final Widget startWidget;
  const MyApp({
    super.key,
    required this.startWidget,
  });

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    // if (kDebugMode) {
    //   return MaterialApp(debugShowCheckedModeBanner: false, home: InstNavBar());
    // }
    return MultiBlocProvider(
        providers: [
          BlocProvider(create: (context) => SignUpCubit()),
          BlocProvider(create: (context) => UserLoginCubit()),
          BlocProvider(create: (context) => InstitutionLoginCubit()),
          BlocProvider(create: (context) => PostTransactionCubit()),
          BlocProvider(create: (context) => EventTransactionCubit()),
          BlocProvider(create: (context) => InstituteTransactionCubit()),
          BlocProvider(create: (context) => CreateEventCubit()),
        ],
        child: MaterialApp(
          debugShowCheckedModeBanner: false,
          theme: lightTheme,
          darkTheme: darkTheme,
          // themeMode: false ? ThemeMode.dark : ThemeMode.light,
          themeMode: ThemeMode.light,
          home: startWidget,
          // home: StartWidget(),
        ));
  }
}

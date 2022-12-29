import 'package:bloodify_front_end/layout/start_layout.dart';
import 'package:bloodify_front_end/modules/Chat/bloc/chat_service.dart';
import 'package:bloodify_front_end/modules/Chat/chat.dart';

import 'package:bloodify_front_end/modules/navbar/institution_navbar.dart';

import 'package:bloodify_front_end/modules/transactions_modules/event_transaction/cubit/eventTransaction_cubit.dart';
import 'package:bloodify_front_end/modules/transactions_modules/post_transaction/cubit/post_transaction_cubit.dart';
import 'package:bloodify_front_end/shared/Constatnt/userInfo.dart';

import 'package:bloodify_front_end/modules/create_event/create_event_cubit/create_event_cubit.dart';

import 'package:bloodify_front_end/shared/bloc_observer.dart';
import 'package:bloodify_front_end/shared/network/local/cach_helper.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
import 'package:bloodify_front_end/shared/styles/themes.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import 'layout/home_layout.dart';
import 'modules/login_UI/User_login/cubit/user_login_cubit.dart';
import 'modules/login_UI/institution_login/cubit/institution_login_cubit.dart';
import 'modules/navbar/navbar.dart';
import 'modules/signUP_UI/sign_up_State_management/sign_up_cubit.dart';
import 'modules/transactions_modules/institution_tranaction/cubic/institution_transaction_cubit.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await CachHelper.init();
  ChatService.init();
  // UserInfo.token = CachHelper.getData(key: 'token');
  UserInfo.token =
      'eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiZm9vIiwiaWF0IjoxNjcwMDkwMTU4fQ.PthvAwbYE70O8FDj0YjDRHqVwkn-OO-y54OIcUSEDByBmEumWZAYvO0lwpinv6aeJwpnpBEHLx2Jjeelap4Njv_8SaX4bWBSQJT8VZZlQGNEvdOWzMpr0tLtMMUsiBYqP03Qdlf7JkLR6jvfcfBZqFZQmZCt6IUbmNTPN7T64iZRoNsl2CL3DllwFcbdQzbTfHrDK8QZStxExrRCPOrbqq2kH0A30mtUv12cwtIwusVivLHTbdhB2VfLp9ZhYqKkR4sAiukmYAfOpP9IK1ioZuj5stuFLOQssWRLPlWHALeapd-Py4YCrTPThfSYN3ZlYQi9Z4EP6pDo1PLdcrRguQ';
  Widget widget;
  if (UserInfo.token != null) {
    // UserInfo.isUser = CachHelper.getData(key: 'isUser');
    UserInfo.isUser = true;

    if (UserInfo.isUser!) {
      widget = HomeLayout();
    } else {
      widget = const InstNavBar();
    }
  } else {
    widget = const StartWidget();
  }
  Bloc.observer = MyBlocObserver();
  DioHelper.init();
  runApp(MyApp(
    // startWidget: widget,
    startWidget: ChatScreen(
      postID: 1,
      donorID: 2,
      firstName: "Rick",
      lastName: "Astley",
      myID: 2,
    ),
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
        ));
  }
}

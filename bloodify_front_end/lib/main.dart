import 'package:bloodify_front_end/layout/start_layout.dart';
import 'package:bloodify_front_end/modules/BloodFinding/bloc/blood_finder_service.dart';

import 'package:bloodify_front_end/modules/navbar/institution_navbar.dart';
import 'package:bloodify_front_end/modules/notifications_history/notification_history.dart';

import 'package:bloodify_front_end/modules/transactions_modules/event_transaction/cubit/eventTransaction_cubit.dart';
import 'package:bloodify_front_end/modules/transactions_modules/post_transaction/cubit/post_transaction_cubit.dart';
import 'package:bloodify_front_end/shared/Constatnt/userInfo.dart';

import 'package:bloodify_front_end/modules/create_event/create_event_cubit/create_event_cubit.dart';

import 'package:bloodify_front_end/shared/bloc_observer.dart';
import 'package:bloodify_front_end/shared/network/local/cach_helper.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
import 'package:bloodify_front_end/shared/notification/notification_starter.dart';
import 'package:bloodify_front_end/shared/styles/themes.dart';
import 'package:firebase_messaging/firebase_messaging.dart';
import 'package:flutter/material.dart';

import 'package:flutter_bloc/flutter_bloc.dart';

import 'layout/home_layout.dart';
import 'modules/login_UI/User_login/cubit/user_login_cubit.dart';
import 'modules/login_UI/institution_login/cubit/institution_login_cubit.dart';
import 'modules/notifications_history/notification_history_cubit/notification_history_cubit.dart';
import 'modules/signUP_UI/sign_up_State_management/sign_up_cubit.dart';
import 'modules/transactions_modules/institution_tranaction/cubic/institution_transaction_cubit.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  NotificationIntalizor? notificationSystem = NotificationIntalizor.getObject();
  await notificationSystem?.start();

  UserInfo.deviceToken = await FirebaseMessaging.instance.getToken();
  await CachHelper.init();
  // CachHelper.removeData(key: 'token');
  // CachHelper.removeData(key: 'isUser');
  UserInfo.token = CachHelper.getData(key: 'token');
  Widget widget;

  if (UserInfo.token != null) {
    UserInfo.isUser = CachHelper.getData(key: 'isUser');

    if (UserInfo.isUser!) {
      UserInfo.location = await getLocation();
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
          BlocProvider(
              create: (context) =>
                  NotificationHistoryCubit()..getNotifications()),
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

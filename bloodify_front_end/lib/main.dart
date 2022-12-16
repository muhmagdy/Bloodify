import 'package:bloodify_front_end/layout/start_layout.dart';
import 'package:bloodify_front_end/modules/transactions_modules/event_transaction/cubit/eventTransaction_cubit.dart';
import 'package:bloodify_front_end/modules/transactions_modules/post_transaction/cubit/post_cubit.dart';
import 'package:bloodify_front_end/shared/Constatnt/login.dart';
import 'package:bloodify_front_end/shared/bloc_observer.dart';
import 'package:bloodify_front_end/shared/network/local/cach_helper.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
import 'package:bloodify_front_end/shared/styles/themes.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import 'layout/home_layout.dart';
import 'models/institution.dart';
import 'modules/login_UI/User_login/cubit/user_login_cubit.dart';
import 'modules/login_UI/institution_login/cubit/institution_login_cubit.dart';
import 'modules/signUP_UI/sign_up_State_management/sign_up_cubit.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await CachHelper.init();
  String? token = CachHelper.getData(key: 'token');
  Widget widget;
  if (token != null) {
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
    logedInInstitution =
        InstitutionData("Alexandria Hospital", "a.g@c", "dasdas");
    return MultiBlocProvider(
        providers: [
          BlocProvider(create: (context) => SignUpCubit()),
          BlocProvider(create: (context) => UserLoginCubit()),
          BlocProvider(create: (context) => InstitutionLoginCubit()),
          BlocProvider(create: (context) => PostCubit()),
          BlocProvider(create: (context) => EventTransactionCubit())
        ],
        child: MaterialApp(
          debugShowCheckedModeBanner: false,
          theme: lightTheme,
          darkTheme: darkTheme,
          // themeMode: false ? ThemeMode.dark : ThemeMode.light,
          themeMode: ThemeMode.light,
          // home: startWidget,
          home: HomeLayout(),
        ));
  }
}

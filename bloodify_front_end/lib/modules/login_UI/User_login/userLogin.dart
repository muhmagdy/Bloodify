import 'package:bloodify_front_end/layout/home_layout.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../shared/Constatnt/Component.dart';
import '../../../shared/Constatnt/sharedFunctions.dart';
import '../../../shared/Constatnt/userInfo.dart';
import '../../../shared/network/local/cach_helper.dart';
import '../shareable_login.dart';
import 'cubit/user_login_cubit.dart';
import 'cubit/user_states_login.dart';

// ignore: must_be_immutable
class UserLogin extends StatelessWidget {
  var loginKey = GlobalKey<FormState>();
  var emailController = TextEditingController();
  var passwordController = TextEditingController();

  UserLogin({super.key});
  @override
  Widget build(BuildContext context) {
    return BlocConsumer<UserLoginCubit, UserLoginStates>(
      listener: (context, state) {
        if (state is UserLoginSuccessState) {
          if (state.loginModel.status) {
            print(state.loginModel.message);
            print(state.loginModel.data!.token);
            CachHelper.saveData(key: "isUser", value: true)
                .then((value) => print("is User saved to Cache $value"));
            token = state.loginModel.data!.token;
            isUser = true;
            CachHelper.saveData(
              key: 'token',
              value: state.loginModel.data!.token,
            ).then((value) {
              navigateAndFinish(
                context,
                HomeLayout(),
              );
            });
          } else {
            print("             ");
            print(state.loginModel.message);
            showToast(
              text: state.loginModel.message,
              color: Colors.red,
              time: 1000,
            );
          }
        }
      },
      builder: (context, state) {
        double width = MediaQuery.of(context).size.width;
        double height = MediaQuery.of(context).size.height;
        return loginUI(
          context: context,
          state: state,
          isUser: true,
          firstFormController: emailController,
          loginKey: loginKey,
          passwordController: passwordController,
          width: width,
          height: height,
        );
      },
    );
  }
}

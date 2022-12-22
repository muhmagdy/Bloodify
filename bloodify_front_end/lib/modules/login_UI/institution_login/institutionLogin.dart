// ignore_for_file: must_be_immutable

import 'package:bloodify_front_end/layout/home_layout.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../shared/Constatnt/Component.dart';
import '../../../shared/Constatnt/sharedFunctions.dart';
import '../../../shared/Constatnt/userInfo.dart';
import '../../../shared/network/local/cach_helper.dart';
import '../shareable_login.dart';
import 'cubit/institution_login_cubit.dart';
import 'cubit/institution_states_login.dart';

class InstitutionLogin extends StatelessWidget {
  String token = UserInfo.token ?? "";
  bool isUser = UserInfo.isUser ?? true;
  var loginKey = GlobalKey<FormState>();
  var emailController = TextEditingController();
  var passwordController = TextEditingController();

  InstitutionLogin({super.key});
  @override
  Widget build(BuildContext context) {
    return BlocConsumer<InstitutionLoginCubit, InstitutionLoginStates>(
      listener: (context, state) {
        if (state is InstitutionLoginSuccessState) {
          if (state.loginModel.status) {
            print(state.loginModel.message);
            print(state.loginModel.data!.token);
            token = state.loginModel.data!.token;
            isUser = false;
            CachHelper.saveData(key: "isUser", value: false)
                .then((value) => print("is Institution saved to Cache $value"));
            token = state.loginModel.data!.token;
            isUser = false;
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
            print(state.loginModel.message);

            showToast(
              color: Colors.red,
              text: state.loginModel.message,
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
          isUser: false,
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

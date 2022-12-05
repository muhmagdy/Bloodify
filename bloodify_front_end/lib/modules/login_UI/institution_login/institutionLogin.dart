import 'package:bloodify_front_end/layout/home_layout.dart';
import 'package:conditional_builder_null_safety/conditional_builder_null_safety.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/container.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../shared/Constatnt/Component.dart';
import '../../../shared/Constatnt/sharedFunctions.dart';
import '../../../shared/network/local/cach_helper.dart';
import '../shareable_login.dart';
import 'cubit/institution_login_cubit.dart';
import 'cubit/institution_states_login.dart';

class InstitutionLogin extends StatelessWidget {
  var loginKey = GlobalKey<FormState>();
  var emailController = TextEditingController();
  var passwordController = TextEditingController();
  @override
  Widget build(BuildContext context) {
    return BlocConsumer<InstitutionLoginCubit, InstitutionLoginStates>(
      listener: (context, state) {
        if (state is InstitutionLoginSuccessState) {
          bool login_state = state.loginModel.status;
          if (state.loginModel.status) {
            print(state.loginModel.message);
            print(state.loginModel.data!.token);
            CachHelper.saveData(key: "isUser", value: false)
                .then((value) => print("is Institution saved to Cache $value"));
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
            print("      555       ");
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

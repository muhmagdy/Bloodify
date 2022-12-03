import 'package:bloodify_front_end/modules/login_UI/institution_login/cubit/institution_login_cubit.dart';
import 'package:bloodify_front_end/modules/signUP_UI/sign_up_pages/sign_up_1.dart';
import 'package:conditional_builder_null_safety/conditional_builder_null_safety.dart';
import 'package:flutter/material.dart';

import '../../shared/Constatnt/Component.dart';
import '../../shared/Constatnt/sharedFunctions.dart';
import 'User_login/cubit/user_login_cubit.dart';
import 'User_login/cubit/user_states_login.dart';
import 'institution_login/cubit/institution_states_login.dart';

Widget loginUI({
  required loginKey,
  required height,
  required width,
  required context,
  required bool isUser,
  required firstFormController,
  required passwordController,
  required state,
}) =>
    Scaffold(
      // backgroundColor: Colors.white,
      body: SingleChildScrollView(
        child: Form(
          key: loginKey,
          child: Column(children: [
            DefaultProgramPhoto(height: height, width: width),
            Container(
              margin: const EdgeInsets.only(left: 20, right: 20),
              child: Column(children: [
                SizedBox(height: 40),
                DefaultInputText(
                  controller: firstFormController,
                  labelText: isUser ? "Email" : "Institution Email",
                  prefix: Icons.email,
                  type: TextInputType.emailAddress,
                  validate: (String value) {
                    print(passwordController.text);
                    if (value.isEmpty) {
                      return 'email can\'t be empty';
                    }
                    if (!validateEmail(value)) {
                      return 'enter valid email';
                    }

                    return null;
                  },
                ),
                SizedBox(
                  height: 20,
                ),
                DefaultInputText(
                  controller: passwordController,
                  labelText: "password",
                  isPassword: isUser
                      ? UserLoginCubit.get(context).isPassword
                      : InstitutionLoginCubit().isPassword,
                  suffix: isUser
                      ? UserLoginCubit.get(context).suffix
                      : InstitutionLoginCubit.get(context).suffix,
                  suffixPressed: () =>
                      UserLoginCubit.get(context).changePasswordVisibility(),
                  prefix: Icons.lock,
                  validate: (String value) {
                    print(passwordController.text);
                    if (value.isEmpty) {
                      return 'password can\'t be empty';
                    }
                    if (value.length < 8) {
                      return 'password can\'t be less than 8';
                    }
                    return null;
                  },
                ),
                SizedBox(
                  height: 20,
                ),
                Row(
                  children: [
                    Expanded(child: Container()),
                    InkWell(
                      onTap: () {},
                      child: new Text(
                        "Forgotten password?",
                        style:
                            TextStyle(color: Color.fromARGB(255, 255, 78, 66)),
                      ),
                    )
                  ],
                )
              ]),
            ),
            SizedBox(
              height: 40,
            ),
            ConditionalBuilder(
              condition: (state is! UserLoginLoadingState && isUser) ||
                  (state is! InstitutionLoginLoadingState && !isUser),
              builder: (context) => DefaultButton(
                  onClick: () {
                    print(
                        "${firstFormController.text} ${passwordController.text}");
                    if (loginKey.currentState!.validate()) {
                      if (isUser) {
                        UserLoginCubit.get(context).userLogin(
                          email: firstFormController.text,
                          password: passwordController.text,
                        );
                      } else {
                        InstitutionLoginCubit.get(context).institutionLogin(
                          email: firstFormController.text,
                          password: passwordController.text,
                        );
                      }
                    } else {}
                  },
                  text: "LOGIN"),
              fallback: (context) => Center(child: CircularProgressIndicator()),
            ),
            SizedBox(
              height: 20,
            ),
            isUser
                ? Container(
                    margin: const EdgeInsets.only(left: 20, right: 20),
                    child: Row(
                      children: [
                        Expanded(child: Container()),
                        Text("Don't Have an account?"),
                        InkWell(
                          onTap: () {
                            navigateTo(context, SignUp1());
                          },
                          child: Text(
                            "sign up",
                            style: TextStyle(
                                color: Color.fromARGB(255, 255, 78, 66)),
                          ),
                        )
                      ],
                    ))
                : Container(),
          ]),
        ),
      ),
    );

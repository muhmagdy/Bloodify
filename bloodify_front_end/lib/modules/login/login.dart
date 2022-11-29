import 'package:conditional_builder_null_safety/conditional_builder_null_safety.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/container.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../shared/Constatnt/Component.dart';
import '../../shared/functions/sharedFunctions.dart';
import 'cubit/login_cubit.dart';
import 'cubit/states_login.dart';

class Login extends StatelessWidget {
  var loginKey = GlobalKey<FormState>();
  var emailController = TextEditingController();
  var passwordController = TextEditingController();
  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (BuildContext context) => LoginCubit(),
      child: BlocConsumer<LoginCubit, LoginStates>(
        listener: (context, state) {},
        builder: (context, state) {
          double width = MediaQuery.of(context).size.width;
          double height = MediaQuery.of(context).size.height;
          return Scaffold(
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
                        controller: emailController,
                        labelText: "Email",
                        prefix: Icons.email,
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
                        isPassword: LoginCubit.get(context).isPassword,
                        suffix: LoginCubit.get(context).suffix,
                        suffixPressed: () =>
                            LoginCubit.get(context).changePasswordVisibility(),
                        prefix: Icons.lock,
                        validate: (String value) {
                          print(passwordController.text);
                          if (value.isEmpty) {
                            return 'password can\'t be empty';
                          }
                          if (value.length < 6) {
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
                            onTap: () {
                              Navigator.pushNamed(context, 'SignUp(context)');
                            },
                            child: new Text(
                              "Forgotten password?",
                              style: TextStyle(
                                  color: Color.fromARGB(255, 255, 78, 66)),
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
                    condition: state is! LoginLoadingState,
                    builder: (context) => DefaultButton(
                        onClick: () {
                          print(
                              "${emailController.text} ${passwordController.text}");
                          if (loginKey.currentState!.validate()) {
                            LoginCubit.get(context).userLogin(
                              email: emailController.text,
                              password: passwordController.text,
                            );
                          } else {}
                        },
                        text: "LOGIN"),
                    fallback: (context) =>
                        Center(child: CircularProgressIndicator()),
                  ),
                  SizedBox(
                    height: 20,
                  ),
                  Container(
                      margin: const EdgeInsets.only(left: 20, right: 20),
                      child: Row(
                        children: [
                          Expanded(child: Container()),
                          Text("Don't Have an account?"),
                          InkWell(
                            onTap: () {
                              Navigator.pushNamed(context, "/signup");
                            },
                            child: Text(
                              "sign up",
                              style: TextStyle(
                                  color: Color.fromARGB(255, 255, 78, 66)),
                            ),
                          )
                        ],
                      ))
                ]),
              ),
            ),
          );
        },
      ),
    );
  }
}

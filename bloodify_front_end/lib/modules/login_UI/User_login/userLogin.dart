import 'package:bloodify_front_end/layout/home_layout.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../shared/Constatnt/Component.dart';
import '../../../shared/Constatnt/sharedFunctions.dart';
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
        // Scaffold(
        //   // backgroundColor: Colors.white,
        //   body: SingleChildScrollView(
        //     child: Form(
        //       key: loginKey,
        //       child: Column(children: [
        //         DefaultProgramPhoto(height: height, width: width),
        //         Container(
        //           margin: const EdgeInsets.only(left: 20, right: 20),
        //           child: Column(children: [
        //             SizedBox(height: 40),
        //             DefaultInputText(
        //               controller: emailController,
        //               labelText: "Email",
        //               prefix: Icons.email,
        //               type: TextInputType.emailAddress,
        //               validate: (String value) {
        //                 print(passwordController.text);
        //                 if (value.isEmpty) {
        //                   return 'email can\'t be empty';
        //                 }
        //                 if (!validateEmail(value)) {
        //                   return 'enter valid email';
        //                 }
        //                 return null;
        //               },
        //             ),
        //             SizedBox(
        //               height: 20,
        //             ),
        //             DefaultInputText(
        //               controller: passwordController,
        //               labelText: "password",
        //               isPassword: UserLoginCubit.get(context).isPassword,
        //               suffix: UserLoginCubit.get(context).suffix,
        //               suffixPressed: () => UserLoginCubit.get(context)
        //                   .changePasswordVisibility(),
        //               prefix: Icons.lock,
        //               validate: (String value) {
        //                 print(passwordController.text);
        //                 if (value.isEmpty) {
        //                   return 'password can\'t be empty';
        //                 }
        //                 if (value.length < 6) {
        //                   return 'password can\'t be less than 8';
        //                 }
        //                 return null;
        //               },
        //             ),
        //             SizedBox(
        //               height: 20,
        //             ),
        //             Row(
        //               children: [
        //                 Expanded(child: Container()),
        //                 InkWell(
        //                   onTap: () {
        //                     Navigator.pushNamed(context, 'SignUp(context)');
        //                   },
        //                   child: new Text(
        //                     "Forgotten password?",
        //                     style: TextStyle(
        //                         color: Color.fromARGB(255, 255, 78, 66)),
        //                   ),
        //                 )
        //               ],
        //             )
        //           ]),
        //         ),
        //         SizedBox(
        //           height: 40,
        //         ),
        //         ConditionalBuilder(
        //           condition: state is! UserLoginLoadingState,
        //           builder: (context) => DefaultButton(
        //               onClick: () {
        //                 print(
        //                     "${emailController.text} ${passwordController.text}");
        //                 if (loginKey.currentState!.validate()) {
        //                   UserLoginCubit.get(context).userLogin(
        //                     email: emailController.text,
        //                     password: passwordController.text,
        //                   );
        //                 } else {}
        //               },
        //               text: "LOGIN"),
        //           fallback: (context) =>
        //               Center(child: CircularProgressIndicator()),
        //         ),
        //         SizedBox(
        //           height: 20,
        //         ),
        //         Container(
        //             margin: const EdgeInsets.only(left: 20, right: 20),
        //             child: Row(
        //               children: [
        //                 Expanded(child: Container()),
        //                 Text("Don't Have an account?"),
        //                 InkWell(
        //                   onTap: () {
        //                     Navigator.pushNamed(context, "/signup");
        //                   },
        //                   child: Text(
        //                     "sign up",
        //                     style: TextStyle(
        //                         color: Color.fromARGB(255, 255, 78, 66)),
        //                   ),
        //                 )
        //               ],
        //             ))
        //       ]),
        //     ),
        //   ),
        // );
      },
    );
  }
}

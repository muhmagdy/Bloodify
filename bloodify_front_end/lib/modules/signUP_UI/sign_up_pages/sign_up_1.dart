import 'package:bloodify_front_end/modules/login_UI/User_login/userLogin.dart';
import 'package:bloodify_front_end/shared/Constatnt/nationalIDValidator.dart';
import 'package:bloodify_front_end/modules/signUP_UI/sign_up_pages/sign_up_2.dart';
import 'package:flutter/material.dart';
import 'package:bloodify_front_end/shared/Constatnt/Component.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:bloodify_front_end/shared/Constatnt/sharedFunctions.dart';
import 'package:bloodify_front_end/modules/signUP_UI/sign_up_pages/Languages.dart';

import '../sign_up_State_management/sign_up_cubit.dart';

// ignore: must_be_immutable
class SignUp1 extends StatelessWidget {
  final fNameController = TextEditingController();
  final lNameController = TextEditingController();
  final nationalIdController = TextEditingController();
  final mailController = TextEditingController();
  final passController = TextEditingController();
  final passConfirmingController = TextEditingController();
  NationalIDParser parser = EgyptNationalIDParser();
  Language language = EnglishLanguage();
  final _formKey = GlobalKey<FormState>();

  SignUp1({super.key});

  @override
  Widget build(BuildContext context) {
    fNameController.addListener(() {});
    lNameController.addListener(() {});
    mailController.addListener(() {});
    nationalIdController.addListener(() {});
    passController.addListener(() {});

    return BlocConsumer<SignUpCubit, SignUpStates>(
      listener: (context, state) {},
      builder: (context, state) {
        double width = MediaQuery.of(context).size.width;
        double height = MediaQuery.of(context).size.height;
        return Scaffold(
          backgroundColor: Colors.white,
          // resizeToAvoidBottomInset: false,
          body: Form(
            key: _formKey,
            child: SingleChildScrollView(
              child: Column(
                children: [
                  defaultProgramPhoto(height: height, width: width),
                  Container(
                    height: height * .95,
                    margin: const EdgeInsets.only(left: 20.0, right: 20.0),
                    child: Column(
                      children: [
                        Row(
                          children: [
                            Expanded(
                              child: defaultInputText(
                                  controller: fNameController,
                                  validate: (fName) {
                                    if (fName == null || fName == '') {
                                      return language.enterValue('fname');
                                    }
                                    if (!validateName(fName)) {
                                      return language.showInvalidValue("fname");
                                    }
                                    return null;
                                  },
                                  labelText: language.getLabel('fname'),
                                  prefix: Icons.perm_identity),
                            ),
                            const SizedBox(width: 10),
                            Expanded(
                              child: defaultInputText(
                                  controller: lNameController,
                                  validate: (lName) {
                                    if (lName == null || lName == '') {
                                      return language.enterValue('lname');
                                    }
                                    if (!validateName(lName)) {
                                      return language.showInvalidValue("lname");
                                    }
                                    return null;
                                  },
                                  labelText: language.getLabel('lname'),
                                  prefix: Icons.people_alt_outlined),
                            ),
                          ],
                        ),
                        const SizedBox(height: 10),
                        defaultInputText(
                            controller: nationalIdController,
                            validate: (number) {
                              if (number == null || number == '') {
                                return language.enterValue('natID');
                              }
                              if (!parser
                                  .validateNationalID(int.parse(number))) {
                                return language.showInvalidValue('natID');
                              }
                              return null;
                            },
                            labelText: language.getLabel('natID'),
                            type: TextInputType.number,
                            prefix: Icons.credit_card_rounded),
                        const SizedBox(height: 10),
                        defaultInputText(
                            controller: mailController,
                            validate: (String mail) {
                              if (mail.isEmpty) {
                                return language.enterValue('mail');
                              }
                              return validateEmail(mail)
                                  ? null
                                  : language.showInvalidValue('mail');
                            },
                            labelText: language.getLabel('mail'),
                            type: TextInputType.emailAddress,
                            prefix: Icons.mail_outline),
                        const SizedBox(height: 10),
                        defaultInputText(
                            controller: passController,
                            validate: (pass) {
                              if (pass == null || pass == '' || pass.length < 8) {
                                return language.enterPassword();
                              }
                              if (!validatePassword(pass)) {
                                return language.showInvalidPassword();
                              }
                              return null;
                            },
                            labelText: language.getLabel('pass'),
                            isPassword:
                                SignUpCubit.get(context).PasswordIsPassword,
                            suffix: SignUpCubit.get(context).PasswordSuffix,
                            suffixPressed: SignUpCubit.get(context)
                                .changePassWordVisibilityPass,
                            prefix: Icons.lock_outline_rounded),
                        const SizedBox(height: 10),
                        defaultInputText(
                            controller: passConfirmingController,
                            validate: (pass) => (pass == null ||
                                    pass == '' ||
                                    pass != passController.text)
                                ? language.showInvalidPasswordConfirm()
                                : null,
                            labelText: language.getLabel('passConfirm'),
                            isPassword: SignUpCubit.get(context)
                                .PasswordConfirmIsPassword,
                            prefix: Icons.lock_outline_rounded,
                            suffix:
                                SignUpCubit.get(context).PasswordConfirmSuffix,
                            suffixPressed: SignUpCubit.get(context)
                                .changePassWordVisibilityConfirm),
                        const SizedBox(height: 20),
                        defaultButton(
                            onClick: () {
                              if (_formKey.currentState!.validate()) {
                                SignUpCubit.get(context)
                                    .setSupposedDateOfBirth(parser);
                                SignUpCubit.get(context).user.fName =
                                    fNameController.text;
                                SignUpCubit.get(context).user.lName =
                                    lNameController.text;
                                SignUpCubit.get(context).user.password =
                                    passController.text;
                                SignUpCubit.get(context).user.nationalID =
                                    nationalIdController.text;
                                SignUpCubit.get(context).user.email =
                                    mailController.text;
                                navigateTo(context, SignUp2());
                              }
                            },
                            text: language.getLabel('next')),
                        const SizedBox(height: 10),
                        Padding(
                          padding: const EdgeInsets.symmetric(vertical: 10),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.end,
                            children: [
                              Text(
                                  language.getLabel("do you have an account?")),
                              TextButton(
                                  onPressed: () {
                                    navigateTo(context, UserLogin());
                                  },
                                  child: Text(
                                    language.getLabel('login'),
                                    style: const TextStyle(
                                        decoration: TextDecoration.underline,
                                        color:
                                            Color.fromARGB(255, 255, 78, 66)),
                                  ))
                            ],
                          ),
                        )
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ),
        );
      },
    );
  }
}

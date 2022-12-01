import 'package:bloodify_front_end/sign_up_pages/sign_up_2.dart';
import 'package:flutter/material.dart';
import 'package:bloodify_front_end/sign_up_State_management/sign_up_cubit.dart';
import 'package:bloodify_front_end/shared/Constatnt/Component.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:bloodify_front_end/shared/Constatnt/sharedFunctions.dart';

class SignUp1 extends StatelessWidget {
  final fNameController = TextEditingController();
  final lNameController = TextEditingController();
  final nationalIdController = TextEditingController();
  final mailController = TextEditingController();
  final passController = TextEditingController();
  final passConfirmingController = TextEditingController();
  final _formKey = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    fNameController.addListener(() {});
    lNameController.addListener(() {});
    mailController.addListener(() {});
    nationalIdController.addListener(() {});
    passController.addListener(() {});

    return BlocConsumer<SignUpCubit, SignUpStates>(
      listener: (context, state) {
        // TODO: implement listener
      },
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
                  DefaultProgramPhoto(height: height, width: width),
                  Container(
                    height: height * .8,
                    margin: EdgeInsets.only(left: 20.0, right: 20.0),
                    child: Column(
                      children: [
                        Row(
                          children: [
                            Expanded(
                              child: DefaultInputText(
                                  controller: fNameController,
                                  validate: (fName) =>
                                      fName != null && fName != ""
                                          ? null
                                          : "First Name Not Entered",
                                  labelText: 'First Name',
                                  prefix: Icons.perm_identity),
                            ),
                            SizedBox(width: 10),
                            Expanded(
                              child: DefaultInputText(
                                  controller: lNameController,
                                  validate: (lName) =>
                                      lName != null && lName != ""
                                          ? null
                                          : "Last Name Not Entered",
                                  labelText: 'Last Name',
                                  prefix: Icons.people_alt_outlined),
                            ),
                          ],
                        ),
                        SizedBox(height: 10),
                        DefaultInputText(
                            controller: nationalIdController,
                            validate: (number) {
                              if (number == null ||
                                  number == '' ||
                                  number.toString().length != 14) {
                                return 'Invalid National Id';
                              }
                              return null;
                            },
                            labelText: 'National ID',
                            type: TextInputType.number,
                            prefix: Icons.credit_card_rounded),
                        SizedBox(height: 10),
                        DefaultInputText(
                            controller: mailController,
                            validate: (String mail) {
                              if (mail.isEmpty)
                                return "Mail Address Can't be Empty";
                              return validateEmail(mail)
                                  ? null
                                  : "Invalid Mail Address";
                            },
                            labelText: 'Mail',
                            type: TextInputType.emailAddress,
                            prefix: Icons.mail_outline),
                        SizedBox(height: 10),
                        DefaultInputText(
                            controller: passController,
                            validate: (pass) => (pass == null || pass == '')
                                ? 'Invalid Password'
                                : null,
                            labelText: 'Password',
                            isPassword:
                                SignUpCubit.get(context).PasswordIsPassword,
                            suffix: SignUpCubit.get(context).PasswordSuffix,
                            suffixPressed: SignUpCubit.get(context)
                                .changePassWordVisibilityPass,
                            prefix: Icons.lock_outline_rounded),
                        SizedBox(height: 10),
                        DefaultInputText(
                            controller: passConfirmingController,
                            validate: (pass) => (pass == null ||
                                    pass == '' ||
                                    pass != passController.text)
                                ? 'Non Matching Passwords'
                                : null,
                            labelText: 'Password Confirmation',
                            isPassword: SignUpCubit.get(context)
                                .PasswordConfirmIsPassword,
                            prefix: Icons.lock_outline_rounded,
                            suffix:
                                SignUpCubit.get(context).PasswordConfirmSuffix,
                            suffixPressed: SignUpCubit.get(context)
                                .changePassWordVisibilityConfirm),
                        SizedBox(height: 20),
                        DefaultButton(
                            onClick: () {
                              if (_formKey.currentState!.validate()) {
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
                            text: 'Next'),
                        SizedBox(height: 10),
                        Padding(
                          padding: const EdgeInsets.symmetric(vertical: 10),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.end,
                            children: [
                              Text('Do You Have an Account?'),
                              TextButton(
                                  onPressed: () {},
                                  child: const Text(
                                    'Login!',
                                    style: TextStyle(
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

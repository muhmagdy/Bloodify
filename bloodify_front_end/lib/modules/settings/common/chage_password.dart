import 'package:bloodify_front_end/shared/network/local/cach_helper.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../shared/Constatnt/Component.dart';
import '../../../shared/Constatnt/sharedFunctions.dart';
import '../../../shared/network/remote/dio_helper.dart';
import 'change_password_cubit.dart';
import 'change_password_state.dart';

class ChangePassword extends StatefulWidget {
  String confirmationCode;
  ChangePassword(this.confirmationCode, {Key? key}) : super(key: key);

  @override
  State<ChangePassword> createState() => _ChangePasswordState();
}

class _ChangePasswordState extends State<ChangePassword> {
  final _formKey = GlobalKey<FormState>();
  final passController = TextEditingController();
  final passConfirmingController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    final height = MediaQuery.of(context).size.height;
    return BlocBuilder<ChangePasswordCubit, PasswordState>(
        builder: (context, state) {
      return Scaffold(
        appBar: AppBar(title: const Text("Change Password")),
        body: Form(
          key: _formKey,
          child: Column(
            children: [
              SizedBox(height: 0.02 * height),
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
                      ChangePasswordCubit.get(context).PasswordIsPassword,
                  suffix: ChangePasswordCubit.get(context).PasswordSuffix,
                  suffixPressed: ChangePasswordCubit.get(context)
                      .changePassWordVisibilityPass,
                  prefix: Icons.lock_outline_rounded),
              SizedBox(height: 0.02 * height),
              defaultInputText(
                  controller: passConfirmingController,
                  validate: (pass) => (pass == null ||
                          pass == '' ||
                          pass != passController.text)
                      ? language.showInvalidPasswordConfirm()
                      : null,
                  labelText: language.getLabel('passConfirm'),
                  isPassword: ChangePasswordCubit.get(context)
                      .PasswordConfirmIsPassword,
                  prefix: Icons.lock_outline_rounded,
                  suffix:
                      ChangePasswordCubit.get(context).PasswordConfirmSuffix,
                  suffixPressed: ChangePasswordCubit.get(context)
                      .changePassWordVisibilityConfirm),
              SizedBox(height: 0.02 * height),
              TextButton(
                onPressed: () {
                  // validating the input
                  if (_formKey.currentState!.validate()) {
                    // TODO: CONTACT THE BACKEND TO VALIDATE THE CODE
                    DioHelper.patchData(url: "password", data: {
                      'email': CachHelper.getData(key: 'email'),
                      'code': widget.confirmationCode,
                      'newPassword': passController.value.text
                    }).then((value) {
                      showToast(
                          text: value.data['message'],
                          color: Colors.blue,
                          time: 3000);
                      if (value.data['status'] == true) {
                        _onSubmit(context);
                      }
                      Navigator.pop(context);
                    }).catchError((error) => print(error));
                  }
                },
                child: const Text('Submit'),
              ),
            ],
          ),
        ),
      );
    });
  }

  void _onSubmit(BuildContext context) {
    Navigator.pop(context);
    Navigator.pop(context);
  }
}

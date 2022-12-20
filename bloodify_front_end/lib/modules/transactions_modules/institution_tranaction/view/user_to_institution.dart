import 'package:conditional_builder_null_safety/conditional_builder_null_safety.dart';
import 'package:dropdown_button2/custom_dropdown_button2.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../../shared/Constatnt/Component.dart';
import '../cubic/institution_transaction_cubit.dart';
import '../cubic/institution_transaction_states.dart';

// ignore: must_be_immutable
class UserToInstitute extends StatelessWidget {
  UserToInstitute({super.key});
  var fromUserContoller = TextEditingController();
  final _formKey = GlobalKey<FormState>();

  Widget build(BuildContext context) {
    var cubit = InstituteTransactionCubit.get(context);
    return BlocConsumer<InstituteTransactionCubit, InstituteTransactionStates>(
        listener: (context, state) {},
        builder: (context, state) {
          return SingleChildScrollView(
            child: Form(
                key: _formKey,
                child: Column(
                  children: [
                    defaultInputText(
                        controller: fromUserContoller,
                        validate: (value) {
                          return nationalValidate(value);
                        },
                        labelText: "Donar nationalId"),
                    const SizedBox(
                      height: 20,
                    ),
                    SizedBox(
                      height: 65,
                      width: double.infinity,
                      child: CustomDropdownButton2(
                        dropdownWidth: 175,
                        hint: 'Select Item',
                        dropdownItems: cubit.types,
                        value: cubit.types[cubit.currentBloodType],
                        onChanged: (newValue) {
                          cubit.changeBloodType(newValue!);
                        },
                      ),
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    ConditionalBuilder(
                        condition: state
                            is! InstituteTransactionUserToInstituteLoading,
                        builder: (context) {
                          return defaultButton(
                              onClick: () {
                                if (_formKey.currentState!.validate()) {
                                  cubit.submitUsertoInstitute(
                                      id: fromUserContoller.text);
                                }
                              },
                              text: "Submit",
                              merginLeft: 0.0,
                              merginRight: 0.0);
                        },
                        fallback: (context) {
                          return const CircularProgressIndicator();
                        }),
                  ],
                )),
          );
        });
  }
}

import 'package:bloodify_front_end/shared/Constatnt/Component.dart';
import 'package:conditional_builder_null_safety/conditional_builder_null_safety.dart';
import 'package:dropdown_button2/custom_dropdown_button2.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/container.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_spinbox/material.dart'; // or flutter_spinbox.dart for both

import '../cubic/institution_transaction_cubit.dart';
import '../cubic/institution_transaction_states.dart';

// ignore: must_be_immutable
class InstituteToUser extends StatelessWidget {
  InstituteToUser({super.key});
  var toUserContoller = TextEditingController();
  final _formKey = GlobalKey<FormState>();

  Widget build(BuildContext context) {
    var cubit = InstituteTransactionCubit.get(context);
    return BlocConsumer<InstituteTransactionCubit, InstituteTransactionStates>(
      listener: (context, state) {},
      builder: (context, state) {
        double width = MediaQuery.of(context).size.width;

        return SingleChildScrollView(
          child: Form(
              key: _formKey,
              child: Column(
                children: [
                  defaultInputText(
                      controller: toUserContoller,
                      validate: (value) {
                        return nationalValidate(value);
                      },
                      labelText: "Acceptor nationalId"),
                  const SizedBox(
                    height: 20,
                  ),
                  Row(
                    children: [
                      SizedBox(
                        width: (width - 75) / 2,
                        height: 63,
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
                        width: 35,
                      ),
                      SizedBox(
                        width: (width - 75) / 2,
                        child: SpinBox(
                          decoration:
                              const InputDecoration(labelText: "blood Bags"),
                          min: 1,
                          max: 4,
                          value: cubit.bloodBags,
                          onChanged: (value) =>
                              {cubit.changeBloodBagsCount(value)},
                        ),
                      ),
                    ],
                  ),
                  const SizedBox(
                    height: 20,
                  ),
                  ConditionalBuilder(
                      condition:
                          state is! InstituteTransactionInstituteToUserLoading,
                      builder: (context) {
                        return defaultButton(
                            onClick: () {
                              if (_formKey.currentState!.validate()) {
                                cubit.submitInstitutetoUser(
                                    id: toUserContoller.text);
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
      },
    );
  }

  // @override
  // Widget build(BuildContext context) {
  //   var cubit = InstituteTransactionCubit.get(context);
  //   double width = MediaQuery.of(context).size.width;
  //   return SingleChildScrollView(
  //     child: Form(
  //         child: Column(
  //       children: [
  //         defaultInputText(
  //             controller: toUserContoller,
  //             validate: (value) {
  //               return nationalValidate(value);
  //             },
  //             labelText: "Acceptor nationalId"),
  //         const SizedBox(
  //           height: 20,
  //         ),
  //         Row(
  //           children: [
  //             SizedBox(
  //               width: (width - 75) / 2,
  //               height: 63,
  //               child: CustomDropdownButton2(
  //                 dropdownWidth: 175,
  //                 hint: 'Select Item',
  //                 dropdownItems: cubit.types,
  //                 value: cubit.types[cubit.currentBloodType],
  //                 onChanged: (newValue) {
  //                   cubit.changeBloodType(newValue!);
  //                 },
  //               ),
  //             ),
  //             const SizedBox(
  //               width: 35,
  //             ),
  //             SizedBox(
  //               width: (width - 75) / 2,
  //               child: SpinBox(
  //                 decoration: const InputDecoration(labelText: "blood Bags"),
  //                 min: 1,
  //                 max: 4,
  //                 value: cubit.bloodBags,
  //                 onChanged: (value) => {cubit.changeBloodBagsCount(value)},
  //               ),
  //             ),
  //           ],
  //         ),
  //         const SizedBox(
  //           height: 20,
  //         ),
  //         defaultButton(
  //             onClick: () {},
  //             text: "Submit",
  //             merginLeft: 0.0,
  //             merginRight: 0.0),
  //       ],
  //     )),
  //   );
  // }
}

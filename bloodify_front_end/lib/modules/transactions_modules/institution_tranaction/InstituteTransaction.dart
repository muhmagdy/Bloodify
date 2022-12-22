// ignore: file_names
import 'package:bloodify_front_end/modules/transactions_modules/institution_tranaction/cubic/institution_transaction_cubit.dart';
import 'package:dropdown_button2/custom_dropdown_button2.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../shared/Constatnt/Component.dart';
import 'cubic/institution_transaction_states.dart';

// ignore: must_be_immutable
class InstituteTransaction extends StatelessWidget {
  InstituteTransaction({super.key});
  var transactionsContoller = TextEditingController();
  @override
  Widget build(BuildContext context) {
    var cubit = InstituteTransactionCubit.get(context);
    return BlocConsumer<InstituteTransactionCubit, InstituteTransactionStates>(
      listener: (context, state) {
        if (state is InstituteTransactionInstituteToUserSuccess ||
            state is InstituteTransactionUserToInstituteSuccess) {
          showSuccessToast(state);
        }
      },
      builder: (context, state) {
        double width = MediaQuery.of(context).size.width;
        double height = MediaQuery.of(context).size.height;
        return SingleChildScrollView(
          child: Container(
            margin: const EdgeInsets.only(left: 20, right: 20),
            child: Column(
              mainAxisSize: MainAxisSize.min,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                defaultProgramPhoto(width: width, height: height * .8),
                const SizedBox(
                  height: 20,
                ),
                SizedBox(
                  height: 50.0,
                  width: 175.0,
                  child: CustomDropdownButton2(
                    dropdownWidth: 175,
                    hint: 'Select Item',
                    dropdownItems: cubit.transactions,
                    value: cubit.transactions[cubit.currentIndex],
                    onChanged: (newValue) {
                      if (newValue == "User To Institute") {
                        cubit.changeScreen(0);
                      } else if (newValue == "Institute To User") {
                        cubit.changeScreen(1);
                      }
                    },
                  ),
                ),
                const SizedBox(
                  height: 20,
                ),
                cubit.screens[cubit.currentIndex],
              ],
            ),
          ),
        );
      },
    );
  }

  void showSuccessToast(state) {
    if (state.resp.state) {
      showToast(text: state.resp.message, color: Colors.green, time: 1000);
    } else {
      showToast(text: state.resp.message, color: Colors.red, time: 1000);
    }
  }
}

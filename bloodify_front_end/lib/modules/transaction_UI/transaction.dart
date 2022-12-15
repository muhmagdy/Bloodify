import 'package:dropdown_button2/custom_dropdown_button2.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../shared/Constatnt/Component.dart';
import 'cubit/transaction_cubit.dart';
import 'cubit/transaction_state.dart';

enum Status { userToUser, userToInstitution, institutionToInstitution }

// ignore: must_be_immutable
class Transaction extends StatelessWidget {
  Transaction({super.key});

  var transactionsContoller = TextEditingController();
  @override
  Widget build(BuildContext context) {
    var cubit = TransactionCubit.get(context);
    // print(transactions);

    return BlocConsumer<TransactionCubit, TransactionStates>(
      listener: (context, state) {},
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
                      if (newValue == "User to User") {
                        cubit.changeScreen(0);
                      } else if (newValue == "User to Institutions") {
                        cubit.changeScreen(1);
                      } else {
                        cubit.changeScreen(2);
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
        // Scaffold(
        //   appBar: AppBar(
        //     title: Row(children: [
        //       ClipRRect(
        //         borderRadius: BorderRadius.circular(70),
        //         child: Container(
        //           width: 40,
        //           height: 40,
        //           decoration: const BoxDecoration(
        //               color: Colors.white,
        //               image: DecorationImage(
        //                   image: AssetImage(
        //                       'assets/icons/blood-removebg-preview.ico'),
        //                   fit: BoxFit.contain)),
        //         ),
        //       ),
        //       const SizedBox(
        //         width: 10,
        //       ),
        //       const Text(
        //         'Home',
        //         style: TextStyle(color: Colors.white),
        //       )
        //     ]),
        //   ),
        //   body: cubit.screens[cubit.currentIndex],

        // );
      },
    );
  }
}

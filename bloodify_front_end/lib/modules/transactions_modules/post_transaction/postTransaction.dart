// ignore_for_file: prefer_typing_uninitialized_variables, file_names

import 'package:bloodify_front_end/modules/transactions_modules/post_transaction/cubit/post_cubit.dart';
import 'package:bloodify_front_end/modules/transactions_modules/post_transaction/cubit/post_state.dart';
import 'package:dropdown_button2/custom_dropdown_button2.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../shared/Constatnt/Component.dart';

// ignore: must_be_immutable
class PostTransaction extends StatelessWidget {
  final post;
  PostTransaction({super.key, this.post});

  var transactionsContoller = TextEditingController();
  @override
  Widget build(BuildContext context) {
    var cubit = PostCubit.get(context);
    // print(transactions);
    cubit.post = post;
    return BlocConsumer<PostCubit, PostStates>(
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
                      } else if (newValue == "Institution to user") {
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

// ignore_for_file: prefer_typing_uninitialized_variables, file_names

import 'package:bloodify_front_end/models/postBrief.dart';
import 'package:bloodify_front_end/modules/transactions_modules/post_transaction/cubit/post_transaction_cubit.dart';
import 'package:bloodify_front_end/modules/transactions_modules/post_transaction/cubit/post_transaction_state.dart';
import 'package:bloodify_front_end/modules/transactions_modules/post_transaction/user_to_user/userToUser_module.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../shared/Constatnt/Component.dart';

// ignore: must_be_immutable
class PostTransaction extends StatelessWidget {
  final PostBrief post;
  PostTransaction(this.post, {super.key});

  var transactionsContoller = TextEditingController();
  @override
  Widget build(BuildContext context) {
    var cubit = PostTransactionCubit.get(context);
    // print(transactions);
    cubit.post = post;
    return BlocConsumer<PostTransactionCubit, PostTransactionStates>(
      listener: (context, state) {},
      builder: (context, state) {
        double width = MediaQuery.of(context).size.width;
        double height = MediaQuery.of(context).size.height;
        return Scaffold(
          body: Container(
            margin: const EdgeInsets.only(left: 20, right: 20),
            child: Column(
              mainAxisSize: MainAxisSize.min,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                defaultProgramPhoto(width: width, height: height * .8),
                const SizedBox(
                  height: 20,
                ),
                const SizedBox(
                  height: 20,
                ),
                UserToUser(),
              ],
            ),
          ),
        );
      },
    );
  }
}

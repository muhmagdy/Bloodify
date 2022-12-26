// ignore_for_file: must_be_immutable

import 'package:bloodify_front_end/models/postBrief.dart';
import 'package:conditional_builder_null_safety/conditional_builder_null_safety.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../../models/post_model.dart';
import '../../../../shared/Constatnt/Component.dart';
import '../cubit/post_transaction_cubit.dart';
import '../cubit/post_transaction_state.dart';

class UserToUser extends StatelessWidget {
  UserToUser({super.key});
  final toController = TextEditingController();
  final fromController = TextEditingController();
  final formKey = GlobalKey<FormState>();
  @override
  Widget build(BuildContext context) {
    return BlocConsumer<PostTransactionCubit, PostTransactionStates>(
        listener: (context, state) {},
        builder: (context, state) {
          PostBrief post = PostTransactionCubit.get(context).post;
          toController.text = post.nationalID;
          return Form(
            key: formKey,
            child: Column(
              children: [
                defaultInputText(
                    type: TextInputType.number,
                    prefix: Icons.credit_card_rounded,
                    controller: fromController,
                    validate: (id) => nationalValidate(id),
                    labelText: "donor national-ID"),
                const SizedBox(
                  height: 20,
                ),
                defaultInputText(
                    isClickable: false,
                    type: TextInputType.number,
                    prefix: Icons.credit_card_rounded,
                    controller: toController,
                    validate: (id) => null,
                    labelText: "acceptor user-id"),
                const SizedBox(
                  height: 20,
                ),
                ConditionalBuilder(
                    condition: state is! PostTransactionLoadingState,
                    builder: (context) {
                      return defaultButton(
                        onClick: () {
                          if (formKey.currentState!.validate()) {
                            PostTransactionCubit.get(context)
                                .postTransaction(fromController.text);
                          } else {}
                        },
                        text: "Submit",
                        merginLeft: 0.0,
                        merginRight: 0.0,
                      );
                    },
                    fallback: (context) {
                      return const CircularProgressIndicator();
                    }),
              ],
            ),
          );
        });
  }
}

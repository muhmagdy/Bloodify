// ignore_for_file: must_be_immutable

import 'package:bloodify_front_end/models/postBrief.dart';
import 'package:conditional_builder_null_safety/conditional_builder_null_safety.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../../models/post_model.dart';
import '../../../../shared/Constatnt/Component.dart';
import '../cubit/post_transaction_cubit.dart';
import '../cubit/post_transaction_state.dart';

class UserToUser extends StatelessWidget {
  UserToUser({super.key});
  var toController = TextEditingController();

  // final formKey = GlobalKey<FormState>();
  @override
  Widget build(BuildContext context) {
    return BlocConsumer<PostTransactionCubit, PostTransactionStates>(
        listener: (context, state) {
      if (state is PostTransactionSuccessState) {
        Navigator.pop(context);
        PostTransactionCubit.get(context).formKey.currentState?.reset();
        PostTransactionCubit.get(context).fromController.clear();
      }
    }, builder: (context, state) {
      PostBrief post = PostTransactionCubit.get(context).post;
      toController.text = post.nationalID;
      return Form(
        key: PostTransactionCubit.get(context).formKey,
        autovalidateMode: AutovalidateMode.onUserInteraction,
        child: Column(
          children: [
            TextFormField(
                autofocus: true,
                keyboardType: TextInputType.number,
                decoration: const InputDecoration(
                  labelText: "donor national-ID",
                  prefixIcon: Icon(
                    Icons.credit_card_rounded,
                    color: Color.fromARGB(255, 255, 78, 66),
                  ),
                ),
                controller: PostTransactionCubit.get(context).fromController,
                validator: (id) => nationalValidate(id),
                // labelText: "donor national-ID",
                inputFormatters: [FilteringTextInputFormatter.digitsOnly]),
            const SizedBox(
              height: 20,
            ),
            defaultInputText(
                isClickable: false,
                type: TextInputType.number,
                prefix: Icons.credit_card_rounded,
                controller: toController,
                validate: (id) => null,
                labelText: "acceptor national-ID"),
            const SizedBox(
              height: 20,
            ),
            if (state is PostTransactionIntialState ||
                state is PostTransactionErrorState ||
                state is PostTransactionSuccessState)
              defaultButton(
                onClick: () {
                  if (PostTransactionCubit.get(context)
                          .formKey
                          .currentState
                          ?.validate() ??
                      false) {
                    PostTransactionCubit.get(context).postTransaction();
                  }
                },
                text: "Submit",
                merginLeft: 0.0,
                merginRight: 0.0,
              ),
            if (state is PostTransactionLoadingState)
              const CircularProgressIndicator()
          ],
        ),
      );
    });
  }
}

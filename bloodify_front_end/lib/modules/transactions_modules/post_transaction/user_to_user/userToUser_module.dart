// ignore_for_file: must_be_immutable

import 'package:bloodify_front_end/shared/Constatnt/sharedFunctions.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/container.dart';
import 'package:flutter/src/widgets/framework.dart';

import '../../../../models/post_model.dart';
import '../../../../shared/Constatnt/Component.dart';
import '../cubit/post_cubit.dart';

class UserToUser extends StatelessWidget {
  UserToUser({super.key});
  final toController = TextEditingController();
  final fromController = TextEditingController();
  final formKey = GlobalKey<FormState>();
  @override
  Widget build(BuildContext context) {
    Post post = PostCubit.get(context).post;
    toController.text = post.user_ID;
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
          defaultButton(
            onClick: () {
              if (formKey.currentState!.validate()) {
              } else {}
            },
            text: "Submit",
            merginLeft: 0.0,
            merginRight: 0.0,
          ),
        ],
      ),
    );
  }
}

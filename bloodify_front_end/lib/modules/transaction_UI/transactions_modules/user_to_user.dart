import 'package:bloodify_front_end/shared/Constatnt/Component.dart';
import 'package:flutter/material.dart';

// ignore: must_be_immutable
class TransactionUserToUser extends StatelessWidget {
  var fromController = TextEditingController();
  var toController = TextEditingController();

  var formKey = GlobalKey<FormState>();

  TransactionUserToUser({super.key});

  @override
  Widget build(BuildContext context) {
    return Form(
      key: formKey,
      child: Column(
        children: [
          defaultInputText(
              type: TextInputType.number,
              prefix: Icons.credit_card_rounded,
              controller: fromController,
              validate: (id) => nationalValidate(id),
              labelText: "donor user-id"),
          const SizedBox(
            height: 20,
          ),
          defaultInputText(
              type: TextInputType.number,
              prefix: Icons.credit_card_rounded,
              controller: toController,
              validate: (id) => nationalValidate(id),
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

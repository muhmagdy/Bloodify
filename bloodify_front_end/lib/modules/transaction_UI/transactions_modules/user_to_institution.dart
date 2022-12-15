import 'package:flutter/material.dart';

import '../../../shared/Constatnt/Component.dart';

// ignore: must_be_immutable
class TransactionUserToInstitution extends StatelessWidget {
  var fromController = TextEditingController();
  var formKey = GlobalKey<FormState>();

  TransactionUserToInstitution({super.key});

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

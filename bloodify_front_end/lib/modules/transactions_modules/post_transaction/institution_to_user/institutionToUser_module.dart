// ignore_for_file: file_names

import 'package:bloodify_front_end/modules/transactions_modules/post_transaction/cubit/post_cubit.dart';
import 'package:bloodify_front_end/shared/Constatnt/login.dart';
import 'package:conditional_builder_null_safety/conditional_builder_null_safety.dart';
import 'package:flutter/material.dart';

import '../../../../models/post_model.dart';
import '../../../../shared/Constatnt/Component.dart';

class InstitutionToUser extends StatelessWidget {
  // ignore: prefer_typing_uninitialized_variables

  InstitutionToUser({
    super.key,
  });
  Widget buildSelectionItem({available, index}) {
    return InkWell(
        onTap: () {},
        child: Container(
          width: 54,
          height: 54,
          decoration: BoxDecoration(
              border: Border.all(
            color: Colors.grey,
            width: 5,
          )),
          child: Row(children: [
            const SizedBox(width: 20),
            Column(
              children: [
                const SizedBox(
                  height: 5,
                ),
                const Text("Blood Type"),
                Text(available.type),
              ],
            ),
            const SizedBox(
              width: 70,
            ),
            Column(
              children: [
                const SizedBox(
                  height: 5,
                ),
                const Text("number of bags"),
                Text((available.number_of_bags.toString())),
              ],
            ),
          ]),
        ));
  }

  final toController = TextEditingController();
  final fromController = TextEditingController();
  final formKey = GlobalKey<FormState>();
  @override
  Widget build(BuildContext context) {
    Post post = PostCubit.get(context).post;
    var avilable = PostCubit.get(context).l;

    toController.text = post.user_ID;
    fromController.text = logedInInstitution.email;
    return Form(
      key: formKey,
      child: Column(
        children: [
          ConditionalBuilder(
            condition: !avilable.isNotEmpty,
            builder: (context) => Column(
              children: [
                defaultInputText(
                    type: TextInputType.number,
                    prefix: Icons.credit_card_rounded,
                    controller: fromController,
                    validate: (id) => null,
                    isClickable: false,
                    labelText: "Institution Email"),
                const SizedBox(
                  height: 10,
                ),
                defaultInputText(
                    isClickable: false,
                    type: TextInputType.number,
                    prefix: Icons.credit_card_rounded,
                    controller: toController,
                    validate: (id) => null,
                    labelText: "acceptor user-id"),
                const SizedBox(
                  height: 10,
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
            fallback: (context) => Padding(
              padding: const EdgeInsets.all(20),
              child: ListView.separated(
                  physics: const NeverScrollableScrollPhysics(),
                  shrinkWrap: true,
                  itemBuilder: (context, index) => buildSelectionItem(
                      available: avilable[index], index: index),
                  separatorBuilder: (context, index) => const SizedBox(
                        height: 10,
                      ),
                  itemCount: avilable.length),
            ),
          ),
        ],
      ),
    );
  }
}

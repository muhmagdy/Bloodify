import 'package:bloodify_front_end/modules/transactions_modules/event_transaction/cubit/eventTransaction_cubit.dart';
import 'package:bloodify_front_end/modules/transactions_modules/event_transaction/cubit/eventTransaction_states.dart';
import 'package:dropdown_button2/custom_dropdown_button2.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../shared/Constatnt/Component.dart';

class EventTransaction extends StatelessWidget {
  var event;
  var fromController = TextEditingController();
  var formKey = GlobalKey<FormState>();
  var toController = TextEditingController();

  EventTransaction({super.key, required this.event});
  @override
  Widget build(BuildContext context) {
    toController.text = event.title;
    var cubit = EventTransactionCubit().get(context);
    cubit.event = event;
    return BlocConsumer<EventTransactionCubit, EventTransactionStates>(
      listener: (context, state) {},
      builder: (context, state) {
        double width = MediaQuery.of(context).size.width;
        double height = MediaQuery.of(context).size.height;
        return Form(
          key: formKey,
          child: SingleChildScrollView(
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
                      type: TextInputType.number,
                      prefix: Icons.title_rounded,
                      controller: toController,
                      validate: (val) => null,
                      isClickable: false,
                      labelText: "Event title"),
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

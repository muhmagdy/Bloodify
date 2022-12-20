import 'package:bloodify_front_end/layout/start_layout.dart';
import 'package:bloodify_front_end/models/event_model.dart';
import 'package:bloodify_front_end/modules/transactions_modules/post_transaction/postTransaction.dart';
import 'package:bloodify_front_end/shared/network/local/cach_helper.dart';
import 'package:flutter/material.dart';
import '../models/post_model.dart';
import '../modules/transactions_modules/institution_tranaction/InstituteTransaction.dart';
import '../shared/Constatnt/sharedFunctions.dart';

// ignore: must_be_immutable
class HomeLayout extends StatelessWidget {
  HomeLayout({super.key});
  var post = Post(1, "3010010152045", 4, "A.con");
  var event = Event_model(
      title: "tbor3",
      to_date: DateTime.now(),
      from_date: DateTime.now(),
      event_ID: 1);
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: PostTransaction(
        post: post,
      ),
      appBar: AppBar(
        backgroundColor: const Color.fromARGB(255, 255, 78, 66),
        title: Row(children: [
          ClipRRect(
            borderRadius: BorderRadius.circular(70),
            child: Container(
              width: 40,
              height: 40,
              decoration: const BoxDecoration(
                  color: Colors.white,
                  image: DecorationImage(
                      image:
                          AssetImage('assets/icons/blood-removebg-preview.ico'),
                      fit: BoxFit.contain)),
            ),
          ),
          const SizedBox(
            width: 10,
          ),
          const Text(
            'Home',
            style: TextStyle(color: Colors.white),
          )
        ]),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          CachHelper.removeData(
            key: 'token',
          ).then((value) {
            if (value) {
              navigateAndFinish(
                context,
                const StartWidget(),
              );
            }
          });
          CachHelper.removeData(
            key: 'isUser',
          ).then((value) {
            if (value) {
              navigateAndFinish(
                context,
                const StartWidget(),
              );
            }
          });
        },
        backgroundColor: Colors.red,
        child: const Icon(Icons.logout_rounded),
      ),
    );
  }
}

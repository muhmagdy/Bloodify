import 'package:bloodify_front_end/models/transaction.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../../shared/Constatnt/fonts.dart';

class PostTransactionWidget extends StatelessWidget {
  final Transaction transaction;
  const PostTransactionWidget(this.transaction, {super.key});

  @override
  Widget build(BuildContext context) {
    final width = MediaQuery.of(context).size.width;
    final height = MediaQuery.of(context).size.height;
    return Scaffold(
      appBar: AppBar(
        toolbarHeight: 0.07 * height,
        backgroundColor: blue,
        centerTitle: true,
        title: Text(
          "Request Transaction",
          style: AppHeaderStyle(height, Colors.white),
        ),
      ),
      body: Text(
        "Transaction #${transaction.id}",
        textAlign: TextAlign.center,
        style: NormalStyle(height, Colors.black),
      ),
    );
  }
}

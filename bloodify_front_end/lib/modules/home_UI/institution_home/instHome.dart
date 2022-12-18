import 'package:bloodify_front_end/models/transaction.dart';
import 'package:bloodify_front_end/modules/home_UI/institution_home/widgets/transactionTile.dart';
import 'package:bloodify_front_end/shared/Constatnt/fonts.dart';
import 'package:flutter/material.dart';

import '../../../shared/Constatnt/colors.dart';

class InstitutionHome extends StatefulWidget {
  @override
  State<InstitutionHome> createState() => _InstitutionHome();
}

class _InstitutionHome extends State<InstitutionHome> {
  @override
  Widget build(BuildContext context) {
    final isCurrent = true;
    final transactions = [
      Transaction(36672, "Karim Alaa", "30101010109876",
          DateTime(2022, 12, 20, 13, 30), 1, "AB+", "Pending"),
      Transaction(56453, "Loai Magdy", "30101010109876",
          DateTime(2022, 12, 20, 13, 30), 1, "A-", "Pending"),
      Transaction(78742, "Mohamed Magdy", "30101015555555",
          DateTime(2022, 12, 20, 13, 30), 2, "O+", "Pending"),
      Transaction(12546, "Youhanna Yousi", "30101010109876",
          DateTime(2022, 12, 20, 13, 30), 1, "B-", "Pending"),
      Transaction(89545, "Youssef Saber", "30101010109876",
          DateTime(2022, 12, 20, 13, 30), 5, "AB-", "Pending"),
      Transaction(12584, "Youssef Magdy", "30101010109876",
          DateTime(2022, 12, 20, 13, 30), 2, "B+", "Pending"),
      Transaction(79846, "Someone Else", "30101010109876",
          DateTime(2022, 12, 20, 13, 30), 3, "O-", "Pending"),
    ];
    final transaction = Transaction(36672, "Karim Alaa", "30101010109876",
        DateTime(2022, 12, 20, 13, 30), 2, "AB+", "Pending");
    final double width = MediaQuery.of(context).size.width;
    final height = MediaQuery.of(context).size.height;
    return Container(
        color: blue,
        child: SafeArea(
            child: Scaffold(
          appBar: AppBar(
            toolbarHeight: 0.07 * height,
            backgroundColor: blue,
            centerTitle: true,
            title: Text(
              "Home",
              style: AppHeaderStyle(height, Colors.white),
            ),
          ),
          body: Container(
            padding: EdgeInsets.only(left: 0.02 * width, right: 0.02 * width),
            child: RefreshIndicator(
                triggerMode: RefreshIndicatorTriggerMode.anywhere,
                color: blue,
                onRefresh: _pullRefresh,
                child: ListView(
                  physics: const ClampingScrollPhysics(
                      parent: AlwaysScrollableScrollPhysics()),
                  children: [
                    Text(
                      isCurrent
                          ? "Current Transactions"
                          : "Previous Transactions",
                      style: HeadingStyle(height, grey),
                    ),
                    // TransactionTile(transaction)
                    ListView.builder(
                        physics: const NeverScrollableScrollPhysics(),
                        shrinkWrap: true,
                        itemCount: transactions.length,
                        itemBuilder: (context, index) =>
                            TransactionTile(transactions[index])),
                    Container(height: 0.03 * height)
                  ],
                )),
          ),
        )));
  }

  Future<void> _pullRefresh() async {
    setState(() {});
  }
}

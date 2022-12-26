import 'package:bloodify_front_end/models/postBrief.dart';
import 'package:bloodify_front_end/models/transaction.dart';
import 'package:bloodify_front_end/modules/home_UI/institution_home/widgets/transactionTile.dart';
import 'package:bloodify_front_end/shared/Constatnt/fonts.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';

import '../../../shared/Constatnt/colors.dart';

class InstitutionHome extends StatefulWidget {
  @override
  State<InstitutionHome> createState() => _InstitutionHome();
}

class _InstitutionHome extends State<InstitutionHome> {
  var isCurrent = true;
  var posts = <PostBrief>[];

  _InstitutionHome() {
    _pullRefresh();
  }

  @override
  Widget build(BuildContext context) {
    final double width = MediaQuery.of(context).size.width;
    final height = MediaQuery.of(context).size.height;
    return Container(
        color: blue,
        child: SafeArea(
            child: Scaffold(
          appBar: AppBar(
              title: Row(
            children: [
              ClipRRect(
                borderRadius: BorderRadius.circular(70),
                child: Container(
                  width: 40,
                  height: 40,
                  decoration: const BoxDecoration(
                      color: Colors.white,
                      image: DecorationImage(
                          image: AssetImage(
                              'assets/icons/blood-removebg-preview.ico'),
                          fit: BoxFit.contain)),
                ),
              ),
              SizedBox(
                width: width * .25,
              ),
              const Text(
                "Home",
                style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold),
              ),
            ],
          )),
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
                    if (posts.isEmpty)
                      Text(
                        "There's no current transactions",
                        style: HeadingStyle(height, grey),
                      ),
                    if (posts.isNotEmpty)
                      Text(
                        isCurrent
                            ? "Current Transactions"
                            : "Previous Transactions",
                        style: HeadingStyle(height, grey),
                      ),
                    // TransactionTile(transaction)
                    ListView.builder(
                        key: UniqueKey(),
                        physics: const NeverScrollableScrollPhysics(),
                        shrinkWrap: true,
                        itemCount: posts.length,
                        itemBuilder: (context, index) =>
                            TransactionTile(posts[index])),
                    Container(height: 0.03 * height)
                  ],
                )),
          ),
        )));
  }

  Future<void> _pullRefresh() async {
    DioHelper.getData(url: "institution/posts", query: {}).then((value) {
      posts = [];
      if (kDebugMode) print(value.data);
      for (int i = 0; i < value.data.length; i++) {
        // print(value.data[i]);
        posts.add(PostBrief.fromInstJson(value.data[i]));
      }
      posts.sort((a, b) => a.dateTime.compareTo(b.dateTime));
      print(posts);
      setState(() {});
    });
  }
}

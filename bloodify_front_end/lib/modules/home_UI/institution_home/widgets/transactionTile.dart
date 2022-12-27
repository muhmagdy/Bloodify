import 'package:bloodify_front_end/models/transaction.dart';
import 'package:bloodify_front_end/modules/institution/postTransaction.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:bloodify_front_end/shared/Constatnt/fonts.dart';
import 'package:bloodify_front_end/shared/styles/container.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

class TransactionTile extends StatefulWidget {
  final Transaction transaction;
  const TransactionTile(this.transaction, {super.key});

  @override
  createState() => _TransactionTile(transaction);
}

class _TransactionTile extends State<TransactionTile>
    with TickerProviderStateMixin {
  late final AnimationController _controller = AnimationController(
    duration: const Duration(seconds: 10),
    vsync: this,
  )..repeat();
  late final Animation<double> _animation = CurvedAnimation(
    parent: _controller,
    curve: Curves.fastOutSlowIn,
  );

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  _TransactionTile(this.transaction);

  final Transaction transaction;
  late String dateTime;

  void init() {
    dateTime = DateFormat("d MMM y 'at' h:m a").format(transaction.dateTime);
  }

  @override
  Widget build(BuildContext context) {
    init();
    final width = MediaQuery.of(context).size.width;
    final height = MediaQuery.of(context).size.height;
    return TileContainer(
        onTap: () => _onTap(context),
        width: width,
        height: height,
        color: lightGrey,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text(
                  "#${transaction.id}",
                  style: VerySmallStyle(width, Colors.black),
                ),
                Text(
                  transaction.status,
                  style: VerySmallBoldStyle(width, Colors.black),
                )
              ],
            ),
            SizedBox(
                width: 0.95 * width,
                child: FittedBox(
                    alignment: Alignment.centerLeft,
                    fit: BoxFit.scaleDown,
                    child: Text(
                      transaction.name,
                      textAlign: TextAlign.start,
                      style: NormalStyle(height, Colors.black),
                    ))),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                SizedBox(
                    width: 0.5 * width,
                    child: FittedBox(
                        alignment: Alignment.centerLeft,
                        fit: BoxFit.scaleDown,
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text(
                              transaction.nationalID,
                              style: SmallStyle(width, Colors.black),
                            ),
                            Text(
                              dateTime,
                              style: SmallStyle(width, Colors.black),
                            ),
                          ],
                        ))),
                Column(
                  children: [
                    Text("Count", style: SmallStyle(width, red)),
                    Text(
                      transaction.count.toString(),
                      style: NormalStyle(height, red),
                    )
                  ],
                ),
                Column(
                  children: [
                    Text(
                      "Type ",
                      style: SmallStyle(width, red),
                    ),
                    Text(
                      transaction.bloodType,
                      style: NormalStyle(height, red),
                    )
                  ],
                )
              ],
            ),
          ],
        ));
  }

  void _onTap(BuildContext context) {
    Navigator.of(context).push(PageRouteBuilder(
        pageBuilder: (context, animation, secondaryAnimation) =>
            PostTransactionWidget(transaction),
        transitionsBuilder: ((context, animation, secondaryAnimation, child) {
          const begin = Offset(0.0, 1.0);
          const end = Offset.zero;
          const curve = Curves.ease;
          var tween =
              Tween(begin: begin, end: end).chain(CurveTween(curve: curve));
          return SlideTransition(
            position: animation.drive(tween),
            child: child,
          );
        })));
  }
}

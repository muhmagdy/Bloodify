import 'package:bloodify_front_end/models/event.dart';
import 'package:bloodify_front_end/models/event_model.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:bloodify_front_end/shared/Constatnt/fonts.dart';
import 'package:bloodify_front_end/shared/styles/container.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

class InstEventTile extends StatefulWidget {
  final Event_model event;
  const InstEventTile(this.event, {super.key});

  @override
  createState() => _EventTile();
}

class _EventTile extends State<InstEventTile> {
  @override
  Widget build(BuildContext context) {
    final width = MediaQuery.of(context).size.width;
    final height = MediaQuery.of(context).size.height;

    return Container(
      margin: const EdgeInsets.only(left: 20, right: 20),
      child: TileContainer(
          onTap: () => _onTap(context),
          height: height,
          width: width,
          child: SizedBox(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  maxLines: 2,
                  overflow: TextOverflow.ellipsis,
                  "#${widget.event.event_ID}",
                  style: VerySmallStyle(width, Colors.black),
                ),
                SizedBox(
                    width: 0.95 * width,
                    child: FittedBox(
                        alignment: Alignment.centerLeft,
                        fit: BoxFit.scaleDown,
                        child: Text(
                          widget.event.title,
                          textAlign: TextAlign.start,
                          style: NormalStyle(height, Colors.black),
                        ))),
                Row(crossAxisAlignment: CrossAxisAlignment.start, children: [
                  Container(
                      margin: EdgeInsets.only(right: 0.0282 * width),
                      width: width / 39,
                      height: height / 52,
                      child: Icon(
                        Icons.location_pin,
                        size: 0.05 * width,
                        color: blue,
                      )),
                  Expanded(
                    child: Text(widget.event.location!,
                        maxLines: 2,
                        overflow: TextOverflow.ellipsis,
                        style: SmallStyle(width, Colors.black)),
                  ),
                ]),
                Row(
                  children: [
                    Text(
                      "Ends:  ",
                      style: SmallBoldStyle(width, Colors.black),
                    ),
                    Text(
                      dateFormat.format(widget.event.endDate),
                      style: SmallStyle(width, Colors.black),
                    ),
                  ],
                ),
              ],
            ),
          )),
    );
  }

  void _onTap(BuildContext context) {
    Navigator.of(context).push(PageRouteBuilder(
        pageBuilder: (context, animation, secondaryAnimation) =>
            InstitutionEventDetailsWindow(widget.event),
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

class InstitutionEventDetailsWindow extends StatelessWidget {
  final Event_model event;
  const InstitutionEventDetailsWindow(this.event, {super.key});

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
          "Event Details",
          style: AppHeaderStyle(height, Colors.white),
        ),
      ),
      body: Container(
        alignment: Alignment.center,
        child: Text(
          "Event #${event.title}",
          textAlign: TextAlign.center,
          style: NormalStyle(height, Colors.black),
        ),
      ),
    );
  }
}

import 'package:bloodify_front_end/models/event.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:bloodify_front_end/shared/Constatnt/fonts.dart';
import 'package:bloodify_front_end/shared/styles/container.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

class EventTile extends StatefulWidget {
  final Event event;
  const EventTile(this.event, {super.key});

  @override
  createState() => _EventTile();
}

class _EventTile extends State<EventTile> {
  @override
  Widget build(BuildContext context) {
    final width = MediaQuery.of(context).size.width;
    final height = MediaQuery.of(context).size.height;
    return TileContainer(
        onTap: () => _onTap(context),
        height: height,
        width: width,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              "#${widget.event.id}",
              style: VerySmallStyle(width, Colors.black),
            ),
            SizedBox(
                width: 0.95 * width,
                child: FittedBox(
                    alignment: Alignment.centerLeft,
                    fit: BoxFit.scaleDown,
                    child: Text(
                      widget.event.name,
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
              Text(widget.event.location,
                  style: SmallStyle(width, Colors.black)),
            ]),
            Row(
              children: [
                Text(
                  "Ends:  ",
                  style: SmallBoldStyle(width, Colors.black),
                ),
                Text(
                  DateFormat("d MMM y 'at' h:m a").format(widget.event.end),
                  style: SmallStyle(width, Colors.black),
                ),
              ],
            ),
          ],
        ));
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
  final Event event;
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
          "Event #${event.id}",
          textAlign: TextAlign.center,
          style: NormalStyle(height, Colors.black),
        ),
      ),
    );
  }
}

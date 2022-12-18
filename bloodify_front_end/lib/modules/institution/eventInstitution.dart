import 'dart:async';

import 'package:bloodify_front_end/models/event.dart';
import 'package:bloodify_front_end/modules/institution/eventTile.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:bloodify_front_end/shared/Constatnt/fonts.dart';
import 'package:flutter/material.dart';

class InstitutionEventPage extends StatefulWidget {
  @override
  createState() => _InstitutionEventPage();
}

class _InstitutionEventPage extends State<InstitutionEventPage> {
  final events = [
    Event(123, "Some Event Name", "Location of event",
        DateTime(2022, 12, 31, 12, 30)),
    Event(952, "The Greatest Event in The World", "A dump location",
        DateTime(2022, 12, 20, 12, 30)),
    Event(654, "Another Name for an Event", "Location of locations",
        DateTime(2023, 1, 31, 12, 30)),
    Event(741, "Also another event name", "Somewhere on earth",
        DateTime(2022, 12, 25, 20)),
  ];
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
            "Events",
            style: AppHeaderStyle(height, Colors.white),
          ),
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () => {},
          backgroundColor: red,
          child: const Icon(Icons.add),
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
                        "Current Events",
                        style: HeadingStyle(height, grey),
                      ),
                      ListView.builder(
                          physics: const NeverScrollableScrollPhysics(),
                          shrinkWrap: true,
                          itemCount: events.length,
                          itemBuilder: (context, index) =>
                              EventTile(events[index])),
                      Container(height: 0.03 * height)
                    ]))));
  }

  Future<void> _pullRefresh() async {
    setState(() {});
  }
}

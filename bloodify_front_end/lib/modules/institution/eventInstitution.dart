import 'dart:async';

import 'package:bloodify_front_end/models/event.dart';
import 'package:bloodify_front_end/modules/create_event/create_event.dart';
import 'package:bloodify_front_end/modules/institution/eventTile.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:bloodify_front_end/shared/Constatnt/fonts.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
import 'package:flutter/material.dart';

import '../../models/event_model.dart';
import '../../shared/Constatnt/sharedFunctions.dart';

class InstitutionEventPage extends StatefulWidget {
  @override
  createState() => _InstitutionEventPage();
}

class _InstitutionEventPage extends State<InstitutionEventPage> {
  List<Event_model> events = [
    // Event(123, "Some Event Name", "Location of event",
    //     DateTime(2022, 12, 31, 12, 30)),
    // Event(952, "The Greatest Event in The World", "A dump location",
    //     DateTime(2022, 12, 20, 12, 30)),
    // Event(654, "Another Name for an Event", "Location of locations",
    //     DateTime(2023, 1, 31, 12, 30)),
    // Event(741, "Also another event name", "Somewhere on earth",
    //     DateTime(2022, 12, 25, 20)),
  ];
  @override
  Widget build(BuildContext context) {
    final width = MediaQuery.of(context).size.width;
    final height = MediaQuery.of(context).size.height;

    return Scaffold(
        appBar: defaultAppBAr("Events"),
        floatingActionButton: FloatingActionButton(
          onPressed: () => {navigateTo(context, CreateEvent())},
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
                              InstEventTile(events[index])),
                      Container(height: 0.03 * height)
                    ]))));
  }

  Future<void> _pullRefresh() async {
    setState(() {
      DioHelper.getData(url: 'institution/events', query: {}).then((value) {
        events = [];
        print(value.data);
        for (int i = 0; i < value.data.length; i++) {
          var event = Event_model.fromJson(value.data[i]);
          events.add(event);
        }
        print(events);
      }).catchError((error) {});
    });
  }
}

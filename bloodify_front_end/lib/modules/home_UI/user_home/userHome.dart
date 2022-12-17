import 'package:bloodify_front_end/models/request.dart';
import 'package:bloodify_front_end/modules/home_UI/user_home/widgets/personTile.dart';
import 'package:bloodify_front_end/modules/home_UI/user_home/widgets/requestHeader.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:flutter/material.dart';

import '../../../models/person.dart';
import '../../../shared/Constatnt/fonts.dart';

class UserHome extends StatefulWidget {
  @override
  State<UserHome> createState() => _UserHome();
}

class _UserHome extends State<UserHome> {
  var bloodRequest = BloodRequest(
      0, "General Hospital", 'A+', 1, DateTime(2022, 12, 20, 12), false);
  var person = Person(0, "Ahmed Mohamed", 1.488, "A+", 5);
  var persons = [
    Person(0, "Ahmed Mohamed", 1.488, "A+", 5),
    Person(1, "Mostafa Mahmoud", 2.345, "A+", 0),
    Person(2, "Mohamed Sharif", 2.489, "A+", 2),
    Person(3, "Yasmin Ashraf", 2.785, "A+", 1),
    Person(4, "Ayman Anwar", 3.2, "A+", 0),
    Person(5, "Ahmed Mohamed", 3.43, "A+", 11)
  ];
  @override
  Widget build(BuildContext context) {
    final double width = MediaQuery.of(context).size.width;
    final height = MediaQuery.of(context).size.height;
    return Container(
      color: blue,
      child: SafeArea(
          child: Container(
              color: Colors.white,
              padding: EdgeInsets.fromLTRB(0.02 * width, 0, 0.02 * width, 0),
              child: Scaffold(
                  body: RefreshIndicator(
                triggerMode: RefreshIndicatorTriggerMode.anywhere,
                onRefresh: _pullRefresh,
                child: ListView(
                    shrinkWrap: true,
                    physics: const ClampingScrollPhysics(
                        parent: AlwaysScrollableScrollPhysics()),
                    // child: Column(
                    // crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      RequestHeader(bloodRequest),
                      Text(
                          bloodRequest.isDonor
                              ? "Recipient"
                              : "Possibile Donors",
                          textAlign: TextAlign.left,
                          style: SafeGoogleFont('Poppins',
                              fontSize: 0.025 * height,
                              fontWeight: FontWeight.w500,
                              height: 1.5,
                              color: grey)),
                      // PersonTile(person),
                      ListView.builder(
                          physics: const NeverScrollableScrollPhysics(),
                          shrinkWrap: true,
                          itemCount: persons.length,
                          itemBuilder: (context, index) =>
                              PersonTile(persons[index]))
                    ]),
              )))),
    );
  }

  Future<void> _pullRefresh() async {
    setState(() {});
  }
}

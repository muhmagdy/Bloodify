// import 'dart:ffi';

import 'package:bloodify_front_end/models/postBrief.dart';
import 'package:bloodify_front_end/models/request.dart';
import 'package:bloodify_front_end/models/userBrief.dart';
import 'package:bloodify_front_end/modules/home_UI/user_home/widgets/personTile.dart';
import 'package:bloodify_front_end/modules/home_UI/user_home/widgets/requestFull.dart';
import 'package:bloodify_front_end/modules/home_UI/user_home/widgets/requestHeader.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:bloodify_front_end/shared/Constatnt/userInfo.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
import 'package:flutter/material.dart';

import '../../../models/person.dart';
import '../../../shared/Constatnt/fonts.dart';

class UserHome extends StatefulWidget {
  @override
  State<UserHome> createState() => _UserHome();
}

class _UserHome extends State<UserHome> {
  var status = 0;
  var posts = [];
  //  <PostBrief>[
  //   PostBrief(4, "nationalID", "name", DateTime.now(), 2, "A+", 5,
  //       "International Hospital For Kidssss", 0)
  // ];

  var people = <List<UserBrief>>[
    [
      UserBrief(0, "Ahmed Mohamed", 5.0, "A+"),
      UserBrief(1, "Mostafa Mahmoud", 2.345, "A+"),
      UserBrief(2, "Mohamed Sharif", 2.489, "A+"),
      UserBrief(3, "Yasmin Ashraf", 2.785, "A+"),
      UserBrief(4, "Ayman Anwar", 3.2, "A+")
    ]
  ];
  // var bloodRequest = BloodRequest(
  //     0, "General Hospital", 'A+', 1, DateTime(2022, 12, 20, 12), false);
  // var person = Person(0, "Ahmed Mohamed", 1.488, "A+", 5);
  // var persons = [
  //   Person(0, "Ahmed Mohamed", 1.488, "A+", 5),
  //   Person(1, "Mostafa Mahmoud", 2.345, "A+", 0),
  //   Person(2, "Mohamed Sharif", 2.489, "A+", 2),
  //   Person(3, "Yasmin Ashraf", 2.785, "A+", 1),
  //   Person(4, "Ayman Anwar", 3.2, "A+", 0),
  //   Person(5, "Ahmed Mohamed", 3.43, "A+", 11)
  // ];
  @override
  Widget build(BuildContext context) {
    String title;
    if (status == 0) {
      title = "Avaliable Requests";
    } else {
      title = "Current Request";
    }

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
                  title:
                      Text(title, style: AppHeaderStyle(height, Colors.white))),
              body: Container(
                  color: Colors.white,
                  padding:
                      EdgeInsets.fromLTRB(0.02 * width, 0, 0.02 * width, 0),
                  child: Scaffold(
                      body: RefreshIndicator(
                    triggerMode: RefreshIndicatorTriggerMode.anywhere,
                    onRefresh: _pullRefresh,
                    child: ListView(
                        shrinkWrap: false,
                        physics: const ClampingScrollPhysics(
                            parent: AlwaysScrollableScrollPhysics()),
                        // child: Column(
                        // crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Container(
                            height: 0.01 * height,
                          ),
                          // if (status == 0)
                          //   Text(
                          //     "Current posts",
                          //     textAlign: TextAlign.left,
                          //     style: SafeGoogleFont('Poppins',
                          //         fontSize: 0.035 * height,
                          //         fontWeight: FontWeight.w500,
                          //         height: 1.5,
                          //         color: grey),
                          //   ),
                          if (posts.isEmpty)
                            Text("There's no avaliable request for you..",
                                style: SafeGoogleFont('Poppins',
                                    fontSize: 0.025 * height,
                                    fontWeight: FontWeight.w500,
                                    height: 1.5,
                                    color: grey)),
                          ListView.builder(
                            physics: const NeverScrollableScrollPhysics(),
                            shrinkWrap: true,
                            itemCount: posts.length,
                            itemBuilder: (context, index) => RequestFullWidget(
                                posts[index], people[index], status),
                          )
                          // ListView.builder(
                          //     physics: const NeverScrollableScrollPhysics(),
                          //     shrinkWrap: true,
                          //     itemCount: posts.length,
                          //     itemBuilder: (context, index) =>
                          //         RequestHeader(posts[index])),
                          // if (status == 1 || status == 2)
                          //   Text(status == 2 ? "Recipient" : "Possibile Donors",
                          //       textAlign: TextAlign.left,
                          //       style: SafeGoogleFont('Poppins',
                          //           fontSize: 0.025 * height,
                          //           fontWeight: FontWeight.w500,
                          //           height: 1.5,
                          //           color: grey)),
                          // // PersonTile(person),
                          // if (status == 3)
                          //   ListView.builder(
                          //       physics: const NeverScrollableScrollPhysics(),
                          //       shrinkWrap: true,
                          //       itemCount: persons.length,
                          //       itemBuilder: (context, index) =>
                          //           PersonTile(persons[index]))
                        ]),
                  )))),
        ));
  }

  Future<void> _pullRefresh() async {
    setState(() {
      DioHelper.getData(url: "user/status", query: {}).then(
        (value) {
          posts = [];
          people = [];
          var token =
              "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiam9obkBsZWdlbmQubWUiLCJpYXQiOjE2NzE2Mzk1NDR9.DIUuR9b8BC9FuN6m0TPMAcGFp1j5ipQ3c5IGDEVttY8XBmUucl2_ogiPRzxdxlZidjlSvr_t4c8NnWnYR1UmTXlilhRgv14eDOnICz4woFqquM35KA3FDfiLPl5TQjGo4fe9UHdRcbfOOJLkjk0SBrrD54VQgoyH2YyzPRwBK_-gpjCAH2030zO0o438mnPq2E3Wl_Z1vfDevjfANNJG6httNA606qmHAR9r66staZkAedLDrlgYA5_a-MyHSs1jSTQLfWFq4CNSK9PZwVO-LZ3dUujG26aF7gTV8q9eJsdoWRnvZqpj2Tov3FWMmrMzD-ZxoJ0SLEkHyKUc4GNq5Q";
          status = int.parse(value.data.toString());
          switch (status) {
            case 0:
              DioHelper.getData(url: "user/posts/compatible", query: {
                'longitude': 30.0,
                'latitude': 30.0,
                'threshold': 500000.0
              }).then((value) {
                print(value.data);
                for (int i = 0; i < value.data.length; i++) {
                  var event = PostBrief.fromJson(value.data[i], status);
                  posts.add(event);
                  people.add([]);
                }
                print(posts);
              });
              break;
            case 1:
              DioHelper.getData(url: "user/posts/requester", query: {
                'longitude': 30.0,
                'latitude': 30.0,
                'threshold': 500000.0
              }).then((value) {
                print(value.data);
                for (int i = 0; i < value.data.length; i++) {
                  var event = PostBrief.fromJson(value.data[i], status);
                  posts.add(event);
                  people.add(<UserBrief>[]);
                }
                for (int i = 0; i < posts.length; i++) {
                  DioHelper.getData(
                      url: "user/post/donors",
                      query: {'id': posts[i].id}).then((value) {
                    print(
                        "posts length = ${posts.length} \n users length = ${value.data.length}");
                    for (int j = 0; j < value.data.length; j++) {
                      print(j);
                      people[i].add(UserBrief.fromJson(value.data[j]));
                    }
                    print(value.data);
                  });
                }
                print(posts);
              });
              break;
            case 2:
              DioHelper.getData(url: "user/posts/current", query: {
                'longitude': 30.0,
                'latitude': 30.0,
                'threshold': 500000.0
              }).then((value) {
                print(value.data);
                for (int i = 0; i < value.data.length; i++) {
                  var event = PostBrief.fromJson(value.data[i], status);
                  posts.add(event);
                  people.add([]);
                }
                for (int i = 0; i < posts.length; i++) {
                  DioHelper.getData(url: "user/post/requester", query: {
                    'id': posts[i].id,
                    'longitude': 30.0,
                    'latitude': 30.0,
                    'threshold': 500000.0
                  }).then((value) {
                    people[i].add(UserBrief.fromJson(value.data));
                  });
                }
                print(posts);
              });
              break;
          }
        },
      );
    });
  }
}

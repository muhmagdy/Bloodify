import 'package:bloodify_front_end/models/postBrief.dart';
import 'package:bloodify_front_end/models/userBrief.dart';
import 'package:bloodify_front_end/modules/BloodFinding/bloc/blood_finder_service.dart';
import 'package:bloodify_front_end/modules/home_UI/user_home/widgets/requestFull.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
import 'package:flutter/material.dart';

import '../../../shared/Constatnt/fonts.dart';

class UserHome extends StatefulWidget {
  const UserHome({super.key});

  @override
  State<UserHome> createState() => _UserHome();
}

class _UserHome extends State<UserHome> {
  var status = 0;
  var posts = [];
  var _isLoading = true;
  var people = <List<UserBrief>>[];

  _UserHome() {
    _pullRefresh(isStarting: true);
  }

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
                  child: Stack(
                    children: [
                      Scaffold(
                          body: RefreshIndicator(
                        triggerMode: RefreshIndicatorTriggerMode.anywhere,
                        onRefresh: _pullRefresh,
                        child: ListView(
                            shrinkWrap: false,
                            physics: const ClampingScrollPhysics(
                                parent: AlwaysScrollableScrollPhysics()),
                            children: [
                              Container(
                                height: 0.01 * height,
                              ),
                              if (posts.isEmpty)
                                Text("There's no avaliable request for you..",
                                    style: NormalStyle(height, grey)),
                              if (status == 0 && posts.length == 1)
                                Text("Compatible Post",
                                    style: NormalStyle(height, grey)),
                              if (status == 0 && posts.length > 1)
                                Text("Compatible Posts",
                                    style: NormalStyle(height, grey)),
                              if (status == 1)
                                Text("You have the following requests:",
                                    style: NormalStyle(height, grey)),
                              if (status == 2)
                                Text("You have accepted the following request:",
                                    style: NormalStyle(height, grey)),
                              Container(
                                height: 0.01 * height,
                              ),
                              ListView.builder(
                                physics: const NeverScrollableScrollPhysics(),
                                shrinkWrap: true,
                                itemCount: posts.length,
                                itemBuilder: (context, index) =>
                                    RequestFullWidget(
                                        posts[index], people[index], status),
                              )
                            ]),
                      )),
                      if (_isLoading)
                        Center(
                            child: Container(
                          width: 0.2 * width,
                          height: 0.2 * width,
                          decoration: BoxDecoration(
                              color: const Color.fromARGB(150, 128, 128, 128),
                              borderRadius: BorderRadius.circular(width / 26),
                              backgroundBlendMode: BlendMode.overlay),
                          child: const Center(
                              child: CircularProgressIndicator(color: blue)),
                        ))
                    ],
                  ))),
        ));
  }

  Future<void> _pullRefresh({bool isStarting = false}) async {
    if (!isStarting) {
      setState(() {
        _isLoading = true;
      });
    }
    var position = await getLocation();
    DioHelper.getData(url: "user/status", query: {}).then(
      (value) {
        posts = [];
        people = [];
        status = int.parse(value.data.toString());
        switch (status) {
          case 0:
            DioHelper.getData(url: "user/posts/compatible", query: {
              'longitude': position.longitude,
              'latitude': position.latitude,
              'threshold': 500000.0
            }).then((value) {
              print(value.data);
              for (int i = 0; i < value.data.length; i++) {
                var event = PostBrief.fromJson(value.data[i], status);
                posts.add(event);
                people.add([]);
              }
              setState(() {
                _isLoading = false;
              });
              print(posts);
            });
            break;
          case 1:
            DioHelper.getData(url: "user/posts/requester", query: {
              'longitude': position.longitude,
              'latitude': position.latitude,
              'threshold': 500000.0
            }).then((value) {
              print(value.data);
              for (int i = 0; i < value.data.length; i++) {
                var event = PostBrief.fromJsonWithLocation(
                    value.data[i], status, position);
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
                  if (i == posts.length - 1) {
                    setState(() {
                      _isLoading = false;
                    });
                  }
                  print(value.data);
                });
              }
              print(posts);
            });
            break;
          case 2:
            DioHelper.getData(url: "user/posts/current", query: {
              'longitude': position.longitude,
              'latitude': position.latitude,
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
                  'longitude': position.longitude,
                  'latitude': position.latitude,
                  'threshold': 500000.0
                }).then((value) {
                  people[i].add(UserBrief.fromJson(value.data));
                  if (i == posts.length - 1) {
                    setState(() {
                      _isLoading = false;
                    });
                  }
                });
              }
              print(posts);
            });
            break;
        }
      },
    );
  }
}

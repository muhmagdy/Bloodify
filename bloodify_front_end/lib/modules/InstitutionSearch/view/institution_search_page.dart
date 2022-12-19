import 'package:bloodify_front_end/modules/UserRequest_UI/view/user_request_page.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/container.dart';
import 'package:flutter/src/widgets/framework.dart';

class BloodFinder extends StatelessWidget {
  const BloodFinder({super.key});

  @override
  Widget build(BuildContext context) {
    List<String> bloodTypes = [
      'A+',
      'B+',
      'O+',
      'AB+',
      'A-',
      'B-',
      'O-',
      'AB-'
    ];
    double width = MediaQuery.of(context).size.width;
    double height = MediaQuery.of(context).size.height;

    return Scaffold(
      resizeToAvoidBottomInset: false,
      appBar: AppBar(
        title: Text("Blood Finder", style: TextStyle(color: Colors.white)),
        backgroundColor: defaultColor,
      ),
      body: Padding(
        padding: EdgeInsets.all(12.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            _CreatePostButton(width: width),
            _SearchBar(width: width, bloodTypes: bloodTypes),
            _SearchResults()
          ],
        ),
      ),
    );
  }
}

class _CreatePostButton extends StatelessWidget {
  const _CreatePostButton({
    Key? key,
    required this.width,
  }) : super(key: key);

  final double width;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
        width: width * 0.7,
        child: ElevatedButton(
            onPressed: () => createPost(context), child: Text("Create Post")));
  }

  void createPost(BuildContext context) {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => UserRequestForm()),
    );
  }
}

class _SearchBar extends StatelessWidget {
  const _SearchBar({
    Key? key,
    required this.width,
    required this.bloodTypes,
  }) : super(key: key);

  final double width;
  final List<String> bloodTypes;

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: [
        SizedBox(
          width: (width - 50) / 2,
          child: makeDropDown("Blood type", bloodTypes, bloodTypeChanged),
        ),
        IconButton(onPressed: find, icon: Icon(Icons.search_rounded))
      ],
    );
  }

  bloodTypeChanged(String value) {}

  void find() {}
}

class _SearchResults extends StatelessWidget {
  const _SearchResults({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ListView(
      shrinkWrap: true,
      children: [Text("hello"), Text("data")],
    );
  }
}

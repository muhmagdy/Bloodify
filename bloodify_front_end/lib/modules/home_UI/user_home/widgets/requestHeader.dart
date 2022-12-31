import 'package:bloodify_front_end/models/postBrief.dart';
import 'package:bloodify_front_end/models/request.dart';
import 'package:bloodify_front_end/shared/Constatnt/Component.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:bloodify_front_end/shared/network/local/cach_helper.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
import 'package:bloodify_front_end/shared/styles/container.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';
import 'package:intl/intl.dart';
import 'package:maps_launcher/maps_launcher.dart';

import '../../../../shared/Constatnt/fonts.dart';

class RequestHeader extends StatelessWidget {
  final PostBrief request;
  final Function updateParent;
  const RequestHeader(this.request, this.updateParent, {super.key});

  @override
  Widget build(BuildContext context) {
    final String bloodBag;
    if (request.count > 1) {
      bloodBag = "${request.count} Blood Bags";
    } else {
      bloodBag = "${request.count} Blood Bag";
    }
    final height = MediaQuery.of(context).size.height;
    final width = MediaQuery.of(context).size.width;
    return TileContainer(
      height: height,
      width: width,
      color: blue,
      onTap: () => _onTap(context),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            // crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              SizedBox(
                  width: 0.75 * width,
                  child: FittedBox(
                      alignment: Alignment.centerLeft,
                      fit: BoxFit.scaleDown,
                      child: Text(
                        request.hospitalName,
                        textScaleFactor: MediaQuery.textScaleFactorOf(context),
                        style: SafeGoogleFont(
                          'Poppins',
                          fontSize: 0.027 * height,
                          fontWeight: FontWeight.w700,
                          color: Colors.white,
                        ),
                      ))),
              Icon(
                CupertinoIcons.location_solid,
                size: 0.0385 * height,
                color: red,
              ),
            ],
          ),
          Container(
              margin: EdgeInsets.only(bottom: 0.0128 * height),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text(
                    "${request.distance.toStringAsFixed(2)} KM away",
                    style: SafeGoogleFont(
                      'Poppins',
                      fontSize: 0.025 * height,
                      fontWeight: FontWeight.w500,
                      color: lightBlue,
                    ),
                  )
                ],
              )),
          Container(
            margin: EdgeInsets.only(bottom: 0.01 * height),
            width: 0.8 * width,
            height: 1,
            decoration: const BoxDecoration(
              color: grey,
            ),
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            // crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Container(
                    margin: EdgeInsets.zero,
                    child: Text(
                      bloodBag,
                      style: SafeGoogleFont(
                        'Poppins',
                        fontSize: 0.025 * height,
                        fontWeight: FontWeight.w500,
                        color: lightBlue,
                      ),
                    ),
                  ),
                  Text(
                    DateFormat("d MMM y 'at' h:mm a").format(request.dateTime),
                    style: SafeGoogleFont(
                      'Poppins',
                      fontSize: 0.025 * height,
                      fontWeight: FontWeight.w500,
                      color: lightBlue,
                    ),
                  ),
                ],
              ),
              Container(
                width: 0.154 * width,
                height: 0.154 * width,
                decoration: BoxDecoration(
                  color: middleBlue,
                  borderRadius: BorderRadius.circular(0.0513 * width),
                ),
                child: Center(
                  child: Text(
                    request.bloodType,
                    style: SafeGoogleFont(
                      'Poppins',
                      fontSize: 0.03 * height,
                      fontWeight: FontWeight.w700,
                      color: Colors.white,
                    ),
                  ),
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }

  void _onTap(BuildContext context) {
    final width = MediaQuery.of(context).size.width;
    final height = MediaQuery.of(context).size.height;
    StyledBottomSheet(context: context, children: [
      Text("#${request.id}", style: SmallStyle(width, blue)),
      Text("Requester", style: SmallStyle(width, Colors.black)),
      Text(request.name, style: BigBoldStyle(height, Colors.black)),
      Text(
        request.hospitalName,
        style: NormalStyle(height, Colors.black),
      ),
      Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          if (request.count == 1)
            Text(
              "${request.count} Blood Bag",
              style: NormalStyle(height, blue),
            ),
          if (request.count > 1)
            Text(
              "${request.count} Blood Bags",
              style: NormalStyle(height, blue),
            ),
          Text(
            request.bloodType,
            style: BigBoldStyle(height, red),
          )
        ],
      ),
      Text("Needed At", style: SmallStyle(width, blue)),
      Text(
        DateFormat("EEE d MMMM y h:mm a").format(request.dateTime),
        style: SmallStyle(width, Colors.black),
      ),
      Text("Distance", style: SmallStyle(width, blue)),
      Text(
        "${request.distance.toStringAsFixed(3)} KM",
        style: SmallStyle(width, Colors.black),
      ),
      Container(
        height: 0.01 * height,
      ),
      TextButton(
          onPressed: () {
            MapsLauncher.launchCoordinates(
                request.latitude, request.longitude, request.hospitalName);
          },
          style: TextButton.styleFrom(
              backgroundColor: blue,
              minimumSize: Size(width * 0.9, height * 0.05),
              shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(width / 26))),
          child: Text(
            "Directions",
            style: NormalStyle(height, Colors.white),
          )),
      Container(
        height: 0.01 * height,
      ),
      if (request.state == 0)
        TextButton(
            onPressed: () => acceptPost(context),
            style: TextButton.styleFrom(
                backgroundColor: red,
                minimumSize: Size(width * 0.9, height * 0.05),
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(width / 26))),
            child: Text(
              "Accept",
              style: NormalStyle(height, Colors.white),
            )),
      if (request.state == 1)
        TextButton(
            onPressed: () => deletePost(context),
            style: TextButton.styleFrom(
                backgroundColor: red,
                minimumSize: Size(width * 0.9, height * 0.05),
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(width / 26))),
            child: Text(
              "Delete Post",
              style: NormalStyle(height, Colors.white),
            )),
      Container(
        height: 0.01 * height,
      ),
    ]);
  }

  void deletePost(BuildContext context) {
    DioHelper.deleteData(url: 'user/posting/${request.id}', data: {})
        .then((value) {
      if (value.data['status']) {
        showToast(text: "Post deleted successfully!", color: blue, time: 3000);
      } else {
        showToast(text: value.data['message'], color: red, time: 3000);
      }
      Navigator.pop(context);
      updateParent();
    });
  }

  void acceptPost(BuildContext context) {
    var threshold = CachHelper.getData(key: 'threshold').toDouble();
    DioHelper.postData(url: 'user/post/accept', data: {
      'id': request.id,
      'longitude': request.location.longitude,
      'latitude': request.location.latitude,
      'threshold': threshold,
    }).then((value) {
      if (value.data['state']) {
        showToast(text: "Post accepted successfully!", color: blue, time: 3000);
      } else {
        showToast(text: value.data['message'], color: red, time: 3000);
      }
      Navigator.pop(context);
      updateParent();
    });
  }
}

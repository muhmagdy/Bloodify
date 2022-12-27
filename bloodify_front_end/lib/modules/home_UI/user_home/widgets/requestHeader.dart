import 'package:bloodify_front_end/models/postBrief.dart';
import 'package:bloodify_front_end/models/request.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:bloodify_front_end/shared/styles/container.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';
import 'package:intl/intl.dart';

import '../../../../shared/Constatnt/fonts.dart';

class RequestHeader extends StatelessWidget {
  final PostBrief request;
  const RequestHeader(this.request, {super.key});

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
          // if (request.state != 0)
          //   Container(
          //     alignment: Alignment.centerLeft,
          //     margin: EdgeInsets.only(bottom: .005 * height),
          //     child: Text(
          //       title,
          //       style: SafeGoogleFont(
          //         'Poppins',
          //         fontSize: 0.02 * height,
          //         fontWeight: FontWeight.w400,
          //         color: lightBlue,
          //       ),
          //     ),
          //   ),
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
      Text(
        "#${request.id}",
        style: SmallStyle(width, blue),
      ),
    ]);
  }
}

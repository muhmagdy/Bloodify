import 'package:bloodify_front_end/models/request.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../../../../shared/Constatnt/fonts.dart';

class RequestHeader extends StatelessWidget {
  final BloodRequest request;
  const RequestHeader(this.request, {super.key});

  @override
  Widget build(BuildContext context) {
    final String bloodBag;
    if (request.nBags > 1) {
      bloodBag = "${request.nBags} Blood Bags";
    } else {
      bloodBag = "${request.nBags} Blood Bag";
    }
    final height = MediaQuery.of(context).size.height;
    final width = MediaQuery.of(context).size.width;
    return Container(
      margin: EdgeInsets.only(bottom: 0.005 * height),
      padding: EdgeInsets.fromLTRB(
          0.05 * width, 0.02 * height, 0.05 * width, 0.02 * height),
      width: double.infinity,
      decoration: BoxDecoration(
        color: blue,
        borderRadius: BorderRadius.circular(0.05128 * width),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Container(
            alignment: Alignment.centerLeft,
            margin: EdgeInsets.only(bottom: .005 * height),
            child: Text(
              request.isDonor
                  ? 'You have accepted a request'
                  : 'You have a current request',
              style: SafeGoogleFont(
                'Poppins',
                fontSize: 0.02 * height,
                fontWeight: FontWeight.w400,
                color: lightBlue,
              ),
            ),
          ),
          Container(
            margin: EdgeInsets.only(bottom: 0.0128 * height),
            width: double.infinity,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              // crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  request.location,
                  textScaleFactor: MediaQuery.textScaleFactorOf(context),
                  style: SafeGoogleFont(
                    'Poppins',
                    fontSize: 0.027 * height,
                    fontWeight: FontWeight.w700,
                    color: Colors.white,
                  ),
                ),
                Icon(
                  CupertinoIcons.location_solid,
                  size: 0.0385 * height,
                  color: red,
                ),
              ],
            ),
          ),
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
                    request.dateTime.toLocal().toString(),
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
}

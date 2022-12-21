import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

Widget TileContainer({height, width, child, onTap}) {
  return GestureDetector(
      onTap: onTap,
      child: Container(
          margin: EdgeInsets.only(bottom: 0.005 * height),
          padding: EdgeInsets.fromLTRB(
              width / 30, 0.027 * height, width / 30, 0.027 * height),
          width: double.infinity,
          // height: 93 * fem,
          decoration: BoxDecoration(
            color: lightGrey,
            borderRadius: BorderRadius.circular(width / 26),
          ),
          child: child));
}

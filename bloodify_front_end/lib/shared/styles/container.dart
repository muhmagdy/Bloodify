import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

Widget TileContainer({height, width, child, onTap, color}) {
  return GestureDetector(
      onTap: onTap,
      child: Container(
          margin: EdgeInsets.only(bottom: 0.005 * height),
          padding: EdgeInsets.fromLTRB(
              width / 30, 0.027 * height, width / 30, 0.027 * height),
          width: double.infinity,
          // height: 93 * fem,
          decoration: BoxDecoration(
            color: color,
            borderRadius: BorderRadius.circular(width / 26),
          ),
          child: child));
}

void StyledBottomSheet(
    {required BuildContext context, required List<Widget> children}) {
  showModalBottomSheet(
      backgroundColor: Colors.transparent,
      context: context,
      builder: (context) {
        final width = MediaQuery.of(context).size.width;
        return Container(
            clipBehavior: Clip.hardEdge,
            decoration: BoxDecoration(
                color: lightGrey,
                borderRadius: BorderRadius.only(
                    topLeft: Radius.circular(width / 15),
                    topRight: Radius.circular(width / 15))),
            padding: EdgeInsets.all(0.05 * width),
            child: Column(
                // mainAxisAlignment: MainAxisAlignment.spaceBetween,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: children));
      });
}

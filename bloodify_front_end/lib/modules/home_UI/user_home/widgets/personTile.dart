import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../../../../models/person.dart';
import '../../../../shared/Constatnt/fonts.dart';

class PersonTile extends StatefulWidget {
  final Person person;
  const PersonTile(this.person, {super.key});
  @override
  State<StatefulWidget> createState() => _PersonTile();
}

class _PersonTile extends State<PersonTile> {
  @override
  Widget build(BuildContext context) {
    final height = MediaQuery.of(context).size.height;
    final width = MediaQuery.of(context).size.width;
    double fem = MediaQuery.of(context).size.width / width;
    double ffem = fem * 0.97;
    return Container(
      margin: EdgeInsets.only(bottom: 0.005 * height),
      padding: EdgeInsets.fromLTRB(
          width / 30, 0.027 * height, width / 30, 0.027 * height),
      width: double.infinity,
      // height: 93 * fem,
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(width / 26),
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          SizedBox(
            width: 0.57 * width,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                FittedBox(
                  child: Container(
                    margin: EdgeInsets.only(bottom: height / 260),
                    child: Text(
                      widget.person.name,
                      style: SafeGoogleFont(
                        'Poppins',
                        fontSize: 0.025 * height,
                        fontWeight: FontWeight.w700,
                        color: Colors.black,
                      ),
                    ),
                  ),
                ),
                Row(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Container(
                        margin: EdgeInsets.only(right: 0.0282 * width),
                        width: width / 39,
                        height: height / 52,
                        child: Icon(
                          CupertinoIcons.location_solid,
                          size: 0.05 * width,
                          color: blue,
                        )),
                    Text(
                      "${widget.person.dist.toStringAsFixed(2)} Kilometers",
                      style: SafeGoogleFont(
                        'Poppins',
                        fontSize: 0.0359 * width,
                        fontWeight: FontWeight.w400,
                        color: Colors.black,
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
          Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Text(
                'Type',
                style: SafeGoogleFont(
                  'Poppins',
                  fontSize: width / 26,
                  fontWeight: FontWeight.w500,
                  color: red,
                ),
              ),
              Text(
                widget.person.bloodType,
                style: SafeGoogleFont(
                  'Poppins',
                  fontSize: 0.0487 * width,
                  fontWeight: FontWeight.w700,
                  color: red,
                ),
              ),
            ],
          ),
          Stack(
            children: [
              Align(
                alignment: Alignment.centerRight,
                child: IconButton(
                  icon: Icon(
                    CupertinoIcons.chat_bubble_fill,
                    size: 0.08 * width,
                  ),
                  onPressed: () => {},
                ),
              ),
              if (widget.person.nMessages > 0)
                Align(
                  alignment: Alignment.topRight,
                  child: SizedBox(
                    width: width / 26,
                    height: width / 26,
                    child: Container(
                      alignment: AlignmentDirectional.center,
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(width / 52),
                        color: red,
                      ),
                      child: Text(
                        textAlign: TextAlign.left,
                        widget.person.nMessages.toString(),
                        style: SafeGoogleFont('Poppins',
                            fontSize: 0.0307 * width,
                            fontWeight: FontWeight.w700,
                            height: 1.5 * ffem / fem,
                            color: Colors.white),
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

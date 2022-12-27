import 'package:bloodify_front_end/models/event.dart';
import 'package:bloodify_front_end/models/event_model.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:bloodify_front_end/shared/Constatnt/fonts.dart';
import 'package:bloodify_front_end/shared/styles/container.dart';
import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';
import 'package:intl/intl.dart';
import 'package:maps_launcher/maps_launcher.dart';

class UserEventTile extends StatefulWidget {
  final Event_model event;
  final Position position;
  const UserEventTile(this.event, this.position, {super.key});

  @override
  createState() => _EventTile();
}

class _EventTile extends State<UserEventTile> {
  @override
  Widget build(BuildContext context) {
    final width = MediaQuery.of(context).size.width;
    final height = MediaQuery.of(context).size.height;

    return Container(
      margin: const EdgeInsets.only(left: 20, right: 20),
      child: TileContainer(
          onTap: () => _onTap(context),
          height: height,
          width: width,
          child: SizedBox(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  maxLines: 2,
                  overflow: TextOverflow.ellipsis,
                  "#${widget.event.event_ID}",
                  style: VerySmallStyle(width, Colors.black),
                ),
                SizedBox(
                    width: 0.95 * width,
                    child: FittedBox(
                        alignment: Alignment.centerLeft,
                        fit: BoxFit.scaleDown,
                        child: Text(
                          widget.event.title,
                          textAlign: TextAlign.start,
                          style: NormalStyle(height, Colors.black),
                        ))),
                Row(crossAxisAlignment: CrossAxisAlignment.start, children: [
                  Container(
                      margin: EdgeInsets.only(right: 0.0282 * width),
                      width: width / 39,
                      height: height / 52,
                      child: Icon(
                        Icons.location_pin,
                        size: 0.05 * width,
                        color: blue,
                      )),
                  Expanded(
                    child: Text(widget.event.location!,
                        maxLines: 2,
                        overflow: TextOverflow.ellipsis,
                        style: SmallStyle(width, Colors.black)),
                  ),
                ]),
                Row(
                  children: [
                    Text(
                      "Distance:  ",
                      style: SmallBoldStyle(width, Colors.black),
                    ),
                    Text(
                      "${(Geolocator.distanceBetween(widget.event.latitude, widget.event.longitude, widget.position.latitude, widget.position.longitude) / 1000).toStringAsPrecision(3)} km",
                      style: SmallStyle(width, Colors.black),
                    ),
                  ],
                ),
                Row(
                  children: [
                    Text(
                      "Ends:  ",
                      style: SmallBoldStyle(width, Colors.black),
                    ),
                    Text(
                      dateFormat.format(widget.event.endDate),
                      style: SmallStyle(width, Colors.black),
                    ),
                  ],
                ),
              ],
            ),
          )),
    );
  }

  void _onTap(BuildContext context) {
    final width = MediaQuery.of(context).size.width;
    final height = MediaQuery.of(context).size.height;
    StyledBottomSheet(
      context: context,
      children: [
        Text(
          "#${widget.event.event_ID}",
          style: SmallStyle(width, blue),
        ),
        Text(
          widget.event.title,
          style: BigStyle(height, Colors.black),
        ),
        if (widget.event.location != null && widget.event.location!.isNotEmpty)
          Text(
            "Location:",
            style: SmallBoldStyle(width, blue),
          ),
        if (widget.event.location != null && widget.event.location!.isNotEmpty)
          Text(
            widget.event.location ?? "",
            style: SmallStyle(width, Colors.black),
          ),
        Container(
          height: 0.01 * height,
        ),
        Row(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            SizedBox(
              width: 0.45 * width,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    "Start Date",
                    style: SmallStyle(width, blue),
                  ),
                  Text(
                    DateFormat('d-M-yyyy').format(widget.event.startDate),
                    style: SmallStyle(width, Colors.black),
                  )
                ],
              ),
            ),
            SizedBox(
              width: 0.45 * width,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    "End Date",
                    style: SmallStyle(width, blue),
                  ),
                  Text(
                    DateFormat('d-M-yyyy').format(widget.event.endDate),
                    style: SmallStyle(width, Colors.black),
                  )
                ],
              ),
            ),
          ],
        ),
        Container(
          height: 0.01 * height,
        ),
        Row(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            SizedBox(
              width: 0.45 * width,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    "Start Time",
                    style: SmallStyle(width, blue),
                  ),
                  Text(
                    DateFormat('h:mm a').format(widget.event.startWorkingHour),
                    style: SmallStyle(width, Colors.black),
                  )
                ],
              ),
            ),
            SizedBox(
              width: 0.45 * width,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    "End Time",
                    style: SmallStyle(width, blue),
                  ),
                  Text(
                    DateFormat('h:mm a').format(widget.event.endWorkingHour),
                    style: SmallStyle(width, Colors.black),
                  )
                ],
              ),
            ),
          ],
        ),
        Container(
          height: 0.01 * height,
        ),
        TextButton(
            onPressed: () {
              MapsLauncher.launchCoordinates(widget.event.latitude,
                  widget.event.longitude, widget.event.location);
            },
            style: TextButton.styleFrom(
                backgroundColor: blue,
                minimumSize: Size(width * 0.9, height * 0.05),
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(width / 26))),
            child: Text(
              "Directions",
              style: NormalStyle(height, Colors.white),
            ))
      ],
    );
  }
}

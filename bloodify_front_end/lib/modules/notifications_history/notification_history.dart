import 'package:bloodify_front_end/modules/BloodFinding/bloc/blood_finder_service.dart';
import 'package:bloodify_front_end/shared/Constatnt/Component.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:geolocator/geolocator.dart';
import 'package:intl/intl.dart';

import '../../models/notification.dart';
import '../../shared/Constatnt/fonts.dart';
import '../../shared/Constatnt/userInfo.dart';
import '../../shared/styles/container.dart';
import 'notification_history_cubit/notification_history_cubit.dart';
import 'notification_history_cubit/notification_history_states.dart';

Widget buildNotification(
  NotificationBody notification,
  height,
  width,
) {
  return TileContainer(
      height: height,
      width: width,
      child: Container(
        child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                "${notification.acceptorName} needs your help",
                maxLines: 2,
                overflow: TextOverflow.ellipsis,
                style: const TextStyle(
                  color: Colors.black,
                  fontSize: 16,
                  fontWeight: FontWeight.bold,
                ),
              ),
              const SizedBox(
                height: 15,
              ),
              Text(
                "The institute name ${notification.instituteName}",
                style: const TextStyle(
                  color: Colors.black,
                  fontSize: 15,
                ),
                maxLines: 2,
                overflow: TextOverflow.ellipsis,
              ),
              const SizedBox(
                height: 15,
              ),
              Text(
                  "expiry time ${DateFormat('yyyy-MM-dd – kk:mm').format(notification.lastTime)}"),
              const SizedBox(
                height: 10,
              ),
              Text("${notification.distance.toStringAsFixed(2)} KM"),
              const SizedBox(
                height: 10,
              ),
              TextButton(
                  onPressed: () {},
                  // deletePost(context),
                  style: TextButton.styleFrom(
                      backgroundColor: Colors.red,
                      minimumSize: Size(width * 0.9, height * 0.05),
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(width / 26))),
                  child: Text(
                    "Accept Post",
                    style: NormalStyle(height, Colors.white),
                  ))
            ]),
      ),
      onTap: () {
        // TODO
      });
}

class NotificationHistory extends StatelessWidget {
  const NotificationHistory({super.key});

  @override
  Widget build(BuildContext context) {
    double width = MediaQuery.of(context).size.width;
    double height = MediaQuery.of(context).size.height;
    return BlocConsumer<NotificationHistoryCubit, NotificationStates>(
        listener: (context, state) {},
        builder: (context, state) {
          var cubit = NotificationHistoryCubit.get(context);
          var notifications = cubit.notifications;
          print("buider");
          print(UserInfo.deviceToken);
          print(notifications);
          return Scaffold(
            appBar: AppBar(
              backgroundColor: const Color.fromARGB(255, 255, 78, 66),
              title: Row(children: [
                ClipRRect(
                  borderRadius: BorderRadius.circular(70),
                  child: Container(
                    width: 40,
                    height: 40,
                    decoration: const BoxDecoration(
                        color: Colors.white,
                        image: DecorationImage(
                            image: AssetImage(
                                'assets/icons/blood-removebg-preview.ico'),
                            fit: BoxFit.contain)),
                  ),
                ),
                const SizedBox(
                  width: 10,
                ),
                const Text(
                  'Notification',
                  style: TextStyle(color: Colors.white),
                )
              ]),
            ),
            body: Container(
              margin: const EdgeInsets.all(20),
              child: RefreshIndicator(
                onRefresh: () async {
                  cubit.getNotifications();
                },
                child: ListView.separated(
                  itemBuilder: (context, index) {
                    return buildNotification(
                        notifications[index], height, width);
                  },
                  separatorBuilder: (context, index) =>
                      const SizedBox(height: 10),
                  itemCount: notifications.length,
                ),
              ),
            ),
          );
        });
  }
}

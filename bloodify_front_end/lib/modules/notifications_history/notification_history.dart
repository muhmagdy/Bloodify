import 'package:bloodify_front_end/modules/notifications_history/notification_history_cubit/notification_history_states.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/container.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../shared/Constatnt/fonts.dart';
import 'notification_history_cubit/notification_history_cubit.dart';

Widget buildNotificatio(Notification notification) {
  return Container(
    height: 200,
    width: double.infinity,
    color: Colors.purple,
    child: const Text("Hello! i am inside a container!",
        style: TextStyle(fontSize: 20)),
  );
}

class NotificationHistory extends StatelessWidget {
  const NotificationHistory({super.key});

  @override
  Widget build(BuildContext context) {
    return BlocConsumer<NotificationHistoryCubit, NotificationStates>(
        listener: (context, state) {},
        builder: (context, state) {
          var cubit = NotificationHistoryCubit.get(context);
          var notifications = cubit.notifications;
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
            body: RefreshIndicator(
              onRefresh: () async {
                cubit.getNotifications();
              },
              child: ListView.separated(
                itemBuilder: (context, index) {
                  return Container();
                },
                separatorBuilder: (context, index) =>
                    const SizedBox(height: 10),
                itemCount: notifications.length,
              ),
            ),
          );
        });
  }
}

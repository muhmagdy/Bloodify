import 'package:firebase_core/firebase_core.dart';
import 'package:firebase_messaging/firebase_messaging.dart';
import 'package:flutter_local_notifications/flutter_local_notifications.dart';
import 'package:geolocator/geolocator.dart';
import 'package:notification_permissions/notification_permissions.dart';

import '../../models/push_notification.dart';
import '../../modules/BloodFinding/bloc/blood_finder_service.dart';
import 'dart:math' show cos, sqrt, asin;

enum PermissionStatus { provisional, granted, unknown, denied }

int i = 0;

Future<bool> isInRange(PushNotification notification) async {
  var location = await getLocation();
  var dist = Geolocator.distanceBetween(location.latitude, location.longitude,
          notification.latitude, notification.longtitude) /
      1000;
  print(dist);
  return (Geolocator.distanceBetween(location.latitude, location.longitude,
              notification.latitude, notification.longtitude) /
          1000 <
      50);
}

Future<void> _firebaseMessagingBackgroundHandler(RemoteMessage message) async {
  print(message.data);
  if (message.data["latitude"] != null) {
    return;
  }
  PushNotification notification = PushNotification.fromJson(message.data);
  bool inRange = await isInRange(notification);
  if (inRange) {
    LocalNotificationService ls = LocalNotificationService();

    print("sending.....");
    await ls.intialize();
    await ls.showNotification(
        id: i,
        title: "call of duty",
        body:
            "Some one need your help in hosbital ${notification.instituteName}");
    i++;
  } else {
    print("No");
  }
  print(message.data);
}

class NotificationIntalizor {
  static NotificationIntalizor? _object;
  static NotificationIntalizor? getObject() {
    if (_object == null) {
      _object = NotificationIntalizor._();
      return _object;
    }
    return _object;
  }

  NotificationIntalizor._();
  Future<void> start() async {
    await Firebase.initializeApp();
    FirebaseMessaging messaging = FirebaseMessaging.instance;
    NotificationSettings settings = await messaging.requestPermission(
      alert: true,
      announcement: false,
      badge: true,
      carPlay: false,
      criticalAlert: false,
      provisional: false,
      sound: true,
    );
    await NotificationPermissions.getNotificationPermissionStatus()
        .then((value) => null);

    if (settings.authorizationStatus == AuthorizationStatus.authorized) {
      print('User granted permission');
    } else if (settings.authorizationStatus ==
        AuthorizationStatus.provisional) {
      print('User granted provisional permission');
    } else {
      print('User declined or has not accepted permission');
    }

    FirebaseMessaging.onMessage.listen((RemoteMessage message) async {
      print(message.data);
      if (message.data["latitude"] != null) {
        return;
      }
      PushNotification notification = PushNotification.fromJson(message.data);
      bool inRange = await isInRange(notification);
      print("sending.....");

      if (inRange) {
        print("sending.....");
        LocalNotificationService ls = LocalNotificationService();
        await ls.intialize();
        await ls.showNotification(
            id: i,
            title: "call of duty",
            body:
                "Some one need your help in hosbital ${notification.instituteName}");
        i++;
      } else {
        print("No");
      }
    });
    print('User granted permission: ${settings.authorizationStatus}');
    FirebaseMessaging.onBackgroundMessage(_firebaseMessagingBackgroundHandler);
  }
}

class LocalNotificationService {
  LocalNotificationService();

  final _localNotificationService = FlutterLocalNotificationsPlugin();

  Future<void> intialize() async {
    const AndroidInitializationSettings androidInitializationSettings =
        AndroidInitializationSettings('@drawable/ic_stat_android');

    const InitializationSettings settings = InitializationSettings(
      android: androidInitializationSettings,
    );

    await _localNotificationService.initialize(
      settings,
    );
  }

  Future<NotificationDetails> _notificationDetails() async {
    const AndroidNotificationDetails androidNotificationDetails =
        AndroidNotificationDetails('channel_id', 'channel_name',
            channelDescription: 'description',
            importance: Importance.max,
            priority: Priority.max,
            playSound: true);

    return const NotificationDetails(
      android: androidNotificationDetails,
    );
  }

  Future<void> showNotification({
    required int id,
    required String title,
    required String body,
  }) async {
    final details = await _notificationDetails();
    await _localNotificationService.show(id, title, body, details);
  }

  Future<void> showNotificationWithPayload(
      {required int id,
      required String title,
      required String body,
      required String payload}) async {
    final details = await _notificationDetails();
    await _localNotificationService.show(id, title, body, details,
        payload: payload);
  }

  void onDidReceiveLocalNotification(
      int id, String? title, String? body, String? payload) {
    print('id $id');
  }

  void onSelectNotification(String? payload) {
    print('payload $payload');
    if (payload != null && payload.isNotEmpty) {
      // onNotificationClick.add(payload);
    }
  }
}

import 'package:bloodify_front_end/modules/notifications_history/notification_history_cubit/notification_history_states.dart';
import 'package:bloodify_front_end/shared/Constatnt/userInfo.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:geolocator/geolocator.dart';
import 'package:http/http.dart';

import '../../../models/notification.dart';

class NotificationHistoryCubit extends Cubit<NotificationStates> {
  NotificationHistoryCubit() : super(NotificationIntialState());
  var notifications = [];
  static NotificationHistoryCubit get(context) => BlocProvider.of(context);
  void getNotifications() {
    print("called get ");
    emit(NotificationLoadingState());
    DioHelper.getData(url: '/user/notification/getNotifications', query: {})
        .then((value) {
      // print(UserInfo.token);
      print("length ======= ${value.data['notificationResponses'].length}");
      this.notifications = [];

      for (int i = 0; i < value.data['notificationResponses'].length; i++) {
        print(value.data['notificationResponses'][i]);
        NotificationBody notificationBody =
            NotificationBody.fromJson(value.data['notificationResponses'][i]);
        double distance = Geolocator.distanceBetween(
                UserInfo.location!.latitude,
                UserInfo.location!.longitude,
                notificationBody.latitude,
                notificationBody.longitude) /
            1000;
        notificationBody.distance = distance;
        print("Body");
        print(notificationBody);

        notifications.add(notificationBody);
      }
      print(notifications);
      emit(NotificationSuccesState());
    }).catchError((onError) {
      this.notifications = [];
      emit(NotificationErrorState(onError.toString()));
    });
  }
}

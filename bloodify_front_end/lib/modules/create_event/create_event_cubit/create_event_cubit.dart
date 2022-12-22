import 'package:bloodify_front_end/modules/create_event/create_event_cubit/create_event_states.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:open_street_map_search_and_pick/open_street_map_search_and_pick.dart';

import '../../../models/event_creation_resp.dart';

class CreateEventCubit extends Cubit<CreateEventStates> {
  CreateEventCubit() : super(CreateEventIntialState());
  DateTime startDate = DateTime.now();
  static CreateEventCubit get(context) => BlocProvider.of(context);
  var location = LatLong(31.2082599, 29.920789376803622);
  String? address;
  // ignore: non_constant_identifier_names
  void change_date(value) {
    startDate = value;
    emit(CreateEventChangeStartDateState());
  }

  void change_location(address, latitude, longitude) {
    this.address = address;
    location = LatLong(latitude, longitude);
    emit(CreateEventChangeStartDateState());
  }

  late EventCreationReponse response;
  void createEvent({title, fromDate, toDate, startWorking, endWorking}) {
    emit(CreateEventLoadingState());
    DioHelper.postData(url: "/institution/event", data: {
      "title": title,
      "startDate": fromDate,
      "endDate": toDate,
      "startWorkingHour": startWorking,
      "endWorkingHour": endWorking,
      "location": address,
      "longitude": location.longitude,
      "latitude": location.latitude
    }).then((value) {
      response = EventCreationReponse.fromJson(value.data);
      emit(CreateEventSuccessState(response));
    }).catchError((error) {
      if (error.response.statusCode == 406) {
        // print(error.response);
        emit(CreateEventSuccessState(
            EventCreationReponse.fromJson(error.response.data)));
      } else {
        // print(error.response.statusCode);
        emit(CreateEventErrorState(error.toString()));
      }
    });
  }
}

import '../../../models/event_creation_resp.dart';

abstract class CreateEventStates {}

class CreateEventIntialState extends CreateEventStates {}

class CreateEventChangeStartDateState extends CreateEventStates {}

class CreateEventChangeLocationDateState extends CreateEventStates {}

class CreateEventLoadingState extends CreateEventStates {}

class CreateEventSuccessState extends CreateEventStates {
  final EventCreationReponse eventCreationReponse;

  CreateEventSuccessState(this.eventCreationReponse);
}

class CreateEventErrorState extends CreateEventStates {
  final String error;

  CreateEventErrorState(this.error);
}

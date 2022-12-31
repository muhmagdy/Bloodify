abstract class NotificationStates {}

class NotificationIntialState extends NotificationStates {}

class NotificationLoadingState extends NotificationStates {}

class NotificationSuccesState extends NotificationStates {}

class NotificationErrorState extends NotificationStates {
  final String error;

  NotificationErrorState(this.error);
}

part of 'stats_cubit.dart';


@immutable
abstract class StatsState {
  List<RequestedOrAvailableBlood> get availableBlood => [];
  List<RequestedOrAvailableBlood> get requestedBlood => [];
  List<TransactionBlood> get donationAndTransactionBlood => [];
}

class StatsInitial extends StatsState {}

class StatsLoading extends StatsState{}

class StatsLoaded extends StatsState{
  final List<RequestedOrAvailableBlood> requestedBlood;
  final List<RequestedOrAvailableBlood> availableBlood;
  final List<TransactionBlood> donationAndTransactionBlood;

  StatsLoaded(this.requestedBlood, this.availableBlood,
      this.donationAndTransactionBlood);

  List<RequestedOrAvailableBlood> get_requested_blood() => requestedBlood;
  List<RequestedOrAvailableBlood> get_available_blood() => availableBlood;
  List<TransactionBlood> get_donations_transactions() => donationAndTransactionBlood;

}

class StatsError extends StatsState{}



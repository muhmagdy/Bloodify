import 'package:bloodify_front_end/modules/transactions_modules/event_transaction/cubit/eventTransaction_states.dart';

import 'package:flutter_bloc/flutter_bloc.dart';

class EventTransactionCubit extends Cubit<EventTransactionStates> {
  // ignore: prefer_typing_uninitialized_variables
  var event;
  EventTransactionCubit() : super(EventTransactioIntialState());

  EventTransactionCubit get(context) => BlocProvider.of(context);
}

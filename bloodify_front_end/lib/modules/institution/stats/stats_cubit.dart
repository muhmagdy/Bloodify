import 'package:bloc/bloc.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:meta/meta.dart';
import 'package:bloodify_front_end/modules/institution/stats/chart_item.dart';
import 'package:bloodify_front_end/modules/institution/stats/stats_service.dart';


part 'stats_state.dart';

class StatsCubit extends Cubit<StatsState> {
  StatsCubit() : super(StatsLoading()){
    loadStats();
  }

  static StatsCubit get(context) => BlocProvider.of(context);

  void loadStats() async{
    try{
      emit(StatsLoading());
      List<RequestedOrAvailableBlood> requestedBags = await getRequestedTypes();
      List<RequestedOrAvailableBlood> availableBags = await getAvailableBags();
      List<TransactionBlood> transactionBags = await getTransactionBags();
      emit(StatsLoaded(requestedBags, availableBags, transactionBags));
      // emit(StatsLoaded(requestedBags, const [], const []));
    }catch(e){
      emit(StatsError());
    }
  }

}




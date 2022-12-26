// ignore_for_file: prefer_typing_uninitialized_variables

import 'package:bloodify_front_end/models/postBrief.dart';
import 'package:bloodify_front_end/modules/transactions_modules/post_transaction/cubit/post_transaction_state.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../../models/transaction_response.dart';
import '../../../../shared/network/remote/dio_helper.dart';

class PostTransactionCubit extends Cubit<PostTransactionStates> {
  var post;
  PostTransactionCubit() : super(PostTransactionIntialState());
  static PostTransactionCubit get(context) => BlocProvider.of(context);
  TransactionResponse? response;
  void postTransaction(String donorNationalID) {
    emit(PostTransactionLoadingState());
    DioHelper.postData(url: '/institution/transaction/user-to-user', data: {
      "donorNationalID": donorNationalID,
      "postID": post.id,
    })
        .then((value) => {
              response = TransactionResponse.fromJson(value.data),
              emit(PostTransactionSuccessState(response!))
            })
        .catchError((onError) {
      print("error $onError");
      PostTransactionErrorState(onError.toString());
    });
  }
}

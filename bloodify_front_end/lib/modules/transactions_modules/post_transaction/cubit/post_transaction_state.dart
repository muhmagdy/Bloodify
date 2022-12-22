import '../../../../models/transaction_response.dart';

abstract class PostTransactionStates {}

class PostTransactionIntialState extends PostTransactionStates {}

class PostTransactionLoadingState extends PostTransactionStates {}

class PostTransactionSuccessState extends PostTransactionStates {
  final TransactionResponse response;

  PostTransactionSuccessState(this.response);
}

class PostTransactionErrorState extends PostTransactionStates {
  final String error;
  PostTransactionErrorState(this.error);
}

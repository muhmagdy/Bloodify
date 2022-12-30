import 'package:bloodify_front_end/shared/Constatnt/Component.dart';
import 'package:flutter/material.dart';

import '../../../../models/transaction_response.dart';

abstract class PostTransactionStates {}

class PostTransactionIntialState extends PostTransactionStates {}

class PostTransactionLoadingState extends PostTransactionStates {}

class PostTransactionSuccessState extends PostTransactionStates {
  final String response;

  PostTransactionSuccessState(this.response) {
    showToast(text: response, color: Colors.blue, time: 3000);
  }
}

class PostTransactionErrorState extends PostTransactionStates {
  final String error;
  PostTransactionErrorState(this.error) {
    showToast(text: error, color: Colors.red, time: 3000);
  }
}

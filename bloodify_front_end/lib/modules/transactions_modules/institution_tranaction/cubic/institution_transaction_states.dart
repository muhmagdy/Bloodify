// ignore_for_file: prefer_typing_uninitialized_variables

import 'package:bloodify_front_end/models/transaction_response.dart';
import 'package:dio/dio.dart';

abstract class InstituteTransactionStates {}

class InstituteTransactionIntialState extends InstituteTransactionStates {}

class InstituteTransactionChangeScreenState
    extends InstituteTransactionStates {}

class InstituteTransactionChangeBloodBagsCountState
    extends InstituteTransactionStates {}

class InstituteTransactionChangeBloodType extends InstituteTransactionStates {}

class InstituteTransactionUserToInstituteSuccess
    extends InstituteTransactionStates {
  final TransactionResponse resp;

  InstituteTransactionUserToInstituteSuccess(this.resp);
}

class InstituteTransactionUserToInstituteError
    extends InstituteTransactionStates {
  final String error;
  InstituteTransactionUserToInstituteError(this.error);
}

class InstituteTransactionUserToInstituteLoading
    extends InstituteTransactionStates {}

class InstituteTransactionInstituteToUserSuccess
    extends InstituteTransactionStates {
  final TransactionResponse resp;
  InstituteTransactionInstituteToUserSuccess(this.resp);
}

class InstituteTransactionInstituteToUserError
    extends InstituteTransactionStates {
  final String error;
  InstituteTransactionInstituteToUserError(this.error);
}

class InstituteTransactionInstituteToUserLoading
    extends InstituteTransactionStates {}

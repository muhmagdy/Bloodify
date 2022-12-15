import 'package:bloodify_front_end/modules/transaction_UI/cubit/transaction_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../transactions_modules/institution_to_user.dart';
import '../transactions_modules/user_to_institution.dart';
import '../transactions_modules/user_to_user.dart';

class TransactionCubit extends Cubit<TransactionStates> {
  var currentIndex = 0;
  var transactions = [
    "User to User",
    "User to Institutions",
    "Institution to user"
  ];
  List<Widget> screens = [
    TransactionUserToUser(),
    TransactionUserToInstitution(),
    TransactionInstitutionToUser(),
  ];

  TransactionCubit() : super(TransactionIntialState());
  bool toUser = true;
  static TransactionCubit get(context) => BlocProvider.of(context);
  void changeScreen(int index) {
    currentIndex = index;
    emit(TransactionChangeNavigatorBar());
  }
}

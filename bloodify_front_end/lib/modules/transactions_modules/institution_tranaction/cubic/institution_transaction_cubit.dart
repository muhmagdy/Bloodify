// ignore_for_file: prefer_typing_uninitialized_variables, camel_case_types

import 'package:dio/dio.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../../models/transaction_response.dart';
import '../../../../shared/network/remote/dio_helper.dart';
import '../view/institution_to_user.dart';
import '../view/user_to_institution.dart';
import 'institution_transaction_states.dart';

class InstituteTransactionCubit extends Cubit<InstituteTransactionStates> {
  var currentIndex = 0;
  double bloodBags = 0.0;
  var currentBloodType = 0;
  List<String> types = ['A+', 'B+', 'O+', 'AB+', 'A-', 'B-', 'O-', 'AB-'];
  var post;
  var transactions = ["User To Institute", "Institute To User"];
  List<Widget> screens = [
    UserToInstitute(),
    InstituteToUser(),
  ];

  InstituteTransactionCubit() : super(InstituteTransactionIntialState());

  static InstituteTransactionCubit get(context) => BlocProvider.of(context);
  void changeScreen(int index) {
    currentIndex = index;
    emit(InstituteTransactionChangeScreenState());
  }

  void changeBloodType(String value) {
    currentBloodType = types.indexOf(value);
    emit(InstituteTransactionChangeBloodType());
  }

  void changeBloodBagsCount(value) {
    bloodBags = value;
    emit(InstituteTransactionChangeBloodBagsCountState());
  }

  TransactionResponse? response;
  void submitInstitutetoUser({required String id}) {
    emit(InstituteTransactionInstituteToUserLoading());
    DioHelper.postData(url: '/institution/transaction/instToUser', data: {
      "acceptorNationalID": id,
      "bloodType": types[currentBloodType],
      "bagsCount": bloodBags
    })
        .then((value) => {
              response = TransactionResponse.fromJson(value.data),
              emit(InstituteTransactionInstituteToUserSuccess(response!))
            })
        .catchError((onError) {
      print(onError.toString());
      InstituteTransactionInstituteToUserError(onError.toString());
    });
  }

  void submitUsertoInstitute({required String id}) {
    emit(InstituteTransactionUserToInstituteLoading());
    DioHelper.postData(url: '/institution/transaction/UserToinst', data: {
      "donorNationalID": id,
      "bloodType": types[currentBloodType],
    })
        .then((value) => {
              response = TransactionResponse.fromJson(value.data),
              emit(InstituteTransactionUserToInstituteSuccess(response!))
            })
        .catchError((onError) {
      print(onError.toString());
      InstituteTransactionUserToInstituteError(onError.toString());
    });
  }
}

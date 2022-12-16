// ignore_for_file: prefer_typing_uninitialized_variables

import 'package:bloodify_front_end/modules/transactions_modules/post_transaction/cubit/post_state.dart';
import 'package:bloodify_front_end/modules/transactions_modules/post_transaction/institution_to_user/institutionToUser_module.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../../models/available.dart';
import '../user_to_user/userToUser_module.dart';

class PostCubit extends Cubit<PostStates> {
  var currentIndex = 1;
  var post;
  var transactions = ["User to User", "Institution to user"];
  List<Available> l = [
    Available("A+", 5),
    Available("B+", 6),
    Available("B+", 6),
    Available("B+", 6),
    Available("B+", 6),
    Available("B+", 6),
    Available("B+", 6)
  ];
  List<Widget> screens = [
    UserToUser(),
    InstitutionToUser(),
  ];

  PostCubit() : super(PostIntialState());
  static PostCubit get(context) => BlocProvider.of(context);
  void changeScreen(int index) {
    currentIndex = index;
    emit(PostIntialChangeScreenState());
  }
}

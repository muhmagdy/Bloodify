// ignore_for_file: avoid_print

import 'package:bloc/bloc.dart';
import 'package:bloodify_front_end/modules/Chat/bloc/chat_bloc.dart';

class MyBlocObserver extends BlocObserver {
  @override
  void onCreate(BlocBase bloc) {
    super.onCreate(bloc);
    print('onCreate -- ${bloc.runtimeType}');
  }

  @override
  void onChange(BlocBase bloc, Change change) {
    super.onChange(bloc, change);

    // print('onChange -- ${bloc.runtimeType}, $change');
  }

  @override
  void onError(BlocBase bloc, Object error, StackTrace stackTrace) {
    print('onError -- ${bloc.runtimeType}, $error');
    super.onError(bloc, error, stackTrace);
  }

  @override
  void onClose(BlocBase bloc) {
    if (bloc.runtimeType == ChatCubit) {
      ChatCubit cubit = bloc as ChatCubit;
      cubit.disconnect();
    }
    super.onClose(bloc);
    print('onClose -- ${bloc.runtimeType}');
  }
}

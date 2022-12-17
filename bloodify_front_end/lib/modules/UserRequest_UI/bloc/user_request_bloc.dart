import 'package:bloodify_front_end/modules/UserRequest_UI/bloc/user_request_state.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class UserRequestFormCubit extends Cubit<UserRequestFormStates> {
  UserRequestFormCubit() : super(UserRequestFormInitialState());
  static UserRequestFormCubit get(context) => BlocProvider.of(context);
}

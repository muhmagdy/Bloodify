import 'package:bloodify_front_end/models/found_institution.dart';
import 'package:bloodify_front_end/modules/BloodFinding/bloc/blood_finder_service.dart';
import 'package:equatable/equatable.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:geolocator/geolocator.dart';

part 'blood_finder_state.dart';

class BloodFinderCubit extends Cubit<BloodFinderState> {
  BloodFinderCubit() : super(BloodFinderState.initial());
  static BloodFinderCubit get(context) => BlocProvider.of(context);

  void changedBloodType(String value) {
    emit(state.copyWith(pickedBloodType: value));
  }

  Future<void> findInstitutions() async {
    if (state.pickedBloodType == null) {
      emit(BloodFinderState.noBloodTypePicked());

      emit(BloodFinderState.initial());
      return;
    }
    var currState = state.copyWith();

    emit(BloodFinderState.findingInstitutions());
    emit(state.copyWith(pickedBloodType: currState.pickedBloodType));

    List<FoundInstitutionWithDistance> foundInstitutions =
        await find_Institutions(currState.pickedBloodType);

    emit(BloodFinderState.findingInstitutionsSuccess(
            institutions: foundInstitutions)
        .copyWith(pickedBloodType: currState.pickedBloodType));
  }
}

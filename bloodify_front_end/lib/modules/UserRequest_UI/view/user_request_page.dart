import 'package:bloodify_front_end/modules/UserRequest_UI/bloc/user_request_cubit.dart';
import 'package:bloodify_front_end/modules/signUP_UI/sign_up_pages/Languages.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:dropdown_search/dropdown_search.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
// import 'package:flutter_datetime_picker/flutter_datetime_picker.dart';
import 'package:flutter_spinbox/material.dart';
import 'package:intl/intl.dart';
import 'package:omni_datetime_picker/omni_datetime_picker.dart';

Language language = EnglishLanguage();
var dateFormat = new DateFormat("E, d MMM yyy, h:mm a");

class UserRequestForm extends StatelessWidget {
  UserRequestForm({super.key});
  var title = "Post Request";

  @override
  Widget build(BuildContext context) {
    return BlocConsumer<UserRequestFormCubit, UserRequestFormState>(
        builder: (context, state) {
          return Scaffold(
              backgroundColor: Colors.white,
              resizeToAvoidBottomInset: false,
              appBar: AppBar(
                backgroundColor: defaultColor,
                title: Text(
                  title,
                  style: TextStyle(color: Colors.white),
                ),
              ),
              body: BlocProvider(
                create: (_) => UserRequestFormCubit(),
                child: _UserRequestForm(),
              ));
        },
        listener: (context, state) {});
  }
}

class _UserRequestForm extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    double width = MediaQuery.of(context).size.width;
    double height = MediaQuery.of(context).size.height;
    return SingleChildScrollView(
      padding: EdgeInsets.symmetric(vertical: height * 0.01, horizontal: 8),
      child: Padding(
        padding: const EdgeInsets.all(10.0),
        child: Form(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  _BloodTypeDropDown(width),
                  _BloodBagsCounter(width),
                ],
              ),
              _RequestExpiration(),
              _InstitutionPicker(),
              _SubmitButton(width)
            ],
          ),
        ),
      ),
    );
  }
}

class _BloodTypeDropDown extends StatelessWidget {
  final double width;
  final String bloodType = "Blood Type";

  const _BloodTypeDropDown(this.width);

  @override
  Widget build(BuildContext context) {
    final List<String> types =
        context.select((UserRequestFormCubit cubit) => cubit.state.bloodTypes);

    bloodTypeChanged(String value) {
      print(value);
      UserRequestFormCubit.get(context).changeBloodType(value);
    }

    return SizedBox(
      width: (width - 50) / 2,
      child: makeDropDown(bloodType, types, bloodTypeChanged),
    );
  }
}

class _BloodBagsCounter extends StatelessWidget {
  final double width;
  final String bloodBags = "Blood bags";

  const _BloodBagsCounter(this.width);

  @override
  Widget build(BuildContext context) {
    changedBloodCount(double value) {
      UserRequestFormCubit.get(context).changeBloodBagsCountChan(value);
    }

    return SizedBox(
      width: (width - 50) / 2,
      child: SpinBox(
        decoration: InputDecoration(labelText: bloodBags),
        min: 1,
        max: 4,
        value: context
            .select((UserRequestFormCubit cubit) => cubit.state.bloodBagsCount),
        onChanged: (value) => {changedBloodCount(value)},
      ),
    );
  }
}

class _RequestExpiration extends StatelessWidget {
  final String postExpiration = "Post Expiration Date";
  @override
  Widget build(BuildContext context) {
    changeRequestExpiration(DateTime value) {
      UserRequestFormCubit.get(context).changeRequestExpiryDate(value);
    }

    return makeDatePicker(context, postExpiration, changeRequestExpiration);
  }
}

class _InstitutionPicker extends StatelessWidget {
  final String institutionLabel = "Institution";

  @override
  Widget build(BuildContext context) {
    UserRequestFormCubit.get(context).loadInstitutions();

    List<String> institutions = context
        .select((UserRequestFormCubit cubit) => cubit.state.institutions);
    institutionChanged(String value) {
      UserRequestFormCubit.get(context).changeInstitution(value);
    }

    return makeDropDown(institutionLabel, institutions, institutionChanged);
  }
}

class _SubmitButton extends StatelessWidget {
  final double width;
  final String buttonText = "Post";
  const _SubmitButton(this.width);

  @override
  Widget build(BuildContext context) {
    void submit() {
      if (UserRequestFormCubit.get(context).validate()) {
        UserRequestFormCubit.get(context).submit();
      } else {
        showError(context, "Form is incomplete!");
      }
    }

    return Padding(
      padding: const EdgeInsets.all(15.0),
      child: Row(mainAxisAlignment: MainAxisAlignment.end, children: [
        SizedBox(
            width: width / 5,
            child: ElevatedButton(onPressed: submit, child: Text(buttonText)))
      ]),
    );
  }
}

Padding makeDropDown(
    String title, List<String> list, Function(String value) changed) {
  return Padding(
      padding: const EdgeInsets.all(10.0),
      child: DropdownSearch<String>(
        popupProps: PopupProps.menu(
            showSelectedItems: true,
            showSearchBox: true,
            searchFieldProps: TextFieldProps(
                decoration: InputDecoration(
                    enabledBorder: UnderlineInputBorder(
                        borderSide: BorderSide(color: defaultColor))))),
        items: list,
        dropdownDecoratorProps: DropDownDecoratorProps(
            dropdownSearchDecoration: InputDecoration(
              labelText: title,
            ),
            baseStyle: TextStyle(fontSize: 18)),
        onChanged: (value) => {changed(value!)},
      ));
}

Center makeDatePicker(
    BuildContext context, String labelText, Function(DateTime value) changed) {
  var now = DateTime.now(), requestDuration = const Duration(days: 30);
  return Center(
      child: TextField(
    controller: TextEditingController()
      ..text = context.select((UserRequestFormCubit cubit) {
        if (cubit.state.expiryDate != null) {
          return dateFormat
              .format(cubit.state.expiryDate ?? DateTime.now())
              .toString();
        }
        return "";
      }),
    decoration: InputDecoration(
        icon: const Icon(Icons.calendar_today), labelText: labelText),
    readOnly: true,
    onTap: () async {
      DateTime? pickedDate = await showOmniDateTimePicker(
          context: context,
          startInitialDate: now,
          startFirstDate: now,
          startLastDate: now.add(requestDuration));

      if (pickedDate != null) {
        if (pickedDate.isAfter(DateTime.now())) {
          changed(pickedDate);
        } else {
          showError(context, 'Invalid Date');
        }
      }
    },
  ));
}

void showError(context, String errMsg) {
  ScaffoldMessenger.of(context).showSnackBar(SnackBar(
    duration: Duration(seconds: 5),
    content: Text(errMsg),
  ));
}

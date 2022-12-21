// ignore_for_file: must_be_immutable

import 'package:bloodify_front_end/modules/create_event/create_event_cubit/create_event_cubit.dart';
import 'package:bloodify_front_end/modules/create_event/create_event_cubit/create_event_states.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:intl/intl.dart';

import 'package:open_street_map_search_and_pick/open_street_map_search_and_pick.dart';

import '../../shared/Constatnt/Component.dart';
import '../../shared/Constatnt/colors.dart';

class CreateEvent extends StatelessWidget {
  double getTime(String timeOfDay) {
    var split = timeOfDay.split(":");
    var time;
    if (split[1].split(" ")[1] == "PM") {
      time = TimeOfDay(
          hour: int.parse(split[0]) + 12,
          minute: int.parse(split[1].split(" ")[0]));
    } else {
      time = TimeOfDay(
          hour: int.parse(split[0]), minute: int.parse(split[1].split(" ")[0]));
    }
    return time.hour + time.minute / 60.0;
  }

  var formKey = GlobalKey<FormState>();
  var titleController = TextEditingController();
  var fromDate = TextEditingController();
  var toDate = TextEditingController();
  var startWorking = TextEditingController();
  var endWorking = TextEditingController();
  CreateEvent({super.key});
  @override
  Widget build(BuildContext context) {
    CreateEventCubit cubit = CreateEventCubit.get(context);

    return BlocConsumer<CreateEventCubit, CreateEventStates>(
      listener: (context, state) {
        if (state is CreateEventSuccessState) {
          if (state.eventCreationReponse.state!) {
            showToast(
                text: state.eventCreationReponse.message!,
                color: Colors.green,
                time: 1000);
          } else {
            showToast(
                text: state.eventCreationReponse.message!,
                color: Colors.red,
                time: 1000);
          }
        }
      },
      builder: (context, state) {
        return Scaffold(
          appBar: AppBar(
            backgroundColor: const Color.fromARGB(255, 255, 78, 66),
            title: Row(children: [
              ClipRRect(
                borderRadius: BorderRadius.circular(70),
                child: Container(
                  width: 40,
                  height: 40,
                  decoration: const BoxDecoration(
                      color: Colors.white,
                      image: DecorationImage(
                          image: AssetImage(
                              'assets/icons/blood-removebg-preview.ico'),
                          fit: BoxFit.contain)),
                ),
              ),
              const SizedBox(
                width: 10,
              ),
              const Text(
                'Create event',
                style: TextStyle(color: Colors.white),
              )
            ]),
          ),
          body: SingleChildScrollView(
            child: Form(
              key: formKey,
              child: Container(
                margin: const EdgeInsets.all(20),
                child: Column(
                    mainAxisAlignment: MainAxisAlignment.start,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      const Text(
                        "Create Event",
                        style: TextStyle(
                          fontSize: 30,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      const SizedBox(
                        height: 20,
                      ),
                      defaultInputText(
                          controller: titleController,
                          validate: (String val) {
                            if (val.isEmpty) {
                              return "cann't be empty";
                            }
                            return null;
                          },
                          labelText: "Event title"),
                      const SizedBox(
                        height: 20,
                      ),
                      SizedBox(
                        height: 80,
                        child: Row(
                          children: [
                            Expanded(
                              child: TextFormField(
                                validator: (String? value) {
                                  if (value!.isEmpty) return "can't be empty";
                                  return null;
                                },
                                onTap: () {
                                  DateTime now = DateTime.now();
                                  showDatePicker(
                                    context: context,
                                    initialDate: DateTime(
                                        now.year, now.month, now.day + 3),
                                    firstDate:
                                        DateTime(now.year, now.month, now.day),
                                    lastDate: DateTime(
                                        now.year + 1, now.month, now.day),
                                  ).then((value) => {
                                        if (value != null)
                                          {
                                            print(value),
                                            print(value),
                                            cubit.change_date(value),
                                            fromDate.text =
                                                dateFormat.format(value),
                                          }
                                      });
                                },
                                controller: fromDate,
                                keyboardType: TextInputType.datetime,
                                decoration: InputDecoration(
                                  prefix: const Icon(
                                    Icons.calendar_month_outlined,
                                    color: Color.fromARGB(255, 255, 78, 66),
                                  ),
                                  labelText: "start time",
                                  focusedBorder: const OutlineInputBorder(
                                      borderSide:
                                          BorderSide(color: Colors.grey)),
                                  border: OutlineInputBorder(
                                      borderRadius: BorderRadius.circular(10)),
                                ),
                              ),
                            ),
                            const SizedBox(
                              width: 10,
                            ),
                            Expanded(
                              child: TextFormField(
                                validator: (String? value) {
                                  if (value!.isEmpty) return "can't be empty";
                                  return null;
                                },
                                onTap: () {
                                  DateTime now = DateTime.now();
                                  DateTime start =
                                      CreateEventCubit.get(context).startDate;
                                  toDate.text = dateFormat.format(DateTime(
                                      start.year, start.month, start.day + 3));
                                  showDatePicker(
                                    context: context,
                                    initialDate: DateTime(
                                        start.year, start.month, start.day + 3),
                                    firstDate: DateTime(
                                        start.year, start.month, start.day + 2),
                                    lastDate: DateTime(
                                        start.year + 1, start.month, start.day),
                                  ).then((value) => {
                                        if (value != null)
                                          {
                                            toDate.text =
                                                dateFormat.format(value),
                                          }
                                      });
                                },
                                controller: toDate,
                                keyboardType: TextInputType.datetime,
                                decoration: InputDecoration(
                                  prefix: const Icon(
                                    Icons.calendar_month_outlined,
                                    color: Color.fromARGB(255, 255, 78, 66),
                                  ),
                                  labelText: "end time",
                                  focusedBorder: const OutlineInputBorder(
                                      borderSide:
                                          BorderSide(color: Colors.grey)),
                                  border: OutlineInputBorder(
                                      borderRadius: BorderRadius.circular(10)),
                                ),
                              ),
                            ),
                          ],
                        ),
                      ),
                      const SizedBox(
                        height: 20,
                      ),
                      SizedBox(
                        height: 80,
                        child: Row(
                          children: [
                            Expanded(
                              child: TextFormField(
                                validator: (String? value) {
                                  if (value!.isEmpty) return "can't be empty";
                                  return null;
                                },
                                onTap: () {
                                  DateTime now = DateTime.now();
                                  var time;
                                  showTimePicker(
                                    context: context,
                                    initialTime: TimeOfDay(
                                        hour: now.hour, minute: now.minute),
                                  ).then((value) => {
                                        if (value != null)
                                          {
                                            time = DateTime(
                                                now.year,
                                                now.month,
                                                now.day,
                                                value.hour,
                                                value.minute),
                                            startWorking.text =
                                                DateFormat('hh:mm aa')
                                                    .format(time),
                                          }
                                      });
                                },
                                controller: startWorking,
                                keyboardType: TextInputType.datetime,
                                decoration: InputDecoration(
                                  prefix: const Icon(
                                    Icons.timer_outlined,
                                    color: Color.fromARGB(255, 255, 78, 66),
                                  ),
                                  labelText: "start working time",
                                  focusedBorder: const OutlineInputBorder(
                                      borderSide:
                                          BorderSide(color: Colors.grey)),
                                  border: OutlineInputBorder(
                                      borderRadius: BorderRadius.circular(10)),
                                ),
                              ),
                            ),
                            const SizedBox(
                              width: 10,
                            ),
                            Expanded(
                              child: TextFormField(
                                validator: (String? value) {
                                  endWorking;
                                  if (startWorking.text.isEmpty) {
                                    return "not valid";
                                  }
                                  if (endWorking.text.isEmpty) {
                                    return "not valid";
                                  }
                                  double startInhour =
                                      getTime(startWorking.text);
                                  double endInhour = getTime(endWorking.text);
                                  if (value!.isEmpty) {
                                    return "can't be empty";
                                  } else if (startInhour >= endInhour) {
                                    return 'not valid working hours';
                                  }
                                  return null;
                                },
                                onTap: () {
                                  DateTime now = DateTime.now();
                                  var time;

                                  showTimePicker(
                                    context: context,
                                    initialTime: TimeOfDay(
                                        hour: now.hour, minute: now.minute),
                                  ).then((value) => {
                                        if (value != null)
                                          {
                                            time = DateTime(
                                                now.year,
                                                now.month,
                                                now.day,
                                                value.hour,
                                                value.minute),
                                            endWorking.text =
                                                DateFormat('hh:mm aa')
                                                    .format(time),
                                          }
                                      });
                                },
                                controller: endWorking,
                                keyboardType: TextInputType.datetime,
                                decoration: InputDecoration(
                                  prefix: const Icon(
                                    Icons.timer_outlined,
                                    color: Color.fromARGB(255, 255, 78, 66),
                                  ),
                                  labelText: "end time",
                                  focusedBorder: const OutlineInputBorder(
                                      borderSide:
                                          BorderSide(color: Colors.grey)),
                                  border: OutlineInputBorder(
                                      borderRadius: BorderRadius.circular(10)),
                                ),
                              ),
                            ),
                          ],
                        ),
                      ),
                      const SizedBox(
                        height: 20,
                      ),
                      Column(
                        children: [
                          const SizedBox(
                            height: 5,
                          ),
                          const Text("Select Location"),
                          const SizedBox(
                            height: 5,
                          ),
                          Container(
                            decoration: BoxDecoration(
                              border: Border.all(width: 10, color: Colors.grey),
                              color: const Color.fromARGB(255, 237, 237, 237),
                              shape: BoxShape.circle,
                            ),
                            child: IconButton(
                              iconSize: 80,
                              icon: const Icon(
                                Icons.gps_fixed_rounded,
                                color: Colors.pink,
                              ),
                              onPressed: () {
                                showModalBottomSheet(
                                  context: context,
                                  builder: (context) =>
                                      OpenStreetMapSearchAndPick(
                                          center: cubit.location,
                                          buttonColor: Colors.blue,
                                          buttonText: 'Select location',
                                          onPicked: (pickedData) {
                                            print(pickedData.latLong);
                                            cubit.change_location(
                                                pickedData.address,
                                                pickedData.latLong.latitude,
                                                pickedData.latLong.longitude);
                                            Navigator.pop(context);
                                          }),
                                );
                              },
                            ),
                          ),
                        ],
                      ),
                      const SizedBox(
                        height: 20,
                      ),
                      defaultButton(
                          onClick: () {
                            if (formKey.currentState!.validate()) {
                              cubit.createEvent(
                                title: titleController.text,
                                fromDate: fromDate.text,
                                toDate: toDate.text,
                                startWorking: startWorking.text,
                                endWorking: endWorking.text,
                              );
                            }
                          },
                          text: "Create Event",
                          merginLeft: 0.0,
                          merginRight: 0.0),
                    ]),
              ),
            ),
          ),
        );
      },
    );
  }
}

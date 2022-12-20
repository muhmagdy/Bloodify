import 'package:bloodify_front_end/models/found_institution.dart';
import 'package:bloodify_front_end/modules/BloodFinding/bloc/blood_finder_cubit.dart';
import 'package:bloodify_front_end/modules/UserRequest_UI/view/user_request_page.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:flutter/material.dart';

import 'package:flutter_bloc/flutter_bloc.dart';

class BloodFinder extends StatelessWidget {
  const BloodFinder({super.key});

  @override
  Widget build(BuildContext context) {
    double width = MediaQuery.of(context).size.width;
    double height = MediaQuery.of(context).size.height;

    return BlocConsumer<BloodFinderCubit, BloodFinderState>(
      builder: (context, state) {
        var pageTitle = "Blood Finder";
        return Scaffold(
          resizeToAvoidBottomInset: false,
          appBar: AppBar(
            title: Text(pageTitle, style: TextStyle(color: Colors.white)),
            backgroundColor: defaultColor,
          ),
          body: SingleChildScrollView(
            child: Padding(
              padding: EdgeInsets.all(12.0),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.start,
                children: [
                  _CreatePostButton(width: width),
                  _SearchBar(width: width),
                  _SearchResults()
                ],
              ),
            ),
          ),
        );
      },
      listener: (context, state) {},
    );
  }
}

class _CreatePostButton extends StatelessWidget {
  const _CreatePostButton({
    Key? key,
    required this.width,
  }) : super(key: key);

  final double width;

  @override
  Widget build(BuildContext context) {
    var buttonText = "Create Post";
    return SizedBox(
        width: width * 0.7,
        child: ElevatedButton(
            onPressed: () => createPost(context), child: Text(buttonText)));
  }

  void createPost(BuildContext context) {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => UserRequestForm()),
    );
  }
}

class _SearchBar extends StatelessWidget {
  const _SearchBar({Key? key, required this.width}) : super(key: key);

  final double width;

  @override
  Widget build(BuildContext context) {
    void find() {
      if (BloodFinderCubit.get(context).state.pickedBloodType == null) {
        var errMsg = "No Blood Type was Picked!";
        showError(context, errMsg);
      } else {
        BloodFinderCubit.get(context).findInstitutions();
      }
    }

    void bloodTypeChanged(dynamic value) {
      BloodFinderCubit.get(context).changedBloodType(value);
    }

    var bloodTypelabel = "Blood type";
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: [
        SizedBox(
          width: (width - 50) / 2,
          child: makeDropDown(bloodTypelabel,
              BloodFinderCubit.get(context).state.bloodTypes, bloodTypeChanged),
        ),
        IconButton(onPressed: find, icon: Icon(Icons.search_rounded))
      ],
    );
  }
}

class _SearchResults extends StatelessWidget {
  const _SearchResults({
    Key? key,
  }) : super(key: key);

  Card makeInstitutionCard(FoundInstitutionWithDistance inst) {
    Function formatter = (double x) => '${x.ceil()} m';
    if (inst.getDistance() >= 1000) {
      formatter = (double x) => '${(x / 1000).toStringAsFixed(2)} Km';
    }
    return Card(
      child: Padding(
        padding: const EdgeInsets.all(10.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  InstitutionName(institutionName: inst.getName()),
                  DistanceWidget(
                      formatter: formatter, distance: inst.getDistance())
                ],
              ),
            ),
            InstitutionAddress(institutionLocation: inst.getLocation()),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: AvailableBloodTypesWidget(
                availableBloodTypes: inst.getBloodBags().entries,
              ),
            ),
          ],
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      shrinkWrap: true,
      itemBuilder: (context, i) {
        return Padding(
          padding: const EdgeInsets.all(8.0),
          child: makeInstitutionCard(
              BloodFinderCubit.get(context).state.foundInstitutions[i]),
        );
      },
      itemCount: BloodFinderCubit.get(context).state.foundInstitutions.length,
    );
  }
}

class InstitutionName extends StatelessWidget {
  const InstitutionName({
    Key? key,
    required this.institutionName,
  }) : super(key: key);

  final String institutionName;

  @override
  Widget build(BuildContext context) {
    return Text(
      institutionName,
      style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold),
    );
  }
}

class InstitutionAddress extends StatelessWidget {
  const InstitutionAddress({
    Key? key,
    required this.institutionLocation,
  }) : super(key: key);

  final String institutionLocation;

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        Icon(Icons.location_on_outlined),
        Text(institutionLocation),
      ],
    );
  }
}

class AvailableBloodTypesWidget extends StatelessWidget {
  final Iterable<MapEntry> availableBloodTypes;
  const AvailableBloodTypesWidget({Key? key, required this.availableBloodTypes})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Wrap(
        children: availableBloodTypes
            .map((e) => Padding(
                  padding: const EdgeInsets.symmetric(
                      vertical: 5.0, horizontal: 8.0),
                  child: Text('${e.key}: ${e.value}'),
                ))
            .toList());
  }
}

class DistanceWidget extends StatelessWidget {
  const DistanceWidget(
      {Key? key, required this.formatter, required this.distance})
      : super(key: key);

  final Function formatter;
  final double distance;

  @override
  Widget build(BuildContext context) {
    if (distance == -1) return Row();
    return Row(
      children: [
        Text(formatter(distance)),
        Icon(Icons.directions_walk_outlined),
      ],
    );
  }
}
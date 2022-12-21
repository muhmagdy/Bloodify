import 'package:bloodify_front_end/models/BloodRequest.dart';
import 'package:bloodify_front_end/models/found_institution.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
import 'package:dio/dio.dart';

Future<List<InstitutionBrief>> getInstitutions() async {
  String url = "user/blood/allInstitutions";
  Response response = await DioHelper.getData(url: url, query: {});
  if (response.statusCode == 200) {
    return List.from(response.data.map((e) => InstitutionBrief.fromJson(e)));
  } else {
    throw Exception("Error Occured");
  }
}

Future<bool> createRequest(BloodRequest bloodRequest) async {
  String url = "user/posting/add";
  print(bloodRequest);
  try {
    Response response =
        await DioHelper.postData(url: url, data: bloodRequest.toJson());
    return response.statusCode == 201;
  } catch (e) {
    print(e);
    return false;
  }
}




  // return await Future.delayed(
  //     Duration(seconds: 5),
  //     (() => [
  //           InstitutionBrief(institutionID: 1, name: "one", location: "alex"),
  //           InstitutionBrief(institutionID: 2, name: "two", location: "alex"),
  //           InstitutionBrief(
  //               institutionID: 3, name: "three", location: "alex"),
  //           InstitutionBrief(
  //               institutionID: 4, name: "four", location: "alex"),
  //           InstitutionBrief(
  //               institutionID: 5, name: "five", location: "alex"),
  //           InstitutionBrief(institutionID: 6, name: "six", location: "alex"),
  //           InstitutionBrief(
  //               institutionID: 7, name: "seven", location: "alex"),
  //           InstitutionBrief(
  //               institutionID: 8, name: "eight", location: "alex"),
  //           InstitutionBrief(
  //               institutionID: 9, name: "nine", location: "alex"),
  //         ]));
  // }
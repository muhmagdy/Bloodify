import 'package:bloodify_front_end/modules/institution/stats/chart_item.dart';
import 'package:dio/dio.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';



Future<List<RequestedOrAvailableBlood>> getRequestedTypes() async{
  // return []; ///to be deleted once backend endpoint is ready
  String url = "/institution/stats/postBags";
  Response response = await DioHelper.getData(url: url, query: {});
  // print(response);
  if (response.statusCode == 200) {
    return List.from(response.data["responseBody"].map((e) => RequestedOrAvailableBlood.fromJson(e)));
  } else {
    throw Exception("Error Occurred");
  }
}

Future<List<RequestedOrAvailableBlood>> getAvailableBags() async{
  // return []; ///to be deleted once backend endpoint is ready
  String url = "institution/stats/institutionBags";
  Response response = await DioHelper.getData(url: url, query: {});
  // print(response.data);
  if (response.statusCode == 200) {
    return List.from(response.data["responseBody"].map((e) => RequestedOrAvailableBlood.fromJson(e)));
  } else {
    throw Exception("Error Occurred");
  }
}

Future<List<TransactionBlood>> getTransactionBags() async{
  // return []; ///to be deleted once backend endpoint is ready
  String url = "institution/stats/collectedBagsFromUser";
  Response response = await DioHelper.getData(url: url, query: {});
  print(response.data);
  var donations, allTranasactons;
  if (response.statusCode == 200) {
    donations = List.from(response.data["responseBody"].map((e) => RequestedOrAvailableBlood.fromJson(e)));
  } else {
    throw Exception("Error Occurred");
  }
  url = "institution/stats/collectedBagsFromAllTransactions";
  response = await DioHelper.getData(url: url, query: {});
  // print(response.data);
  if (response.statusCode == 200) {
    allTranasactons = List.from(response.data["responseBody"].map((e) => RequestedOrAvailableBlood.fromJson(e)));
  } else {
    throw Exception("Error Occurred");
  }
  var toReturn = <TransactionBlood>[];
  for(int i = 0; i < 8; i++) {
    toReturn.add(TransactionBlood((donations[i] as RequestedOrAvailableBlood).type,
                  (donations[i] as RequestedOrAvailableBlood).bags,
                  (allTranasactons[i] as RequestedOrAvailableBlood).bags));
  }
  return toReturn;
}


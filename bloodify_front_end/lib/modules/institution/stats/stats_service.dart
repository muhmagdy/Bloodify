import 'package:bloodify_front_end/modules/institution/stats/chart_item.dart';
import 'package:dio/dio.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';



Future<List<RequestedOrAvailableBlood>> getRequestedTypes() async{
  return []; ///to be deleted once backend endpoint is ready
  String url = "";
  Response response = await DioHelper.getData(url: url, query: {});
  if (response.statusCode == 200) {
    return List.from(response.data.map((e) => RequestedOrAvailableBlood.fromJson(e)));
  } else {
    throw Exception("Error Occurred");
  }
}

Future<List<RequestedOrAvailableBlood>> getAvailableBags() async{
  return []; ///to be deleted once backend endpoint is ready
  String url = "";
  Response response = await DioHelper.getData(url: url, query: {});
  if (response.statusCode == 200) {
    return List.from(response.data.map((e) => RequestedOrAvailableBlood.fromJson(e)));
  } else {
    throw Exception("Error Occurred");
  }
}

Future<List<TransactionBlood>> getTransactionBags() async{
  return []; ///to be deleted once backend endpoint is ready
  String url = "";
  Response response = await DioHelper.getData(url: url, query: {});
  if (response.statusCode == 200) {
    return List.from(response.data.map((e) => TransactionBlood.fromJson(e)));
  } else {
    throw Exception("Error Occurred");
  }
}


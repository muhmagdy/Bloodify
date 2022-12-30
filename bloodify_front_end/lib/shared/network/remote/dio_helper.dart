import 'dart:convert';

import 'package:bloodify_front_end/shared/Constatnt/userInfo.dart';
import 'package:dio/dio.dart';

class DioHelper {
  static String? token = UserInfo.token;
  static bool? isUser = UserInfo.isUser;
  static Dio? dio;

  static init() {
    dio = Dio(
      BaseOptions(
        // baseUrl:
        // "https://7722b390-519c-4d05-810f-90091b05282c.mock.pstmn.io/api/v1/",
        baseUrl: 'http://192.168.1.113:8080/api/v1/',
        receiveDataWhenStatusError: true,
        headers: {
          'Content-Type': 'application/json',
        },
      ),
    );
  }

  static Future<Response> getData({
    required String url,
    required Map<String, dynamic> query,
    String lang = 'en',
  }) async {
    if (token == null) {
      dio!.options.headers = {
        'Content-Type': 'application/json',
        'lang': lang,
      };
    } else {
      dio!.options.headers = {
        'Content-Type': 'application/json',
        'lang': lang,
        'Authorization': "Bearer $token",
      };
    }
    return await dio!.get(
      url,
      queryParameters: query,
    );
  }

  static Future<Response> postData({
    required String url,
    Map<String, dynamic>? query,
    required Map<String, dynamic> data,
    String lang = 'en',
  }) async {
    String? auth;
    if (token == null) {
      dio!.options.headers = {
        'Content-Type': 'application/json',
        'lang': lang,
      };
    } else {
      dio!.options.headers = {
        'Content-Type': 'application/json',
        'lang': lang,
        'Authorization': "Bearer $token",
      };
    }
    print(dio!.options.headers);
    return dio!.post(
      url,
      queryParameters: query,
      data: data,
    );
  }
  
  static Future<Response> patchData({
    required String url,
    Map<String, dynamic>? query,
    required Map<String, dynamic> data,
    String lang = 'en',
  }) async {
    String? auth;
    if (token == null) {
      dio!.options.headers = {
        'Content-Type': 'application/json',
        'lang': lang,
      };
    } else {
      dio!.options.headers = {
        'Content-Type': 'application/json',
        'lang': lang,
        'Authorization': "Bearer $token",
      };
    }
    print(dio!.options.headers);
    return dio!.patch(
      url,
      queryParameters: query,
      data: data,
    );
  }


  static Future<Response> deleteData({
    required String url,
    Map<String, dynamic>? query,
    required Map<String, dynamic> data,
    String lang = 'en',
  }) async {
    String? auth;
    if (token == null) {
      dio!.options.headers = {
        'Content-Type': 'application/json',
        'lang': lang,
      };
    } else {
      dio!.options.headers = {
        'Content-Type': 'application/json',
        'lang': lang,
        'Authorization': "Bearer $token",
      };
    }
    print(dio!.options.headers);
    return dio!.delete(
      url,
      queryParameters: query,
      data: data,
    );
  }

  static Future<Response> postLogin({
    required String url,
    required email,
    required password,
    Map<String, dynamic>? data,
  }) async {
    var auth = 'Basic ${base64Encode(utf8.encode('$email:$password'))}';
    print(auth);
    dio!.options.headers = {
      'authorization': auth,
      'Content-Type': 'application/json',
    };
    if (data == null) {
      return dio!.post(
        url,
      );
    }
    return dio!.post(url, data: data);
  }

  static Future<Response> putData({
    required String url,
    required Map<String, dynamic> data,
    Map<String, dynamic>? query,
    String lang = 'en',
    String? token,
  }) async {
    dio!.options.headers = {
      'lang': lang,
      'Authorization': token ?? '',
      'Content-Type': 'application/json',
    };

    return dio!.put(
      url,
      queryParameters: query,
      data: data,
    );
  }
}

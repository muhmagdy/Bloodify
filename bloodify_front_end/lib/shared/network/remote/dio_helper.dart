import 'dart:convert';

import 'package:dio/dio.dart';
import 'package:flutter/cupertino.dart';

import '../../Constatnt/userInfo.dart';

class DioHelper {
  static Dio? dio;

  static init() {
    dio = Dio(
      BaseOptions(
        baseUrl: 'http://192.168.1.5:8080/api/v1/',
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
    String? token,
  }) async {
    dio!.options.headers = {
      'lang': lang,
      'Authorization': token,
    };

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

  static Future<Response> postLogin({
    required String url,
    required email,
    required password,
  }) async {
    var auth = 'Basic ' + base64Encode(utf8.encode('$email:$password'));
    print(auth);
    dio!.options.headers = {
      'authorization': auth,
      'Content-Type': 'application/json',
    };

    return dio!.post(
      url,
    );
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

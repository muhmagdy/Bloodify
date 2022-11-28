import 'package:shared_preferences/shared_preferences.dart';

class CachHelper {
  static SharedPreferences? sharedPreferences;
  static init() async {
    sharedPreferences = await SharedPreferences.getInstance();
  }

  static Future<bool> putString(String key, String value) async {
    return await sharedPreferences!.setString(key, value);
  }

  static String? getData(String key) {
    return sharedPreferences!.getString(key);
  }
}

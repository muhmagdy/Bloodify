import 'package:bloodify_front_end/models/chat_message.dart';
import 'package:bloodify_front_end/modules/Chat/bloc/chat_bloc.dart';
import 'package:bloodify_front_end/shared/network/remote/dio_helper.dart';
import 'package:dio/src/response.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:socket_io_client/socket_io_client.dart' as IO;
import 'package:socket_io_client/socket_io_client.dart';

class ChatService {
  static String socketUrl = "ws://192.168.249.67:8085";
  static String restUrl = "user/chat/messages";

  static IO.Socket? socket;

  // static late ChatService instance;

  static init() {
    // if (instance == null) instance = ChatService();
  }

  static void socketConnect(int postID, int donorID, ChatCubit cubit) {
    // if (socket != null) return;
    String room = '${postID}_$donorID';

    socket = IO.io(
        socketUrl,
        OptionBuilder()
            .setTransports(['websocket'])
            .enableForceNew()
            .enableReconnection()
            .setQuery({'room': room})
            .build());
    socket!.onConnect((_) {
      print('connect');
    });
    socket!.on('get_message', (data) => cubit.updateMessages(data));

    socket!.onDisconnect((_) => print('disconnect'));
  }

  static void disconnect() {
    socket!.dispose();
    print("===== Disconnected =====");
    socket = null;
  }

  static void sendMessage(ChatMessage message) {
    socket!.emit("send_message", message.toJson());
  }

  static Future<List<ChatMessage>> loadMessages(
      int? postID, int? donorID) async {
    if (postID == null || donorID == null) return [];
    print("loading messages...");

    Response response = await DioHelper.getData(
        url: restUrl, query: {"postID": postID, "donorID": donorID});

    print("messages loaded.");
    if (response.statusCode == 200) {
      return List.from(response.data.map((e) => ChatMessage.fromJson(e)));
    } else {
      throw Exception("Error Occured");
    }
  }

  // static Future<List<ChatMessage>> loadMessages(
  //     int? postID, int? donorID) async {
  //   if (postID == null || donorID == null) return [];
  //   return msgs
  //       .asMap()
  //       .entries
  //       .map((entry) => ChatMessage(
  //           messageID: entry.key,
  //           postID: 2,
  //           donorID: 3,
  //           direction: entry.key % 2 == 0,
  //           content: entry.value,
  //           timestamp: DateTime.now()
  //               .subtract(Duration(days: msgs.length - entry.key))))
  //       .toList()
  //       .reversed
  //       .toList();
  // }
}

// List<String> msgs = '''We're no strangers to love
// You know the rules and so do I (do I)
// A full commitment's what I'm thinking of
// You wouldn't get this from any other guy
// I just wanna tell you how I'm feeling
// Gotta make you understand
// Never gonna give you up
// Never gonna let you down
// Never gonna run around and desert you
// Never gonna make you cry
// Never gonna say goodbye
// Never gonna tell a lie and hurt you
// We've known each other for so long
// Your heart's been aching, but you're too shy to say it (say it)
// Inside, we both know what's been going on (going on)
// We know the game and we're gonna play it
// And if you ask me how I'm feeling
// Don't tell me you're too blind to see
// Never gonna give you up
// Never gonna let you down
// Never gonna run around and desert you
// Never gonna make you cry
// Never gonna say goodbye
// Never gonna tell a lie and hurt you
// Never gonna give you up
// Never gonna let you down
// Never gonna run around and desert you
// Never gonna make you cry
// Never gonna say goodbye
// Never gonna tell a lie and hurt you
// We've known each other for so long
// Your heart's been aching, but you're too shy to say it (to say it)
// Inside, we both know what's been going on (going on)
// We know the game and we're gonna play it
// I just wanna tell you how I'm feeling
// Gotta make you understand
// Never gonna give you up
// Never gonna let you down
// Never gonna run around and desert you
// Never gonna make you cry
// Never gonna say goodbye
// Never gonna tell a lie and hurt you
// Never gonna give you up
// Never gonna let you down
// Never gonna run around and desert you
// Never gonna make you cry
// Never gonna say goodbye
// Never gonna tell a lie and hurt you
// Never gonna give you up
// Never gonna let you down
// Never gonna run around and desert you
// Never gonna make you cry
// Never gonna say goodbye
// Never gonna tell a lie and hurt you'''
//     .split("\n");

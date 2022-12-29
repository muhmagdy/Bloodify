import 'package:bloodify_front_end/modules/UserRequest_UI/user_request.dart';
import 'package:intl/intl.dart';

class ChatMessage {
  int messageID, postID, donorID;
  bool direction;
  String content;
  DateTime timestamp;

  ChatMessage(
      {required this.messageID,
      required this.postID,
      required this.donorID,
      required this.direction,
      required this.content,
      required this.timestamp});

  static var dateFormat = DateFormat("yyyy-MM-dd hh:mm:ss");

  Map<String, dynamic> toJson() {
    return Map.of({
      "messageID": messageID,
      "postID": postID,
      "donorID": donorID,
      "direction": direction,
      "content": content,
      "timestamp": dateFormat.format(timestamp),
    });
  }

  static ChatMessage fromJson(Map<String, dynamic> json) {
    return ChatMessage(
        messageID: json['messageID'],
        postID: json['postID'],
        donorID: json['donorID'],
        direction: json['direction'],
        content: json['content'],
        timestamp: dateFormat.parse(json['timestamp']));
  }
}

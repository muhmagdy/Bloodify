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

  var dateFormat = DateFormat("yyyy-MM-ddThh:mm:ss");

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
}

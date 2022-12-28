import 'package:bloc/bloc.dart';
import 'package:bloodify_front_end/models/chat_message.dart';
import 'package:bloodify_front_end/shared/Constatnt/Component.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:equatable/equatable.dart';
import 'package:flutter/cupertino.dart';

part 'chat_state.dart';

class ChatCubit extends Cubit<ChatState> {
  ChatCubit(super.initialState);

  bool whoAmI(bool direction) {
    return state.myID == state.donorID && !direction ||
        state.myID != state.donorID && direction;
  }

  validateMessage(String? value) {
    if (value == null || value.isEmpty) return "";
  }

  Future<void> loadMessages() async {
    //TODO: load message

    List<String> msgs = '''We're no strangers to love
You know the rules and so do I (do I)
A full commitment's what I'm thinking of
You wouldn't get this from any other guy
I just wanna tell you how I'm feeling
Gotta make you understand
Never gonna give you up
Never gonna let you down
Never gonna run around and desert you
Never gonna make you cry
Never gonna say goodbye
Never gonna tell a lie and hurt you
We've known each other for so long
Your heart's been aching, but you're too shy to say it (say it)
Inside, we both know what's been going on (going on)
We know the game and we're gonna play it
And if you ask me how I'm feeling
Don't tell me you're too blind to see
Never gonna give you up
Never gonna let you down
Never gonna run around and desert you
Never gonna make you cry
Never gonna say goodbye
Never gonna tell a lie and hurt you
Never gonna give you up
Never gonna let you down
Never gonna run around and desert you
Never gonna make you cry
Never gonna say goodbye
Never gonna tell a lie and hurt you
We've known each other for so long
Your heart's been aching, but you're too shy to say it (to say it)
Inside, we both know what's been going on (going on)
We know the game and we're gonna play it
I just wanna tell you how I'm feeling
Gotta make you understand
Never gonna give you up
Never gonna let you down
Never gonna run around and desert you
Never gonna make you cry
Never gonna say goodbye
Never gonna tell a lie and hurt you
Never gonna give you up
Never gonna let you down
Never gonna run around and desert you
Never gonna make you cry
Never gonna say goodbye
Never gonna tell a lie and hurt you
Never gonna give you up
Never gonna let you down
Never gonna run around and desert you
Never gonna make you cry
Never gonna say goodbye
Never gonna tell a lie and hurt you'''
        .split("\n");

    emit(state.copyWith(
        msgs: msgs
            .asMap()
            .entries
            .map((entry) => ChatMessage(
                messageID: entry.key,
                postID: 2,
                donorID: 3,
                direction: entry.key % 2 == 0,
                content: entry.value,
                timestamp: DateTime.now()
                    .subtract(Duration(days: msgs.length - entry.key))))
            .toList()
            .reversed
            .toList()));
  }

  sendMessage() {
    if (state.formKey!.currentState!.validate()) {
      print(state.messageController!.text);
      //TODO: send message
      state.formKey!.currentState!.reset();
      state.messageController!.clear();
      showToast(text: "message sent", color: defaultColor, time: 1);
      emit(state);
    }
  }
}

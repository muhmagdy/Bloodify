import 'package:bloc/bloc.dart';
import 'package:bloodify_front_end/models/chat_message.dart';
import 'package:bloodify_front_end/modules/Chat/bloc/chat_service.dart';
import 'package:bloodify_front_end/modules/Chat/chat.dart';
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

  Future<void> loadMessages(bool update) async {
    emit(state.copyWith(loadingMessages: true));
    List<ChatMessage> chatMessages;
    if (state.postID != null &&
        state.donorID != null &&
        (!state.messagesAreLoaded || update)) {
      chatMessages =
          await ChatService.loadMessages(state.postID, state.donorID);
      emit(state.copyWith(
          msgs: chatMessages, loadingMessages: false, messagesAreLoaded: true));
    }
  }

  updateMessages(Map<String, dynamic> json) {
    if (json['postID'] != state.postID || json['donorID'] != state.donorID)
      return;
    print("updating messages...");
    loadMessages(true);
  }

  sendMessage() {
    if (state.formKey!.currentState!.validate()) {
      print(state.messageController!.text);
      ChatService.sendMessage(ChatMessage(
          messageID: 0,
          postID: state.postID ?? -1,
          donorID: state.donorID ?? -1,
          direction: state.myID != state.donorID,
          content: state.messageController!.text,
          timestamp: DateTime.now()));

      state.formKey!.currentState!.reset();
      state.messageController!.clear();
      showToast(text: "message sent", color: defaultColor, time: 1);
      emit(state);

      // loadMessages();
    }
  }

  void establishConnection() {
    if (state.connected) return;
    print("establishing connection...");
    ChatService.socketConnect(state.postID ?? -1, state.donorID ?? -1, this);
    emit(state.copyWith(connected: true));
  }
}

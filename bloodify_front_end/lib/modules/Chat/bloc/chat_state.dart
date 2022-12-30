part of 'chat_bloc.dart';

class ChatState extends Equatable {
  final List<ChatMessage> msgs;

  final String? name;
  final int? postID, donorID, myID;

  final GlobalKey<FormState>? formKey;

  final TextEditingController? messageController;

  final bool loadingMessages, messagesAreLoaded, connected;

  const ChatState._(
      {this.msgs = const <ChatMessage>[],
      this.name,
      this.donorID,
      this.postID,
      this.myID,
      this.formKey,
      this.messageController,
      this.loadingMessages = false,
      this.messagesAreLoaded = false,
      this.connected = false});

  const ChatState.initial(
      {required String name,
      required int donorID,
      required int postID,
      required int myID,
      required GlobalKey<FormState> formKey,
      required TextEditingController messageController})
      : this._(
            name: name,
            donorID: donorID,
            postID: postID,
            myID: myID,
            formKey: formKey,
            messageController: messageController);

  ChatState copyWith(
      {List<ChatMessage>? msgs,
      String? name,
      int? donorID,
      int? postID,
      int? myID,
      GlobalKey<FormState>? formKey,
      TextEditingController? messageController,
      bool? loadingMessages,
      bool? messagesAreLoaded,
      bool? connected}) {
    return ChatState._(
        msgs: msgs ?? this.msgs,
        name: name ?? name,
        donorID: donorID ?? this.donorID,
        postID: postID ?? this.postID,
        myID: myID ?? this.myID,
        formKey: formKey ?? this.formKey,
        messageController: messageController ?? this.messageController,
        loadingMessages: loadingMessages ?? this.loadingMessages,
        messagesAreLoaded: messagesAreLoaded ?? this.messagesAreLoaded,
        connected: connected ?? this.connected);
  }

  @override
  List<Object?> get props => [msgs, messageController, formKey];
}

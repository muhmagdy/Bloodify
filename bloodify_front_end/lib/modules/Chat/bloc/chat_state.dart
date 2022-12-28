part of 'chat_bloc.dart';

class ChatState extends Equatable {
  final List<ChatMessage> msgs;

  final String? firstName, lastName;
  final int? postID, donorID, myID;

  final GlobalKey<FormState>? formKey;

  final TextEditingController? messageController;

  const ChatState._(
      {this.msgs = const <ChatMessage>[],
      this.firstName,
      this.lastName,
      this.donorID,
      this.postID,
      this.myID,
      this.formKey,
      this.messageController});

  const ChatState.initial(
      {required String firstName,
      required String lastName,
      required int donorID,
      required int postID,
      required int myID,
      required GlobalKey<FormState> formKey,
      required TextEditingController messageController})
      : this._(
            firstName: firstName,
            lastName: lastName,
            donorID: donorID,
            postID: postID,
            myID: myID,
            formKey: formKey,
            messageController: messageController);

  ChatState copyWith(
      {List<ChatMessage>? msgs,
      String? firstName,
      String? lastName,
      int? donorID,
      int? postID,
      int? myID,
      GlobalKey<FormState>? formKey,
      TextEditingController? messageController}) {
    return ChatState._(
        msgs: msgs ?? this.msgs,
        firstName: firstName ?? this.firstName,
        lastName: lastName ?? this.lastName,
        donorID: donorID ?? this.donorID,
        postID: postID ?? this.postID,
        myID: myID ?? this.myID,
        formKey: formKey ?? this.formKey,
        messageController: messageController ?? this.messageController);
  }

  @override
  List<Object?> get props => [msgs, messageController, formKey];
}

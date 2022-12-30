import 'package:bloodify_front_end/models/chat_message.dart';
import 'package:bloodify_front_end/modules/Chat/chat.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:intl/intl.dart';

class ChatScreen extends StatelessWidget {
  final String name;
  final int postID, donorID, myID;
  const ChatScreen(
      {super.key,
      required this.postID,
      required this.donorID,
      required this.myID,
      required this.name});

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
        create: (context) => ChatCubit(ChatState.initial(
            name: name,
            postID: postID,
            donorID: donorID,
            myID: myID,
            formKey: GlobalKey<FormState>(),
            messageController: TextEditingController(text: ""))),
        child: BlocListener<ChatCubit, ChatState>(
          listener: (context, state) {},
          child: _MainChatScreen(),
        ));
  }
}

class _MainChatScreen extends StatelessWidget {
  const _MainChatScreen({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final cubit = context.watch<ChatCubit>();
    cubit.establishConnection();
    return Scaffold(
      appBar: AppBar(
        iconTheme: IconThemeData(color: lightGrey),
        backgroundColor: defaultColor,
        title: Text('${cubit.state.name}'),
      ),
      body: Container(
        height: MediaQuery.of(context).size.height * 0.9,
        // width: MediaQuery.of(context).size.width * 0.9,
        child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              _MessagesList(cubit: cubit),
              Padding(
                padding:
                    const EdgeInsets.symmetric(vertical: 4.0, horizontal: 8.0),
                child: _MessageForm(cubit: cubit),
              )
            ]),
      ),
    );
  }
}

class _MessagesList extends StatelessWidget {
  _MessagesList({
    Key? key,
    required this.cubit,
  }) : super(key: key);

  final ChatCubit cubit;

  final ScrollController _scrollController = ScrollController();

  @override
  Widget build(BuildContext context) {
    cubit.loadMessages(false);

    return Flexible(
      child: ListView.builder(
        reverse: true,
        shrinkWrap: true,
        controller: _scrollController,
        physics: const AlwaysScrollableScrollPhysics(),
        itemBuilder: (context, i) {
          ChatMessage msg = cubit.state.msgs[i];

          Widget msgCard = Padding(
              padding: EdgeInsets.symmetric(vertical: 4.0, horizontal: 8.0),
              child: cubit.whoAmI(cubit.state.msgs[i].direction)
                  ? HomeMessageCard(message: cubit.state.msgs[i])
                  : AwayMessageCard(message: cubit.state.msgs[i]));

          if (i == cubit.state.msgs.length - 1 ||
              !DateUtils.isSameDay(
                  msg.timestamp, cubit.state.msgs[i + 1].timestamp)) {
            var dateFormat = DateFormat("MMM d, yyyy");
            return Column(
              children: [
                _DateSeparatorWidget(dateFormat: dateFormat, msg: msg),
                msgCard
              ],
            );
          }
          return msgCard;
        },
        itemCount: cubit.state.msgs.length,
      ),
    );
  }
}

class _DateSeparatorWidget extends StatelessWidget {
  const _DateSeparatorWidget({
    Key? key,
    required this.dateFormat,
    required this.msg,
  }) : super(key: key);

  final DateFormat dateFormat;
  final ChatMessage msg;

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Card(
        child: Padding(
          padding: const EdgeInsets.symmetric(vertical: 4.0, horizontal: 8.0),
          child: Text(dateFormat.format(msg.timestamp)),
        ),
      ),
    );
  }
}

class _MessageForm extends StatelessWidget {
  const _MessageForm({
    Key? key,
    required this.cubit,
  }) : super(key: key);

  final ChatCubit cubit;

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Form(
        key: cubit.state.formKey,
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            _MessageTextField(cubit: cubit),
            _SendButton(cubit: cubit),
          ],
        ),
      ),
    );
  }
}

class _SendButton extends StatelessWidget {
  const _SendButton({
    Key? key,
    required this.cubit,
  }) : super(key: key);

  final ChatCubit cubit;

  @override
  Widget build(BuildContext context) {
    return IconButton(
      onPressed: () => cubit.sendMessage(),
      // icon: Icon(Icons.send_rounded))
      icon: Icon(Icons.send_rounded),
      color: defaultColor,
    );
  }
}

class _MessageTextField extends StatelessWidget {
  const _MessageTextField({
    Key? key,
    required this.cubit,
  }) : super(key: key);

  final ChatCubit cubit;

  @override
  Widget build(BuildContext context) {
    return Flexible(
      child: Container(
        // height: MediaQuery.of(context).size.height * 0.05,
        width: MediaQuery.of(context).size.width * 0.8,
        decoration: BoxDecoration(borderRadius: BorderRadius.circular(20)),
        constraints:
            BoxConstraints(maxHeight: MediaQuery.of(context).size.height * 0.1),
        child: TextFormField(
          controller: cubit.state.messageController,
          validator: (value) => cubit.validateMessage(value),
          onFieldSubmitted: (value) {
            cubit.sendMessage();
          },
          onEditingComplete: () {},
          textInputAction: TextInputAction.newline,
          keyboardType: TextInputType.multiline,
          maxLines: null,
          maxLength: 250,
          // onChanged: ((value) => {}),
        ),
      ),
    );
  }
}

abstract class MessageCard extends StatelessWidget {
  final ChatMessage message;
  MessageCard({required this.message, super.key});

  MainAxisAlignment getAlignment();

  Color getBackgroundColor();

  Color getTextColor();

  @override
  Widget build(BuildContext context) {
    double width = MediaQuery.of(context).size.width;
    return Container(
      decoration: BoxDecoration(borderRadius: BorderRadius.circular(10)),
      child: Row(
        mainAxisAlignment: getAlignment(),
        children: [
          Flexible(
            child: Card(
              color: getBackgroundColor(),
              child: Container(
                padding: const EdgeInsets.all(8.0),
                constraints: BoxConstraints(maxWidth: width * 0.6),
                child: IntrinsicWidth(
                  child: Column(
                    children: [
                      Container(
                        constraints: BoxConstraints(minWidth: width * 0.12),
                        child: Text(
                          message.content,
                          style: TextStyle(color: getTextColor()),
                          softWrap: true,
                        ),
                      ),
                      Container(
                        padding: EdgeInsets.fromLTRB(0, 2, 0, 0),
                        alignment: Alignment.bottomRight,
                        child: Text(
                          DateFormat("HH:mm").format(message.timestamp),
                          style:
                              TextStyle(color: getTextColor(), fontSize: 12.0),
                        ),
                      )
                    ],
                  ),
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}

class HomeMessageCard extends MessageCard {
  HomeMessageCard({required super.message});

  @override
  MainAxisAlignment getAlignment() {
    return MainAxisAlignment.end;
  }

  @override
  Color getBackgroundColor() {
    return defaultColor;
  }

  @override
  Color getTextColor() {
    return lightGrey;
  }
}

class AwayMessageCard extends MessageCard {
  AwayMessageCard({required super.message});

  @override
  MainAxisAlignment getAlignment() {
    return MainAxisAlignment.start;
  }

  @override
  Color getBackgroundColor() {
    return lightGrey;
  }

  @override
  Color getTextColor() {
    return Colors.black;
  }
}

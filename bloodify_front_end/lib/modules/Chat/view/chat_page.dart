import 'package:bloodify_front_end/models/chat_message.dart';
import 'package:bloodify_front_end/modules/Chat/chat.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/container.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class ChatScreen extends StatelessWidget {
  final String firstName, lastName;
  final int postID, donorID, myID;
  const ChatScreen({
    super.key,
    required this.postID,
    required this.donorID,
    required this.myID,
    required this.firstName,
    required this.lastName,
  });

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
        create: (context) => ChatCubit(ChatState.initial(
            firstName: firstName,
            lastName: lastName,
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
    return Scaffold(
      appBar: AppBar(
        iconTheme: IconThemeData(color: lightGrey),
        backgroundColor: defaultColor,
        title: Text('${cubit.state.firstName} ${cubit.state.lastName}'),
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
  const _MessagesList({
    Key? key,
    required this.cubit,
  }) : super(key: key);

  final ChatCubit cubit;

  @override
  Widget build(BuildContext context) {
    cubit.loadMessages();

    return Flexible(
      child: ListView.builder(
        physics: const AlwaysScrollableScrollPhysics(),
        itemBuilder: (context, i) {
          return Padding(
              padding: EdgeInsets.symmetric(vertical: 4.0, horizontal: 8.0),
              child: cubit.whoAmI(cubit.state.msgs[i].direction)
                  ? HomeMessageCard(message: cubit.state.msgs[i].content)
                  : AwayMessageCard(message: cubit.state.msgs[i].content));
        },
        itemCount: cubit.state.msgs.length,
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
        icon: Icon(Icons.send_rounded));
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
          textInputAction: TextInputAction.send,
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
  final String message;
  MessageCard({required this.message, super.key});

  MainAxisAlignment getAlignment();

  Color getBackgroundColor();

  Color getTextColor();

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(borderRadius: BorderRadius.circular(10)),
      child: Row(
        mainAxisAlignment: getAlignment(),
        children: [
          Flexible(
            child: Card(
              color: getBackgroundColor(),
              child: Padding(
                padding: const EdgeInsets.all(8.0),
                child: Container(
                  constraints: BoxConstraints(
                      maxWidth: MediaQuery.of(context).size.width * 0.6),
                  child: Text(
                    message,
                    style: TextStyle(color: getTextColor()),
                    softWrap: true,
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

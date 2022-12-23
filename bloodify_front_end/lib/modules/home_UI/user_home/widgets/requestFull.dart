import 'package:bloodify_front_end/models/postBrief.dart';
import 'package:bloodify_front_end/models/userBrief.dart';
import 'package:bloodify_front_end/modules/home_UI/user_home/widgets/personTile.dart';
import 'package:bloodify_front_end/modules/home_UI/user_home/widgets/requestHeader.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:bloodify_front_end/shared/Constatnt/fonts.dart';
import 'package:flutter/widgets.dart';

class RequestFullWidget extends StatelessWidget {
  final PostBrief postBrief;
  final List<UserBrief> people;
  final int status;
  const RequestFullWidget(this.postBrief, this.people, this.status,
      {super.key});

  @override
  Widget build(BuildContext context) {
    final height = MediaQuery.of(context).size.height;
    String header = "";
    if (status == 1)
      header = "Possibile Donors";
    else if (status == 2) header = "Requester";
    return Column(
      children: [
        RequestHeader(postBrief),
        if (people.isNotEmpty)
          Text(header,
              textAlign: TextAlign.left,
              style: SafeGoogleFont('Poppins',
                  fontSize: 0.025 * height,
                  fontWeight: FontWeight.w500,
                  height: 1.5,
                  color: grey)),
        ListView.builder(
          physics: const NeverScrollableScrollPhysics(),
          shrinkWrap: true,
          itemCount: people.length,
          itemBuilder: (context, index) => PersonTile(people[index]),
        ),
        Container(
          height: 0.01 * height,
        ),
      ],
    );
  }
}

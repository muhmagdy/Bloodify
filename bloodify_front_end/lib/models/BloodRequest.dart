import 'package:intl/intl.dart';

class BloodRequest {
  final postID;
  final int? institutionID;
  final DateTime? LastUpdateTime;
  final DateTime? expiryTime;
  final int? requiredBags;
  final String? bloodType;

  BloodRequest(
      {this.postID = -1,
      this.institutionID,
      this.LastUpdateTime,
      this.expiryTime,
      this.requiredBags,
      this.bloodType});

  var dateFormat = new DateFormat("yyyy-MM-ddThh:mm:ss");

  Map<String, dynamic> toJson() {
    return Map.of({
      "postID": postID,
      "institutionID": institutionID,
      "LastUpdateTime": dateFormat.format(LastUpdateTime!),
      "expiryTime": dateFormat.format(expiryTime!),
      "requiredBags": requiredBags,
      "bloodType": bloodType
    });
  }
}

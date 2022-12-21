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
}

class BloodRequest {
  int id;
  final String location;
  final String bloodType;
  final int nBags;
  final DateTime dateTime;
  final bool isDonor;

  BloodRequest(this.id, this.location, this.bloodType, this.nBags,
      this.dateTime, this.isDonor);
}

class Transaction {
  int id;
  String name;
  String nationalID;
  DateTime dateTime;
  int count;
  String bloodType;
  String status;

  Transaction(this.id, this.name, this.nationalID, this.dateTime, this.count,
      this.bloodType, this.status);
}

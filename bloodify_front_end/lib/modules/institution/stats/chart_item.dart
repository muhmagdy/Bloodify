class RequestedOrAvailableBlood{
  String type;
  int bags;

  RequestedOrAvailableBlood(this.type, this.bags);
  static RequestedOrAvailableBlood fromJson(Map<String, dynamic> json) {
    return RequestedOrAvailableBlood(json["bloodType"], json["bagsCount"]);
  }
}

class TransactionBlood{
  String type;
  int bagsDonated;
  int bagsTransacted;

  TransactionBlood(this.type, this.bagsDonated, this.bagsTransacted);
  static TransactionBlood fromJson(Map<String, dynamic> json) {
    return TransactionBlood(json["type"], json["bagsDonated"], json["bagsTransacted"]);
  }
}
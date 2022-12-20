class TransactionResponse {
  late bool state;
  late String message;
  TransactionResponse.fromJson(Map<String, dynamic> json) {
    state = json['state'];
    message = json['message'];
  }
}

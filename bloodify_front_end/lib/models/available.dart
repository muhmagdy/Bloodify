// ignore_for_file: non_constant_identifier_names

class Available {
  late String type;
  late int number_of_bags;
  Available(this.type, this.number_of_bags);
  Available.fromJson(Map<String, dynamic> json) {
    type = json['type'];
    number_of_bags = json['number_of_bags'];
  }
}

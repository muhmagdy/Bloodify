// ignore_for_file: non_constant_identifier_names

class Post {
  late int postID;
  late String user_ID;
  late int number_of_bags;
  late String institution_id;
  Post(this.postID, this.user_ID, this.number_of_bags, this.institution_id);
  Post.fromJson(Map<String, dynamic> json) {
    postID = json['postID'];
    user_ID = json['user_ID'];
    number_of_bags = json['number_of_bags'];
    institution_id = json['institution_id'];
  }
  @override
  String toString() {
    // ignore: prefer_interpolation_to_compose_strings
    return "Post id" + postID.toString();
  }
}

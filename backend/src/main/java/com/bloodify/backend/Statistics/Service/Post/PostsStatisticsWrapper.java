package com.bloodify.backend.Statistics.Service.Post;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class PostsStatisticsWrapper {
    String bloodType;

    int postsCount;
    double postsPercentage;

    int bagsCount;
    double bagsPercentage;

}

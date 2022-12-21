package com.bloodify.backend.UserRequests.service.entities;

import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.service.bloodTypes.BloodType;
import com.bloodify.backend.UserRequests.service.bloodTypes.BloodTypeFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Criteria {
    @Autowired
    private UserDAO userDAO;

    double distance(double lat1, double long1, double lat2, double long2) {
//      Radius of the earth
        final int R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(long2 - long1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

    public List<User> getUsersMatchingWithPostBloodType(Post acceptedPost) {
        BloodTypeFactory factory = BloodTypeFactory.getFactory();
        BloodType type = factory.generateFromString(acceptedPost.getBloodType());
        List<BloodType> compatibleTypes = type.getCompatibleTypes();
        List<String> typesInString = new ArrayList<>();
        for (BloodType bType: compatibleTypes) typesInString.add(bType.toString());
        return userDAO.findByBloodTypeIn(typesInString);
    }

    public List<User> getPotentialDonorsOnStatus (int status) {
        return userDAO.getUsersByStatus(status);
    }

    public List<User> filterOnDistance (List<User> potentialDonors, Double instLongitude, Double instLatitude, int threshold) {
        List<User> currentlyAvailableDonors = new ArrayList<>();
        for(User u: potentialDonors) {
            if(distance(instLatitude, instLongitude, u.getLatitude(), u.getLongitude()) < threshold)
                currentlyAvailableDonors.add(u);
        }
        return currentlyAvailableDonors;
    }




}

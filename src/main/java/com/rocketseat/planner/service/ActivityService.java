package com.rocketseat.planner.service;

import com.rocketseat.planner.dto.activity.ActivityData;
import com.rocketseat.planner.dto.activity.ActivityRequestPayload;
import com.rocketseat.planner.dto.activity.ActivityResponse;
import com.rocketseat.planner.domain.model.activity.Activity;
import com.rocketseat.planner.domain.model.trip.Trip;
import com.rocketseat.planner.domain.repositories.ActivityRepository;
import com.rocketseat.planner.exceptions.InvalidDatesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public ActivityResponse createActivity(ActivityRequestPayload payload, Trip trip){
        Activity newActivity = new Activity(payload.title(), payload.occurs_at(), trip);

        if(((newActivity.getOccursAt().isAfter(trip.getStartsAt()) && (newActivity.getOccursAt().isBefore(trip.getEndsAt()))))) {
            activityRepository.save(newActivity);
            return new ActivityResponse(newActivity.getId());

        } else throw new InvalidDatesException();
        
    }

    public List<ActivityData> getAllActivities(UUID tripId){
        List<Activity> activityList = new ArrayList<>((activityRepository.findByTripId(tripId)));
        return activityList.stream().map(activity -> new ActivityData(activity.getId(), activity.getOccursAt(), activity.getTitle())).toList();

    }
}

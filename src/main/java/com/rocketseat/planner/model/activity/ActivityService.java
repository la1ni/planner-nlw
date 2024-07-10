package com.rocketseat.planner.model.activity;

import com.rocketseat.planner.model.participant.Participant;
import com.rocketseat.planner.model.participant.ParticipantData;
import com.rocketseat.planner.model.trip.Trip;
import com.rocketseat.planner.repositories.ActivityRepository;
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
        activityRepository.save(newActivity);
        return new ActivityResponse(newActivity.getId());
    }

    public List<ActivityData> getAllActivities(UUID tripId){
        List<Activity> activityList = new ArrayList<>((activityRepository.findByTripId(tripId)));
        return activityList.stream().map(activity -> new ActivityData(activity.getId(), activity.getOccursAt(), activity.getTitle())).toList();

    }
}

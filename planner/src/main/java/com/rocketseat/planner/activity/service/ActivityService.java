package com.rocketseat.planner.activity.service;

import com.rocketseat.planner.activity.record.ActivityData;
import com.rocketseat.planner.activity.record.ActivityRequestPayload;
import com.rocketseat.planner.activity.entity.Activity;
import com.rocketseat.planner.activity.repository.ActivityRepository;
import com.rocketseat.planner.activity.response.ActivityResponse;
import com.rocketseat.planner.trip.entity.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository repository;

    public ActivityResponse registerActivity(ActivityRequestPayload payload, Trip trip) {
        Activity newActivity = new Activity(payload.title(), payload.occurs_at(), trip);

        this.repository.save(newActivity);

        return new ActivityResponse(newActivity.getId());
    }

    public List<ActivityData> getAllActivitiesFromId(UUID tripId) {
        return this.repository.findByTripId(tripId)
                .stream().map(activity -> new ActivityData(activity.getId(), activity.getTitle(), activity.getOccursAt())).toList();
    }
}

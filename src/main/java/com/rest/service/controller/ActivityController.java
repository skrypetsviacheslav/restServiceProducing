package com.rest.service.controller;

import com.rest.service.entity.Activity;
import com.rest.service.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ActivityController {

    private ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/activities")
    public Iterable<Activity> getActivities() {
        return activityService.getAllActivities();
    }

    @GetMapping("/activities/{activityId}")
    public ResponseEntity<Activity> getActivity(@PathVariable Integer activityId) {
        return ResponseEntity.of(activityService.getActivityById(activityId));
    }

    @PostMapping("/activities")
    public ResponseEntity<Activity> postActivity(@RequestBody Activity activity) {
        return ResponseEntity.of(activityService.createActivity(activity));
    }

    @PutMapping("/activities/{activityId}")
    public ResponseEntity<Activity> putActivity(@PathVariable Integer activityId, @RequestBody Activity activity) {
        return ResponseEntity.of(activityService.updateActivity(activityId, activity));
    }

    @DeleteMapping("/activities/{activityId}")
    public void deleteActivity(@PathVariable Integer activityId) {
        try {
            activityService.deleteActivity(activityId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Activity with " + activityId + " not found");
        }
    }
}
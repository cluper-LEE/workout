package healthcare.workout.controller;

import healthcare.workout.domain.DailyWorkout;
import healthcare.workout.domain.Workout;
import healthcare.workout.service.DailyWorkoutService;
import healthcare.workout.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DailyWorkoutController {
    private final DailyWorkoutService dailyWorkoutService;
    private final WorkoutService workoutService;

//    @GetMapping("/dailyWorkouts")
//    public String calendar(DateForm dateForm, Model model) {
//        if (dateForm.getDate() == null) dateForm.setDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        DailyWorkout dailyWorkout = dailyWorkoutService.findByDate(dateForm.getDate());
//        DailyWorkoutForm dailyWorkoutForm = null;
//        if (dailyWorkout != null) dailyWorkoutForm = DailyWorkoutForm.create(dailyWorkout);
//        model.addAttribute("dailyWorkoutForm", dailyWorkoutForm);
//        model.addAttribute("dateForm", dateForm);
//        return "dailyWorkouts/workoutList";
//    }

    @PostMapping("/dailyWorkouts")
    public ResponseEntity<DailyWorkoutForm> createDailyWorkout(@RequestBody DailyWorkoutForm dailyWorkoutForm, UriComponentsBuilder ucBuilder) {
        dailyWorkoutService.save(dailyWorkoutForm.getDate(), dailyWorkoutForm.getMemo());
        DailyWorkout dailyWorkout = dailyWorkoutService.findByDate(dailyWorkoutForm.getDate());
        DailyWorkoutForm ret = DailyWorkoutForm.create(dailyWorkout);
        URI uri = ucBuilder.path("/dailyWorkouts/{id}").buildAndExpand(dailyWorkout.getId()).toUri();
        return ResponseEntity.created(uri).body(ret);
    }

    @GetMapping("/dailyWorkouts")
    public ResponseEntity<List<DailyWorkoutForm>> getDailyWorkouts() {
        List<DailyWorkout> dailyWorkouts = dailyWorkoutService.findAll();
        List<DailyWorkoutForm> dailyWorkoutForms = new ArrayList<>();
        for (DailyWorkout dailyWorkout : dailyWorkouts) {
            dailyWorkoutForms.add(DailyWorkoutForm.create(dailyWorkout));
        }
        return ResponseEntity.ok(dailyWorkoutForms);
    }

    @GetMapping("/dailyWorkouts/{id}")
    public ResponseEntity<DailyWorkoutForm> getDailyWorkout(@PathVariable("id") Long id) {
        DailyWorkout dailyWorkout = dailyWorkoutService.findOne(id);
        DailyWorkoutForm dailyWorkoutForm = DailyWorkoutForm.create(dailyWorkout);
        return ResponseEntity.ok(dailyWorkoutForm);
    }

    @GetMapping(value = "/dailyWorkouts", params = "date")
    public ResponseEntity<DailyWorkoutForm> getDailyWorkoutByDate(@RequestParam("date") String date) {
        DailyWorkout dailyWorkout = dailyWorkoutService.findByDate(date);
        if(dailyWorkout == null){
            return ResponseEntity.noContent().build();
        }
        DailyWorkoutForm dailyWorkoutForm = DailyWorkoutForm.create(dailyWorkout);
        return ResponseEntity.ok(dailyWorkoutForm);
    }

    @GetMapping(value = "/workouts", params = "dailyWorkoutId")
    public ResponseEntity<List<WorkoutForm>> getWorkoutsByDailyWorkoutId(@RequestParam("dailyWorkoutId") Long dailyWorkoutId) {
        List<Workout> workouts = dailyWorkoutService.findOne(dailyWorkoutId).getWorkouts();
        List<WorkoutForm> workoutForms = new ArrayList<>();
        for (Workout workout : workouts) {
            workoutForms.add( WorkoutForm.create(workout));
        }
        return ResponseEntity.ok(workoutForms);
    }

    @PatchMapping("/dailyWorkouts")
    public ResponseEntity<DailyWorkoutForm> patchDailyWorkout(@RequestBody DailyWorkoutForm dailyWorkoutForm, UriComponentsBuilder ucBuilder){
        DailyWorkout dailyWorkout = dailyWorkoutService.update(dailyWorkoutForm.getId(), dailyWorkoutForm.getDate(), dailyWorkoutForm.getMemo());
        DailyWorkoutForm retForm = DailyWorkoutForm.create(dailyWorkout);
        URI uri = ucBuilder.path("/dailyWorkouts/{id}").buildAndExpand(dailyWorkout.getId()).toUri();
        return ResponseEntity.created(uri).body(dailyWorkoutForm);
    }

    @PostMapping("/workouts")
    public ResponseEntity<WorkoutForm> createWorkout( @RequestBody WorkoutForm workoutForm, UriComponentsBuilder ucBuilder) {
        Workout workout = workoutService.createWorkout(workoutForm.getDailyWorkoutForm().getId(), workoutForm.getExerciseForm().getId(), workoutForm.getMemo());
        WorkoutForm retForm = WorkoutForm.create(workout);
        URI uri = ucBuilder.buildAndExpand(workoutForm).toUri();
        return ResponseEntity.created(uri).body(retForm);
    }
}

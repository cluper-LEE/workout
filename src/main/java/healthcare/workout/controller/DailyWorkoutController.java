package healthcare.workout.controller;

import healthcare.workout.domain.DailyWorkout;
import healthcare.workout.domain.Workout;
import healthcare.workout.service.DailyWorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DailyWorkoutController {
    private final DailyWorkoutService dailyWorkoutService;

    @GetMapping("/dailyWorkouts/{date}")
    public String calendar(@PathVariable("date") String date, Model model) {
        if (date.equals("today")) date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        DailyWorkout dailyWorkout = dailyWorkoutService.findByDate(date);
        DailyWorkoutForm dailyWorkoutForm = null;
        if (dailyWorkout != null) dailyWorkoutForm = DailyWorkoutForm.create(dailyWorkout);
        model.addAttribute("dailyWorkoutForm", dailyWorkoutForm);
        model.addAttribute("date", date);
        return "dailyWorkouts/workoutList";
    }
}

package healthcare.workout.controller;

import healthcare.workout.domain.DailyWorkout;
import healthcare.workout.domain.Workout;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyWorkoutForm {
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String memo;
    private List<WorkoutForm> workoutForms = new ArrayList<>();

    public DailyWorkoutForm(Long id, LocalDate date, String memo) {
        this.id = id;
        this.date = date;
        this.memo = memo;
    }

    public static DailyWorkoutForm create(DailyWorkout dailyWorkout) {
        DailyWorkoutForm dailyWorkoutForm = new DailyWorkoutForm(dailyWorkout.getId(), dailyWorkout.getDate(), dailyWorkout.getMemo());
        for (Workout workout : dailyWorkout.getWorkouts()) dailyWorkoutForm.getWorkoutForms().add(WorkoutForm.create(workout));
        return dailyWorkoutForm;
    }
}

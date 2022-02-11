package healthcare.workout.controller;

import healthcare.workout.domain.DailyWorkout;
import healthcare.workout.domain.Exercise;
import healthcare.workout.domain.Workout;
import healthcare.workout.domain.WorkoutSet;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkoutForm {
    private Long id;
    private ExerciseForm exerciseForm;
    private String memo;
    private DailyWorkoutForm dailyWorkoutForm;
    private List<WorkoutSetForm> workoutSetForms = new ArrayList<>();

    public WorkoutForm(Long id, Exercise exercise, String memo) {
        this.id = id;
        this.exerciseForm = ExerciseForm.create(exercise);
//        this.dailyWorkoutForm = DailyWorkoutForm.create(dailyWorkout);
        this.memo = memo;
    }

    public static WorkoutForm create(Workout workout) {
        WorkoutForm workoutForm = new WorkoutForm(workout.getId(), workout.getExercise(), workout.getMemo());
        for (WorkoutSet workoutSet : workout.getWorkoutSets()) {
            workoutForm.getWorkoutSetForms().add(WorkoutSetForm.create(workoutSet));
        }
        return workoutForm;
    }
}

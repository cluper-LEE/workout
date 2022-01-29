package healthcare.workout.controller;

import healthcare.workout.domain.Workout;
import healthcare.workout.domain.WorkoutSet;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkoutForm {
    private Long id;
    private String exerciseName;
    private String memo;
    private List<WorkoutSetForm> workoutSetFormList = new ArrayList<>();

    public WorkoutForm(Long id, String exerciseName, String memo) {
        this.id = id;
        this.exerciseName = exerciseName;
        this.memo = memo;
    }

    public static WorkoutForm create(Workout workout) {
        WorkoutForm workoutForm = new WorkoutForm(workout.getId(), workout.getExercise().getName(), workout.getMemo());
        for (WorkoutSet workoutSet : workout.getWorkoutSets()) {
            workoutForm.getWorkoutSetFormList().add(WorkoutSetForm.create(workoutSet));
        }
        return workoutForm;
    }
}

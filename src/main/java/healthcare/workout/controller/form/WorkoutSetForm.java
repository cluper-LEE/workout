package healthcare.workout.controller.form;

import healthcare.workout.domain.WorkoutSet;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkoutSetForm {
    private Long id;
    private int setNum;
    private int weight;
    private int reps;
    private WorkoutForm workoutForm;

    public WorkoutSetForm(Long id, int setNum, int weight, int reps) {
        this.id = id;
        this.setNum = setNum;
        this.weight = weight;
        this.reps = reps;
    }

    public static WorkoutSetForm create(WorkoutSet workoutSet) {
        return new WorkoutSetForm(workoutSet.getId(), workoutSet.getSetNum(), workoutSet.getWeight(), workoutSet.getReps());
    }
}

package healthcare.workout.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkoutSet {
    @Id
    @GeneratedValue
    @Column(name = "workout_set_id")
    private Long id;

    private int setNum;
    private int weight;
    private int reps;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id")
    private Workout workout;

    public static WorkoutSet create(Workout workout, int setNum, int weight, int reps) {
        WorkoutSet workoutSet = new WorkoutSet();
        workoutSet.workout = workout;
        workoutSet.setNum = setNum;
        workoutSet.weight = weight;
        workoutSet.reps = reps;
        return workoutSet;
    }

    public void associate(Workout workout) {
        this.workout = workout;
    }

    public void update(int setNum, int weight, int reps) {
        this.setNum = setNum; this.weight = weight; this.reps = reps;
    }
}

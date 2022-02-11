package healthcare.workout.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Workout {
    @Id
    @GeneratedValue
    @Column(name = "workout_id")
    private Long id;

    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @OneToMany(mappedBy = "workout")
    private List<WorkoutSet> workoutSets = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_workout_id")
    private DailyWorkout dailyWorkout;

    public static Workout create(DailyWorkout dailyWorkout, Exercise exercise, String memo) {
        Workout workout = new Workout();
        workout.dailyWorkout = dailyWorkout;
        dailyWorkout.addWorkout(workout);
        workout.exercise = exercise;
        workout.memo = memo;
        return workout;
    }

    public void associate(DailyWorkout dailyWorkout) {
        this.dailyWorkout = dailyWorkout;
    }

    public void addWorkoutSet(WorkoutSet workoutSet) {
        workoutSets.add(workoutSet);
        workoutSet.associate(this);
    }

    public void update(Exercise exercise, String memo) {
        this.exercise = exercise;
        this.memo = memo;
    }
}

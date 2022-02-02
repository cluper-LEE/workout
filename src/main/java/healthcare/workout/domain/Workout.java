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

    public void associate(DailyWorkout dailyWorkout) {
        this.dailyWorkout = dailyWorkout;
    }

    public void addWorkoutSet(WorkoutSet workoutSet) {
        workoutSets.add(workoutSet);
        workoutSet.associate(this);
    }
}

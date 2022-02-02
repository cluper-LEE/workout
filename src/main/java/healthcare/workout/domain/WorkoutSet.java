package healthcare.workout.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
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

    public void associate(Workout workout) {
        this.workout = workout;
    }
}

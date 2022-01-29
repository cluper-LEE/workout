package healthcare.workout.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class DailyWorkout {
    @Id
    @GeneratedValue
    @Column(name = "daily_workout_id")
    private Long id;

    private LocalDateTime date;

    private String memo;

    @OneToMany(mappedBy = "dailyWorkout")
    private List<Workout> workouts = new ArrayList<>();
}

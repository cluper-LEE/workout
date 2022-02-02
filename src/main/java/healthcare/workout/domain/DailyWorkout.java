package healthcare.workout.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class DailyWorkout {
    @Id
    @GeneratedValue
    @Column(name = "daily_workout_id")
    private Long id;

    private LocalDate date;

    private String memo;

    @OneToMany(mappedBy = "dailyWorkout")
    private List<Workout> workouts = new ArrayList<>();


    public static DailyWorkout create(LocalDate date, String memo) {
        DailyWorkout dailyWorkout = new DailyWorkout();
        dailyWorkout.setDate(date);
        dailyWorkout.setMemo(memo);
        return dailyWorkout;
    }

    public void addWorkout(Workout workout) {
        this.addWorkout(workout);
        workout.associate(this);
    }

    private void setMemo(String memo) {
        this.memo = memo;
    }

    private void setDate(LocalDate date) {
        this.date = date;
    }
}

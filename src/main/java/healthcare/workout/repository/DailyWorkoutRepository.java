package healthcare.workout.repository;

import healthcare.workout.domain.DailyWorkout;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DailyWorkoutRepository {
    private final EntityManager em;

    public List<DailyWorkout> findAll() {
        return em.createQuery("select dw from DailyWorkout dw", DailyWorkout.class).getResultList();
    }

    public DailyWorkout findByDate(LocalDate date) {
        List<DailyWorkout> dailyWorkouts = em.createQuery("select dw from DailyWorkout dw where dw.date = :date", DailyWorkout.class)
                .setParameter("date", date)
                .getResultList();
        if (dailyWorkouts.isEmpty()) {
            return null;
        } else {
            return dailyWorkouts.get(0);
        }
    }
}

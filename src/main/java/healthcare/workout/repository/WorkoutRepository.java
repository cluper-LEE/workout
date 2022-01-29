package healthcare.workout.repository;

import healthcare.workout.domain.Workout;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class WorkoutRepository {
    private final EntityManager em;

    public void save(Workout workout) {
        em.persist(workout);
    }
}

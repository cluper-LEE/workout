package healthcare.workout.repository;

import healthcare.workout.domain.Workout;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class WorkoutRepository {
    private final EntityManager em;

    public Workout save(Workout workout) {
        em.persist(workout);
        return workout;
    }

    public Workout findOne(Long id) {
        return em.find(Workout.class, id);
    }

    public void delete(Workout workout) {
        em.remove(workout);
    }
}

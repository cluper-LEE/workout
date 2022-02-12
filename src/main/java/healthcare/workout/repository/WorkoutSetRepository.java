package healthcare.workout.repository;

import healthcare.workout.domain.WorkoutSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class WorkoutSetRepository {
    private final EntityManager em;

    public void delete(WorkoutSet workoutSet) {
        em.remove(workoutSet);
    }

    public void save(WorkoutSet workoutSet) {
        em.persist(workoutSet);
    }

    public WorkoutSet findOne(Long id) {
        return em.find(WorkoutSet.class, id);
    }

    public void remove(WorkoutSet workoutSet) {
        em.remove(workoutSet);
    }
}

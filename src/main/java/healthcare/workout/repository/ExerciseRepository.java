package healthcare.workout.repository;

import healthcare.workout.domain.Exercise;
import healthcare.workout.domain.ExerciseMuscleCategory;
import healthcare.workout.domain.MuscleCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExerciseRepository {
    private final EntityManager em;

    public void save(Exercise exercise) {
        em.persist(exercise);
    }

    public Exercise findOne(Long id) {
        return em.find(Exercise.class, id);
    }

    public List<Exercise> findAll() {
        return em.createQuery("select e from Exercise e", Exercise.class)
                .getResultList();
    }

    public List<Exercise> findByName(String name) {
        return em.createQuery("select e from Exercise e where e.name = :name", Exercise.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Exercise> findByMuscleCategoryName(String name) {
        MuscleCategory muscleCategory = em.createQuery("select mc from MuscleCategory mc where mc.name = :name", MuscleCategory.class)
                .setParameter("name", name)
                .getResultList().get(0);
        List<Exercise> result = new ArrayList<>();
        for (ExerciseMuscleCategory emc : muscleCategory.getExerciseMuscleCategories()) {
            result.add(emc.getExercise());
        }
        return result;
    }
}

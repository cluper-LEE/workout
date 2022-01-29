package healthcare.workout.repository;

import healthcare.workout.domain.ExerciseMuscleCategory;
import healthcare.workout.domain.MuscleCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExerciseMuscleCategoryRepository {
    private final EntityManager em;

    public void save(ExerciseMuscleCategory exerciseMuscleCategory) {
        em.persist(exerciseMuscleCategory);
    }

    public void remove(Long id) {
        ExerciseMuscleCategory exerciseMuscleCategory = em.find(ExerciseMuscleCategory.class, id);
        exerciseMuscleCategory.unlink();
        em.remove(exerciseMuscleCategory);
    }

}

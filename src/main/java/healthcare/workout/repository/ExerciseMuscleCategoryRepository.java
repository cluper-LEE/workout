package healthcare.workout.repository;

import healthcare.workout.domain.ExerciseMuscleCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ExerciseMuscleCategoryRepository {
    private final EntityManager em;

    public void save(ExerciseMuscleCategory exerciseMuscleCategory) {
        em.persist(exerciseMuscleCategory);
    }


}

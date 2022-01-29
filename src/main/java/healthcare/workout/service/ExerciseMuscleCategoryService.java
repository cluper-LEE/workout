package healthcare.workout.service;

import healthcare.workout.domain.ExerciseMuscleCategory;
import healthcare.workout.repository.ExerciseMuscleCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExerciseMuscleCategoryService {
    private final ExerciseMuscleCategoryRepository exerciseMuscleCategoryRepository;

    @Transactional
    public void save(ExerciseMuscleCategory exerciseMuscleCategory) {
        exerciseMuscleCategoryRepository.save(exerciseMuscleCategory);
    }
}

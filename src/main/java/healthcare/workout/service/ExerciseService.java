package healthcare.workout.service;

import healthcare.workout.domain.Exercise;
import healthcare.workout.domain.ExerciseMuscleCategory;
import healthcare.workout.domain.MuscleCategory;
import healthcare.workout.repository.ExerciseMuscleCategoryRepository;
import healthcare.workout.repository.ExerciseRepository;
import healthcare.workout.repository.MuscleCategoryRepository;
import healthcare.workout.service.dto.CreateExerciseDto;
import healthcare.workout.service.dto.UpdateExerciseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseMuscleCategoryRepository exerciseMuscleCategoryRepository;
    private final MuscleCategoryRepository muscleCategoryRepository;

    @Transactional
    public void saveExercise(Exercise exercise) {
        exerciseRepository.save(exercise);
    }

    @Transactional
    public void linkParentChild(Exercise parent, Exercise... child) {
        for (Exercise exercise : child) {
            parent.addChild(exercise);
        }
    }

    public Exercise findOne(Long id) {
        return exerciseRepository.findOne(id);
    }

    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Transactional
    public void updateExercise(UpdateExerciseDto dto) {
        Exercise exercise = exerciseRepository.findOne(dto.getId());
        exercise.changeName(dto.getName());
        if (!dto.getCategory().equals(exercise.getExerciseMuscleCategories().get(0).getMuscleCategory().getName())) {
            exerciseMuscleCategoryRepository.remove(exercise.getExerciseMuscleCategories().get(0).getId());
            MuscleCategory muscleCategory = muscleCategoryRepository.findByName(dto.getCategory());
            if (muscleCategory == null) {
                muscleCategory = MuscleCategory.create(dto.getCategory());
                muscleCategoryRepository.save(muscleCategory);
            }
            ExerciseMuscleCategory exerciseMuscleCategory = ExerciseMuscleCategory.create(exercise, muscleCategory);
            exerciseMuscleCategoryRepository.save(exerciseMuscleCategory);
        }
    }

    @Transactional
    public void createExercise(CreateExerciseDto dto) {
        Exercise exercise = Exercise.createExercise(dto.getName());
        exerciseRepository.save(exercise);

        MuscleCategory muscleCategory = muscleCategoryRepository.findByName(dto.getCategory());
        if (muscleCategory == null) {
            muscleCategory = MuscleCategory.create(dto.getCategory());
            muscleCategoryRepository.save(muscleCategory);
        }

        ExerciseMuscleCategory exerciseMuscleCategory = ExerciseMuscleCategory.create(exercise, muscleCategory);
        exerciseMuscleCategoryRepository.save(exerciseMuscleCategory);
    }
}

package healthcare.workout.service;

import healthcare.workout.domain.Exercise;
import healthcare.workout.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

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

}

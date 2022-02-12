package healthcare.workout.service;

import healthcare.workout.domain.Workout;
import healthcare.workout.domain.WorkoutSet;
import healthcare.workout.repository.WorkoutRepository;
import healthcare.workout.repository.WorkoutSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkoutSetService {
    private final WorkoutSetRepository workoutSetRepository;
    private final WorkoutRepository workoutRepository;

    @Transactional
    public WorkoutSet createWorkoutSet(Long workoutId, int setNum, int weight, int reps) {
        Workout workout = workoutRepository.findOne(workoutId);
        WorkoutSet workoutSet = WorkoutSet.create(workout, setNum, weight, reps);
        workoutSetRepository.save(workoutSet);
        return workoutSet;
    }

    @Transactional
    public WorkoutSet update(Long id, int setNum, int weight, int reps) {
        WorkoutSet workoutSet = workoutSetRepository.findOne(id);
        workoutSet.update(setNum, weight, reps);
        return workoutSet;
    }

    @Transactional
    public void remove(Long id) {
        WorkoutSet workoutSet = workoutSetRepository.findOne(id);
        workoutSetRepository.remove(workoutSet);
    }
}

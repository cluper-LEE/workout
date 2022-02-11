package healthcare.workout.service;

import healthcare.workout.domain.DailyWorkout;
import healthcare.workout.domain.Exercise;
import healthcare.workout.domain.Workout;
import healthcare.workout.repository.DailyWorkoutRepository;
import healthcare.workout.repository.ExerciseRepository;
import healthcare.workout.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final DailyWorkoutRepository dailyWorkoutRepository;

    @Transactional
    public Workout createWorkout(Long dailyWorkoutId, Long exerciseId, String memo) {
        Exercise exercise = exerciseRepository.findOne(exerciseId);
        DailyWorkout dailyWorkout = dailyWorkoutRepository.findOne(dailyWorkoutId);
        Workout workout = Workout.create(dailyWorkout, exercise, memo);
        return workoutRepository.save(workout);
    }

    public Workout findOne(Long id) {
        return workoutRepository.findOne(id);
    }
}

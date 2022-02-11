package healthcare.workout.service;

import healthcare.workout.domain.DailyWorkout;
import healthcare.workout.repository.DailyWorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DailyWorkoutService {
    private final DailyWorkoutRepository dailyWorkoutRepository;

    public List<DailyWorkout> findAll() {
        return dailyWorkoutRepository.findAll();
    }

    public DailyWorkout findByDate(LocalDate date) {
        return dailyWorkoutRepository.findByDate(date);
    }
    public DailyWorkout findByDate(String date) {
        return this.findByDate(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    public DailyWorkout findOne(Long id) {
        return dailyWorkoutRepository.findOne(id);
    }

    @Transactional
    public void save(LocalDate date, String memo) {
        DailyWorkout dailyWorkout = DailyWorkout.create(date, memo);
        dailyWorkoutRepository.save(dailyWorkout);
    }

    @Transactional
    public DailyWorkout update(Long id, LocalDate date, String memo) {
        DailyWorkout dailyWorkout = dailyWorkoutRepository.findOne(id);
        dailyWorkout.update(
                (date == null) ? dailyWorkout.getDate() : date,
                (memo == null) ? dailyWorkout.getMemo() : memo
        );
        return dailyWorkout;
    }
}

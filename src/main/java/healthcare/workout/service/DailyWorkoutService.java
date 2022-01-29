package healthcare.workout.service;

import healthcare.workout.domain.DailyWorkout;
import healthcare.workout.repository.DailyWorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
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
}

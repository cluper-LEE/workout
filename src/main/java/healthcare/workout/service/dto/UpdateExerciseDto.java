package healthcare.workout.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateExerciseDto {
    private Long id;
    private String name;
    private String category;

    public UpdateExerciseDto(Long id, String name, String category) {
        this.id = id; this.name = name; this.category = category;
    }
}

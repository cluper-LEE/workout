package healthcare.workout.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateExerciseDto {
    private String name;
    private String category;

    public CreateExerciseDto(String name, String category) {
        this.name = name; this.category = category;
    }
}

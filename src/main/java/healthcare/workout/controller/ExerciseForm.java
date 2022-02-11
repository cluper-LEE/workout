package healthcare.workout.controller;

import healthcare.workout.domain.Exercise;
import healthcare.workout.domain.ExerciseMuscleCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExerciseForm {
    Long id;
    private String name;
    private String category;

    public ExerciseForm(Long id,String name, String category){
        this.id = id; this.name = name; this.category = category;
    }

    public static ExerciseForm create(Exercise exercise) {
        return new ExerciseForm(exercise.getId(), exercise.getName(), exercise.getExerciseMuscleCategories().get(0).getMuscleCategory().getName());
    }
}


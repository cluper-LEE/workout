package healthcare.workout.controller;

import healthcare.workout.domain.Exercise;
import healthcare.workout.domain.ExerciseMuscleCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ExerciseForm {
    private Long id;
    private String name;
    private String category;
    private String parent;
//    private List<String> children = new ArrayList<>();
}


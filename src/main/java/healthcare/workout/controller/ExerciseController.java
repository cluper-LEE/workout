package healthcare.workout.controller;

import healthcare.workout.controller.form.ExerciseForm;
import healthcare.workout.domain.Exercise;
import healthcare.workout.service.dto.CreateExerciseDto;
import healthcare.workout.service.ExerciseService;
import healthcare.workout.service.MuscleCategoryService;
import healthcare.workout.service.dto.UpdateExerciseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final MuscleCategoryService muscleCategoryService;


    // Rest Controller
    @PostMapping("/exercises")
    public void createExercise(@RequestBody ExerciseForm form) {
        CreateExerciseDto dto = new CreateExerciseDto(form.getName(), form.getCategory());
        exerciseService.createExercise(dto);
    }

    @GetMapping("/exercises")
    public ResponseEntity<List<ExerciseForm>> exerciseList() {
        List<Exercise> exerciseList = exerciseService.findAll();
        List<ExerciseForm> exerciseFormList = new ArrayList<>();
        for(Exercise exercise : exerciseList){
            exerciseFormList.add(new ExerciseForm(exercise.getId(), exercise.getName(), exercise.getExerciseMuscleCategories().get(0).getMuscleCategory().getName()));
        }
        if(exerciseFormList.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(exerciseFormList);
    }

    @GetMapping("/exercises/{id}")
    public ResponseEntity<ExerciseForm> exerciseById(@PathVariable("id") Long id) {
        Exercise exercise = exerciseService.findOne(id);
        return ResponseEntity.ok(new ExerciseForm(exercise.getId(), exercise.getName(), exercise.getExerciseMuscleCategories().get(0).getMuscleCategory().getName()));
    }

    @PatchMapping("/exercises/{id}")
    public void patchExercise(@PathVariable("id") Long id, @RequestBody ExerciseForm exerciseForm){
        UpdateExerciseDto updateExerciseDto = new UpdateExerciseDto(exerciseForm.getId(), exerciseForm.getName(), exerciseForm.getCategory());
        exerciseService.updateExercise(updateExerciseDto);
    }
}

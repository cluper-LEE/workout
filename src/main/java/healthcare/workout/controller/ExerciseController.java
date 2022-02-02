package healthcare.workout.controller;

import healthcare.workout.domain.Exercise;
import healthcare.workout.domain.ExerciseMuscleCategory;
import healthcare.workout.domain.MuscleCategory;
import healthcare.workout.service.CreateExerciseDto;
import healthcare.workout.service.ExerciseService;
import healthcare.workout.service.MuscleCategoryService;
import healthcare.workout.service.UpdateExerciseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
//
//    @GetMapping("/exercises/new")
//    public String createForm(Model model) {
//        List<MuscleCategory> muscleCategories = muscleCategoryService.findAll();
//        model.addAttribute("categories", muscleCategories);
//        model.addAttribute("form", new ExerciseForm());
//        return "exercises/createExerciseForm";
//    }
//
//    @PostMapping("/exercises/new")
//    public String create(ExerciseForm form) {
//        CreateExerciseDto dto = new CreateExerciseDto(form.getName(), form.getCategory());
//        exerciseService.createExercise(dto);
//        return "redirect:/";
//    }
//
//    @GetMapping("/exercises")
//    public String exerciseList(Model model) {
//        List<Exercise> exerciseList = exerciseService.findAll();
//        model.addAttribute("exerciseList", exerciseList);
//        return "exercises/exerciseList";
//    }
//
//    @GetMapping("/exercises/{exerciseId}/edit")
//    public String updateExerciseForm(@PathVariable("exerciseId") Long exerciseId, Model model) {
//        Exercise exercise = exerciseService.findOne(exerciseId);
//        ExerciseForm form = new ExerciseForm();
//        form.setName(exercise.getName());
//        List<ExerciseMuscleCategory> exerciseMuscleCategories = exercise.getExerciseMuscleCategories();
//        if (exerciseMuscleCategories.size() > 0) {
//            form.setCategory(exerciseMuscleCategories.get(0).getMuscleCategory().getName());
//        }
//        model.addAttribute("form", form);
//
//        List<MuscleCategory> muscleCategories = muscleCategoryService.findAll();
//        model.addAttribute("categories", muscleCategories);
//        return "exercises/updateExerciseForm";
//    }
//
//    @PostMapping("/exercises/{exerciseId}/edit")
//    public String updateExercise(@PathVariable("exerciseId") Long exerciseId, ExerciseForm form) {
//        UpdateExerciseDto dto = new UpdateExerciseDto(null, form.getName(), form.getCategory());
//        exerciseService.updateExercise(dto);
//        return "redirect:/exercises";
//    }

}

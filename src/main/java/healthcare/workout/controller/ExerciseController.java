package healthcare.workout.controller;

import healthcare.workout.domain.Exercise;
import healthcare.workout.domain.ExerciseMuscleCategory;
import healthcare.workout.domain.MuscleCategory;
import healthcare.workout.service.CreateExerciseDto;
import healthcare.workout.service.ExerciseService;
import healthcare.workout.service.MuscleCategoryService;
import healthcare.workout.service.UpdateExerciseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final MuscleCategoryService muscleCategoryService;

    @GetMapping("/exercises/new")
    public String createForm(Model model) {
        List<MuscleCategory> muscleCategories = muscleCategoryService.findAll();
        model.addAttribute("categories", muscleCategories);
        model.addAttribute("form", new ExerciseForm());
        return "exercises/createExerciseForm";
    }

    @PostMapping("/exercises/new")
    public String create(ExerciseForm form) {
        CreateExerciseDto dto = new CreateExerciseDto(form.getName(), form.getCategory());
        exerciseService.createExercise(dto);
        return "redirect:/";
    }

    @GetMapping("/exercises")
    public String exerciseList(Model model) {
        List<Exercise> exerciseList = exerciseService.findAll();
        model.addAttribute("exerciseList", exerciseList);
        return "exercises/exerciseList";
    }

    @GetMapping("/exercises/{exerciseId}/edit")
    public String updateExerciseForm(@PathVariable("exerciseId") Long exerciseId, Model model) {
        Exercise exercise = exerciseService.findOne(exerciseId);
        ExerciseForm form = new ExerciseForm();
        form.setId(exercise.getId());
        form.setName(exercise.getName());
        List<ExerciseMuscleCategory> exerciseMuscleCategories = exercise.getExerciseMuscleCategories();
        if (exerciseMuscleCategories.size() > 0) {
            form.setCategory(exerciseMuscleCategories.get(0).getMuscleCategory().getName());
        }
        model.addAttribute("form", form);
        return "exercises/updateExerciseForm";
    }

    @PostMapping("/exercises/{exerciseId}/edit")
    public String updateExercise(@PathVariable("exerciseId") Long exerciseId, ExerciseForm form) {
        UpdateExerciseDto dto = new UpdateExerciseDto(form.getId(), form.getName(), form.getCategory());
        exerciseService.updateExercise(dto);
        return "redirect:/exercises";
    }

}

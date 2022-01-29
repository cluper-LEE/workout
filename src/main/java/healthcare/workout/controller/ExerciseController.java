package healthcare.workout.controller;

import healthcare.workout.domain.Exercise;
import healthcare.workout.domain.ExerciseMuscleCategory;
import healthcare.workout.domain.MuscleCategory;
import healthcare.workout.service.ExerciseMuscleCategoryService;
import healthcare.workout.service.ExerciseService;
import healthcare.workout.service.MuscleCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final MuscleCategoryService muscleCategoryService;
    private final ExerciseMuscleCategoryService exerciseMuscleCategoryService;

    @GetMapping("/exercises/new")
    public String createForm(Model model) {
        List<MuscleCategory> muscleCategories = muscleCategoryService.findAll();
        model.addAttribute("categories", muscleCategories);
        model.addAttribute("form", new ExerciseForm());
        return "exercises/createExerciseForm";
    }

    @PostMapping("/exercises/new")
    public String create(ExerciseForm form) {
        // create and save exercise
        Exercise exercise = Exercise.createExercise(form.getName());
        exerciseService.saveExercise(exercise);

        // search exist category
        MuscleCategory muscleCategory = muscleCategoryService.findByName(form.getName());
        if (muscleCategory == null) { // if not exist, create category and save
            muscleCategory = MuscleCategory.create(form.getCategory());
            muscleCategoryService.saveMuscleCategory(muscleCategory);
        }

        // create EMC to link exercise and category
        ExerciseMuscleCategory exerciseMuscleCategory = ExerciseMuscleCategory.create(exercise, muscleCategory);
        exerciseMuscleCategoryService.save(exerciseMuscleCategory);
        return "redirect:/";
    }
}

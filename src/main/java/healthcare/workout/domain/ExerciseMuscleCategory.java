package healthcare.workout.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ExerciseMuscleCategory {
    @Id
    @GeneratedValue
    @Column(name = "exercise_muscle_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "muscle_category_id")
    private MuscleCategory muscleCategory;

    public static ExerciseMuscleCategory create(Exercise exercise, MuscleCategory muscleCategory) {
        ExerciseMuscleCategory emc = new ExerciseMuscleCategory();
        emc.setExercise(exercise);
        emc.setMuscleCategory(muscleCategory);
        exercise.addExerciseMuscleCategory(emc);
        muscleCategory.addExerciseMuscleCategory(emc);
        return emc;
    }

    private void setMuscleCategory(MuscleCategory muscleCategory) {
        this.muscleCategory = muscleCategory;
    }

    private void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}

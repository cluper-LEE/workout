package healthcare.workout.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exercise {
    @Id
    @GeneratedValue
    @Column(name = "exercise_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<ExerciseMuscleCategory> exerciseMuscleCategories = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Exercise parent;

    @OneToMany
    private List<Exercise> children = new ArrayList<>();

    public static Exercise createExercise(String name) {
        Exercise exercise = new Exercise();
        exercise.setName(name);
        return exercise;
    }

    public void addExerciseMuscleCategory(ExerciseMuscleCategory exerciseMuscleCategory) {
        exerciseMuscleCategories.add(exerciseMuscleCategory);
    }

    public void removeExerciseMuscleCategory(ExerciseMuscleCategory exerciseMuscleCategory) {
        exerciseMuscleCategories.remove(exerciseMuscleCategory);
    }

    public void addChild(Exercise child) {
        if (this.children.contains(child)) {
            return;
        }
        this.children.add(child);
        child.setParent(parent);
    }

    private void setParent(Exercise parent) {
        if (this.parent == parent) {
            return;
        }
        this.parent.removeChild(this);
        this.parent = parent;
    }

    private void removeChild(Exercise child) {
        this.children.remove(child);
    }

    private void setName(String name) {
        this.name = name;
    }

    public void changeName(String name) {
        setName(name);
    }
}

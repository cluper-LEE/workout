package healthcare.workout.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class MuscleCategory {
    @Id
    @GeneratedValue
    @Column(name = "muscle_category_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "muscleCategory", cascade = CascadeType.ALL)
    private List<ExerciseMuscleCategory> exerciseMuscleCategories = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private MuscleCategory parent;

    @OneToMany
    private List<MuscleCategory> children = new ArrayList<>();

    public static MuscleCategory create(String name) {
        MuscleCategory muscleCategory = new MuscleCategory();
        muscleCategory.setName(name);
        return muscleCategory;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void addExerciseMuscleCategory(ExerciseMuscleCategory exerciseMuscleCategory) {
        exerciseMuscleCategories.add(exerciseMuscleCategory);
    }

    public void addChild(MuscleCategory child) {
        if (this.children.contains(child)) {
            return;
        }
        this.children.add(child);
        child.setParent(parent);
    }
    private void setParent(MuscleCategory parent) {
        if (this.parent == parent) {
            return;
        }
        this.parent.removeChild(this);
        this.parent = parent;
    }
    private void removeChild(MuscleCategory child) {
        this.children.remove(child);
    }

    public void removeExerciseMuscleCategory(ExerciseMuscleCategory exerciseMuscleCategory) {
        exerciseMuscleCategories.remove(exerciseMuscleCategory);
    }
}

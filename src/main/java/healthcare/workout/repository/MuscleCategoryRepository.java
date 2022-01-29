package healthcare.workout.repository;

import healthcare.workout.domain.MuscleCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MuscleCategoryRepository {
    private final EntityManager em;

    public void save(MuscleCategory muscleCategory) {
        em.persist(muscleCategory);
    }

    public void delete(MuscleCategory muscleCategory) {
        em.remove(muscleCategory);
    }

    public MuscleCategory findOne(Long id) {
        return em.find(MuscleCategory.class, id);
    }
    public List<MuscleCategory> findAll() {
        return em.createQuery("select mc from MuscleCategory mc", MuscleCategory.class)
                .getResultList();
    }
    public List<MuscleCategory> findByName(String name) {
        return em.createQuery("select mc from MuscleCategory mc where mc.name = :name", MuscleCategory.class)
                .setParameter("name", name)
                .getResultList();
    }
}

package engine.spectro.filter;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class GenericRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    @Autowired
    public GenericRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public <T> List<T> filterObjects(Class<T> clazz, Map<String, Object> filters) {
        CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
        Root<T> root = query.from(clazz);

        Predicate[] predicates = buildPredicates(root, filters);
        query.where(predicates);

        return entityManager.createQuery(query).getResultList();
    }

    private <T> Predicate[] buildPredicates(Root<T> root, Map<String, Object> filters) {
        List<Predicate> predicateList = new ArrayList<>();

        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();

            if (fieldValue != null) {
                Predicate predicate = criteriaBuilder.equal(root.get(fieldName), fieldValue);
                predicateList.add(predicate);
            }
        }

        return predicateList.toArray(new Predicate[0]);
    }
}

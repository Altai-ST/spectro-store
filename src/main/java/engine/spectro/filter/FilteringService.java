package engine.spectro.filter;

import engine.spectro.model.LaptopSearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.*;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

//@Service
//public class FilteringService {
//    public <T> Specification<T> buildSpecification(LaptopSearchCriteria searchCriteria) {
//        return (root, query, builder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            Class<?> searchCriteriaClass = searchCriteria.getClass();
//            Field[] fields = searchCriteriaClass.getDeclaredFields();
//
//            for (Field field : fields) {
//                field.setAccessible(true);
//                Object value = null;
//                try {
//                    value = field.get(searchCriteria);
//                } catch (IllegalAccessException e) {
//                    throw new RuntimeException(e);
//                }
//
//                if (Objects.nonNull(value)) {
//                    String fieldName = field.getName();
//                    predicates.add((Predicate) builder.like(root.get(fieldName), "%" + value + "%"));
//                }
//            }
//
//            return builder.and(predicates.toArray(new Predicate[0]));
//        };
//    }
//}


package nl.novi.techiteasyfull.repository.Specifications;

import jakarta.persistence.criteria.Predicate;

import nl.novi.techiteasyfull.models.Television;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TelevisionSpecifications {
    private final TelevisionSearchCriteria criteria;

    public TelevisionSpecifications(TelevisionSearchCriteria criteria) {
        this.criteria = criteria;
    }

    public static Specification<Television> toPredicate(TelevisionSearchCriteria criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (Field field : TelevisionSearchCriteria.class.getDeclaredFields()) {
                try {
                    Object value = field.get(criteria);
                    if (value != null) {

                        if (field.getType().equals(Double.class)) {
                            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(field.getName()), (Double) value));
                        } else if (field.getType().equals(Boolean.class)) {
                            predicates.add(criteriaBuilder.equal(root.get(field.getName()), value));
                        } else if (field.getType().equals(Integer.class)){
                            predicates.add(criteriaBuilder.equal(root.get(field.getName()), value));
                        } else if (field.getType().equals(Long.class)){
                            predicates.add(criteriaBuilder.equal(root.get(field.getName()), value));
                        } else {
                            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(field.getName())), value.toString().toLowerCase()));
                        }
                    }

                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

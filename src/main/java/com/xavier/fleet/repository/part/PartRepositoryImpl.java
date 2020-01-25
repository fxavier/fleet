package com.xavier.fleet.repository.part;

import com.xavier.fleet.model.Part;
import com.xavier.fleet.repository.filter.PartFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PartRepositoryImpl implements PartRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Part> filter(PartFilter partFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Part> criteria = builder.createQuery(Part.class);
        Root<Part> root = criteria.from(Part.class);

        Predicate[] predicates = createRestrictions(partFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Part> query = manager.createQuery(criteria);
        addPaginationRestrictions(query, pageable);

        return new PageImpl<Part>(query.getResultList(), pageable, total(partFilter));
    }

    private long total(PartFilter partFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Part> root = criteria.from(Part.class);

        Predicate[] predicates = createRestrictions(partFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private void addPaginationRestrictions(TypedQuery<Part> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPage = currentPage * totalRecordsPerPage;

        query.setFirstResult(firstPage);
        query.setMaxResults(totalRecordsPerPage);
    }

    private Predicate[] createRestrictions(PartFilter partFilter, CriteriaBuilder builder, Root<Part> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(partFilter.getName())) {
            predicates.add(builder.like(
                    builder.lower(root.get("name")), "%" + partFilter.getName().toLowerCase() + "%"
            ));
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }


}

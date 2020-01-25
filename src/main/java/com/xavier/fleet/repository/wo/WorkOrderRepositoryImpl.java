package com.xavier.fleet.repository.wo;

import com.xavier.fleet.model.WorkOrder;
import com.xavier.fleet.repository.filter.WorkOrderFilter;
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

public class WorkOrderRepositoryImpl implements WorkOrderRepositoryQuery {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<WorkOrder> filter(WorkOrderFilter workOrderFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<WorkOrder> criteria = builder.createQuery(WorkOrder.class);
        Root<WorkOrder> root = criteria.from(WorkOrder.class);

        Predicate[] predicates = createRestrictions(workOrderFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<WorkOrder> query = manager.createQuery(criteria);
        addPaginationRestrictions(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(workOrderFilter));
    }

    private long total(WorkOrderFilter WorkOrderFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<WorkOrder> root = criteria.from(WorkOrder.class);

        Predicate[] predicates = createRestrictions(WorkOrderFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private void addPaginationRestrictions(TypedQuery<WorkOrder> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPage = currentPage * totalRecordsPerPage;

        query.setFirstResult(firstPage);
        query.setMaxResults(totalRecordsPerPage);
    }

    private Predicate[] createRestrictions(WorkOrderFilter workOrderFilter, CriteriaBuilder builder, Root<WorkOrder> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(workOrderFilter.getCustomer())) {
            predicates.add(builder.like(
                    builder.lower(root.get("customer")), "%" + workOrderFilter.getCustomer().toLowerCase() + "%"
            ));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

}


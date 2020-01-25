package com.xavier.fleet.repository.po;

import com.xavier.fleet.model.PurchaseOrder;
import com.xavier.fleet.repository.filter.PurchaseOrderFilter;
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

public class PurchaseOrderRepositoryImpl implements PurchaseOrderRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<PurchaseOrder> filter(PurchaseOrderFilter purchaseOrderFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<PurchaseOrder> criteria = builder.createQuery(PurchaseOrder.class);
        Root<PurchaseOrder> root = criteria.from(PurchaseOrder.class);

        Predicate[] predicates = createRestrictions(purchaseOrderFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<PurchaseOrder> query = manager.createQuery(criteria);
        addPaginationRestrictions(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(purchaseOrderFilter));
    }

    private long total(PurchaseOrderFilter purchaseOrderFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<PurchaseOrder> root = criteria.from(PurchaseOrder.class);

        Predicate[] predicates = createRestrictions(purchaseOrderFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private void addPaginationRestrictions(TypedQuery<PurchaseOrder> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPage = currentPage * totalRecordsPerPage;

        query.setFirstResult(firstPage);
        query.setMaxResults(totalRecordsPerPage);
    }

    private Predicate[] createRestrictions(PurchaseOrderFilter purchaseOrderFilter, CriteriaBuilder builder, Root<PurchaseOrder> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(purchaseOrderFilter.getCustomer())) {
            predicates.add(builder.like(
                    builder.lower(root.get("customer")), "%" + purchaseOrderFilter.getCustomer().toLowerCase() + "%"
            ));
        }

        if (!StringUtils.isEmpty(purchaseOrderFilter.getNumber())) {
            predicates.add(builder.like(
                    builder.lower(root.get("number")), "%" + purchaseOrderFilter.getNumber() + "%"
            ));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

}


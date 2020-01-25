package com.xavier.fleet.repository.Customer;

import com.xavier.fleet.model.Customer;
import com.xavier.fleet.repository.filter.CustomerFilter;
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

public class CustomerRepositoryImpl implements CustomerRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Customer> filter(CustomerFilter customerFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
        Root<Customer> root = criteria.from(Customer.class);

        Predicate[] predicates = createRestrictions(customerFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Customer> query = manager.createQuery(criteria);
        addPaginationRestrictions(query, pageable);

        return new PageImpl<Customer>(query.getResultList(), pageable, total(customerFilter));
    }

    private long total(CustomerFilter customerFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Customer> root = criteria.from(Customer.class);

        Predicate[] predicates = createRestrictions(customerFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private void addPaginationRestrictions(TypedQuery<Customer> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPage = currentPage * totalRecordsPerPage;

        query.setFirstResult(firstPage);
        query.setMaxResults(totalRecordsPerPage);
    }

    private Predicate[] createRestrictions(CustomerFilter customerFilter, CriteriaBuilder builder, Root<Customer> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(customerFilter.getName())) {
            predicates.add(builder.like(
                    builder.lower(root.get("name")), "%" + customerFilter.getName().toLowerCase() + "%"
            ));
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }
}

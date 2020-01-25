package com.xavier.fleet.repository.equipment;

import com.xavier.fleet.model.Equipment;
import com.xavier.fleet.repository.filter.EquipmentFilter;
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

public class EquipmentRepositoryImpl implements EquipmentRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Equipment> filter(EquipmentFilter equipmentFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Equipment> criteria = builder.createQuery(Equipment.class);
        Root<Equipment> root = criteria.from(Equipment.class);

        Predicate[] predicates = createRestrictions(equipmentFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Equipment> query = manager.createQuery(criteria);
        addPaginationRestrictions(query, pageable);

        return new PageImpl<Equipment>(query.getResultList(), pageable, total(equipmentFilter));
    }

    private long total(EquipmentFilter equipmentFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Equipment> root = criteria.from(Equipment.class);

        Predicate[] predicates = createRestrictions(equipmentFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private void addPaginationRestrictions(TypedQuery<Equipment> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPage = currentPage * totalRecordsPerPage;

        query.setFirstResult(firstPage);
        query.setMaxResults(totalRecordsPerPage);
    }

    private Predicate[] createRestrictions(EquipmentFilter equipmentFilter, CriteriaBuilder builder, Root<Equipment> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(equipmentFilter.getMake())) {
            predicates.add(builder.like(
                    builder.lower(root.get("make")), "%" + equipmentFilter.getMake().toLowerCase() + "%"
            ));

        }

        if (!StringUtils.isEmpty(equipmentFilter.getModel())) {
            predicates.add(builder.like(
                    builder.lower(root.get("model")), "%" + equipmentFilter.getModel().toLowerCase() + "%"
            ));
        }

        if (!StringUtils.isEmpty((equipmentFilter.getNumberPlate()))) {
            predicates.add(builder.like(
                    builder.lower(root.get("numberPlate")), "%" + equipmentFilter.getNumberPlate().toLowerCase() + "%"
            ));
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }

}


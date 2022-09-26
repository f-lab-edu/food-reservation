package com.foodlab.foodReservation.store.repository;


import com.foodlab.foodReservation.store.dto.response.StoreListResponse;
import com.foodlab.foodReservation.store.entity.QStore;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public class StoreRepositoryCustomImpl implements StoreRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public StoreRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    QStore store = QStore.store;

    @Override
    public Page<StoreListResponse> getStores(Pageable pageable) {

        List<StoreListResponse> storeList =
                queryFactory.select(
                                Projections.constructor(StoreListResponse.class
                                        , store.id.as("storeId")
                                        , store.name.as("storeName")
                                ))
                        .from(store)
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(store.id.desc())
                        .fetch();

        int count = queryFactory
                .selectFrom(store)
                .fetch()
                .size();

        return new PageImpl<>(storeList, pageable, count);
    }
}

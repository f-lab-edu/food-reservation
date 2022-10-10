package com.foodlab.foodReservation.item.repository;

import com.foodlab.foodReservation.item.entity.QItem;
import com.foodlab.foodReservation.store.dto.response.StoreDetailWithItemsResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    QItem item = QItem.item;

    @Override
    public Page<StoreDetailWithItemsResponse> getItems(Long storeId, Pageable pageable) {

        List<StoreDetailWithItemsResponse> items = queryFactory
                .select(Projections.constructor(StoreDetailWithItemsResponse.class
                        , item.store.id.as("storeId")
                        , item.store.name.as("storeName")
                        , item.store.address.address
                        , item.id.as("itemId")
                        , item.name.as("itemName")
                        , item.price
                ))
                .where(item.store.id.eq(storeId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int count = queryFactory.selectFrom(item)
                .where(item.store.id.eq(storeId))
                .fetch()
                .size();

        return new PageImpl<>(items, pageable, count);
    }
}

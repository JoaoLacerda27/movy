package com.movy.application.repository.custom.Delivery;

import com.movy.application.domain.enums.DeliveryStatus;
import com.movy.application.domain.model.Delivery;
import com.movy.application.domain.model.QDelivery;
import com.querydsl.core.BooleanBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public class DeliveryRepositoryCustomImpl implements DeliveryRepositoryCustom {

    private final EntityManager entityManager;

    @Autowired
    public DeliveryRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Delivery> findDeliveriesWithFilters(UUID senderId, UUID recipientId, Instant startDate, Instant endDate, String status) {
        QDelivery qDelivery = QDelivery.delivery;

        BooleanBuilder whereClause = new BooleanBuilder();

        if (senderId != null) {
            whereClause.and(qDelivery.sender.id.eq(senderId));
        }

        if (recipientId != null) {
            whereClause.and(qDelivery.recipient.id.eq(recipientId));
        }

        if (startDate != null && endDate != null) {
            whereClause.and(qDelivery.createdAt.between(startDate, endDate));
        } else if (startDate != null) {
            whereClause.and(qDelivery.createdAt.goe(startDate));
        } else if (endDate != null) {
            whereClause.and(qDelivery.createdAt.loe(endDate));
        }

        if (status != null) {
            try {
                DeliveryStatus statusEnum = DeliveryStatus.valueOf(status.toUpperCase());
                whereClause.and(qDelivery.status.eq(statusEnum));
            } catch (IllegalArgumentException e) {

            }
        }

        TypedQuery<Delivery> query = entityManager.createQuery(
                "SELECT d FROM Delivery d WHERE " + whereClause, Delivery.class);

        return query.getResultList();
    }
}

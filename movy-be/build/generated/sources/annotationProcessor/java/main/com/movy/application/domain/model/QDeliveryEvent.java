package com.movy.application.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDeliveryEvent is a Querydsl query type for DeliveryEvent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeliveryEvent extends EntityPathBase<DeliveryEvent> {

    private static final long serialVersionUID = -1262444207L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDeliveryEvent deliveryEvent = new QDeliveryEvent("deliveryEvent");

    public final QDelivery delivery;

    public final StringPath description = createString("description");

    public final EnumPath<com.movy.application.domain.enums.DeliveryEventType> eventType = createEnum("eventType", com.movy.application.domain.enums.DeliveryEventType.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath location = createString("location");

    public final DateTimePath<java.time.Instant> timestamp = createDateTime("timestamp", java.time.Instant.class);

    public QDeliveryEvent(String variable) {
        this(DeliveryEvent.class, forVariable(variable), INITS);
    }

    public QDeliveryEvent(Path<? extends DeliveryEvent> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDeliveryEvent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDeliveryEvent(PathMetadata metadata, PathInits inits) {
        this(DeliveryEvent.class, metadata, inits);
    }

    public QDeliveryEvent(Class<? extends DeliveryEvent> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.delivery = inits.isInitialized("delivery") ? new QDelivery(forProperty("delivery"), inits.get("delivery")) : null;
    }

}


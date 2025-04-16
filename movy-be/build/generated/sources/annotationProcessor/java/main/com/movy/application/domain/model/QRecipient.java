package com.movy.application.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecipient is a Querydsl query type for Recipient
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipient extends EntityPathBase<Recipient> {

    private static final long serialVersionUID = -1775816220L;

    public static final QRecipient recipient = new QRecipient("recipient");

    public final StringPath address = createString("address");

    public final DateTimePath<java.time.Instant> createdAt = createDateTime("createdAt", java.time.Instant.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath name = createString("name");

    public QRecipient(String variable) {
        super(Recipient.class, forVariable(variable));
    }

    public QRecipient(Path<? extends Recipient> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecipient(PathMetadata metadata) {
        super(Recipient.class, metadata);
    }

}


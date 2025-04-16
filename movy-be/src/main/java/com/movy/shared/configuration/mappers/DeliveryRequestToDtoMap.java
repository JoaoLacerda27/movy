package com.movy.shared.configuration.mappers;

import com.movy.application.controller.request.DeliveryRequest;
import com.movy.application.dto.DeliveryDTO;
import com.movy.application.dto.RecipientDTO;
import com.movy.application.dto.UserDTO;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class DeliveryRequestToDtoMap extends PropertyMap<DeliveryRequest, DeliveryDTO> {

    @Override
    protected void configure() {
        using(ctx -> {
            var id = (ctx.getSource() != null) ? (ctx.getSource()) : null;
            if (id == null) return null;
            var recipient = new RecipientDTO();
            recipient.setId((java.util.UUID) id);
            return recipient;
        }).map(source.getRecipientId(), destination.getRecipient());

        using(ctx -> {
            var id = (ctx.getSource() != null) ? (ctx.getSource()) : null;
            if (id == null) return null;
            var sender = new UserDTO();
            sender.setId((java.util.UUID) id);
            return sender;
        }).map(source.getSenderId(), destination.getSender());
    }
}
package org.ontario.images.model.projections;

import org.springframework.beans.factory.annotation.Value;

public interface ReadableUser {

    @Value("#{target.id}")
    Long getId();

    String getEmail();

    String getFirstName();

    String getLastName();

    String getNickName();

    Boolean getActivated();
}

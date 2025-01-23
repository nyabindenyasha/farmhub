package com.xplug.tech.audit;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

@Embeddable
@Data
@Table(name = "resources")
public class Resource {

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Resource() {
    }

    public Resource(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static void validateResource(Resource resource) {
        requireNonNull(resource, "Resource details should be provided");
        checkArgument(nonNull(resource.getName()),
                "Valid resource name should be provided");
    }

}

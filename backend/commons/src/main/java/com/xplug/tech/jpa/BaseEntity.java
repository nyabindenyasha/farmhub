package com.xplug.tech.jpa;

import com.xplug.tech.security.SecurityUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

    @CreatedDate
    private LocalDateTime createdOn;

    @CreatedBy
    private String createdBy;


    @LastModifiedDate
    private LocalDateTime updatedOn;

    @LastModifiedBy
    private String updatedBy;

    private LocalDateTime deletedOn;

    private String deletedBy;

    private Boolean isDeleted = false;

    public void pseudoDelete() {
        this.isDeleted = true;
    }

    @PreUpdate
    @PrePersist
    public void beforeAnyUpdate() {
        if (isDeleted != null && isDeleted) {

            if (deletedBy == null) {
                deletedBy = SecurityUtils.getSignedInUsername();
            }

            if (getDeletedOn() == null) {
                deletedOn = LocalDateTime.now();
            }
        }
    }

}

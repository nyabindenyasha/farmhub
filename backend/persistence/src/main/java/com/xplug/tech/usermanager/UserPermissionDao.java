package com.xplug.tech.usermanager;

import com.xplug.tech.jpa.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;


public interface UserPermissionDao extends BaseRepository<UserPermission> {

    Collection<UserPermission> findByUser_Id(long userId);

    List<UserPermission> findByUser_IdAndPermission_Id(long userId, long permissionId);


    Page<UserPermission> findByUser_Id(long id, Pageable pageable);

    @Query("delete from UserPermission gp where gp.id in :ids")
    @Modifying
    void deleteAllByIds(@Param("ids") Collection<Long> userPermissionIds);

}

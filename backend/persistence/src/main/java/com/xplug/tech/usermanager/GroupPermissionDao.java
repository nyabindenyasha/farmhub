package com.xplug.tech.usermanager;

import com.xplug.tech.jpa.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface GroupPermissionDao extends BaseRepository<GroupPermission> {

    Collection<GroupPermission> findByUserGroup_Id(long groupId);

    Page<GroupPermission> findByUserGroup_Id(long id, Pageable pageable);

    @Query("delete from GroupPermission gp where gp.id in :ids")
    @Modifying
    void deleteAllByIds(@Param("ids") Collection<Long> groupPermissionIds);

    List<GroupPermission> findByUserGroup_IdAndPermission_Id(long groupId, long permissionId);

}

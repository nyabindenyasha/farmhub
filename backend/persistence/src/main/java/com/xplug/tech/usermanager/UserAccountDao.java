package com.xplug.tech.usermanager;

import com.xplug.tech.jpa.BaseRepository;
import com.xplug.tech.usermanager.UserAccount;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;


@Repository
public interface UserAccountDao extends BaseRepository<UserAccount> {

    Optional<UserAccount> findByUsername(String username);

    UserAccount getByUsername(String username);

    Optional<UserAccount> findByEmail(String email);

    UserAccount getByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Collection<UserAccount> findByGroup_Id(long groupId);


    Collection<UserAccount> findByUsernameAndGroup_Id(String username, long groupId);

}

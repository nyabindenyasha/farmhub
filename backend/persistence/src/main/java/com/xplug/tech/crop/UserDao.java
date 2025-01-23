package com.xplug.tech.crop;

import com.xplug.tech.usermanager.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserAccount, Long> {

}

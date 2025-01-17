package com.xplug.tech.crop.management.dao;

import com.xplug.tech.crop.management.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountDao extends JpaRepository<UserAccount, Long> {

}

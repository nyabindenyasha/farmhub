package com.xplug.tech.crop.management.service.usermanager;

import com.xplug.tech.crop.management.dao.UserAccountDao;
import com.xplug.tech.crop.management.domain.UserAccount;
import com.xplug.tech.crop.management.exceptions.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public non-sealed class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountDao userAccountRepository;

    public UserAccountServiceImpl(UserAccountDao userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount create(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    public UserAccount getById(Long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("User Account not found with Id: " + id));
    }

}

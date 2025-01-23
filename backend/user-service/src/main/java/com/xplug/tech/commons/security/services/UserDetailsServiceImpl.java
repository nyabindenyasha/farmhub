package com.xplug.tech.commons.security.services;

import com.xplug.tech.usermanager.permissions.group.GroupPermissionService;
import com.xplug.tech.usermanager.UserPermission;
import com.xplug.tech.usermanager.permissions.user.UserPermissionsService;
import com.xplug.tech.usermanager.UserAccount;
import com.xplug.tech.usermanager.UserAccountDao;
import com.xplug.tech.usermanager.GroupPermission;
import com.xplug.tech.usermanager.Permission;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

//    private final UserAccountService userAccountService;

    private final UserAccountDao userAccountDao;

    private final GroupPermissionService groupPermissionService;

    private final UserPermissionsService userPermissionService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        UserAccount user = userAccountService.findByUsername(username);

        UserAccount user = userAccountDao.getByUsername(username);

        val groupAuthorities = groupPermissionService.findAllByGroupId(user.getGroup().getId());
        groupAuthorities.stream().map(GroupPermission::getPermission)
                .map(Permission::getName)
                .map(SimpleGrantedAuthority::new)
                .forEach(user::addPermission);

        val userAuthorities = userPermissionService.findAllByUserId(user.getId());
        userAuthorities.stream().map(UserPermission::getPermission)
                .map(Permission::getName)
                .map(SimpleGrantedAuthority::new)
                .forEach(user::addPermission);

        UserPrinciple userPrinciple = new UserPrinciple();

        return userPrinciple.build(user);
    }

}
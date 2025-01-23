package com.xplug.tech.enums;


import com.xplug.tech.usermanager.UserGroupEnum;
import com.xplug.tech.usermanager.user.UserPortal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;


@Slf4j
@CrossOrigin
@RequestMapping("v1/api/enums/")
@RestController
public class EnumsEndpoint {


    @GetMapping("user-groups")

    public Collection<UserGroupEnum> getUserGroupEnum() {
        return Arrays.asList(UserGroupEnum.values());
    }

    @GetMapping("user-portal")

    public Collection<UserPortal> getUserPortalEnum() {
        return Arrays.asList(UserPortal.values());
    }

    @GetMapping("member-types")
    public Collection<MemberType> getMemberTypeEnum() {
        return Arrays.asList(MemberType.values());
    }

    @GetMapping("sponsor-types")
    public Collection<SponsorType> getSponsorTypeEnum() {
        return Arrays.asList(SponsorType.values());
    }

}

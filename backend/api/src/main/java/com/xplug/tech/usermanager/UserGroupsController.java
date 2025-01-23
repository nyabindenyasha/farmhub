package com.xplug.tech.usermanager;


import com.xplug.tech.exception.InvalidRequestException;
import com.xplug.tech.pojo.ResponseMessage;
import com.xplug.tech.usermanager.usergroup.UserGroupRequest;
import com.xplug.tech.usermanager.usergroup.UserGroupService;
import com.xplug.tech.usermanager.usergroup.UserGroupUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@RestController

@RequestMapping("v1/usergroups")
public class UserGroupsController {

    private final UserGroupService userGroupService;

    public UserGroupsController(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @GetMapping("")

    public ResponseEntity<?> getAll(@PageableDefault(sort = "name") Pageable pageable,
                                    @RequestParam(required = false) String search) {
        try {
            Page<UserGroup> userGroups = userGroupService.findAll(pageable, search);
            return new ResponseEntity<Page<UserGroup>>(userGroups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")

    public ResponseEntity<?> getAll() {
        try {
            Collection<UserGroup> userGroups = userGroupService.findAll();
            return new ResponseEntity<Collection<UserGroup>>(userGroups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")

    public ResponseEntity<?> getUserGroups(@PathVariable long id) {
        try {
            UserGroup userGroup = userGroupService.findById(id);
            return new ResponseEntity<UserGroup>(userGroup, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<?> delete(@PathVariable long id) {
        try {
            userGroupService.delete(id);
            return new ResponseEntity<>(new ResponseMessage("success"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("")

    public ResponseEntity<?> create(@RequestBody UserGroupRequest request) {
        try {
            UserGroup userGroupCreated = userGroupService.create(request);
            return new ResponseEntity<UserGroup>(userGroupCreated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")

    public ResponseEntity<?> update(@RequestBody UserGroupUpdateRequest request, @PathVariable long id) {
        try {
            if (request.getId() != id) {
                throw new InvalidRequestException("Record not found!");
            }
            UserGroup userGroupUpdated = userGroupService.update(request);
            return new ResponseEntity<UserGroup>(userGroupUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}

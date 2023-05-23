package com.okta.developer.jugtours.web;

import com.okta.developer.jugtours.model.Group;
import com.okta.developer.jugtours.service.GroupService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Collection;


@RestController
@RequestMapping("/api")
public class GroupController {

    private final Logger log = LoggerFactory.getLogger(GroupController.class);
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

   /* @GetMapping("/groups")
    Collection<Group> groups() {
        log.info("Request to get all groups");
        return groupService.getAll();
    }*/
    @GetMapping("/groups")
    Collection<Group> groups(Principal principal) {
        log.info("Request to get all groups");
        return groupService.groups(principal);
    }

    @GetMapping("/group/{id}")
    ResponseEntity<?> getGroup(@PathVariable Long id) {
        log.info("Request to get group by id: {}", id);
        return groupService.getGroupById(id);
    }

    /*@PostMapping("/group")
    ResponseEntity<Group> createGroup(@Valid @RequestBody Group group) throws URISyntaxException {
        log.info("Request to create group: {}", group);
        return groupService.create(group);
    }*/
    @PostMapping("/group")
    ResponseEntity<Group> createGroup(@Valid @RequestBody Group group,
                                      @AuthenticationPrincipal OAuth2User principal)
            throws URISyntaxException {
        log.info("Request to create group: {}", group);
        return groupService.createGroup(group, principal);
    }

    // ???
    @PutMapping("/group/{id}")
    ResponseEntity<Group> updateGroup(@Valid @RequestBody Group group, @PathVariable Long id) {
        log.info("Request to update group: {}", group);
        return groupService.updateGroup(group, id);
    }

    @DeleteMapping("/group/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
        log.info("Request to delete group: {}", id);
        return groupService.deleteGroup(id);
    }
}

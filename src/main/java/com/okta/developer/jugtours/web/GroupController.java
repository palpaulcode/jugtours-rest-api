package com.okta.developer.jugtours.web;

import com.okta.developer.jugtours.model.NotFoundException;
import com.okta.developer.jugtours.model.Group;
import com.okta.developer.jugtours.model.GroupRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class GroupController {

    private final Logger log = LoggerFactory.getLogger(GroupController.class);
    private final GroupRepository groupRepository;

    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @GetMapping("/groups")
    Collection<Group> groups() {
        log.info("Request to get all groups");
        return groupRepository.findAll();
    }

    @GetMapping("/group/{id}")
    ResponseEntity<?> getGroup(@PathVariable Long id) {
        log.info("Request to get group with id: {}", id);
        Optional<Group> group = groupRepository.findById(id);
        return group.map(response -> ResponseEntity.ok().body(response))
                //.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
                .orElseThrow(() -> new NotFoundException(String.format("Group with id %s not found", id)));
    }

    @PostMapping("/group")
    ResponseEntity<Group> createGroup(@Valid @RequestBody Group group) throws URISyntaxException {
        log.info("Request to create group: {}", group);
        Group result = groupRepository.save(group);
        return ResponseEntity.created(new URI("/api/group/" + result.getId())).body(result);
    }

    // ???
    @PutMapping("/group/{id}")
    ResponseEntity<Group> updateGroup(@Valid @RequestBody Group group, @PathVariable Long id) {
        log.info("Request to update group: {}", group);
        Group update = groupRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Group with id %s not found", id)));

        // update.setId(group.getId());
        update.setName(group.getName());
        update.setAddress(group.getAddress());
        groupRepository.save(update);
        return ResponseEntity.ok().body(update);
    }

    @DeleteMapping("/group/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
        log.info("Request to delete group: {}", id);
        groupRepository.deleteById(id);
        return ResponseEntity.ok()//.build();
                .body("deleted");
    }
}

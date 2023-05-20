package com.okta.developer.jugtours.service;

import com.okta.developer.jugtours.exceptions.NotFoundException;
import com.okta.developer.jugtours.model.Group;
import com.okta.developer.jugtours.repository.GroupRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Collection<Group> getAll() {
        return groupRepository.findAll();
    }

    public ResponseEntity<?> getById(Long id) {
        Optional<Group> group = groupRepository.findById(id);
        return group.map(response -> ResponseEntity.ok().body(response))
                .orElseThrow(() -> new NotFoundException(String.format("Group with id %s not found", id)));
    }

    public ResponseEntity<Group> create(Group group)throws URISyntaxException {
        Group result = groupRepository.save(group);
        return ResponseEntity.created(new URI("/api/group/" + result.getId())).body(result);
    }

    public ResponseEntity<Group> update(Group group, Long id) {
        Group update = groupRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Group with id %s not found", id)));

        update.setName(group.getName());
        update.setAddress(group.getAddress());
        groupRepository.save(update);
        return ResponseEntity.ok().body(update);
    }

    public ResponseEntity<?> delete(Long id) {
        groupRepository.deleteById(id);
        return ResponseEntity.ok().body("deleted");
    }
}

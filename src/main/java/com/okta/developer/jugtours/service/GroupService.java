package com.okta.developer.jugtours.service;

import com.okta.developer.jugtours.exceptions.NotFoundException;
import com.okta.developer.jugtours.model.Group;
import com.okta.developer.jugtours.model.User;
import com.okta.developer.jugtours.repository.GroupRepository;
import com.okta.developer.jugtours.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    /*public Collection<Group> getAll() {
        return groupRepository.findAll();
    }*/
    public Collection<Group> groups(Principal principal) {
        return groupRepository.findAllByUserId(principal.getName());
    }

    public ResponseEntity<?> getGroupById(Long id) {
        Optional<Group> group = groupRepository.findById(id);
        return group.map(response -> ResponseEntity.ok().body(response))
                .orElseThrow(() -> new NotFoundException(String.format("Group with id %s not found", id)));
    }

    /*public ResponseEntity<Group> create(Group group)throws URISyntaxException {
        Group result = groupRepository.save(group);
        return ResponseEntity.created(new URI("/api/group/" + result.getId())).body(result);
    }*/
    public ResponseEntity<Group> createGroup(Group group, OAuth2User principal) throws URISyntaxException {
        Map<String, Object> details = principal.getAttributes();
        String userId = details.get("sub").toString();

        // check to see if user already exists
        Optional<User> user =  userRepository.findById(userId);
        group.setUser(user.orElse(
                new User(userId, details.get("name").toString(), details.get("email").toString())));

        Group result = groupRepository.save(group);
        return ResponseEntity.created(new URI("/api/group" + result.getId())).body(result);
    }

    public ResponseEntity<Group> updateGroup(Group group, Long id) {
        Group update = groupRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Group with id %s not found", id)));

        update.setName(group.getName());
        update.setAddress(group.getAddress());
        groupRepository.save(update);
        return ResponseEntity.ok().body(update);
    }

    public ResponseEntity<?> deleteGroup(Long id) {
        groupRepository.deleteById(id);
        return ResponseEntity.ok().body("deleted");
    }
}

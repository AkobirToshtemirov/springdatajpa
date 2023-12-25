package com.pdp.springdatajpa.service;

import com.pdp.springdatajpa.entity.Group;
import com.pdp.springdatajpa.exception.NotFoundException;
import com.pdp.springdatajpa.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group findByName(String name) {
        return groupRepository.findByName(name);
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElseThrow(()-> new NotFoundException("Group not found with id: " + id));
    }

    public Group createOrUpdateGroup(Group group) {
        return groupRepository.save(group);
    }

    public void deleteGroupById(Long id) {
        groupRepository.deleteById(id);
    }
}

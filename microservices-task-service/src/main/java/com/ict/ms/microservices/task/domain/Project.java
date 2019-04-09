package com.ict.ms.microservices.task.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ict.ms.microservices.task.domain.exception.TaskNotFoundException;
import com.ict.ms.microservices.task.domain.vo.UserEmail;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Getter
@EqualsAndHashCode(of="id")
public class Project{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @AttributeOverride(name = "email", column = @Column(name="owner"))
    private UserEmail owner;
    private String name;
    private Status status = Status.NEW;
    private UUID uuid;

    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Task> tasks;

    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ProjectMember> members;

    private Project(){
        members = new HashSet<>();
        tasks = new HashSet<>();
    }

    @Builder
    public Project(UserEmail owner, String name) {
        this();
        this.owner = owner;
        this.name = name;
        this.uuid = UUID.randomUUID();
        this.status = Status.NEW;
        addMember(ProjectMember.builder()
                .user(owner)
                .project(this)
                .build());
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        task.addToProject(this);
    }

    public Task findTask(UUID uuid) {
        return tasks.stream().filter(task -> task.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException(uuid));
    }

    private void addMember(ProjectMember member) {
        this.members.add(member);
    }

    public boolean isMember(UserEmail user){
        return members.stream().anyMatch(member -> member.getUser().equals(user));
    }


    public void updateStatus(Status status) {
        this.status = status;
    }
}

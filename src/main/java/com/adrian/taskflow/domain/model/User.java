package com.adrian.taskflow.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email_address", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Project> projects =  new HashSet<>();

    @OneToMany(mappedBy = "assignee", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> assignedTasks =  new HashSet<>();

    @ManyToMany(mappedBy = "members")
    private Set<Project> projectUsers = new HashSet<>();

    @OneToMany(mappedBy = "leader", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Invitation> invitations =  new HashSet<>();

    //for the members of the projects
    public void addProjectUser(Project project) {
        this.projectUsers.add(project);
        project.getMembers().add(this);
    }

    public void removeProjectUser(Project project) {
        this.projectUsers.remove(project);
        project.getMembers().remove(this);
    }

    public void removeAllProjectUser() {
        projectUsers.forEach(project -> project.setOwner(null));
        projectUsers.clear();
    }

    //for the project owner
    public void addProject(Project project) {
        this.projects.add(project);
        project.setOwner(this);
    }

    public void removeProject(Project project) {
        this.projects.remove(project);
        project.setOwner(null);
    }


    public void addTask(Task task) {
        this.assignedTasks.add(task);
        task.setAssignee(this);
    }

    public void removeTask(Task task) {
        this.assignedTasks.remove(task);
        task.setAssignee(null);
    }


    public void addInvitation(Invitation invitation) {
        this.invitations.add(invitation);
        invitation.setLeader(this);
    }

    public void removeInvitation(Invitation invitation) {
        this.invitations.remove(invitation);
        invitation.setLeader(null);
    }
}

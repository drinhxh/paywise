package com.paywise.paywise.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Roles {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User user_role;

  @Column(name = "role")
  private String role;

  public Roles(){

  }

  public Roles(String role){
    this.role = role;
  }

  public Roles(User userRole, String role) {
    this.user_role = userRole;
    this.role = role;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public User getUserRole() {
    return user_role;
  }

  public void setUserRole(User userRole) {
    this.user_role = userRole;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String roles) {
    this.role = roles;
  }

  @Override
  public String toString() {
    return "Roles{" +
            "id=" + id +
            ", user_role=" + (user_role != null ? user_role.getId() : null) +
            ", role='" + role + '\'' +
            '}';
  }
}

package com.paywise.paywise.dao;

import com.paywise.paywise.entity.Roles;
import com.paywise.paywise.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RolesDAOImpl implements RolesDAO{

  private final EntityManager entityManager;
  private final UserDAO userDAO;

  @Autowired
  public RolesDAOImpl(EntityManager entityManager, UserDAO userDAO) {
    this.entityManager = entityManager;
    this.userDAO = userDAO;
  }


  @Override
  @Transactional
  public void saveRole(Roles role) {
    entityManager.persist(role);
  }

  @Override
  @Transactional
  public Roles addRole(int userId, String role) {
    User user = userDAO.findUserById(userId);

    if(user == null){
      throw new IllegalArgumentException("Invalid user!");
    }

    Roles newRole = new Roles();
    newRole.setRole(role);
    saveRole(newRole);

    user.addRole(newRole);
    userDAO.updateRoles(userId, newRole);

    return newRole;
  }


}

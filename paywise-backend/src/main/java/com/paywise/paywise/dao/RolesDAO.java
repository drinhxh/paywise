package com.paywise.paywise.dao;

import com.paywise.paywise.entity.Roles;

public interface RolesDAO {
  void saveRole(Roles role);
  Roles addRole(int id, String role);
}

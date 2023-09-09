package com.example.FootballRest.model.repo;

import com.example.FootballRest.model.entity.Member;
import com.example.FootballRest.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, String> {

}

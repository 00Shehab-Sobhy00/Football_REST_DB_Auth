package com.example.FootballRest.model.repo;


import com.example.FootballRest.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository <Member , String> {


}

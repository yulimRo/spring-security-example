package com.yoodaengs.springboot.spring_sercurity_example.repository;

import com.yoodaengs.springboot.spring_sercurity_example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

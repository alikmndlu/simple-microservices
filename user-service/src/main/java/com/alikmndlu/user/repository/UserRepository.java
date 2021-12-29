package com.alikmndlu.user.repository;

import com.alikmndlu.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

package com.rentr.contractormanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rentr.contractormanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

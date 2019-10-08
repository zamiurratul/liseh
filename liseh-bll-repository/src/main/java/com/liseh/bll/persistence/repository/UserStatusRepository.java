package com.liseh.bll.persistence.repository;

import com.liseh.bll.persistence.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {
    List<UserStatus> findAllByUserId(String userIdentifier);
}

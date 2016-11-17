package com.exam.domain.dao;

import com.exam.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by NeilHY on 2016/11/15.
 */
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Admin findByAdminName(String username);
}

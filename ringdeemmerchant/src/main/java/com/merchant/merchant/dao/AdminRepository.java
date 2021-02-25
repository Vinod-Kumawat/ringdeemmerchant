package com.merchant.merchant.dao;

import com.merchant.merchant.bean.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByUserName(String userName);
    }


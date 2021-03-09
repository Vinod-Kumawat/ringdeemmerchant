package com.merchant.merchant.dao;

import com.merchant.merchant.bean.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Transactional
    Admin findByUserName(String userName);
    }


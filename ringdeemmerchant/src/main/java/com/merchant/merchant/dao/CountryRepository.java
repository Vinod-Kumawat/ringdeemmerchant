package com.merchant.merchant.dao;

import com.merchant.merchant.bean.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}

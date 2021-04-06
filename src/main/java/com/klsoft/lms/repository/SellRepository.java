package com.klsoft.lms.repository;

import com.klsoft.lms.entity.Sell;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellRepository extends CrudRepository<Sell, Integer> {
}

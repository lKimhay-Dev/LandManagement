package com.klsoft.lms.repository;

import com.klsoft.lms.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);

    Iterable<User> findAllByStatus(boolean status);

    User findByIdAndStatus(int id, boolean status);

    User findBySellerId(int sellerId);
}

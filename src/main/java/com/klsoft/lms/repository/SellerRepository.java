package com.klsoft.lms.repository;

import com.klsoft.lms.entity.Seller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SellerRepository extends CrudRepository<Seller, Integer> {
    Iterable<Seller> findAllByStatus(boolean status);

    Seller findByIdAndStatus(int id, boolean status);

    String QUERY = "SELECT s.id, s.name, s.phone1 FROM Seller s LEFT JOIN User " +
            "u on s.id = u.seller.id WHERE u.seller.id IS NULL AND s.status = true";
    @Query(QUERY)
    Iterable<Seller> findAvailableSellerForUser();
}

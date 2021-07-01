package com.syh.passbook.merchants.dao;

import com.syh.passbook.merchants.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository<Merchant, Integer>
// Integer is the primary key of the table Merchant.
public interface MerchantDao extends JpaRepository<Merchant, Integer> {
    Optional<Merchant> findById(Integer id);
    Optional<Merchant> findByName(String name);
}

package com.demo.coinbase.dao.repository;

import com.demo.coinbase.dao.model.UserType;
import org.springframework.data.repository.CrudRepository;

public interface UserTypeRepository extends CrudRepository<UserType, Integer> {

    UserType findById(int i);
}

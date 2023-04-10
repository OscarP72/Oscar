package com.example.demo.bancoRepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.bancoEntityes.AwardFineType;

@Repository
public interface AwardFineTypeRepository extends CrudRepository<AwardFineType,Integer> {


}

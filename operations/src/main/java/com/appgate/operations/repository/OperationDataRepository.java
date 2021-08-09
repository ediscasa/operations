package com.appgate.operations.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appgate.operations.model.OperationData;

@Repository
public interface OperationDataRepository extends CrudRepository<OperationData, String> {

}

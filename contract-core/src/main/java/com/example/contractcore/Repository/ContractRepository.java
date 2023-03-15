package com.example.contractcore.Repository;


import com.example.contractcore.Domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface ContractRepository extends
        JpaRepository<Contract,Long>,
        RevisionRepository<Contract,Long,Long> {

    Contract findByContractName(String name);
}

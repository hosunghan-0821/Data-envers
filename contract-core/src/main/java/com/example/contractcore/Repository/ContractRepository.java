package com.example.contractcore.Repository;


import com.example.contractcore.Domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract,Long> {

    Contract findByContractName(String name);
}

package com.example.contractcore.Repository;

import com.example.contractcore.Domain.Contract;
import org.springframework.data.repository.history.RevisionRepository;

public interface ContractHistoryRepository extends RevisionRepository<Contract,Long,Long> {

}

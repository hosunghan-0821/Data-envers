package com.example.contractapi.Service;

import com.example.contractcore.Domain.Contract;
import com.example.contractcore.Domain.File;
import com.example.contractcore.Repository.ContractHistoryRepository;
import com.example.contractcore.Repository.ContractRepository;
import com.example.contractcore.Repository.FileRepository;
import com.example.contractcore.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ContractService {
    private final ContractRepository contractRepository;

    private final ContractHistoryRepository contractHistoryRepository;
    private final MemberRepository memberRepository;

    private final FileRepository fileRepository;

    private final EntityManager em;

    @Transactional
    public void test() {
//
//        for (int i = 0; i < 10; ++i) {
//            Contract contract = Contract.builder().contractNum(i).contractName("123").files(new ArrayList<>()).build();
//            contractRepository.save(contract);
//        }

        Contract contract = contractRepository.findById(1L).orElse(null);
        System.out.println(contract.getId() + " " + contract.getContractNum());
        contract.update(Contract.builder().contractNum(12345).contractName("update").build());
    }

    public void getEnvers() {

        Revisions<Long, Contract> revisions = contractHistoryRepository.findRevisions(1L);
        for (Revision<Long, Contract> revision : revisions) {

            System.out.println("id : " + revision.getEntity().getId() + " contractNum : " + revision.getEntity().getContractNum());
        }
    }
}

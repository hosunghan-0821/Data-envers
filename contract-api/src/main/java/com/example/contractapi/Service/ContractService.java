package com.example.contractapi.Service;

import com.example.contractcore.Domain.Contract;
import com.example.contractcore.Domain.ContractReviewer;
import com.example.contractcore.Domain.File;
import com.example.contractcore.Domain.Member;
import com.example.contractcore.Repository.ContractRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ContractService {

    private final ObjectMapper objectMapper;
    private final ContractRepository contractRepository;
    private final AuditReader auditReader;

    private final EntityManager em;

    @Transactional
    public void test() {

        Contract contract = Contract.builder().contractNum(123).files(new ArrayList<>()).contractReviewers(new ArrayList<>()).contractName("test").build();
        contractRepository.saveAndFlush(contract);

        File file1 = File.builder().originalName("asd").build();
        File file2 = File.builder().originalName("zxc").build();

        file1.changeContract(contract);
        file2.changeContract(contract);

        List<Member> memberList = new ArrayList<>();

        Member member1 = Member.builder().age(1).build();
        Member member2 = Member.builder().age(2).build();

        em.persist(member1);
        em.persist(member2);

        em.flush();
        em.clear();

        //  Member member_new1 = em.createQuery("select  m from Member m  where m.id =5L", Member.class).getSingleResult();
        memberList.add(member1);
        memberList.add(member2);


        for (Member member : memberList) {
            ContractReviewer contractReviewer1 = ContractReviewer.builder().contract(contract).member(member).build();
            contractReviewer1.changeContract(contract);
            em.persist(contractReviewer1);
        }

    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getRevision(Class<?> t, Long revisionNumber) {

        List<Object[]> result = auditReader.createQuery()
                .forRevisionsOfEntity(t, false, true)
                .add(AuditEntity.revisionNumber().eq(revisionNumber.intValue()))
                .getResultList();
        List<T> returnList = new ArrayList<>();
        for (Object[] objects : result) {
            returnList.add((T) objects[0]);
        }
        return returnList;
    }

    //revision을 기반으로 가져오기
    public List<File> getEnvers2() {
        return getRevision(File.class, 1L);
    }

    //revision에 관련된 entity 다 긁어오기
    public void getEnvers3() {
        List<Object> objectList = auditReader.getCrossTypeRevisionChangesReader()
                .findEntities(2);

        try {
            for (Object o : objectList) {
                System.out.println(objectMapper.writeValueAsString(o));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<String> getEnvers() {

        Revisions<Long, Contract> revisions = contractRepository.findRevisions(1L);


        @SuppressWarnings("unchecked")
        List<Contract> contractHistory = auditReader.createQuery()
                .forRevisionsOfEntity(Contract.class, true, true)
                .add(AuditEntity.id().eq(1L))
                .add(AuditEntity.property("contractName").hasChanged())
                .getResultList();
        for (Contract contract : contractHistory) {
            System.out.println("name : " + contract.getContractName());
            System.out.println("num : " + contract.getContractNum());
        }

        System.out.println("===");

        @SuppressWarnings("unchecked")
        List<Contract> contractOnlyUpdateHistory = auditReader.createQuery()
                .forRevisionsOfEntity(Contract.class, true, true)
                .add(AuditEntity.id().eq(37L))
                .add(AuditEntity.revisionType().eq(RevisionType.MOD))
                .setFirstResult(0)
                .setMaxResults(2)
                .addOrder(AuditEntity.property("contractNum").desc())
                .getResultList();

        for (Contract contract : contractOnlyUpdateHistory) {
            for (File file : contract.getFiles()) {
                System.out.println(file.getOriginalName());
            }
        }

//
//        for (Revision<Long, Contract> revision : revisions) {
//            System.out.println("id : " + revision.getEntity().getId() + " contractNum : " + revision.getEntity().getContractNum());
//        }

        List<String> revisionString = revisions.stream().map(Object::toString).collect(Collectors.toList());
        return revisionString;
    }

    private List<Member> initMember() {
        List<Member> memberList = new ArrayList<>();
        memberList.add(Member.builder().age(1).build());
        memberList.add(Member.builder().age(2).build());
        memberList.add(Member.builder().age(3).build());
        memberList.add(Member.builder().age(4).build());
        return memberList;
    }
}

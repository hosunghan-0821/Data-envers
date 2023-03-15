package com.example.contractcore.Domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "contract_reviewer")
@Audited
public class ContractReviewer extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewer_id", nullable = false)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="member_id")
    private Member member;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "contract_id")
    private Contract contract;


    public void changeContract(Contract contract) {
        this.contract = contract;
        contract.getContractReviewers().add(this);
    }
}

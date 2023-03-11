package com.example.contractcore.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table
@Audited
public class Contract extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String contractName;

    @Column
    private int contractNum;

    @Audited(targetAuditMode = NOT_AUDITED)
    @OneToMany(mappedBy = "contract",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> files = new ArrayList<>();


    public void update(Contract contract) {
        this.contractName=contract.getContractName();
        this.contractNum=contract.getContractNum();
    }
}

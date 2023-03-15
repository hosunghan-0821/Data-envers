package com.example.contractcore.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.ModifiedEntityNames;

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
    @Audited(withModifiedFlag = true)
    private String contractName;

    @Column
    private int contractNum;

    @JsonIgnore
    @Audited(targetAuditMode = NOT_AUDITED)
    @OneToMany(mappedBy = "contract",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> files = new ArrayList<>();

    @JsonIgnore
    @Audited(targetAuditMode = NOT_AUDITED)
    @OneToMany(mappedBy = "contract",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ContractReviewer> contractReviewers = new ArrayList<>();

    public void update(String contractName) {
        this.contractName=contractName;
    }
}

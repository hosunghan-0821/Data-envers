package com.example.contractcore.Domain;

import com.example.contractcore.Repository.MemberRepository;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Audited
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member extends MainBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private String tel;


}

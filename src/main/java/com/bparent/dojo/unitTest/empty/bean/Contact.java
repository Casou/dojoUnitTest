package com.bparent.dojo.unitTest.empty.bean;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CONTACT")
@Data
@Builder
public class Contact {

    @Id
    private Integer id;

    @Column
    private String nom;

    @Column
    private String prenom;

    @Column
    private String email;

    @Column
    private Integer age;

}

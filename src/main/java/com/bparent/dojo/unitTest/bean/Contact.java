package com.bparent.dojo.unitTest.bean;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

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
    @Email
    private String email;

    @Column
    private Integer age;

}

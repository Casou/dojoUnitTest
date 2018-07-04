package com.bparent.dojo.unitTest.bean;

import java.time.LocalDateTime;

public class IhmBean extends IIhmBean {

    public IhmBean(Contact contact) {
        this.id = contact.getId();
        this.nomPrenom = contact.getNom() + " " + contact.getPrenom();
        this.horodateur = LocalDateTime.now();
    }

}

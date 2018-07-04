package com.bparent.dojo.unitTest.bean;

import java.time.LocalDateTime;

public class IhmBean extends IIhmBean {

    public IhmBean(Integer id, String nomPrenom) {
        this.id = id;
        this.nomPrenom = nomPrenom;
        this.horodateur = LocalDateTime.now();
    }

}

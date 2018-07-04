package com.bparent.dojo.unitTest.bean;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class IIhmBean {

    protected Integer id;
    protected String nomPrenom;
    protected LocalDateTime horodateur;

}

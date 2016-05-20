package com.foamtec.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by apichat on 5/18/2016 AD.
 */
@Entity
@Table(name="FaRequest")
public class FaRequest implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    @Column(name="createDate")
    private Date createDate;

    @OneToOne(fetch = FetchType.EAGER)
    private AppUser createBy;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    @Column(name="updateDate")
    private Date updateDate;

    @OneToOne(fetch = FetchType.EAGER)
    private AppUser updateBy;

    @Column(name="customer")
    private String customer;

    @Column(name="productGroup")
    private String productGroup;

    @Column(name="revision")
    private String revision;

    @Column(name="saleOut")
    private String saleOut;

    @Column(name="qwsNo")
    private String qwsNo;

    @Column(name="apqpNo")
    private String apqpNo;

    @Column(name="needDate")
    private String needDate;

    @Value("0")
    @Column(name="faApproveQty")
    private Integer faApproveQty;

    @Value("0")
    @Column(name="faForSellQty")
    private Integer faForSellQty;

    @Value("0")
    @Column(name="samplTestQty")
    private Integer samplTestQty;

    @Value("0")
    @Column(name="samplePccQty")
    private Integer samplePccQty;

    @Column(name="material1")
    private String material1;

    @Column(name="material2")
    private String material2;

    @Column(name="material3")
    private String material3;

    @Column(name="material4")
    private String material4;

    @Column(name="material5")
    private String material5;

    @Column(name="material6")
    private String material6;

    @Column(name="tool")
    private String tool;

    @Column(name="remark")
    private String remark;

    @Column(name="status")
    private String status;

    @OrderBy("createDate")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "faRequest")
    private Set<DocumentHistory> documentHistorys = new HashSet<DocumentHistory>();

}

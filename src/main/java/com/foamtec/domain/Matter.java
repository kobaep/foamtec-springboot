package com.foamtec.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by apichat on 4/20/2016 AD.
 */
@Table(name="Matter")
@Entity
public class Matter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    @Column(name = "createDate")
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    @Column(name = "updateDate")
    private Date updateDate;

    @OneToOne(fetch = FetchType.EAGER)
    private AppUser createBy;

    @OneToOne(fetch = FetchType.EAGER)
    private AppUser updateBy;

    @Column(name = "materialName", unique = true)
    private String materialName;

    @Column(name = "manufacturing")
    private String manufacturing;

    @Column(name = "rohs")
    @Lob
    private byte[] rohs;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "rohsDateTest")
    private Date rohsDateTest;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "rohsAlertDateTest")
    private Date rohsAlertDateTest;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "rohsEndDateTest")
    private Date rohsEndDateTest;

    @Column(name = "msds")
    @Lob
    private byte[] msds;

    @Column(name = "spec")
    @Lob
    private byte[] spec;

    @Column(name = "halogen")
    @Lob
    private byte[] halogen;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "halogenDateTest")
    private Date halogenDateTest;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "halogenAlertDateTest")
    private Date halogenAlertDateTest;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "halogenEndDateTest")
    private Date halogenEndDateTest;

    @Column(name = "status")
    private String status;

    @Column(name = "folw")
    private String folw;

    @Column(name = "ulNumber")
    private String ulNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "materialType")
    private MaterialType materialType;

    @Column(name = "reason")
    private String reason;

    @Column(name = "specUrl")
    private String specUrl;

    @Column(name = "rohsUrl")
    private String rohsUrl;

    @Column(name = "msdsUrl")
    private String msdsUrl;

    @Column(name = "halogenUrl")
    private String halogenUrl;

    @OrderBy("createDate")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "matter")
    private Set<MaterialCode> materialCodes = new HashSet<MaterialCode>();

    @OrderBy("createDate")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "matter")
    private Set<DocumentHistory> documentHistorys = new HashSet<DocumentHistory>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public AppUser getCreateBy() {
        return createBy;
    }

    public void setCreateBy(AppUser createBy) {
        this.createBy = createBy;
    }

    public AppUser getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(AppUser updateBy) {
        this.updateBy = updateBy;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getManufacturing() {
        return manufacturing;
    }

    public void setManufacturing(String manufacturing) {
        this.manufacturing = manufacturing;
    }

    public byte[] getRohs() {
        return rohs;
    }

    public void setRohs(byte[] rohs) {
        this.rohs = rohs;
    }

    public Date getRohsDateTest() {
        return rohsDateTest;
    }

    public void setRohsDateTest(Date rohsDateTest) {
        this.rohsDateTest = rohsDateTest;
    }

    public Date getRohsAlertDateTest() {
        return rohsAlertDateTest;
    }

    public void setRohsAlertDateTest(Date rohsAlertDateTest) {
        this.rohsAlertDateTest = rohsAlertDateTest;
    }

    public Date getRohsEndDateTest() {
        return rohsEndDateTest;
    }

    public void setRohsEndDateTest(Date rohsEndDateTest) {
        this.rohsEndDateTest = rohsEndDateTest;
    }

    public byte[] getMsds() {
        return msds;
    }

    public void setMsds(byte[] msds) {
        this.msds = msds;
    }

    public byte[] getSpec() {
        return spec;
    }

    public void setSpec(byte[] spec) {
        this.spec = spec;
    }

    public byte[] getHalogen() {
        return halogen;
    }

    public void setHalogen(byte[] halogen) {
        this.halogen = halogen;
    }

    public Date getHalogenDateTest() {
        return halogenDateTest;
    }

    public void setHalogenDateTest(Date halogenDateTest) {
        this.halogenDateTest = halogenDateTest;
    }

    public Date getHalogenAlertDateTest() {
        return halogenAlertDateTest;
    }

    public void setHalogenAlertDateTest(Date halogenAlertDateTest) {
        this.halogenAlertDateTest = halogenAlertDateTest;
    }

    public Date getHalogenEndDateTest() {
        return halogenEndDateTest;
    }

    public void setHalogenEndDateTest(Date halogenEndDateTest) {
        this.halogenEndDateTest = halogenEndDateTest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFolw() {
        return folw;
    }

    public void setFolw(String folw) {
        this.folw = folw;
    }

    public String getUlNumber() {
        return ulNumber;
    }

    public void setUlNumber(String ulNumber) {
        this.ulNumber = ulNumber;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Set<MaterialCode> getMaterialCodes() {
        return materialCodes;
    }

    public void setMaterialCodes(Set<MaterialCode> materialCodes) {
        this.materialCodes = materialCodes;
    }

    public Set<DocumentHistory> getDocumentHistorys() {
        return documentHistorys;
    }

    public void setDocumentHistorys(Set<DocumentHistory> documentHistorys) {
        this.documentHistorys = documentHistorys;
    }

    public String getSpecUrl() {
        return specUrl;
    }

    public void setSpecUrl(String specUrl) {
        this.specUrl = specUrl;
    }

    public String getRohsUrl() {
        return rohsUrl;
    }

    public void setRohsUrl(String rohsUrl) {
        this.rohsUrl = rohsUrl;
    }

    public String getMsdsUrl() {
        return msdsUrl;
    }

    public void setMsdsUrl(String msdsUrl) {
        this.msdsUrl = msdsUrl;
    }

    public String getHalogenUrl() {
        return halogenUrl;
    }

    public void setHalogenUrl(String halogenUrl) {
        this.halogenUrl = halogenUrl;
    }
}
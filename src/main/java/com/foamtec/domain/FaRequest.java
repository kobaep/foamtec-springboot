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

    @Column(name="faNumber")
    private String faNumber;

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

    @Column(name="partNo")
    private String partNo;

    @Column(name="partName")
    private String partName;

    @Column(name="revision")
    private String revision;

    @Column(name="saleOut")
    private String saleOut;

    @Column(name="qwsNo")
    private String qwsNo;

    @Column(name="apqpNo")
    private String apqpNo;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    @Column(name="needDate")
    private Date needDate;

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

    @Column(name="batch1")
    private String batch1;

    @Column(name="batch2")
    private String batch2;

    @Column(name="batch3")
    private String batch3;

    @Column(name="batch4")
    private String batch4;

    @Column(name="batch5")
    private String batch5;

    @Column(name="batch6")
    private String batch6;

    @Column(name="documentRequest")
    private String documentRequest;

    @Column(name="tool")
    private String tool;

    @Column(name="remark")
    private String remark;

    @Column(name="status")
    private String status;

    @Column(name="process")
    private String process;

    @Column(name="methodFirst")
    private String methodFirst;

    @Column(name="qtyFirst")
    private Integer qtyFirst;

    @Column(name="saleCoContract")
    private String saleCoContract;

    @Column(name="invoiceNo")
    private String invoiceNo;

    @Column(name="customerRemark")
    private String customerRemark;

    @Column(name="fileDrawing")
    private String fileDrawing;

    @Column(name="fileOther")
    private String fileOther;

    @Column(name="fileData1")
    private String fileData1;

    @Column(name="fileData2")
    private String fileData2;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    @Column(name="engCommitDate")
    private Date engCommitDate;

    @Column(name="engineerReason")
    private String engineerReason;

    @Column(name="typeRequest")
    private String typeRequest;

    @Column(name="reSubmitDetail")
    private String reSubmitDetail;

    @Column(name="cuttingType")
    private String cuttingType;

    @OrderBy("createDate")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "faRequest")
    private Set<DocumentHistory> documentHistorys = new HashSet<DocumentHistory>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFaNumber() {
        return faNumber;
    }

    public void setFaNumber(String faNumber) {
        this.faNumber = faNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public AppUser getCreateBy() {
        return createBy;
    }

    public void setCreateBy(AppUser createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public AppUser getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(AppUser updateBy) {
        this.updateBy = updateBy;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getSaleOut() {
        return saleOut;
    }

    public void setSaleOut(String saleOut) {
        this.saleOut = saleOut;
    }

    public String getQwsNo() {
        return qwsNo;
    }

    public void setQwsNo(String qwsNo) {
        this.qwsNo = qwsNo;
    }

    public String getApqpNo() {
        return apqpNo;
    }

    public void setApqpNo(String apqpNo) {
        this.apqpNo = apqpNo;
    }

    public Date getNeedDate() {
        return needDate;
    }

    public void setNeedDate(Date needDate) {
        this.needDate = needDate;
    }

    public Integer getFaApproveQty() {
        return faApproveQty;
    }

    public void setFaApproveQty(Integer faApproveQty) {
        this.faApproveQty = faApproveQty;
    }

    public Integer getFaForSellQty() {
        return faForSellQty;
    }

    public void setFaForSellQty(Integer faForSellQty) {
        this.faForSellQty = faForSellQty;
    }

    public Integer getSamplTestQty() {
        return samplTestQty;
    }

    public void setSamplTestQty(Integer samplTestQty) {
        this.samplTestQty = samplTestQty;
    }

    public Integer getSamplePccQty() {
        return samplePccQty;
    }

    public void setSamplePccQty(Integer samplePccQty) {
        this.samplePccQty = samplePccQty;
    }

    public String getMaterial1() {
        return material1;
    }

    public void setMaterial1(String material1) {
        this.material1 = material1;
    }

    public String getMaterial2() {
        return material2;
    }

    public void setMaterial2(String material2) {
        this.material2 = material2;
    }

    public String getMaterial3() {
        return material3;
    }

    public void setMaterial3(String material3) {
        this.material3 = material3;
    }

    public String getMaterial4() {
        return material4;
    }

    public void setMaterial4(String material4) {
        this.material4 = material4;
    }

    public String getMaterial5() {
        return material5;
    }

    public void setMaterial5(String material5) {
        this.material5 = material5;
    }

    public String getMaterial6() {
        return material6;
    }

    public void setMaterial6(String material6) {
        this.material6 = material6;
    }

    public String getDocumentRequest() {
        return documentRequest;
    }

    public void setDocumentRequest(String documentRequest) {
        this.documentRequest = documentRequest;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public Date getEngCommitDate() {
        return engCommitDate;
    }

    public void setEngCommitDate(Date engCommitDate) {
        this.engCommitDate = engCommitDate;
    }

    public String getEngineerReason() {
        return engineerReason;
    }

    public void setEngineerReason(String engineerReason) {
        this.engineerReason = engineerReason;
    }

    public Set<DocumentHistory> getDocumentHistorys() {
        return documentHistorys;
    }

    public void setDocumentHistorys(Set<DocumentHistory> documentHistorys) {
        this.documentHistorys = documentHistorys;
    }

    public String getBatch1() {
        return batch1;
    }

    public void setBatch1(String batch1) {
        this.batch1 = batch1;
    }

    public String getBatch2() {
        return batch2;
    }

    public void setBatch2(String batch2) {
        this.batch2 = batch2;
    }

    public String getBatch3() {
        return batch3;
    }

    public void setBatch3(String batch3) {
        this.batch3 = batch3;
    }

    public String getBatch4() {
        return batch4;
    }

    public void setBatch4(String batch4) {
        this.batch4 = batch4;
    }

    public String getBatch5() {
        return batch5;
    }

    public void setBatch5(String batch5) {
        this.batch5 = batch5;
    }

    public String getBatch6() {
        return batch6;
    }

    public void setBatch6(String batch6) {
        this.batch6 = batch6;
    }

    public String getMethodFirst() {
        return methodFirst;
    }

    public void setMethodFirst(String methodFirst) {
        this.methodFirst = methodFirst;
    }

    public Integer getQtyFirst() {
        return qtyFirst;
    }

    public void setQtyFirst(Integer qtyFirst) {
        this.qtyFirst = qtyFirst;
    }

    public String getSaleCoContract() {
        return saleCoContract;
    }

    public void setSaleCoContract(String saleCoContract) {
        this.saleCoContract = saleCoContract;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getCustomerRemark() {
        return customerRemark;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

    public String getFileDrawing() {
        return fileDrawing;
    }

    public void setFileDrawing(String fileDrawing) {
        this.fileDrawing = fileDrawing;
    }

    public String getFileOther() {
        return fileOther;
    }

    public void setFileOther(String fileOther) {
        this.fileOther = fileOther;
    }

    public String getFileData1() {
        return fileData1;
    }

    public void setFileData1(String fileData1) {
        this.fileData1 = fileData1;
    }

    public String getFileData2() {
        return fileData2;
    }

    public void setFileData2(String fileData2) {
        this.fileData2 = fileData2;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getTypeRequest() {
        return typeRequest;
    }

    public void setTypeRequest(String typeRequest) {
        this.typeRequest = typeRequest;
    }

    public String getReSubmitDetail() {
        return reSubmitDetail;
    }

    public void setReSubmitDetail(String reSubmitDetail) {
        this.reSubmitDetail = reSubmitDetail;
    }

    public String getCuttingType() {
        return cuttingType;
    }

    public void setCuttingType(String cuttingType) {
        this.cuttingType = cuttingType;
    }
}

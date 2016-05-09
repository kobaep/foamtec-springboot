package com.foamtec.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by apichat on 5/5/2016 AD.
 */
@Entity
@Table(name="File_Backup")
public class FileBackup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "specUrl")
    private String specUrl;

    @Column(name = "rohsUrl")
    private String rohsUrl;

    @Column(name = "msdsUrl")
    private String msdsUrl;

    @Column(name = "halogenUrl")
    private String halogenUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

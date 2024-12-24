package ru.physgun.logger.model;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(columnDefinition = "TEXT")
    private String error;

    @Column(columnDefinition = "TEXT")
    private String stack;
    private String realm;
    private String addon;
    private String hash;
    private String gamemode;
    private String gmv;
    private String os;
    private Boolean ds;
    private Integer v;
    private String serverTagName;

    // Getters and Setters

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getAddon() {
        return addon;
    }

    public void setAddon(String addon) {
        this.addon = addon;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getGamemode() {
        return gamemode;
    }

    public void setGamemode(String gamemode) {
        this.gamemode = gamemode;
    }

    public String getGmv() {
        return gmv;
    }

    public void setGmv(String gmv) {
        this.gmv = gmv;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Boolean getDs() {
        return ds;
    }

    public void setDs(Boolean ds) {
        this.ds = ds;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getServerTagName() {
        return serverTagName;
    }

    public void setServerTagName(String serverTagName) {
        this.serverTagName = serverTagName;
    }
}
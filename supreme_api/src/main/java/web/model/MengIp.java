package web.model;

import java.io.Serializable;

public class MengIp implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String ip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }
}
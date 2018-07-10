package web.model;

import java.io.Serializable;
import java.util.Date;

public class SupGoodsColour implements Serializable {
    private static final long serialVersionUID = 1L;

    private String colourId;

    private String goodsId;

    private String colourImg;

    private String colourName;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public String getColourId() {
        return colourId;
    }

    public void setColourId(String colourId) {
        this.colourId = colourId == null ? null : colourId.trim();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getColourImg() {
        return colourImg;
    }

    public void setColourImg(String colourImg) {
        this.colourImg = colourImg == null ? null : colourImg.trim();
    }

    public String getColourName() {
        return colourName;
    }

    public void setColourName(String colourName) {
        this.colourName = colourName == null ? null : colourName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
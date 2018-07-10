package web.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SupGoods implements Serializable {
    private static final long serialVersionUID = 1L;

    private String goodsId;

    private String goodsName;

    private Long goodsPrices;

    private String colourId;

    private String categoryId;
    
    private String content;

    private Long sizeId;

    private Integer type;
    
    private Integer status;

    private String goodsPicture;

    private Date createTime;

    private Date updateTime;

    private SupCategory category;
    
    
   
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SupCategory getCategory() {
		return category;
	}

	public void setCategory(SupCategory category) {
		this.category = category;
	}

	public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Long getGoodsPrices() {
        return goodsPrices;
    }

    public void setGoodsPrices(Long goodsPrices) {
        this.goodsPrices = goodsPrices;
    }

    public String getColourId() {
        return colourId;
    }

    public void setColourId(String colourId) {
        this.colourId = colourId == null ? null : colourId.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGoodsPicture() {
        return goodsPicture;
    }

    public void setGoodsPicture(String goodsPicture) {
        this.goodsPicture = goodsPicture == null ? null : goodsPicture.trim();
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
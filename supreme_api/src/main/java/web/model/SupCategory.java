package web.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class SupCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	private String categoryId;

	private String categoryName;

	private Integer status;

	private Date createTime;

	private Date updateTime;

	private List<SupGoods> goodsList;

	public List<SupGoods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<SupGoods> goodsList) {
		this.goodsList = goodsList;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId == null ? null : categoryId.trim();
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName == null ? null : categoryName.trim();
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
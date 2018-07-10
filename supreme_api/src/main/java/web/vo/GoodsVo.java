package web.vo;

import java.io.Serializable;
import java.util.List;

import web.model.SupGoodPhoto;
import web.model.SupGoods;
import web.model.SupGoodsColour;
import web.model.SupSize;

public class GoodsVo extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String goodsId;

	private String goodsName;

	private String content;
	
	private Long goodsPrices;

	private String colourId;
	
	private Integer type;

	private String categoryId;

	private Long sizeId;

	private Integer status;

	private String goodsPicture;

	private List<SupSize> goodsSizeList;
	
	private List<SupGoodPhoto> photoList;
	
	private SupGoods goodsEntry;
	
	private List<SupGoodsColour> colourList;
	
	
	
	


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<SupGoodsColour> getColourList() {
		return colourList;
	}

	public void setColourList(List<SupGoodsColour> colourList) {
		this.colourList = colourList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SupGoods getGoodsEntry() {
		return goodsEntry;
	}

	public void setGoodsEntry(SupGoods goodsEntry) {
		this.goodsEntry = goodsEntry;
	}

	public List<SupGoodPhoto> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<SupGoodPhoto> photoList) {
		this.photoList = photoList;
	}

	public List<SupSize> getGoodsSizeList() {
		return goodsSizeList;
	}

	public void setGoodsSizeList(List<SupSize> goodsSizeList) {
		this.goodsSizeList = goodsSizeList;
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

}
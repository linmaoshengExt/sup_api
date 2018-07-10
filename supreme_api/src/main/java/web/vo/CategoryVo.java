package web.vo;

import java.io.Serializable;


public class CategoryVo  implements Serializable  {
	private static final long serialVersionUID = 1L;

	private String categoryId;

	private String categoryName;

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

}
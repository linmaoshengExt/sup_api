package web.constants;

public class PageEntity {
	/**
	 * 当前页
	 */
	private int pageNo;
	/**
	 * 每夜条数
	 */
	private int pageSize;
	/**
	 * 总数
	 */
	private int totalCount;
	
	/**
	 * 总页数
	 */
	private int totalPages;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}

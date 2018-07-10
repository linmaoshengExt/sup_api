package web.utils;

import com.github.pagehelper.PageHelper;

import web.constants.BaseEntity;


import java.util.List;


/**
 * 功能概要：
 * 
 * @author linbingwen
 * @since  2015年10月23日 
 */
public class PagedResult<T> extends BaseEntity {
	
	/*serialVersionUID*/
	private static final long serialVersionUID = 1L;

	private List<T> data;//数据
	
	private long pageNo;//当前页
	
	private long pageSize;//条数
	
	private long total;//总条数
	
	private long pages;//总页面数目

	public List<T> getdata() {
		return data;
	}

	public void setdata(List<T> data) {
		this.data = data;
	}

	public long getPageNo() {
		return pageNo;
	}

	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getPages() {
		return pages;
	}

	public void setPages(long pages) {
		this.pages = pages;
	}

	public static void pageCommon(Integer pageNo , Integer pageSize){
		pageNo = pageNo == null?1:pageNo;
		pageSize = pageSize == null?100:pageSize;
		PageHelper.startPage(pageNo, pageSize);
	}
	
}

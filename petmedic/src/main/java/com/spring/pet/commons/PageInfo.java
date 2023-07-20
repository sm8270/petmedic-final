package com.spring.pet.commons;

public class PageInfo {

	private int startNUM; 
	private int endNUM;
	private int totalPage;
	
	private int pageListCount;

	private int start;
	private int pageNUM;
	
	
	
	
	public int getStartNUM() {
		return startNUM;
	}
	public void setStartNUM(int startNUM) {
		this.startNUM = startNUM;
	}
	public int getEndNUM() {
		return endNUM;
	}
	public void setEndNUM(int endNUM) {
		this.endNUM = endNUM;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageListCount() {
		return pageListCount;
	}
	public void setPageListCount(int pageListCount) {
		this.pageListCount = pageListCount;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getPageNUM() {
		return pageNUM;
	}
	public void setPageNUM(int pageNUM) {
		this.pageNUM = pageNUM;
	}
	@Override
	public String toString() {
		return "PageInfo [startNUM=" + startNUM + ", endNUM=" + endNUM + ", totalPage=" + totalPage + ", pageListCount="
				+ pageListCount + ", start=" + start + ", pageNUM=" + pageNUM + "]";
	}

	
	
	
	
	
	
}

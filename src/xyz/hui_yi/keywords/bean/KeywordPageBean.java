package xyz.hui_yi.keywords.bean;

import java.util.List;

public class KeywordPageBean {
	private long pageNo;
	private long pageSum;
	private List<KeywordBean> keywords;
	private int pageSize;
	public long getPageNo() {
		return pageNo;
	}
	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}
	public long getPageSum() {
		return pageSum;
	}
	public void setPageSum(long pageSum) {
		this.pageSum = pageSum;
	}
	public List<KeywordBean> getKeywordBeans() {
		return keywords;
	}
	public void setKeywordBeans(List<KeywordBean> keywordBeans) {
		this.keywords = keywordBeans;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "KeywordPageBean{" +
				"pageNo=" + pageNo +
				", pageSum=" + pageSum +
				", keywordBeans=" + keywords +
				", pageSize=" + pageSize +
				'}';
	}
}

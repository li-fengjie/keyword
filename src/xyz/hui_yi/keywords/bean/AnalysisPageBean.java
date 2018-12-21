package xyz.hui_yi.keywords.bean;

import java.util.List;

public class AnalysisPageBean {
	private long pageNo;
	private long pageSum;
	private List<AnalysisBean> Analysiss;
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
	public List<AnalysisBean> getAnalysisBeans() {
		return Analysiss;
	}
	public void setAnalysisBeans(List<AnalysisBean> AnalysisBeans) {
		this.Analysiss = AnalysisBeans;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "AnalysisPageBean{" +
				"pageNo=" + pageNo +
				", pageSum=" + pageSum +
				", AnalysisBeans=" + Analysiss +
				", pageSize=" + pageSize +
				'}';
	}
}

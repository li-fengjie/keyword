package xyz.hui_yi.keywords.bean;

import java.util.List;

public class CompanyPageBean {
	private long pageNo;
	private long pageSum;
	private List<CompanyBean> companys;
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
	public List<CompanyBean> getCompanyBeans() {
		return companys;
	}
	public void setCompanyBeans(List<CompanyBean> companyBeans) {
		this.companys = companyBeans;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "CompanyPageBean{" +
				"pageNo=" + pageNo +
				", pageSum=" + pageSum +
				", companyBeans=" + companys +
				", pageSize=" + pageSize +
				'}';
	}
}

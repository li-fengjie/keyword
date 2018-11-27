package xyz.hui_yi.keywords.bean;

import java.util.List;

public class TypeTargetPageBean {
	private long pageNo;
	private long pageSum;
	private List<TypeTargetBean> typeTargets;
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
	public List<TypeTargetBean> getTypeTargetBeans() {
		return typeTargets;
	}
	public void setTypeTargetBeans(List<TypeTargetBean> TypeTargetBeans) {
		this.typeTargets = TypeTargetBeans;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "TypeTargetPageBean{" +
				"pageNo=" + pageNo +
				", pageSum=" + pageSum +
				", typeTargetBeans=" + typeTargets +
				", pageSize=" + pageSize +
				'}';
	}
}

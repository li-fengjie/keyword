package xyz.hui_yi.keywords.bean;

import java.util.List;

public class FilePageBean {
	private long pageNo;
	private long pageSum;
	private List<FileBean> Files;
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
	public List<FileBean> getFileBeans() {
		return Files;
	}
	public void setFileBeans(List<FileBean> FileBeans) {
		this.Files = FileBeans;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "FilePageBean{" +
				"pageNo=" + pageNo +
				", pageSum=" + pageSum +
				", FileBeans=" + Files +
				", pageSize=" + pageSize +
				'}';
	}
}

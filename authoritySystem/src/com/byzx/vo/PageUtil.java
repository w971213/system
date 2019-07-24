package com.byzx.vo;



import java.util.List;

public class PageUtil {

	// 每页显示条数
	private Integer pageSize;
	// 总条数
    private Integer totalNum;
    // 当前页码
  	private Integer currNo;
    // 总页数
  	private Integer pageCount;
	// limit下标,limit第一个参数
	private Integer limitIndex;
	// 存储返回的集合对象
	private List<?> resultList;
	// 请求地址, user/list.action
	private String url;
	// 请求参数(搜索条件参数的封装), userCode=admin&userType=1&userState=1
	
	private StringBuffer params;
	
	
	
	public StringBuffer getParams() {
		return params;
	}
	public void setParams(StringBuffer params) {
		this.params = params;
	}
	//定义无参构造
	public PageUtil(){
	}
	// 有参构造（每页显示条数，下标）
	public PageUtil(Integer limitIndex,Integer pageSize){
		this.limitIndex=limitIndex;
		this.pageSize = pageSize;
	}
	//有参构造 (每页显示条数，总条数，当前页码，返回集合对象，请求地址，模糊查询输入的参数）
	public PageUtil(Integer pageSize,Integer totalNum,Integer currNo,List<?> resultList,String url,StringBuffer params){
		this.pageSize = pageSize;
		this.totalNum = totalNum;
		this.currNo = currNo;
		this.resultList = resultList;
		this.url = url;
		this.params = params;
		System.out.println("pageSize:"+pageSize);
		System.out.println("currNo:"+currNo);
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrNo() {
		return currNo;
	}
	public void setCurrNo(Integer currNo) {
		this.currNo = currNo;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	// 获取总页数
	public Integer getPageCount() {
		pageCount=(totalNum%pageSize==0)?(totalNum/pageSize):(totalNum/pageSize)+1;
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	// 获取limit第一个下标的值
	public Integer getLimitIndex() {
		limitIndex = pageSize*(currNo-1);
		return limitIndex;
	}
	public void setLimitIndex(Integer limitIndex) {
		this.limitIndex = limitIndex;
	}
	public List<?> getResultList() {
		return resultList;
	}
	public void setResultList(List<UserList> resultList) {
		this.resultList = resultList;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "PageUtil [pageSize=" + pageSize + ", totalNum=" + totalNum + ", currNo=" + currNo + ", pageCount="
				+ pageCount + ", limitIndex=" + limitIndex + ", resultList=" + resultList + ", url=" + url + ", params="
				+ params + "]";
	}
	
	
}

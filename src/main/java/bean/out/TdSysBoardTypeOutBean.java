package bean.out;

import mybatisauto.bean.BaseOutBean;

public class TdSysBoardTypeOutBean extends BaseOutBean{
	private String sid;

	private String createTime;

	private String parentId;

	private String typeName;

	private Integer sort;

	private String createId;

	public String getSid(){return sid;}
	public void setSid(String sid){this.sid=sid;}

	public String getCreateTime(){return createTime;}
	public void setCreateTime(String createTime){this.createTime=createTime;}

	public String getParentId(){return parentId;}
	public void setParentId(String parentId){this.parentId=parentId;}

	public String getTypeName(){return typeName;}
	public void setTypeName(String typeName){this.typeName=typeName;}

	public Integer getSort(){return sort;}
	public void setSort(Integer sort){this.sort=sort;}

	public String getCreateId(){return createId;}
	public void setCreateId(String createId){this.createId=createId;}

}
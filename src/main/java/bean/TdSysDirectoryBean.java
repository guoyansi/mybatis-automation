package bean;

import java.io.Serializable;

public class TdSysDirectoryBean implements Serializable{

	private static final long serialVersionUID = -1L;

	private String sid;

	private String createDate;

	private String dictDesc;

	private String editId;

	private String dictTypeName;

	private String dictValue;

	private String createId;

	private String dictName;

	private String dictId;

	private String editDate;

	private Integer sort;

	public String getSid(){return sid;}
	public void setSid(String sid){this.sid=sid;}

	public String getCreateDate(){return createDate;}
	public void setCreateDate(String createDate){this.createDate=createDate;}

	public String getDictDesc(){return dictDesc;}
	public void setDictDesc(String dictDesc){this.dictDesc=dictDesc;}

	public String getEditId(){return editId;}
	public void setEditId(String editId){this.editId=editId;}

	public String getDictTypeName(){return dictTypeName;}
	public void setDictTypeName(String dictTypeName){this.dictTypeName=dictTypeName;}

	public String getDictValue(){return dictValue;}
	public void setDictValue(String dictValue){this.dictValue=dictValue;}

	public String getCreateId(){return createId;}
	public void setCreateId(String createId){this.createId=createId;}

	public String getDictName(){return dictName;}
	public void setDictName(String dictName){this.dictName=dictName;}

	public String getDictId(){return dictId;}
	public void setDictId(String dictId){this.dictId=dictId;}

	public String getEditDate(){return editDate;}
	public void setEditDate(String editDate){this.editDate=editDate;}

	public Integer getSort(){return sort;}
	public void setSort(Integer sort){this.sort=sort;}

}
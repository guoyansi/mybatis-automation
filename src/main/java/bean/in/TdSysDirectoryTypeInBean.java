package bean.in;

import mybatisauto.bean.BaseInBean;

public class TdSysDirectoryTypeInBean extends BaseInBean{
	private String sid;

	private String dictTypeCode;

	private String createDate;

	private String dictDesc;

	private String editId;

	private String dictTypeName;

	private String createId;

	private String editDate;

	public String getSid(){return sid;}
	public void setSid(String sid){this.sid=sid;}

	public String getDictTypeCode(){return dictTypeCode;}
	public void setDictTypeCode(String dictTypeCode){this.dictTypeCode=dictTypeCode;}

	public String getCreateDate(){return createDate;}
	public void setCreateDate(String createDate){this.createDate=createDate;}

	public String getDictDesc(){return dictDesc;}
	public void setDictDesc(String dictDesc){this.dictDesc=dictDesc;}

	public String getEditId(){return editId;}
	public void setEditId(String editId){this.editId=editId;}

	public String getDictTypeName(){return dictTypeName;}
	public void setDictTypeName(String dictTypeName){this.dictTypeName=dictTypeName;}

	public String getCreateId(){return createId;}
	public void setCreateId(String createId){this.createId=createId;}

	public String getEditDate(){return editDate;}
	public void setEditDate(String editDate){this.editDate=editDate;}

}
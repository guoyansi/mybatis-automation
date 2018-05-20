package bean.out;

import mybatisauto.bean.BaseOutBean;

public class TdSysFuncOutBean extends BaseOutBean{
	private String sid;

	private String funcDesc;

	private String remark;

	private String createDate;

	private String editId;

	private String target;

	private String funcCode;

	private String createId;

	private String editDate;

	private String funcType;

	private String funcUrl;

	private String funcName;

	public String getSid(){return sid;}
	public void setSid(String sid){this.sid=sid;}

	public String getFuncDesc(){return funcDesc;}
	public void setFuncDesc(String funcDesc){this.funcDesc=funcDesc;}

	public String getRemark(){return remark;}
	public void setRemark(String remark){this.remark=remark;}

	public String getCreateDate(){return createDate;}
	public void setCreateDate(String createDate){this.createDate=createDate;}

	public String getEditId(){return editId;}
	public void setEditId(String editId){this.editId=editId;}

	public String getTarget(){return target;}
	public void setTarget(String target){this.target=target;}

	public String getFuncCode(){return funcCode;}
	public void setFuncCode(String funcCode){this.funcCode=funcCode;}

	public String getCreateId(){return createId;}
	public void setCreateId(String createId){this.createId=createId;}

	public String getEditDate(){return editDate;}
	public void setEditDate(String editDate){this.editDate=editDate;}

	public String getFuncType(){return funcType;}
	public void setFuncType(String funcType){this.funcType=funcType;}

	public String getFuncUrl(){return funcUrl;}
	public void setFuncUrl(String funcUrl){this.funcUrl=funcUrl;}

	public String getFuncName(){return funcName;}
	public void setFuncName(String funcName){this.funcName=funcName;}

}
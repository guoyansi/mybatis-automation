package bean;

import java.io.Serializable;

public class TdSysFuncResourceBean implements Serializable{

	private static final long serialVersionUID = -1L;

	private String resourceUrl;

	private String sid;

	private String funcId;

	public String getResourceUrl(){return resourceUrl;}
	public void setResourceUrl(String resourceUrl){this.resourceUrl=resourceUrl;}

	public String getSid(){return sid;}
	public void setSid(String sid){this.sid=sid;}

	public String getFuncId(){return funcId;}
	public void setFuncId(String funcId){this.funcId=funcId;}

}
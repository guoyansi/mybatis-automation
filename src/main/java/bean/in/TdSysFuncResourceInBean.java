package bean.in;

import mybatisauto.bean.BaseInBean;

public class TdSysFuncResourceInBean extends BaseInBean{
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
package bean.in;

import mybatisauto.bean.BaseInBean;

public class TdSysTestInBean extends BaseInBean{
	private String name;

	private Integer sid;

	public String getName(){return name;}
	public void setName(String name){this.name=name;}

	public Integer getSid(){return sid;}
	public void setSid(Integer sid){this.sid=sid;}

}
package bean.out;

import mybatisauto.bean.BaseOutBean;

public class TdSysTestOutBean extends BaseOutBean{
	private String name;

	private Integer sid;

	public String getName(){return name;}
	public void setName(String name){this.name=name;}

	public Integer getSid(){return sid;}
	public void setSid(Integer sid){this.sid=sid;}

}
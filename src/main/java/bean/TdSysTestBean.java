package bean;

import java.io.Serializable;

public class TdSysTestBean implements Serializable{

	private static final long serialVersionUID = -1L;

	private String name;

	private Integer sid;

	public String getName(){return name;}
	public void setName(String name){this.name=name;}

	public Integer getSid(){return sid;}
	public void setSid(Integer sid){this.sid=sid;}

}
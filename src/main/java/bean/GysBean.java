package bean;

import java.io.Serializable;

public class GysBean implements Serializable{

	private static final long serialVersionUID = -1L;

	private Integer id;

	private String roleName;

	private String note;

	private Double price3;

	private String tdate;

	public Integer getId(){return id;}
	public void setId(Integer id){this.id=id;}

	public String getRoleName(){return roleName;}
	public void setRoleName(String roleName){this.roleName=roleName;}

	public String getNote(){return note;}
	public void setNote(String note){this.note=note;}

	public Double getPrice3(){return price3;}
	public void setPrice3(Double price3){this.price3=price3;}

	public String getTdate(){return tdate;}
	public void setTdate(String tdate){this.tdate=tdate;}



}
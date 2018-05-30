package bean.out;

import mybatisauto.bean.BaseOutBean;

public class GysOutBean extends BaseOutBean{
	private Integer id;

	private String roleName;

	private String note;

	private Double price3;

	public Integer getId(){return id;}
	public void setId(Integer id){this.id=id;}

	public String getRoleName(){return roleName;}
	public void setRoleName(String roleName){this.roleName=roleName;}

	public String getNote(){return note;}
	public void setNote(String note){this.note=note;}

	public Double getPrice3(){return price3;}
	public void setPrice3(Double price3){this.price3=price3;}


}
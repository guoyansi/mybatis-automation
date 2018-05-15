package bean.in;

import table.bean.BaseInBean;

public class GysInBean extends BaseInBean{

	private Long id;

	private Long age;

	private String name;

	private Double weight;

	public Long getId(){return id;}
	public void setId(Long id){this.id=id;}

	public Long getAge(){return age;}
	public void setAge(Long age){this.age=age;}

	public String getName(){return name;}
	public void setName(String name){this.name=name;}

	public Double getWeight(){return weight;}
	public void setWeight(Double weight){this.weight=weight;}

}
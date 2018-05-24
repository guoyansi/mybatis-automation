package mybatisauto.bean;

import java.io.Serializable;

/**
 * 基础的输出类
 * @author guoyansi
 *
 */
public class BaseOutBean implements Serializable{
	private static final long serialVersionUID = -1L;
	
	//行号
	private Integer rn;

	public Integer getRn() {
		return rn;
	}

	public void setRn(Integer rn) {
		this.rn = rn;
	}
	
	
	

}

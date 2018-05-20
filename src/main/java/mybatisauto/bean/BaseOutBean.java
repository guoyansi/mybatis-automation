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
	private Integer rowNum;
	
	public Integer getRowNum() {
		return rowNum;
	}
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}
	
	

}

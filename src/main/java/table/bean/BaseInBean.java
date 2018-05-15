package table.bean;

import java.io.Serializable;

/**
 * 基础的数据库输入类
 * @author guoyansi
 *
 */
public class BaseInBean implements Serializable{
	private static final long serialVersionUID = -1L;
	//where语句
	private String sqlWhere;
	//order排序
	private String sqlOrderBy;
	//limit限制
	private Boolean sqlLimit=false;
	//开始数据
	private Integer sqlStartIndex;
	//开始数据
	private Integer sqlEndIndex;
	//获取数据量
	private Integer sqlPageSize=10;
	//当前页
	private Integer sqlCurrentPage;
	//update时set语句
	private String sqlSet;
	//oracle的sqlence
	private String sqlSeq;
	
	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	public String getSqlOrderBy() {
		return sqlOrderBy;
	}

	public void setSqlOrderBy(String sqlOrderBy) {
		this.sqlOrderBy = sqlOrderBy;
	}

	public Boolean getSqlLimit() {
		return sqlLimit;
	}

	public void setSqlLimit(Boolean sqlLimit) {
		this.sqlLimit = sqlLimit;
	}

	public Integer getSqlStartIndex() {
		if(this.sqlCurrentPage==null || this.sqlCurrentPage<=0){
			this.sqlCurrentPage=1;
		}
		return (this.sqlCurrentPage-1)*this.sqlPageSize;
	}

	public void setSqlStartIndex(Integer sqlStartIndex) {
		this.sqlStartIndex = sqlStartIndex;
	}

	public Integer getSqlPageSize() {
		return sqlPageSize;
	}

	public void setSqlPageSize(Integer sqlPageSize) {
		this.sqlPageSize = sqlPageSize;
	}

	public Integer getSqlCurrentPage() {
		return sqlCurrentPage;
	}

	public void setSqlCurrentPage(Integer sqlCurrentPage) {
		this.sqlCurrentPage = sqlCurrentPage;
	}

	

	public String getSqlSet() {
		return sqlSet;
	}

	public void setSqlSet(String sqlSet) {
		this.sqlSet = sqlSet;
	}

	public String getSqlSeq() {
		return sqlSeq;
	}

	public void setSqlSeq(String sqlSeq) {
		this.sqlSeq = sqlSeq;
	}

	public Integer getSqlEndIndex() {
		return this.getSqlStartIndex()+(this.sqlPageSize-1);
	}

	public void setSqlEndIndex(Integer sqlEndIndex) {
		this.sqlEndIndex = sqlEndIndex;
	}
	
	

	

}

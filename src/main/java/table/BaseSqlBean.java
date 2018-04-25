package table;

public class BaseSqlBean {
	//where语句
	private String sqlWhere;
	//order排序
	private String sqlOrderBy;
	//limit限制
	private Boolean sqlLimit=false;
	//开始数据
	private Integer sqlStartIndex;
	//获取数据量
	private Integer sqlPageSize=10;
	//当前页
	private Integer sqlCurrentPage;
	
	private String SqlSet;
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
		return SqlSet;
	}

	public void setSqlSet(String sqlSet) {
		SqlSet = sqlSet;
	}
	
	

	

}

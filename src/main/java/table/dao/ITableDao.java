package table.dao;

import java.util.List;

import table.bean.FieldBean;

public interface ITableDao {
	List<String> getMySqlTableList() throws Exception;
	
	List<FieldBean> getMySqlFiledList(String tableName) throws Exception;
	
	List<String> getOracleTableList() throws Exception;
	
	List<FieldBean> getOracleFiledList(String tableName) throws Exception;
}

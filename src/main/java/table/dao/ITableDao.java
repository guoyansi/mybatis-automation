package table.dao;

import java.util.List;

import table.bean.FieldBean;

public interface ITableDao {
	List<String> getTableList() throws Exception;
	
	List<FieldBean> getFiledList(String tableName) throws Exception;
}

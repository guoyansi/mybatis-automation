package table.dao;

import java.util.List;

import table.bean.FiledBean;

public interface ITableDao {
	List<String> getTableList() throws Exception;
	
	List<FiledBean> getFiledList(String tableName) throws Exception;
}

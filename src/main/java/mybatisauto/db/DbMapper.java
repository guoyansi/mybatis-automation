package mybatisauto.db;

import java.util.HashMap;
import java.util.Map;

import mybatisauto.bean.JdbcBean;

public class DbMapper {
	protected Map<String, DataBase> get(JdbcBean jdbc){
		Map<String, DataBase> map=new HashMap<String, DataBase>();
		map.put("mysql", new MySqlDataBase(jdbc));
		map.put("oracle", new OracleDataBase(jdbc));
		return map;
	}
}

package mybatisauto.db;

import mybatisauto.bean.JdbcBean;

public class DbMapper {
	protected DataBase get(JdbcBean jdbc) throws Exception{
		String url=jdbc.getUrl();
		if(url.indexOf("mysql")!=-1) {
			return new MySqlDataBase(jdbc);
		}else if(url.indexOf("oracle")!=-1) {
			return new OracleDataBase(jdbc);
		}
		throw new Exception("无法根据url匹配到数据源");
	}
}

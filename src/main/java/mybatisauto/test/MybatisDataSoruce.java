package mybatisauto.test;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisDataSoruce {
	private SqlSessionFactory sqlSessionFactory;
	private Reader reader;
	private SqlSession session;

	public MybatisDataSoruce(String jdbc) throws IOException {
		this.reader = Resources.getResourceAsReader(jdbc);
		this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	}

	public void openSession() {
		session = sqlSessionFactory.openSession();
	}

	public void commit() {
		session.commit();
	}

	public void closeSession() {
		session.close();
	}

	public <T> T getDao(Class<T> cs) {
		return session.getMapper(cs);
	}

}

package mybatisauto.create;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface IBaseMyBatisByIdDao<T> {
	/**
	 * 获取单条数据
	 * @param t
	 * @return
	 * @throws Exception
	 */
	T selectOneById(Object id)throws Exception;
	
	/**
	 * 更新数据
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int updateById(T t) throws Exception;
	
	/**
	 * 插入时获取主键id,主键是字段中的第一个值
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int insertGetId(T t) throws Exception;
	
	/**
	 * 通过id删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteById(Object id) throws Exception;
	
	/**
	 * 批量插入获取id
	 * @param list
	 * @return
	 * @throws Exception
	 */
	int batchInsertGetId(@Param("list") List<T> list,@Param("seqName") String seqName) throws Exception;
}

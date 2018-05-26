package mybatisauto.create;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mybatisauto.bean.BaseInBean;
import mybatisauto.bean.BaseOutBean;



public interface IBaseMyBatisDao<T,I extends BaseInBean,O extends BaseOutBean> {
	/**
	 * 获取list
	 * @param t
	 * @return
	 * @throws Exception
	 */
	List<O> selectList(I i) throws Exception;
	/**
	 * 获取单条数据
	 * @param t
	 * @return
	 * @throws Exception
	 */
	O selectOne(T t)throws Exception;
	/**
	 * 获取数量
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int getCount(I i) throws Exception;
	/**
	 * 更新数据
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int update(T t) throws Exception;
	/**
	 * 插入时不获取主键id
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int insert(T t) throws Exception;
	/**
	 * 批量插入
	 * @param list
	 * @return
	 * @throws Exception
	 */
	int batchInsert(@Param("list") List<T> list,@Param("seqName") String seqName) throws Exception;
	
	/**
	 * 删除
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int delete(T t) throws Exception;
}
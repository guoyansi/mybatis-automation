package mybatisauto.db;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import mybatisauto.bean.AutoConfig;
import mybatisauto.bean.FieldBean;
import mybatisauto.bean.JdbcBean;
import mybatisauto.bean.TableBean;

/**
 * @author guoyansi
 *
 */
public class OracleDataBase extends DataBase{
	

	private JdbcBean jdbc;
	
	public OracleDataBase() {
	}
	public OracleDataBase(JdbcBean jdbc) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.jdbc=jdbc;
	}


	@Override
	protected String getJavaType(String sqlType,Integer precision, Integer dataScale)
			throws Exception {
		String t=super.getJavaType(sqlType,precision,dataScale);
		precision=(precision==null?0:precision);
		dataScale=(dataScale==null?0:dataScale);
		if(t!=null){
			return t;
		}
		if(sqlType.indexOf("long")!=-1){
			t="Long";
		}else if(sqlType.indexOf("number")!=-1){
			if(precision<=9&&dataScale==0){
				t="Integer";
			}else if(dataScale>0){
				t="Double";
			}
		}
		if(t==null){
			throw new Exception("暂无配置"+sqlType+"类型的数据");
		}
		return t;
	}
	@Override
	protected List<TableBean> getTableList(Statement stmt, List<String> tables,
			List<String> exceptTable) throws Exception {
		String key="table_name";//字段名
		String sql=getTableSql(key, tables, exceptTable);
		System.out.println(sql);
		ResultSet rs=stmt.executeQuery(sql);
		List<TableBean> ts=new ArrayList<TableBean>();
		while(rs.next()){
			String name=rs.getString(key);
			ts.add(this.setTableBeanName(name));
		}
		if(rs!=null){
			rs.close();
		}
		return ts;
	}

	@Override
	protected List<FieldBean> getFieldList(Statement stmt, TableBean table,
			Boolean isCamel) throws Exception {
		String sql=getFieldsSql(table.getSqlName());
		System.out.println(sql);
		ResultSet rs=stmt.executeQuery(sql);
		List<FieldBean> fs=new ArrayList<FieldBean>();
		FieldBean f=null;
		while(rs.next()){
			f=new FieldBean();
			String type=rs.getString("type");
			String name=rs.getString("name");
			System.out.println(name);
			f.setSqlName(name);
			f.setBeanName(this.setFieldBeanName(isCamel, name));
			f.setSqlType(type);
			f.setJavaType(this.getJavaType(type,rs.getInt("precision"),rs.getInt("dataScale")));
			boolean iskey=this.checkKey(rs.getString("Key"));
			f.setIsKey(iskey);
			//表示table 是否有主键
			if(iskey){
				table.setHaveKey(true);
			}
			//System.out.println("==>>"+rs.getLong("defaults"));
			/**
			 * TODO oracle默认属性读取异常，plsql显示<lomg> 郭延思
			 */
			//f.setDefaults(rs.getString("defaults"));
			fs.add(f);
		}
		return fs;
	}

	@Override
	protected String getTableSql(String key,List<String> tables,List<String> exceptTable){
		StringBuffer sb=new StringBuffer();
		sb.append("select LOWER(table_name) as "+key+" from user_tables ");
		StringBuffer targetWhere=null;
		StringBuffer exceptWhere=null;
		if(tables.size()!=0){
			targetWhere=getSqlWhere(key,tables,true);
		}
		if(exceptTable.size()!=0){
			exceptWhere=getSqlWhere(key,exceptTable,false);
		}
		if(targetWhere!=null&&exceptWhere!=null){
			sb.append("where ");
			sb.append(targetWhere.toString());
			sb.append(" and ");
			sb.append(exceptWhere.toString());
		}else if(targetWhere !=null){
			sb.append("where ");
			sb.append(targetWhere.toString());
		}else if(exceptWhere!=null){
			sb.append("where ");
			sb.append(exceptWhere.toString());
		}
		return sb.toString();
	}
	
	private StringBuffer getSqlWhere(String key,List<String> list,boolean isTarget){
		StringBuffer sb=new StringBuffer();
		sb.append(key);
		if(isTarget){//目标表
			sb.append(" in (");
		}else{//排除表
			sb.append(" not in (");
		}
		int len=list.size();
		for(int i=0;i<len;i++){
			if(i!=0){
				sb.append(",");
			}
			sb.append("upper('");
			sb.append(list.get(i));
			sb.append("')");
		}
		sb.append(")");
		return sb;
	}
	private String getFieldsSql(String tableName){
		StringBuffer sb=new StringBuffer();
		sb.append("select");
		sb.append(" LOWER(a.column_name) as name,");
		sb.append("LOWER(a.data_type) as type,");
	    sb.append("lower(a.DATA_PRECISION) as precision,");
	    sb.append("lower(a.data_scale) as dataScale,");  
	    sb.append("a.data_default as defaults,");
	    sb.append("(select case when b.column_name is not null then 'PRI'  end from dual) as key");
	    sb.append(" from ");
	    sb.append("user_tab_columns a ");
	    sb.append("left join");
	    sb.append("(");
	    sb.append("select a.table_name,a.column_name ");
	    sb.append("from user_cons_columns a, user_constraints b ");
	    sb.append("where a.constraint_name = b.constraint_name "); 
	    sb.append("and b.constraint_type = 'P' ");
	    sb.append("and a.table_name = UPPer('"+tableName+"')");  
	    sb.append(") b on a.column_name=b.column_name ");
	    sb.append("where a.table_name =UPPer('"+tableName+"')"); 
	      
	    return sb.toString();
	}
	
	
	@Override
	public void createMapper(AutoConfig config, TableBean table,List<FieldBean> fs) throws Exception {
		OutputFormat format = new OutputFormat("    ", true);
		//XMLWriter xmlWriter=new XMLWriter(new FileOutputStream("src/t/mapper.xml"),format);
		String url=config.getMapperPackage().replace(".", "/");
		XMLWriter xmlWriter=new XMLWriter(new FileOutputStream(config.getSourceMapper()+"/"+url+"/"+table.getXmlMapperName()+config.getXmlMapperSuffix()+".xml"),format);
		Document document=mapperDocument(config,table,fs);
		xmlWriter.write(document);
		xmlWriter.close();
		
	}
	
	private Document mapperDocument(AutoConfig config, TableBean table,List<FieldBean> fs){
		Document document=DocumentHelper.createDocument();
		document.addDocType("mapper", "-//mybatis.org//DTD Mapper 3.0//EN", "http://mybatis.org/dtd/mybatis-3-mapper.dtd");
		Element root=document.addElement("mapper");
		root.addAttribute("namespace",config.getDaoPackage() +"."+table.getIdaoName());
		//selectList
		selectListDocument(root,config,table,fs);
		//selectOne
		selectOneDocument(root,config, table,fs);
		
		//getCount
		getCountDocument(root, config, table, fs);
		//update
		updateDocument(root, config, table, fs);
		//insert
		insertDocument(root, config, table, fs);
		//delete
		deleteDocument(root, config, table, fs);
		
		if(table.getHaveKey()){
			//selectOneById
			selectOneByIdDocument(root, config, table, fs);
			//updateById
			updateByIdDocument(root, config, table, fs);
			//insertGetId
			insertGetIdDocument(root, config, table, fs);
			//deleteById
			deleteByIdDocument(root, config, table, fs);
		}
		return document;
	}
	/**
	 * selectList
	 * @param root
	 * @param c
	 */
	private void selectListDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs){
		Element select=root.addElement("select");
		select.addAttribute("id", "selectList");
		select.addAttribute("parameterType",config.getBeanPackage()+".in."+table.getBeanInName());
		select.addAttribute("resultType", config.getBeanPackage()+".out."+table.getBeanOutName());
		select.addText("\n\t\t");
		StringBuffer sb=new StringBuffer();
		int i=0;
		for(FieldBean f:fs){
			i++;
			sb.append(f.getSqlName());
			sb.append(" as ");
			sb.append(f.getBeanName());
			if(i<fs.size()){
				sb.append(",");
			}
		}
		
		Element limitIf=select.addElement("if");
		limitIf.addAttribute("test","sqlLimit==true");
		limitIf.addText("\n			select * from (\n		");
		select.addText("\n\t\t");
		select.addText(" select A.*,  rowNum from (");
		select.addText("\n		 	select "+sb.toString()+" from "+table.getSqlName());
		Element whereTag=select.addElement("where");
		for(FieldBean f:fs){
			Element ifTag=whereTag.addElement("if");
			ifTag.addAttribute("test", f.getBeanName()+"!=null");
			ifTag.addText("and "+f.getSqlName()+"=#{"+f.getBeanName()+"}");
		}
		Element whereIf=whereTag.addElement("if");
		whereIf.addAttribute("test", "sqlWhere!=null");
		whereIf.addText("${sqlWhere}");
		Element orderByIf=select.addElement("if");
		orderByIf.addAttribute("test","sqlOrderBy!=null");
		orderByIf.addText("order by ${sqlOrderBy}");
		select.addText("\n			) A");
		
		Element limitIf1=select.addElement("if");
		limitIf1.addAttribute("test","sqlLimit==true");
		limitIf1.addText("\n\t\t");
		limitIf1.addText("where rowNum <= #{sqlEndIndex}+1 ");
		limitIf1.addText("\n		) where rowNum >=#{sqlStartIndex}+1 \n\t\t");
	}
	/**
	 * selectOne
	 * @param root
	 * @param c
	 */
	private void selectOneDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs){
		Element select=root.addElement("select");
		select.addAttribute("id", "selectOne");
		select.addAttribute("parameterType",config.getBeanPackage()+".in."+table.getBeanInName());
		select.addAttribute("resultType", config.getBeanPackage()+".out."+table.getBeanOutName());
		select.addText("\n\t\t");
		StringBuffer sb=new StringBuffer();
		int i=0;
		for(FieldBean f:fs){
			if(i!=0){
				sb.append(",");
				i++;
			}
			sb.append(f.getSqlName());
			sb.append(" as ");
			sb.append(f.getBeanName());
		}
		
		select.addText("select "+sb.toString()+" from "+table.getSqlName());
		
		Element whereIf=select.addElement("if");
		whereIf.addAttribute("test", "sqlWhere!=null");
		Element whereTag=whereIf.addElement("where");
		whereTag.addText("${sqlWhere}");
	}
	
	/**
	 * selectOne
	 * @param root
	 * @param c
	 */
	private void selectOneByIdDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs){
		Element select=root.addElement("select");
		select.addAttribute("id", "selectOneById");
		select.addAttribute("parameterType","object");
		select.addAttribute("resultType", config.getBeanPackage() +"."+table.getBeanName());
		select.addText("\n\t\t");
		StringBuffer sb=new StringBuffer();
		int i=0;
		String key="";
		for(FieldBean f:fs){
			if(i!=0){
				sb.append(",");
				i++;
			}
			sb.append(f.getSqlName());
			sb.append(" as ");
			sb.append(f.getBeanName());
			if(f.getIsKey()){
				key=f.getSqlName();
			}
			
		}
		
		select.addText("select "+sb.toString()+" from "+table.getSqlName());
		
		Element whereTag=select.addElement("where");
		whereTag.addText(key+"=#{_parameter}");
	}
	/**
	 * 获取count
	 * @param root
	 * @param c
	 */
	private void getCountDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs){
		Element select=root.addElement("select");
		select.addAttribute("id", "getCount");
		select.addAttribute("parameterType",config.getBeanPackage()+".in."+table.getBeanInName());
		select.addAttribute("resultType", "int");
		select.addText("\n\t\t");
		
		select.addText("select count(*) from "+table.getSqlName());
		Element whereIf=select.addElement("if");
		whereIf.addAttribute("test", "sqlWhere!=null");
		Element whereTag=whereIf.addElement("where");
		whereTag.addText("${sqlWhere}");
	}
	private void updateDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs){
		Element update=root.addElement("update");
		update.addAttribute("id", "update");
		update.addAttribute("parameterType",config.getBeanPackage()+"."+table.getBeanName());
		update.addText("\n\t\t");
		update.addText("update "+table.getSqlName());
		Element setTag=update.addElement("set");
		for(FieldBean f:fs){
			if(f.getIsKey()){
				continue;
			}
			Element ifTag=setTag.addElement("if");
			ifTag.addAttribute("test", f.getBeanName()+"==null");
			ifTag.addText(f.getSqlName()+"=null,");
			Element ifTag1=setTag.addElement("if");
			ifTag1.addAttribute("test", f.getBeanName()+"!=null");
			ifTag1.addText(f.getSqlName()+"=#{"+f.getBeanName()+"},");
		}
		Element whereIf=update.addElement("if");
		whereIf.addAttribute("test", "sqlWhere!=null");
		Element whereTag=whereIf.addElement("where");
		whereTag.addText("${sqlWhere}");
	}
	
	
	private void updateByIdDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs){
		Element update=root.addElement("update");
		update.addAttribute("id", "updateById");
		update.addAttribute("parameterType",config.getBeanPackage()+".in."+table.getBeanName());
		update.addText("\n\t\t");
		update.addText("update "+table.getSqlName());
		Element setTag=update.addElement("set");
		String key=null;
		for(FieldBean f:fs){
			if(f.getIsKey()){
				key=f.getSqlName();
				continue;
			}
			Element ifTag=setTag.addElement("if");
			ifTag.addAttribute("test", f.getSqlName()+"==null");
			ifTag.addText(f.getSqlName()+"=null,");
			Element ifTag1=setTag.addElement("if");
			ifTag1.addAttribute("test", f.getSqlName()+"!=null");
			ifTag1.addText(f.getSqlName()+"=#{"+f.getBeanName()+"},");
		}
		update.addText("\n			where "+key+"=#{_parameter}");
	}
	
	private void insertDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs){
		Element insert=root.addElement("insert");

		insert.addAttribute("id", "insert");
		insert.addAttribute("parameterType", config.getBeanPackage()+"."+table.getBeanName());
		
		insert.addText("\n\t\t");
		insert.addText("insert into "+table.getSqlName());
		
		Element trim1=insert.addElement("trim");
		trim1.addAttribute("prefix", "(");
		trim1.addAttribute("suffix", ")");
		trim1.addAttribute("suffixOverrides", ",");
		
		insert.addText("\n 			values");
		
		Element trim2=insert.addElement("trim");
		trim2.addAttribute("prefix", "(");
		trim2.addAttribute("suffix", ")");
		trim2.addAttribute("suffixOverrides", ",");
		for(FieldBean f:fs){
			if(f.getIsKey()){
				trim1.addText("\n			"+f.getSqlName()+",");
				
				Element ifseq=trim2.addElement("if");
				ifseq.addAttribute("test","sqlSeq==null");
				ifseq.addText("#{"+f.getBeanName()+"},");
				Element ifseq1=trim2.addElement("if");
				ifseq1.addAttribute("test","sqlSeq!=null");
				ifseq1.addText("\n				${sqlSeq}.Nextval,\n			");
				
			}else{
				Element ifTag1=trim1.addElement("if");
				ifTag1.addAttribute("test", f.getBeanName()+"!=null");
				ifTag1.addText(f.getSqlName()+",");
				
				Element ifTag2=trim2.addElement("if");
				ifTag2.addAttribute("test", f.getBeanName()+"!=null");
				ifTag2.addText("#{"+f.getBeanName()+"},");
			}
		}
	}

	private void insertGetIdDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs){
		Element insert=root.addElement("insert");
		insert.addAttribute("id", "insertGetId");
		
		insert.addAttribute("parameterType", config.getBeanPackage()+"."+table.getBeanName());
		
		insert.addText("\n\t\t");
		insert.addText("insert into "+table.getSqlName());
		
		Element trim1=insert.addElement("trim");
		trim1.addAttribute("prefix", "(");
		trim1.addAttribute("suffix", ")");
		trim1.addAttribute("suffixOverrides", ",");
		
		insert.addText("\n 			values");
		
		Element trim2=insert.addElement("trim");
		trim2.addAttribute("prefix", "(");
		trim2.addAttribute("suffix", ")");
		trim2.addAttribute("suffixOverrides", ",");
		String key=null;
		for(FieldBean f:fs){
			if(f.getIsKey()){
				key=f.getBeanName();
				
				trim1.addText("\n			"+f.getSqlName()+",");
				
				Element ifseq=trim2.addElement("if");
				ifseq.addAttribute("test","sqlSeq==null");
				ifseq.addText("#{"+f.getBeanName()+"},");
				Element ifseq1=trim2.addElement("if");
				ifseq1.addAttribute("test","sqlSeq!=null");
				ifseq1.addText("\n				${sqlSeq}.Nextval,\n			");
				
			}else{
				Element ifTag1=trim1.addElement("if");
				ifTag1.addAttribute("test", f.getBeanName()+"!=null");
				ifTag1.addText(f.getBeanName()+",");
				
				Element ifTag2=trim2.addElement("if");
				ifTag2.addAttribute("test", f.getBeanName()+"!=null");
				ifTag2.addText("#{"+f.getBeanName()+"},");
			}
		}
		Element sk=insert.addElement("selectKey");
		sk.addAttribute("keyProperty",key);
		sk.addAttribute("order", "AFTER");
		sk.addAttribute("resultType", "long");
		sk.addText("select ${sqlSeq}.Currval from dual");
		
		
	}
	private void deleteDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs){
		Element insert=root.addElement("delete");
		insert.addAttribute("id", "delete");
		insert.addAttribute("parameterType",config.getBeanPackage()+"."+table.getBeanName());
		insert.addText("\n\t\t");
		insert.addText("DELETE FROM "+table.getSqlName());
		Element whereIf=insert.addElement("if");
		whereIf.addAttribute("test", "sqlWhere !=null");
		Element where=whereIf.addElement("where");
		where.addText("${sqlWhere}");
	}
	
	private void deleteByIdDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs){
		Element insert=root.addElement("delete");
		insert.addAttribute("id", "deleteById");
		insert.addAttribute("parameterType","object");
		insert.addText("\n\t\t");
		insert.addText("DELETE FROM "+table.getSqlName());
		Element whereIf=insert.addElement("if");
		whereIf.addAttribute("test", "sqlWhere !=null");
		Element where=whereIf.addElement("where");
		String key="";
		for(FieldBean f:fs){
			if(f.getIsKey()){
				key=f.getSqlName();
				break;
			}
		}
			where.addText(key+"=#{_parameter}");
	}
	
}

package table;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.TabExpander;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import table.bean.FieldBean;

public class CreateFile{
	private String mysql="mysql";
	private String oracle="oracle";
	
	
	public void createJavaDao(String sourceDao,String daoPackage,String beanPackage,String tableName,List<FieldBean> fileds,boolean isSpringDao) throws IOException{
		String url=daoPackage.replace(".", "/");
		
		File file=new File(sourceDao+"/"+url+"/I"+firstUpperCase(makeName(true, tableName))+"Dao.java");
		if(file.exists()){
			file.delete();
		}
		BufferedWriter w=new BufferedWriter(new FileWriter(file, true));
		writeJavaDaoContent(w,daoPackage,beanPackage,tableName,fileds,isSpringDao);
		w.flush();
		w.close();
	}
	
	public void createJavaBean(String sourceBean,String beanPackage,String tableName,List<FieldBean> fileds,String type,boolean isCamelField,String dbName) throws Exception{
		//File file=new File("src/t/createjava/IGysDao.java");
		String url=beanPackage.replace(".", "/");
		File other=null;
		File file=null;
		if("in".equals(type)){//入参
			other=new File(sourceBean+"/"+url+"/in");
			if(other.exists()){
				other.delete();
			}
			other.mkdirs();
			file=new File(sourceBean+"/"+url+"/in/"+getInBeanName(tableName)+".java");
		}else if("out".equals(type)){//出参
			other=new File(sourceBean+"/"+url+"/out");
			if(other.exists()){
				other.delete();
			}
			other.mkdirs();
			file=new File(sourceBean+"/"+url+"/out/"+getOutBeanName(tableName)+".java");
		}else{//bean
			file=new File(sourceBean+"/"+url+"/"+getBeanName(tableName)+".java");
			
		}
		if(file.exists()){
			file.delete();
		}
		BufferedWriter w=new BufferedWriter(new FileWriter(file, true));
		writeJavaBeanContent(w,beanPackage,tableName,fileds,type,isCamelField,dbName);
		w.flush();
		w.close();
	}
	
	private void writeJavaBeanContent(BufferedWriter w,String beanPackage,String tableName,List<FieldBean> fileds,String type,boolean isCamelField,String dbName) throws Exception{
		//System.out.println("当前包名："+);
		Package pk=CreateFile.class.getPackage();
		if("in".equals(type)){//入参
			w.write("package "+beanPackage+".in;\n");
			w.write("\n");
			w.write("import "+pk.getName()+".bean.BaseInBean;\n");
			w.write("\n");
			w.write("public class "+getInBeanName(tableName)+" extends BaseInBean{\n");
		}else if("out".equals(type)){//出参
			w.write("package "+beanPackage+".out;\n");
			w.write("\n");
			w.write("import "+pk.getName()+".bean.BaseOutBean;\n");
			w.write("\n");
			w.write("public class "+getOutBeanName(tableName)+" extends BaseOutBean{\n");
		}else{
			w.write("package "+beanPackage+";\n");
			w.write("\n");
			w.write("import java.io.Serializable;\n");
			w.write("\n");
			w.write("public class "+getBeanName(tableName)+" implements Serializable{\n");
			w.write("\n");
			w.write("	private static final long serialVersionUID = -1L;");
			w.write("\n");
		}
		w.write("\n");
		List<FieldBean> filedList=new ArrayList<FieldBean>();
		for(FieldBean f:fileds){
			String sqlType=f.getType().toLowerCase();
			String javaType=null;
			if(sqlType.indexOf("tinyint")!=-1
					||sqlType.indexOf("smallint")!=-1
					||sqlType.indexOf("mediumint")!=-1
					||sqlType.indexOf("int")!=-1
					||sqlType.indexOf("bigint")!=-1
					||sqlType.indexOf("year")!=-1
					){
				javaType="Integer";
			}else if(sqlType.indexOf("text")!=-1
					||sqlType.indexOf("varchar")!=-1
					||sqlType.indexOf("char")!=-1){
				javaType="String";
			}else if(sqlType.indexOf("double")!=-1
					||sqlType.indexOf("decimal")!=-1){
				javaType="Double";
			}else if(sqlType.indexOf("float")!=-1){
				javaType="Float";
			}else if(oracle.equals(dbName)&&sqlType.indexOf("number")!=-1){
				if(f.getData_scale()==null||f.getData_scale()==0){
					javaType="Long";
				}else{
					javaType="Double";
				}
			} else{
				throw new Exception("暂无配置"+sqlType+"类型的数据");
			}
			String fieldName=makeName(isCamelField, f.getField());
			w.write("	private "+javaType+" "+fieldName+";\n");
			w.write("\n");
			FieldBean bean=new FieldBean();
			bean.setType(javaType);
			bean.setField(fieldName);
			filedList.add(bean);
		}
		for(FieldBean f:filedList){
			//get
			w.write("	public "+f.getType()+" get"+firstUpperCase(f.getField())+"(){return "+f.getField()+";}\n");
			//set
			w.write("	public void set"+firstUpperCase(f.getField())+"("+f.getType()+" "+f.getField()+"){this."+f.getField()+"="+f.getField()+";}\n");
			w.write("\n");
		}
		
		w.write("}");
	}
	
	//获取驼峰命名的字段名
	private String makeName(Boolean isCamelField,String name){
		if(!isCamelField){
			return name;
		}
		String[] arr=name.split("_");
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<arr.length;i++){
			if(i!=0){
				arr[i]=firstUpperCase(arr[i]);
			}
			sb.append(arr[i]);
		}
		return sb.toString();	
	}
	private String getBeanName(String tableName){
		return firstUpperCase(makeName(true,tableName))+"Bean";
	}
	private String getInBeanName(String tableName){
		return firstUpperCase(makeName(true,tableName))+"InBean";
	}
	private String getOutBeanName(String tableName){
		return firstUpperCase(makeName(true,tableName))+"OutBean";
	}
	
	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	private String firstUpperCase(String str){
		String fst=str.substring(0, 1).toUpperCase();
		String se=str.substring(1);
		return fst+se;
	}
	
	
	/**
	 * package com.gys.sm.fun.table.dao;

		import org.springframework.stereotype.Repository;
		
		import com.gys.sm.fun.table.bean.GysBean;
		import com.gys.sm.item.inte.ITable;
		
		@Repository
		public interface IGysDao extends ITable<GysBean> {
			
		}

	 * @param w
	 * @throws IOException
	 */
	private void writeJavaDaoContent(BufferedWriter w,String daoPackage,String beanPackage,String tableName,List<FieldBean> fileds,boolean isSpringDao) throws IOException{
		w.write("package "+daoPackage+";\n");
		w.write("\n");
		if(isSpringDao){
			w.write("import org.springframework.stereotype.Repository;\n");
		}
		w.write("import "+ beanPackage+".in."+getInBeanName(tableName)+";\n");
		w.write("import "+ beanPackage+".out."+getOutBeanName(tableName)+";\n");
		w.write("import "+CreateFile.class.getPackage().getName()+".ITable;\n");
		w.write("\n");
		if(isSpringDao){
			w.write("@Repository \n");
		}
		w.write("public interface I"+ firstUpperCase(makeName(true, tableName))+"Dao extends ITable<"+getInBeanName(tableName)+","+getOutBeanName(tableName)+">{\n");
		w.write("\n");
		w.write("}");
	}
	
	/**
	 * 创建mapper文件
	 * @param sourcefolder
	 * @param interDaoUrl
	 * @param mapperUrl
	 * @param c
	 * @throws IOException
	 */
	public void createMapper(String sourceMapper,String mapperPackage,String daoPackage,String beanPackage, String tableName,List<FieldBean> fileds,String dbName,boolean isCamelField) throws IOException{
		OutputFormat format = new OutputFormat("    ", true);
		//XMLWriter xmlWriter=new XMLWriter(new FileOutputStream("src/t/mapper.xml"),format);
		String url=mapperPackage.replace(".", "/");
		XMLWriter xmlWriter=new XMLWriter(new FileOutputStream(sourceMapper+"/"+url+"/"+makeName(true, tableName)+"Mapper.xml"),format);
		Document document=mapperDocument(daoPackage,beanPackage,tableName,fileds,dbName,isCamelField);
		xmlWriter.write(document);
		xmlWriter.close();
	}
	private Document mapperDocument(String daoPackage,String beanPackage,String tableName,List<FieldBean> fields,String dbName,boolean isCamelField){
		Document document=DocumentHelper.createDocument();
		document.addDocType("mapper", "-//mybatis.org//DTD Mapper 3.0//EN", "http://mybatis.org/dtd/mybatis-3-mapper.dtd");
		Element root=document.addElement("mapper");
		root.addAttribute("namespace",daoPackage+".I"+firstUpperCase(makeName(true, tableName))+"Dao");//com.gys.sm.fun.table.dao.IGysDao
		//selectList
		selectListDocument(root,beanPackage,tableName,fields,dbName,isCamelField);
		//selectOne
		selectOneDocument(root,beanPackage,tableName,fields,false,isCamelField);
		//selectOneById
		selectOneDocument(root,beanPackage,tableName,fields,true,isCamelField);
		//getCount
		getCountDocument(root,beanPackage,tableName);
		//update
		updateDocument(root,beanPackage,tableName,fields,false,isCamelField);
		//updateById
		updateDocument(root,beanPackage,tableName,fields,true,isCamelField);
		//insert
		insertDocument(root,beanPackage,tableName,fields,false,isCamelField,dbName);
		//insertGetId
		insertDocument(root,beanPackage,tableName,fields,true,isCamelField,dbName);
		//delete
		deleteDocument(root,beanPackage,tableName,fields,false);
		//deleteById
		deleteDocument(root,beanPackage,tableName,fields,true);
		return document;
	}
	/**
	 * selectList
	 * @param root
	 * @param c
	 */
	private void selectListDocument(Element root,String beanPackage,String tableName,List<FieldBean> fields,String dbName,boolean isCamelField){
		Element select=root.addElement("select");
		select.addAttribute("id", "selectList");
		select.addAttribute("parameterType",beanPackage+".in."+getInBeanName(tableName));
		select.addAttribute("resultType", beanPackage+".out."+getOutBeanName(tableName));
		select.addText("\n\t\t");
		StringBuffer sb=new StringBuffer();
		int i=0;
		for(FieldBean f:fields){
			if(i!=0){
				sb.append(",");
			}
			sb.append(f.getField());
			if(isCamelField){
				sb.append(" as ");
				sb.append(makeName(isCamelField, f.getField()));
			}
			i++;
		}
		if(mysql.equals(dbName)){
			select.addText("select (@i:=@i+1) rowNum,"+sb.toString()+" from "+tableName+", (SELECT @i:=0) as i");
			
			Element whereTag=select.addElement("where");
			for(FieldBean f:fields){
				Element ifTag=whereTag.addElement("if");
				ifTag.addAttribute("test", makeName(isCamelField, f.getField())+"!=null");
				ifTag.addText("and "+f.getField()+"=#{"+makeName(isCamelField, f.getField())+"}");
			}
			Element whereIf=whereTag.addElement("if");
			whereIf.addAttribute("test", "sqlWhere!=null");
			whereIf.addText("${sqlWhere}");
			
			
			Element orderByIf=select.addElement("if");
			orderByIf.addAttribute("test","sqlOrderBy!=null");
			orderByIf.addText("order by ${sqlOrderBy}");
			Element limitIf=select.addElement("if");
			limitIf.addAttribute("test","sqlLimit==true");
			limitIf.addText("LIMIT #{sqlStartIndex},#{sqlPageSize}");
		}else if(oracle.equals(dbName)){
			Element limitIf=select.addElement("if");
			limitIf.addAttribute("test","sqlLimit==true");
			limitIf.addText("\n			select * from (\n		");
			select.addText("\n\t\t");
			select.addText(" select A.*,  rowNum from (");
			select.addText("\n		 	select "+sb.toString()+" from "+tableName);
			Element whereTag=select.addElement("where");
			for(FieldBean f:fields){
				Element ifTag=whereTag.addElement("if");
				ifTag.addAttribute("test", makeName(isCamelField, f.getField())+"!=null");
				ifTag.addText("and "+f.getField()+"=#{"+makeName(isCamelField, f.getField())+"}");
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
	}
	
	/**
	 * selectOne
	 * @param root
	 * @param c
	 */
	private void selectOneDocument(Element root,String beanPackage,String tableName,List<FieldBean> fields,boolean byId,boolean isCamelField){
		Element select=root.addElement("select");
		if(byId){
			select.addAttribute("id", "selectOneById");
			select.addAttribute("parameterType","object");
		}else{
			select.addAttribute("id", "selectOne");
			select.addAttribute("parameterType",beanPackage+".in."+getInBeanName(tableName));
		}		
		select.addAttribute("resultType", beanPackage+".out."+getOutBeanName(tableName));
		select.addText("\n\t\t");
		StringBuffer sb=new StringBuffer();
		int i=0;
		for(FieldBean f:fields){
			if(i!=0){
				sb.append(",");
			}
			sb.append(f.getField());
			if(isCamelField){
				sb.append(" as ");
				sb.append(makeName(isCamelField, f.getField()));
			}
			i++;
		}
		
		select.addText("select "+sb.toString()+" from "+tableName);
		
		Element whereIf=select.addElement("if");
		whereIf.addAttribute("test", "sqlWhere!=null");
		Element whereTag=whereIf.addElement("where");
		//whereTag.addText("${sqlWhere}");
		
		//Element where=select.addElement("where");
		if(byId){
			String key="";
			for(FieldBean f:fields){
				if("pri".equals(f.getKey())){
					key=f.getField();
					break;
				}
			}
			whereTag.addText(key+"=#{_parameter}");
		}else{
			whereTag.addText("${sqlWhere}");
		}
		
	}
	/**
	 * 获取count
	 * @param root
	 * @param c
	 */
	private void getCountDocument(Element root,String beanPackage,String tableName){
		Element select=root.addElement("select");
		select.addAttribute("id", "getCount");
		select.addAttribute("parameterType",beanPackage+".in."+getInBeanName(tableName));
		select.addAttribute("resultType", "int");
		select.addText("\n\t\t");
		
		select.addText("select count(*) from "+tableName);
		Element whereIf=select.addElement("if");
		whereIf.addAttribute("test", "sqlWhere!=null");
		Element whereTag=whereIf.addElement("where");
		whereTag.addText("${sqlWhere}");
	}
	private void updateDocument(Element root,String beanPackage,String tableName,List<FieldBean> fields,boolean byId,boolean isCamelField){
		Element update=root.addElement("update");
		if(byId){
			update.addAttribute("id", "updateById");
			update.addAttribute("parameterType",beanPackage+".in."+getInBeanName(tableName));
		}else{
			update.addAttribute("id", "update");
			update.addAttribute("parameterType",beanPackage+".in."+getInBeanName(tableName));
		}
		update.addText("\n\t\t");
		update.addText("update "+tableName);
		Element setTag=update.addElement("set");
		String key=null;
		for(FieldBean f:fields){
			if("pri".equals(f.getKey())){
				key=f.getField();
				continue;
			}
			Element ifTag=setTag.addElement("if");
			ifTag.addAttribute("test", makeName(isCamelField, f.getField())+"==null");
			ifTag.addText(f.getField()+"=null,");
			Element ifTag1=setTag.addElement("if");
			ifTag1.addAttribute("test", makeName(isCamelField, f.getField())+"!=null");
			ifTag1.addText(f.getField()+"=#{"+makeName(isCamelField, f.getField())+"},");
		}
		if(byId){
			update.addText("\n			where "+key+"=#{"+makeName(isCamelField, key)+"}");
		}else{
			Element whereIf=update.addElement("if");
			whereIf.addAttribute("test", "sqlWhere!=null");
			Element whereTag=whereIf.addElement("where");
			whereTag.addText("${sqlWhere}");
		}
	}
	private void insertDocument(Element root,String beanPackage,String tableName,List<FieldBean> fields,boolean getId,boolean isCamelField,String dbName){
		Element insert=root.addElement("insert");
		String key=null;
		//String keyType=null;
		for(FieldBean f:fields){
			if("pri".equals(f.getKey())){
				key=makeName(isCamelField, f.getField());
				//keyType=f.getType().toLowerCase();
				break;
			}
		}
		if(mysql.equals(dbName)){
			if(getId&&key!=null){
				insert.addAttribute("id", "insertGetId");
				insert.addAttribute("keyProperty",key);
				insert.addAttribute("useGeneratedKeys", "true");
			}else{
				insert.addAttribute("id", "insert");
			}
		}else if(oracle.equals(dbName)){
			if(getId){
				insert.addAttribute("id", "insertGetId");
			}else{
				insert.addAttribute("id", "insert");
			}
		}
		
		insert.addAttribute("parameterType", beanPackage+".in."+getInBeanName(tableName));
		
		insert.addText("\n\t\t");
		insert.addText("insert into "+tableName);
		
		Element trim1=insert.addElement("trim");
		trim1.addAttribute("prefix", "(");
		trim1.addAttribute("suffix", ")");
		trim1.addAttribute("suffixOverrides", ",");
		
		insert.addText("\n 			values");
		
		Element trim2=insert.addElement("trim");
		trim2.addAttribute("prefix", "(");
		trim2.addAttribute("suffix", ")");
		trim2.addAttribute("suffixOverrides", ",");
		for(FieldBean f:fields){
			if("pri".equals(f.getKey())&&"auto_increment".equals(f.getExtra())){
				continue;
			}
			if("pri".equals(f.getKey())&&oracle.equals(dbName)){//oracle主键
				trim1.addText("\n			"+f.getField()+",");
			}else{
				Element ifTag1=trim1.addElement("if");
				ifTag1.addAttribute("test", makeName(isCamelField, f.getField())+"!=null");
				ifTag1.addText(f.getField()+",");
			}			
			
			if("pri".equals(f.getKey())&&oracle.equals(dbName)){//oracle主键
				Element ifseq=trim2.addElement("if");
				ifseq.addAttribute("test","sqlSeq==null");
				ifseq.addText("#{"+makeName(isCamelField, f.getField())+"},");
				Element ifseq1=trim2.addElement("if");
				ifseq1.addAttribute("test","sqlSeq!=null");
				ifseq1.addText("\n				${sqlSeq}.Nextval,\n			");
			}else{
				Element ifTag2=trim2.addElement("if");
				ifTag2.addAttribute("test", makeName(isCamelField, f.getField())+"!=null");
				ifTag2.addText("#{"+makeName(isCamelField, f.getField())+"},");
			}
		}
		if(oracle.equals(dbName)&&getId&&key!=null){
			Element sk=insert.addElement("selectKey");
			sk.addAttribute("keyProperty", makeName(isCamelField, key));
			sk.addAttribute("order", "AFTER");
			sk.addAttribute("resultType", "long");
			sk.addText("select ${sqlSeq}.Currval from dual");
		}
	}
	
	private void deleteDocument(Element root,String beanPackage,String tableName,List<FieldBean> fields,boolean byId){
		Element insert=root.addElement("delete");
		if(byId){
			insert.addAttribute("id", "deleteById");
			insert.addAttribute("parameterType","object");
		}else{
			insert.addAttribute("id", "delete");
			insert.addAttribute("parameterType",beanPackage+".in."+getInBeanName(tableName));
		}
		insert.addText("\n\t\t");
		insert.addText("DELETE FROM "+tableName);
		Element whereIf=insert.addElement("if");
		whereIf.addAttribute("test", "sqlWhere !=null");
		Element where=whereIf.addElement("where");
		if(byId){
			String key="";
			for(FieldBean f:fields){
				if("pri".equals(f.getKey())){
					key=f.getField();
					break;
				}
			}
			where.addText(key+"=#{_parameter}");
		}else{
			where.addText("${sqlWhere}");
		}
	}
}

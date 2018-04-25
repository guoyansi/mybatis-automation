package table;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
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
	public void createJavaDao(String sourceDao,String daoPackage,String beanPackage,String tableName,List<FieldBean> fileds,boolean isSpringDao) throws IOException{
		//File file=new File("src/t/createjava/IGysDao.java");
		String url=daoPackage.replace(".", "/");
		
		File file=new File(sourceDao+"/"+url+"/I"+firstUpperCase(tableName)+"Dao.java");
		if(file.exists()){
			file.delete();
		}
		BufferedWriter w=new BufferedWriter(new FileWriter(file, true));
		writeJavaDaoContent(w,daoPackage,beanPackage,tableName,fileds,isSpringDao);
		w.flush();
		w.close();
	}
	
	public void createJavaBean(String sourceBean,String beanPackage,String tableName,List<FieldBean> fileds) throws Exception{
		//File file=new File("src/t/createjava/IGysDao.java");
		String url=beanPackage.replace(".", "/");
		
		File file=new File(sourceBean+"/"+url+"/"+firstUpperCase(tableName)+"Bean.java");
		if(file.exists()){
			file.delete();
		}
		BufferedWriter w=new BufferedWriter(new FileWriter(file, true));
		writeJavaBeanContent(w,beanPackage,tableName,fileds);
		w.flush();
		w.close();
	}
	
	private void writeJavaBeanContent(BufferedWriter w,String beanPackage,String tableName,List<FieldBean> fileds) throws Exception{
		w.write("package "+beanPackage+";\n");
		w.write("\n");
		w.write("public class "+getBeanName(tableName)+" {\n");
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
			}else{
				throw new Exception("暂无配置"+sqlType+"类型的数据");
			}
			w.write("	private "+javaType+" "+f.getField()+";\n");
			w.write("\n");
			FieldBean bean=new FieldBean();
			bean.setType(javaType);
			bean.setField(f.getField());
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
	
	private String getBeanName(String tableName){
		return firstUpperCase(tableName)+"Bean";
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
		w.write("import "+ beanPackage+"."+getBeanName(tableName)+";\n");
		w.write("import "+CreateFile.class.getPackage().getName()+".ITable;\n");
		w.write("\n");
		if(isSpringDao){
			w.write("@Repository \n");
		}
		w.write("public interface I"+ firstUpperCase(tableName)+"Dao extends ITable<"+getBeanName(tableName)+">{\n");
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
	public void createMapper(String sourceMapper,String mapperPackage,String daoPackage,String beanPackage, String tableName,List<FieldBean> fileds) throws IOException{
		OutputFormat format = new OutputFormat("    ", true);
		//XMLWriter xmlWriter=new XMLWriter(new FileOutputStream("src/t/mapper.xml"),format);
		String url=mapperPackage.replace(".", "/");
		XMLWriter xmlWriter=new XMLWriter(new FileOutputStream(sourceMapper+"/"+url+"/"+tableName+"Mapper.xml"),format);
		Document document=mapperDocument(daoPackage,beanPackage,tableName,fileds);
		xmlWriter.write(document);
		xmlWriter.close();
	}
	private Document mapperDocument(String daoPackage,String beanPackage,String tableName,List<FieldBean> fields){
		Document document=DocumentHelper.createDocument();
		document.addDocType("mapper", "-//mybatis.org//DTD Mapper 3.0//EN", "http://mybatis.org/dtd/mybatis-3-mapper.dtd");
		Element root=document.addElement("mapper");
		root.addAttribute("namespace",daoPackage+".I"+firstUpperCase(tableName)+"Dao");//com.gys.sm.fun.table.dao.IGysDao
		//selectList
		selectListDocument(root,beanPackage,tableName,fields);
		//selectOne
		selectOneDocument(root,beanPackage,tableName,fields,false);
		//selectOneById
		selectOneDocument(root,beanPackage,tableName,fields,true);
		//getCount
		getCountDocument(root,beanPackage,tableName);
		//update
		updateDocument(root,beanPackage,tableName,fields,false);
		//updateById
		updateDocument(root,beanPackage,tableName,fields,true);
		//insert
		insertDocument(root,beanPackage,tableName,fields,false);
		//insertGetId
		insertDocument(root,beanPackage,tableName,fields,true);
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
	private void selectListDocument(Element root,String beanPackage,String tableName,List<FieldBean> fields){
		Element select=root.addElement("select");
		select.addAttribute("id", "selectList");
		select.addAttribute("parameterType",beanPackage+"."+getBeanName(tableName));
		select.addAttribute("resultType", beanPackage+"."+getBeanName(tableName));
		select.addText("\n\t\t");
		//String tableName=getTableName(c);
		//Field[] fsArr=c.getDeclaredFields();
		StringBuffer sb=new StringBuffer();
		int i=0;
		for(FieldBean f:fields){
			if(i!=0){
				sb.append(",");
			}
			sb.append(f.getField());
			i++;
		}
		select.addText("select "+sb.toString()+" from "+tableName);
		Element where=select.addElement("where");
		where.addText("${sqlWhere}");
		Element orderByIf=select.addElement("if");
		orderByIf.addAttribute("test","sqlOrderBy!=null");
		orderByIf.addText("order by ${sqlOrderBy}");
		Element limitIf=select.addElement("if");
		limitIf.addAttribute("test","sqlLimit==true");
		limitIf.addText("LIMIT #{sqlStartIndex},#{sqlPageSize}");
	}
	
	/**
	 * selectOne
	 * @param root
	 * @param c
	 */
	private void selectOneDocument(Element root,String beanPackage,String tableName,List<FieldBean> fields,boolean byId){
		String paramType=beanPackage+"."+getBeanName(tableName);
		Element select=root.addElement("select");
		if(byId){
			select.addAttribute("id", "selectOneById");
			select.addAttribute("parameterType","object");
		}else{
			select.addAttribute("id", "selectOne");
			select.addAttribute("parameterType",paramType);
		}		
		select.addAttribute("resultType", paramType);
		select.addText("\n\t\t");
		StringBuffer sb=new StringBuffer();
		int i=0;
		for(FieldBean f:fields){
			if(i!=0){
				sb.append(",");
			}
			sb.append(f.getField());
			i++;
		}
		
		select.addText("select "+sb.toString()+" from "+tableName);
		Element where=select.addElement("where");
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
	/**
	 * 获取count
	 * @param root
	 * @param c
	 */
	private void getCountDocument(Element root,String beanPackage,String tableName){
		String paramType=beanPackage+"."+getBeanName(tableName);
		Element select=root.addElement("select");
		select.addAttribute("id", "getCount");
		select.addAttribute("parameterType",paramType);
		select.addAttribute("resultType", "int");
		select.addText("\n\t\t");
		
		select.addText("select count(*) from "+tableName);
		Element where=select.addElement("where");
		where.addText("${sqlWhere}");
	}
	private void updateDocument(Element root,String beanPackage,String tableName,List<FieldBean> fields,boolean byId){
		String paramType=beanPackage+"."+getBeanName(tableName);
		Element update=root.addElement("update");
		if(byId){
			update.addAttribute("id", "updateById");
			update.addAttribute("parameterType","object");
		}else{
			update.addAttribute("id", "update");
			update.addAttribute("parameterType",paramType);
		}
		update.addText("\n\t\t");
		update.addText("update "+tableName);
		Element setIf=update.addElement("if");
		setIf.addAttribute("test", "SqlSet==null");
		Element setTag=setIf.addElement("set");
		StringBuffer sb=new StringBuffer();
		String key="";
		int i=0;
		for(FieldBean f:fields){
			if("pri".equals(f.getKey())){
				key=f.getField();
				continue;
			}
			sb.append("\n\t\t\t");
			sb.append(f.getField());
			sb.append("=");
			sb.append("#{");
			sb.append(f.getField());
			sb.append("}");
			sb.append(",");
		}
		setTag.addText(sb.toString());
		Element setIf1=update.addElement("if");
		setIf1.addAttribute("test", "sqlSet!=null");
		Element setTag1=setIf1.addElement("set");
		setTag1.addText("${sqlSet}");
		Element whereTag=update.addElement("where");
		if(byId){
			whereTag.addText(key+"=#{_parameter}");
		}else{
			whereTag.addText("${sqlWhere}");
		}
	}
	private void insertDocument(Element root,String beanPackage,String tableName,List<FieldBean> fields,boolean getId){
		//Field[] fsArr=c.getDeclaredFields();
		String paramType=beanPackage+"."+getBeanName(tableName);
		Element insert=root.addElement("insert");
		
		if(getId){
			String key="";
			for(FieldBean f:fields){
				if("pri".equals(f.getKey())){
					key=f.getField();
					break;
				}
			}
			insert.addAttribute("id", "insertGetId");
			insert.addAttribute("keyProperty",key);
			insert.addAttribute("useGeneratedKeys", "true");
		}else{
			insert.addAttribute("id", "insert");
		}
		insert.addAttribute("parameterType", paramType);
		StringBuffer sbk=new StringBuffer();
		StringBuffer sbv=new StringBuffer();
		int i=0;
		for(FieldBean f:fields){
			if("pri".equals(f.getKey())&&"auto_increment".equals(f.getExtra())){
				continue;
			}
			if(i!=0){
				sbk.append(",");
				sbv.append(",");
			}
			sbk.append(f.getField());
			sbv.append("#{");
			sbv.append(f.getField());
			sbv.append("}");
			i++;
		}
		
		insert.addText("\n\t\t");
		insert.addText("insert into "+tableName);
		insert.addText("\n\t\t(");
		insert.addText(sbk.toString());
		insert.addText(")");
		insert.addText("\n\t\t values");
		insert.addText("\n\t\t(");
		insert.addText(sbv.toString());
		insert.addText(")");
		insert.addText("\r\t");
	}
	
	private void deleteDocument(Element root,String beanPackage,String tableName,List<FieldBean> fields,boolean byId){
		String paramType=beanPackage+"."+getBeanName(tableName);
		Element insert=root.addElement("delete");
		if(byId){
			insert.addAttribute("id", "deleteById");
			insert.addAttribute("parameterType","object");
		}else{
			insert.addAttribute("id", "delete");
			insert.addAttribute("parameterType",paramType);
		}
		insert.addText("\n\t\t");
		insert.addText("DELETE FROM "+tableName);
		Element where=insert.addElement("where");
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

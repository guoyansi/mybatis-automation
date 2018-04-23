package table;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * 根据包名 查找类和类中的字段
 * @author guoyansi
 *
 */
public class FindBean {
	public static List<Class> get(String basePack) throws Exception{
		//包名
		//String basePack="t.table";
		//先把包名转换为路径,首先得到项目的classpath
        String classpath = FindBean.class.getResource("/").getPath();
        //然后把我们的包名basPach转换为路径名
        basePack = basePack.replace(".", File.separator);
        //然后把classpath和basePack合并
        String searchPath = classpath + basePack;
        List<String> classPaths=doPath(new File(searchPath),new ArrayList<String>());
        List<Class> list=new ArrayList<Class>();
        //这个时候我们已经得到了指定包下所有的类的绝对路径了。我们现在利用这些绝对路径和java的反射机制得到他们的类对象
        for (String s : classPaths) {
            //把 D:\work\code\20170401\search-class\target\classes\com\baibin\search\a\A.class 这样的绝对路径转换为全类名com.baibin.search.a.A
            s = s.replace(classpath.replace("/","\\").replaceFirst("\\\\",""),"").replace("\\",".").replace(".class","");
            Class cls = Class.forName(s);
            list.add(cls);
            /*Field[] fs=cls.getDeclaredFields();
            System.out.println("类名："+cls.getName());
            System.out.println("父类名："+cls.getSuperclass().getSimpleName());
            for(Field f:fs){
            	//获取字段名称
            	System.out.println(f.getName());
            }
            System.out.println("=======");*/
        }
        return list;
	}
	 /**
     * 该方法会得到所有的类，将类的绝对路径写入到classPaths中
     * @param file
     */
    private static List<String> doPath(File file,List<String> classPaths) {
        if (file.isDirectory()) {//文件夹
            //文件夹我们就递归
            File[] files = file.listFiles();
            for (File f1 : files) {
                doPath(f1,classPaths);
            }
        } else {//标准文件
            //标准文件我们就判断是否是class文件
            if (file.getName().endsWith(".class")) {
                //如果是class文件我们就放入我们的集合中。
                classPaths.add(file.getPath());
            }
        }
        return classPaths;
    }
}

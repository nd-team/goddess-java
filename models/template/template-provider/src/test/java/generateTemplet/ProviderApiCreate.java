package generateTemplet;

import buildfile.Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-13 10:23]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProviderApiCreate {
    public static void createModel(Map<String, String> cus, List<Model> models,String createOrDelete) {

        String packageName = cus.get("模块名");
        String className = cus.get("类名");
        className = className.substring(className.lastIndexOf("/")+1,className.length());
        String author = cus.get("作者");
        String desc = cus.get("描述")+"业务接口实现";
        LocalDateTime date = LocalDateTime.now();
        String relativePath = "";
        if( cus.get("类名").contains("/")){
            relativePath = cus.get("类名").substring( 0,cus.get("类名").lastIndexOf("/"));
        }
        String packageRelativePath = (relativePath.equals("")?"":"."+relativePath).replaceAll("/",".");


        StringBuilder sb = new StringBuilder("");
        sb.append("package com.bjike.goddess."+packageName+".api"+packageRelativePath+";\n\n")
        .append("import org.springframework.beans.factory.annotation.Autowired;\n")
        .append("import org.springframework.stereotype.Service;\n\n");

        //类描述
        sb.append( "/**\n")
                .append("* "+desc+"\n")
                .append("* @Author:\t\t\t[ "+author+" ]\n")
                .append("* @Date:\t\t\t[  "+date+" ]\n")
                .append("* @Description:\t[ "+desc+" ]\n")
                .append("* @Version:\t\t[ v1.0.0 ]\n")
                .append("* @Copy:   \t\t[ com.bjike ]\n")
                .append("*/\n");

        sb.append("@Service(\""+className.substring(0,1).toLowerCase()+className.substring(1)+"ApiImpl\")\n");
        //类创建
        sb.append("public class "+className+"ApiImpl implements "+className+"API  { \n\n");


        //拼接类完成
        sb.append(" }");

        //文件创建路径
        StringBuffer  filePath = new StringBuffer( System.getProperty("user.dir") + "/models/" )
                .append(packageName.toLowerCase()+"/")
                .append( packageName.toLowerCase()+"-provider/src/main/java/com/bjike/goddess/")
                .append( packageName.toLowerCase()+"/api/")
                ;

        //相对包路径
        if(!relativePath.trim().equals("")){
            filePath.append(relativePath+"/");
        }
        //文件创建
        File file = new File( filePath.toString() );
        //如果文件夹不存在则创建
        if  (!file .exists()  && !file .isDirectory())
        {
            file .mkdirs();
        }
        filePath.append( className+"ApiImpl.java" );
        file = new File( filePath.toString() );
        if( createOrDelete.equals("create")){

            try {
                FileWriter writer = new FileWriter(file);
                writer.write( sb.toString() ,0 ,sb.toString().length());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(createOrDelete.equals("delete")){
            if(file.exists()){
                file.delete();
            }
        }
    }
}


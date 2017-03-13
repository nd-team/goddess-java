package GenerateTemplet;

import buildfile.Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-13 10:32]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DaoCreate {
    public static void createModel(Map<String, String> cus, List<Model> models) {

        String packageName = cus.get("模块名");
        String className = cus.get("类名");
        String author = cus.get("作者");
        String desc = cus.get("描述")+"持久化接口, 继承基类可使用ｊｐａ命名查询";
        LocalDateTime date = LocalDateTime.now();
        int size = 0;
        if (models != null && models.size() > 0) {
            size = models.size(); //属性字段长度
        }


        StringBuilder sb = new StringBuilder("");
        sb.append("package com.bjike.goddess."+packageName+".dao;\n\n")
        .append("import com.bjike.goddess.common.jpa.dao.JpaRep;\n")
        .append("import com.bjike.goddess."+packageName+".dto."+className+"DTO;\n")
        .append("import com.bjike.goddess."+packageName+".entity."+className+";\n\n");

        //类描述
        sb.append( "/**\n")
                .append("* "+desc+"\n")
                .append("* @Author:\t\t\t[ "+author+" ]\n")
                .append("* @Date:\t\t\t[  "+date+" ]\n")
                .append("* @Description:\t[ "+desc+" ]\n")
                .append("* @Version:\t\t[ v1.0.0 ]\n")
                .append("* @Copy:   \t\t[ com.bjike ]\n")
                .append("*/\n");
        //类创建
        sb.append("public interface I"+className+" extends JpaRep<"+className+" ,"+className+"DTO> { \n\n");


        //拼接类完成
        sb.append(" }");

        //文件创建路径
        StringBuffer  filePath = new StringBuffer( System.getProperty("user.dir") + "/" )
                .append(packageName.toLowerCase()+"/")
                .append( packageName.toLowerCase()+"-provider/src/main/java/com/bjike/goddess/")
                .append( packageName.toLowerCase()+"/dao/")
                ;

        //文件创建
        File file = new File( filePath.toString() );
        //如果文件夹不存在则创建
        if  (!file .exists()  && !file .isDirectory())
        {
            System.out.println("//不存在");
            file .mkdirs();
        }
        filePath.append( "I"+className+".java" );
        file = new File( filePath.toString() );
        try {
            FileWriter writer = new FileWriter(file);
            writer.write( sb.toString() ,0 ,sb.toString().length());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


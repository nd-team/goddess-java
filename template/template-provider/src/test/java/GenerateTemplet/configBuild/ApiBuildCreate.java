package GenerateTemplet.configBuild;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-13 11:41]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ApiBuildCreate {


    public static void createConfig(Map<String, String> cus,String createOrDelete){
        String packageName = cus.get("模块名");

        StringBuffer sb = new StringBuffer("");
        sb.append("apply from: '../../config.gradle'\n")
                .append("dependencies { \n")
                .append("compile project(\":common:common-api\")\n" )
                .append("compile project(\":common:common-utils\")")
                .append("} \n");
        //文件创建路径
        StringBuffer  filePath = new StringBuffer( System.getProperty("user.dir") + "/" )
                .append(packageName.toLowerCase()+"/")
                .append( packageName.toLowerCase()+"-api/")
                ;

        //文件创建
        File file = new File( filePath.toString() );
        //如果文件夹不存在则创建
        if  (!file .exists()  && !file .isDirectory())
        {
            file .mkdirs();
        }
        filePath.append( "build.gradle" );
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

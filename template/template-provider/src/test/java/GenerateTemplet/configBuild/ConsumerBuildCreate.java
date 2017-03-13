package GenerateTemplet.configBuild;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-13 11:54]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ConsumerBuildCreate {

    public static void createConfig(Map<String, String> cus) {
        String packageName = cus.get("模块名");

        StringBuffer sb = new StringBuffer("");
        sb.append("buildscript { \n")
                .append(" repositories { \n")
                .append(" mavenCentral()\n")
                .append(" }\n")
                .append(" dependencies {\n")
                .append(" classpath(\"org.springframework.boot:spring-boot-gradle-plugin:$spring_boot_version\")\n")
                .append(" }\n")
                .append(" } \n\n");
        sb.append("apply plugin: 'java'\n")
                .append("apply plugin: 'idea'\n")
                .append("apply plugin: 'org.springframework.boot'\n\n");

        sb.append("jar {\n")
                .append("    baseName = '" + packageName + "-consumer'\n")
                .append("    version =  '1.0'\n")
                .append("}\n\n");

        sb.append("repositories {\n")
                .append(" mavenCentral()\n")
                .append("}\n\n");

        sb.append("sourceCompatibility = 1.8\n" )
                .append(  "targetCompatibility = 1.8\n\n");

        sb.append("dependencies {\n" )
                .append( "compile(project(\":common:common-consumer\")){\n" )
                .append("    exclude group: 'org.slf4j', module: 'slf4j-log4j12'\n" )
                .append("}\n\n");

        sb.append("compile project(\":"+packageName+":"+packageName+"-api\")\n\n");

        sb.append("compile(\"org.springframework.boot:spring-boot-starter-web\")\n" )
                .append( "}\n\n");
        //文件创建路径
        StringBuffer filePath = new StringBuffer(System.getProperty("user.dir") + "/")
                .append(packageName.toLowerCase() + "/")
                .append(packageName.toLowerCase() + "-consumer/");

        //文件创建
        File file = new File(filePath.toString());
        //如果文件夹不存在则创建
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        filePath.append("build.gradle");
        file = new File(filePath.toString());
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(sb.toString(), 0, sb.toString().length());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

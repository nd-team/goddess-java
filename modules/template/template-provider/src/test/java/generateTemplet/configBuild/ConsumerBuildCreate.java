package generateTemplet.configBuild;

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

    public static void createConfig(Map<String, String> cus,String createOrDelete) {
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
        sb.append("apply plugin: 'org.springframework.boot'\n")
                .append("jar {\n")
                .append("    String buildDir = project.buildDir\n")
                .append("    manifest {\n")
                .append("        attributes (\n")
                .append("                \"Main-Class\": \"com.bjike.goddess."+packageName+".Application\",//main主函数加载入口\n")
                .append("    \"Class-Path\": new File(buildDir+'/libs/lib').list().collect { \"lib/${it}\" }.join(\" \")\n")
                .append("   )\n")
                .append("    }\n")
                .append("    }\n\n")
                ;

        sb.append("task copyJars(type:Copy) {\n")
                .append("    from configurations.runtime\n")
                .append("    into new File('build/libs/lib') // 目标位置\n")
                .append(" }\n");
        sb.append("  compileJava.dependsOn copyJars\n\n");
        sb.append(" def env = hasProperty(\"pro\")?\"pro\":(hasProperty(\"dev\")?\"dev\":null)\n")
                .append("sourceSets {\n")
                .append("    main {\n")
                .append("        resources {\n")
                .append("     srcDirs = [\"src/main/resources\", \"src/main/profile/$env\"]\n")
                .append("     }\n")
                .append("     }\n")
                .append("     }\n");

        sb.append("dependencies {\n" )
                .append( "compile(project(\":common:common-consumer\")){\n" )
                .append("    exclude group: 'org.slf4j', module: 'slf4j-log4j12'\n" )
                .append("}\n\n");

        sb.append("compile project(\":modules:"+packageName+":"+packageName+"-api\")\n\n");

        sb.append("compile(\"org.springframework.boot:spring-boot-starter-web\")\n" )
                .append( "}\n\n");
        //文件创建路径
        StringBuffer filePath = new StringBuffer(System.getProperty("user.dir") + "/modules/")
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

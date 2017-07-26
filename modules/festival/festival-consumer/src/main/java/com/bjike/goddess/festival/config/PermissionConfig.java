package com.bjike.goddess.festival.config;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.festival.api.CusPermissionAPI;
import com.bjike.goddess.festival.to.CusPermissionTO;
import com.bjike.goddess.festival.type.CusPermissionType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ike on 17-5-12.
 */
@Component
public class PermissionConfig {
    @PostConstruct
    public void init() throws IOException {
        generatePerssion();
    }

    @Autowired
    private CusPermissionAPI cusPermissionAPI;
    @Autowired
    private Environment env;


    private static List<String> descList = new ArrayList<>();
    private static List<String> idList = new ArrayList<>();
    private static List<String> typeList = new ArrayList<>();
//
//    public static void main(String[] args) throws IOException{
//        generatePerssion();
//    }

    //解析permission.propertity文档
    public void generatePerssion() throws IOException {

        String pro_describe = env.getProperty("describe");
        String pro_ids = env.getProperty("ids");
        String pro_type = env.getProperty("type");

        if (StringUtils.isBlank(pro_describe) || StringUtils.isBlank(pro_ids) || StringUtils.isBlank(pro_type)) {
            return;
        }

        pro_describe = pro_describe.replace("\\", "");
        pro_ids = pro_ids.replace("\\", "");
        pro_type = pro_type.replace("\\", "");


        String[] describeStrList = pro_describe.split(";");
        String[] idStrList = pro_ids.split(";");
        String[] typeStrList = pro_type.split(";");

        for (String desc : describeStrList) {
            descList.add(desc.trim());
        }
        for (String id : idStrList) {
            idList.add(id.trim());
        }
        for (String type : typeStrList) {
            typeList.add(type.trim());
        }


        //获取输入文件路径
//        String inputPath = System.getProperty("user.dir") + "/modules/customer/customer-consumer/src/main/permission/permission.properties";
//        System.err.println(inputPath);
//        String fieldsString = null;
//        File file = new File(inputPath);
//        BufferedReader reader = new BufferedReader(new FileReader(file));
//        Stream<String> stream = reader.lines();
//        List<String> lines = stream.collect(Collectors.toList());
//        System.out.println(lines);
//        //[#描述, describe=客户添加权限, #对应id, id=1]
//        System.out.println(lines.size());
//        createDeails(lines);

        //插入数据库
        List<CusPermissionTO> listTO = new ArrayList<>();
        if (descList.size() != idList.size() && descList.size() != typeList.size()) {
            return;
        }
        for (int i = 0; i < descList.size(); i++) {
            String desc = descList.get(i);
            String id = idList.get(i);
            String type = typeList.get(i);
            CusPermissionTO to = new CusPermissionTO();
            to.setIdFlag(id);
            to.setDescription(desc);
            if (CusPermissionType.LEVEL.name().equals(type.trim())) {
                to.setType(CusPermissionType.LEVEL);
            } else if (CusPermissionType.MODULE.name().equals(type.trim())) {
                to.setType(CusPermissionType.MODULE);
            } else if (CusPermissionType.POSITION.name().equals(type.trim())) {
                to.setType(CusPermissionType.POSITION);
            } else if (CusPermissionType.DEPART.name().equals(type.trim())) {
                to.setType(CusPermissionType.DEPART);
            }
            to.setOperator("");
            listTO.add(to);

        }
        try {
            if (listTO != null && listTO.size() > 0) {
                cusPermissionAPI.add(listTO);
            }
        } catch (SerException e) {
            e.printStackTrace();
        }


    }

    /**
     * 构建类描述
     */
    private static void createDeails(List<String> lines) {
        descList = new ArrayList<>();
        idList = new ArrayList<>();
        int index = 0;
        StringBuffer descSb = new StringBuffer("");
        StringBuffer idSb = new StringBuffer("");
        StringBuffer typeSb = new StringBuffer("");
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.startsWith("ids=")) {
                index = -1;
            }
            if (line.startsWith("type=")) {
                index = -2;
            }
            if (!line.startsWith("#")) {
                if (!line.startsWith("ids=") && index != -1 && index != -2) {
                    descSb.append(line.replace(";\\", ";"));
                }
                if (index == -1) {
                    idSb.append(line.replace(";\\", ";"));
                }
                if (index == -2) {
                    typeSb.append(line.replace(";\\", ";"));
                }
            }

        }
        //描述
        int indexFlag = descSb.toString().indexOf("=");
        String describeStr = descSb.toString().substring(indexFlag + 1);
        //id
        indexFlag = idSb.toString().indexOf("=");
        String idStr = idSb.toString().substring(indexFlag + 1);
        //type
        indexFlag = typeSb.toString().indexOf("=");
        String typeStr = typeSb.toString().substring(indexFlag + 1);

        String[] describeStrList = describeStr.split(";");
        String[] idStrList = idStr.split(";");
        String[] typeStrList = typeStr.split(";");

        for (String desc : describeStrList) {
            descList.add(desc.trim());
        }
        for (String id : idStrList) {
            idList.add(id.trim());
        }
        for (String type : typeStrList) {
            typeList.add(type.trim());
        }

        System.out.println(descSb + "复活节客队：" + describeStr);
        System.out.println(idSb + "复活节客队：" + idStr);
        System.out.println(typeSb + "复活节客队：" + typeStr);
    }
}

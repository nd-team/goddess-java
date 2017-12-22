package com.bjike.goddess.businessprojectmanage.config;

import com.bjike.goddess.businessprojectmanage.api.CusPermissionAPI;
import com.bjike.goddess.businessprojectmanage.enums.CusPermissionType;
import com.bjike.goddess.businessprojectmanage.to.CusPermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
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

}

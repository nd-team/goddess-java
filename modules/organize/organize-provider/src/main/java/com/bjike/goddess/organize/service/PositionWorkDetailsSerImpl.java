package com.bjike.goddess.organize.service;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.organize.bo.IndicatorBO;
import com.bjike.goddess.organize.bo.ManagerBO;
import com.bjike.goddess.organize.bo.ModulesBO;
import com.bjike.goddess.organize.bo.PositionWorkDetailsBO;
import com.bjike.goddess.organize.dto.IndicatorDTO;
import com.bjike.goddess.organize.dto.ModulesDTO;
import com.bjike.goddess.organize.dto.PositionWorkDetailsDTO;
import com.bjike.goddess.organize.entity.Indicator;
import com.bjike.goddess.organize.entity.Modules;
import com.bjike.goddess.organize.entity.PositionWorkDetails;
import com.bjike.goddess.organize.to.IndicatorTO;
import com.bjike.goddess.organize.to.ModulesTO;
import com.bjike.goddess.organize.to.PositionWorkDetailsTO;
import com.bjike.goddess.organize.vo.ActResultOrgan;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 岗位工作明细表业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-12 01:41 ]
 * @Description: [ 岗位工作明细表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "organizeSerCache")
@Service
public class PositionWorkDetailsSerImpl extends ServiceImpl<PositionWorkDetails, PositionWorkDetailsDTO> implements PositionWorkDetailsSer {
    @Autowired
    private ModulesSer modulesSer;
    @Autowired
    private IndicatorSer indicatorSer;
    @Autowired
    private DepartmentDetailSer departmentDetailSer;
    @Autowired
    private PositionDetailSer positionDetailSer;

    @Transactional
    @Override
    public void save(PositionWorkDetailsTO to) throws SerException {
        PositionWorkDetails entity = BeanTransform.copyProperties(to, PositionWorkDetails.class, "modulesTOList");
        entity = transForm(entity);
        //todo: 注意这张表要重新建一下，因为有可能查不到Purpose和Version
        entity.setPurpose("asf");
        entity.setVersion("saf");
        entity = super.save(entity);
        List<ModulesTO> modulesTOList = to.getModulesTOList();
        for (ModulesTO modulesTO : modulesTOList) {
            Modules modules = BeanTransform.copyProperties(modulesTO, Modules.class, "indicatorTOList", "workDetailsId");
            modules.setWorkDetailsId(entity.getId());
            modules = modulesSer.save(modules);
            for (IndicatorTO indicatorTO : modulesTO.getIndicatorTOList()) {
                Indicator indicator = BeanTransform.copyProperties(indicatorTO, Indicator.class, "modulesId");
                indicator.setModulesId(modules.getId());
                indicatorSer.save(indicator);
            }
        }

    }

    @Transactional
    @Override
    public void update(PositionWorkDetailsTO to) throws SerException {
        PositionWorkDetails entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据对象为空");
        }
        BeanTransform.copyProperties(to, entity, "modulesTOList");
        entity = transForm(entity);
//        entity.setPurpose("asf");
//        entity.setVersion("saf");
        super.update(entity);
        for (ModulesTO modulesTO : to.getModulesTOList()) {
            Modules modules = modulesSer.findById(modulesTO.getId());
            BeanTransform.copyProperties(modulesTO, modules);
            modulesSer.update(modules);
            for (IndicatorTO indicatorTO : modulesTO.getIndicatorTOList()) {
                Indicator indicator = indicatorSer.findById(indicatorTO.getId());
                BeanTransform.copyProperties(indicatorTO, indicator, "modulesId");
                indicatorSer.update(indicator);
            }
        }
    }

    @Transactional
    @Override
    public void delete(String id) throws SerException {
        PositionWorkDetails entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据对象为空");
        }
        ModulesDTO modulesDTO = new ModulesDTO();
        modulesDTO.getConditions().add(Restrict.eq("workDetailsId", entity.getId()));
        List<Modules> modulesList = modulesSer.findByCis(modulesDTO);
        for (Modules modules : modulesList) {
            IndicatorDTO indicatorDTO = new IndicatorDTO();
            indicatorDTO.getConditions().add(Restrict.eq("modulesId", modules.getId()));
            indicatorSer.remove(indicatorSer.findByCis(indicatorDTO));
        }
        modulesSer.remove(modulesList);
        super.remove(entity);
    }

    @Override
    public List<PositionWorkDetailsBO> maps(PositionWorkDetailsDTO dto) throws SerException {
        searchCondition(dto);


        List<PositionWorkDetails> positionWorkDetailsList = super.findByPage(dto);
        if (null != positionWorkDetailsList && positionWorkDetailsList.size() > 0) {
            List<PositionWorkDetailsBO> positionWorkDetailsBOList = BeanTransform.copyProperties(positionWorkDetailsList, PositionWorkDetailsBO.class, "modulesBOList");
            for (PositionWorkDetailsBO positionWorkDetailsBO : positionWorkDetailsBOList) {
                ModulesDTO modulesDTO = new ModulesDTO();
                if (StringUtils.isNotBlank(dto.getModulesName())) {
                    modulesDTO.getConditions().add(Restrict.eq("name", dto.getModulesName()));
                    modulesDTO.getConditions().add(Restrict.eq("hasConnet", 1));
                }
//                ModulesDTO modulesDTO1 = new ModulesDTO();
                modulesDTO.getConditions().add(Restrict.eq("workDetailsId", positionWorkDetailsBO.getId()));
                List<Modules> modulesList = modulesSer.findByCis(modulesDTO);
                List<ModulesBO> modulesBOList = BeanTransform.copyProperties(modulesList, ModulesBO.class, "indicatorBOList");
                for (ModulesBO modulesBO : modulesBOList) {
                    IndicatorDTO indicatorDTO = new IndicatorDTO();
                    indicatorDTO.getConditions().add(Restrict.eq("modulesId", modulesBO.getId()));
                    List<Indicator> indicatorList = indicatorSer.findByCis(indicatorDTO);
                    List<IndicatorBO> indicatorBOList = BeanTransform.copyProperties(indicatorList, IndicatorBO.class);
                    modulesBO.setIndicatorBOList(indicatorBOList);
                }
                positionWorkDetailsBO.setModulesBOList(modulesBOList);
            }
            return positionWorkDetailsBOList;
        }
        return null;
    }

    @Override
    public Long getTotal(PositionWorkDetailsDTO dto) throws SerException {
        searchCondition(dto);
        if (StringUtils.isNotBlank(dto.getModulesName())) {
            ModulesDTO modulesDTO = new ModulesDTO();
            modulesDTO.getConditions().add(Restrict.eq("name", dto.getModulesName()));
            modulesDTO.getConditions().add(Restrict.eq("hasConnet", 1));
            List<Modules> modulesList = modulesSer.findByCis(modulesDTO);
            if (null != modulesList && modulesList.size() > 0) {
                List<String> list = modulesList.stream().map(Modules::getWorkDetailsId).distinct().collect(Collectors.toList());
                return Long.valueOf(list.size());
            }
        }
        return super.count(dto);
    }

    @Override
    public PositionWorkDetailsBO getById(String id) throws SerException {
        PositionWorkDetails positionWorkDetails = super.findById(id);
        if (null == positionWorkDetails) {
            throw new SerException("目标数据对象为空");
        }
        PositionWorkDetailsBO positionWorkDetailsBO = BeanTransform.copyProperties(positionWorkDetails, PositionWorkDetailsBO.class, "modulesBOList");
        ModulesDTO modulesDTO = new ModulesDTO();
        modulesDTO.getConditions().add(Restrict.eq("workDetailsId", positionWorkDetails.getId()));
        List<Modules> modulesList = modulesSer.findByCis(modulesDTO);
        List<ModulesBO> modulesBOList = BeanTransform.copyProperties(modulesList, ModulesBO.class, "indicatorBOList");
        for (ModulesBO modulesBO : modulesBOList) {
            IndicatorDTO indicatorDTO = new IndicatorDTO();
            indicatorDTO.getConditions().add(Restrict.eq("modulesId", modulesBO.getId()));
            List<Indicator> indicatorList = indicatorSer.findByCis(indicatorDTO);
            List<IndicatorBO> indicatorBOList = BeanTransform.copyProperties(indicatorList, IndicatorBO.class);
            modulesBO.setIndicatorBOList(indicatorBOList);
        }
        positionWorkDetailsBO.setModulesBOList(modulesBOList);

        return positionWorkDetailsBO;
    }

    @Override
    public List<ManagerBO> weekCollect(String startTime, String endTime) throws SerException {
        if (StringUtils.isBlank(startTime) && StringUtils.isBlank(endTime)) {
            startTime = DateUtil.dateToString(DateUtil.getStartWeek());
            endTime = DateUtil.dateToString(LocalDate.now());
        }
        List<ManagerBO> list = new ArrayList<>(0);
        list.add(setManagerBO(startTime, endTime));
        return list;
    }

    @Override
    public List<ManagerBO> monthCollect(String month) throws SerException {
        if (StringUtils.isBlank(month)) {
            month = LocalDate.now().toString().substring(0, LocalDate.now().toString().lastIndexOf("-"));
        }
        month = month + "-01";
        String startTime = findFirstDayOfMonth(month);
        String endTime = findEndDayOfMonth(month);
        List<ManagerBO> list = new ArrayList<>(0);
        list.add(setManagerBO(startTime, endTime));
        return list;
    }

    @Override
    public List<ManagerBO> totalCollect() throws SerException {
        String startTime = "";
        String endTime = "";
        List<ManagerBO> list = new ArrayList<>(0);
        list.add(setManagerBO(startTime, endTime));
        return list;
    }

    private void searchCondition(PositionWorkDetailsDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getAngle())) {
            dto.getConditions().add(Restrict.eq("angle", dto.getAngle()));
        }
        if (StringUtils.isNotBlank(dto.getDimension())) {
            dto.getConditions().add(Restrict.eq("dimension", dto.getDimension()));
        }
        if (StringUtils.isNotBlank(dto.getClassification())) {
            dto.getConditions().add(Restrict.eq("classification", dto.getClassification()));
        }
        if (StringUtils.isNotBlank(dto.getProjectStage())) {
            dto.getConditions().add(Restrict.eq("projectStage", dto.getProjectStage()));
        }
        if (StringUtils.isNotBlank(dto.getFunction())) {
            dto.getConditions().add(Restrict.eq("function", dto.getFunction()));
        }
        if (StringUtils.isNotBlank(dto.getFrequency())) {
            dto.getConditions().add(Restrict.eq("frequency", dto.getFrequency()));
        }
        if (StringUtils.isNotBlank(dto.getTimeNode())) {
            dto.getConditions().add(Restrict.eq("timeNode", dto.getTimeNode()));
        }
    }

    private PositionWorkDetails transForm(PositionWorkDetails entity) throws SerException {
        String projectStage = entity.getProjectStage();
        projectStage = projectStage.substring(projectStage.length() - 1, projectStage.length());
        entity.setProjectStageNum(projectStage);

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet("https://system.issp.bjike.com:8080/featurelist/v1/getPurpose/"+ entity.getFunction() + "");//线上
//        HttpGet httpGet = new HttpGet("https://system.issp.bjike.com:8080/featurelist/v1/getPurpose/" + entity.getFunction() + "");//线上
        HttpGet httpGet = new HttpGet("http://localhost:51654/featurelist/v1/getPurpose/" +entity.getFunction() +"");//线下测试
        String userToken = RpcTransmit.getUserToken();
        httpGet.setHeader("userToken", userToken);
        RpcTransmit.transmitUserToken(userToken);
        ActResultOrgan resultOrgan = new ActResultOrgan();
        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
            resultOrgan = JSON.parseObject(EntityUtils.toString(response.getEntity()), ActResultOrgan.class);
            String purpose = (String) resultOrgan.getData();
            entity.setPurpose(purpose);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CloseableHttpClient closeableHttpClient1 = HttpClients.createDefault();
//        HttpGet httpGet1 = new HttpGet("https://system.issp.bjike.com:8080/featurelist/v1/getVersion/" + entity.getFunction() + "");//线上
        HttpGet httpGet1 = new HttpGet("http://localhost:51654/featurelist/v1/getVersion/" +entity.getFunction() +"");//线下测试
        httpGet1.setHeader("userToken", userToken);
        RpcTransmit.transmitUserToken(userToken);
        ActResultOrgan resultOrgan1 = new ActResultOrgan();
        try {
            CloseableHttpResponse response = closeableHttpClient1.execute(httpGet1);
            resultOrgan1 = JSON.parseObject(EntityUtils.toString(response.getEntity()), ActResultOrgan.class);
            String version = (String) resultOrgan1.getData();
            entity.setVersion(version);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }

    //获取内部项目个数
    private Long getProjectNum(String startTime, String endTime) throws SerException {
//        startTime = "2017-01-01";
//        endTime = "2017-09-09";
        CloseableHttpClient closeableHttpClient1 = HttpClients.createDefault();
        HttpGet httpGet1 = new HttpGet("https://businessproject.issp.bjike.com:8080/baseinfomanage/v1/getInterProject?startTime=" + startTime +"endTime=" + endTime);//线上
//        HttpGet httpGet1 = new HttpGet("http://localhost:51204/baseinfomanage/v1/getInterProject?startTime=" + startTime + "&endTime=" + endTime);//线下测试
        String userToken = RpcTransmit.getUserToken();
        httpGet1.setHeader("userToken", userToken);
        RpcTransmit.transmitUserToken(userToken);
        ActResultOrgan resultOrgan1 = new ActResultOrgan();
        try {
            CloseableHttpResponse response = closeableHttpClient1.execute(httpGet1);
            resultOrgan1 = JSON.parseObject(EntityUtils.toString(response.getEntity()), ActResultOrgan.class);
            Integer data = (Integer) resultOrgan1.getData();
            return Long.valueOf(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0l;
    }

    //查询岗位无任职人数
    private Long findNoEmployeesNum(String startTime, String endTime) throws SerException {
        String[] fields = new String[]{"noEmployeesNum"};
        StringBuilder sql = new StringBuilder(" select a.num - b.num as noEmployeesNum FROM ");
        sql.append(" ((SELECT count(d.user_id) as num from ");
        sql.append("  (SELECT id from ");
        sql.append(" organize_position_detail_user ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" WHERE modifyTime BETWEEN '" + startTime + "' ");
            sql.append(" and '" + endTime + "'");
        }
        sql.append(" ) c , ");
        sql.append(" organize_position_detail_user_table  d ");
        sql.append("  WHERE c.id =d.user_id) )a, ");
        sql.append(" (SELECT count(id) as num FROM organize_position_detail ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" WHERE modifyTime BETWEEN '" + startTime + "' ");
            sql.append(" and '" + endTime + "'");
        }
        sql.append(" ) b; ");
        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
        if (null != managerBOs && managerBOs.size() > 0) {
            List<Long> noEmployeesNums = managerBOs.stream().map(ManagerBO::getNoEmployeesNum).collect(Collectors.toList());
            Long noEmployeesNum = noEmployeesNums.get(0);
            if (noEmployeesNum < 0l) {
                noEmployeesNum = 0l - noEmployeesNum;
            }
            return noEmployeesNum;
        }
        return 0l;
    }

    private Long findPartjobNum(String startTime, String endTime) throws SerException {
        String[] fields = new String[]{"partjobNum"};
        StringBuilder sql = new StringBuilder(" select count(id) as partjobNum FROM organize_position_user_detail ");
        sql.append(" WHERE workStatus = 1 ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" AND modifyTime BETWEEN '" + startTime + "' ");
            sql.append(" AND '" + endTime + "'");
        }
        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
        if (null != managerBOs && managerBOs.size() > 0) {
            List<Long> partjobNums = managerBOs.stream().map(ManagerBO::getPartjobNum).collect(Collectors.toList());
            return partjobNums.get(0);
        }
        return 0l;
    }

    //查询已有岗位说明数个数
    private Long findJobDescriptionNum(String startTime, String endTime) throws SerException {
        String[] fields = new String[]{"jobDescriptionNum"};
        StringBuilder sql = new StringBuilder(" select count(id) as jobDescriptionNum FROM organize_position_detail ");
        sql.append(" WHERE book = 1 ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" AND modifyTime BETWEEN '" + startTime + "' ");
            sql.append(" AND '" + endTime + "'");
        }
        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
        if (null != managerBOs && managerBOs.size() > 0) {
            List<Long> jobDescriptionNums = managerBOs.stream().map(ManagerBO::getJobDescriptionNum).collect(Collectors.toList());
            return jobDescriptionNums.get(0);
        }
        return 0l;
    }

    //查询无岗位说明数个数
    private Long findNoJobNum(String startTime, String endTime) throws SerException {
        String[] fields = new String[]{"noJobNum"};
        StringBuilder sql = new StringBuilder(" select count(id) as noJobNum FROM organize_position_detail ");
        sql.append(" WHERE book = 0 ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" AND modifyTime BETWEEN '" + startTime + "' ");
            sql.append(" AND '" + endTime + "'");
        }
        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
        if (null != managerBOs && managerBOs.size() > 0) {
            List<Long> noJobNums = managerBOs.stream().map(ManagerBO::getNoJobNum).collect(Collectors.toList());
            return noJobNums.get(0);
        }
        return 0l;
    }

    //查询在职人员数
    private Long findEntryNum(String startTime, String endTime) throws SerException {
        String[] fields = new String[]{"entryNum"};
        StringBuilder sql = new StringBuilder(" select count(id) as entryNum FROM organize_position_detail_user ");
        sql.append(" WHERE staffStatus = 0 ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" AND modifyTime BETWEEN '" + startTime + "' ");
            sql.append(" AND '" + endTime + "'");
        }
        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
        if (null != managerBOs && managerBOs.size() > 0) {
            List<Long> entryNums = managerBOs.stream().map(ManagerBO::getEntryNum).collect(Collectors.toList());
            return entryNums.get(0);
        }
        return 0l;
    }

    //查询离职人员数
    private Long findDimissionNum(String startTime, String endTime) throws SerException {
        String[] fields = new String[]{"dimissionNum"};
        StringBuilder sql = new StringBuilder(" select count(id) as dimissionNum FROM organize_position_detail_user ");
        sql.append(" WHERE staffStatus = 1 ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" AND modifyTime BETWEEN '" + startTime + "' ");
            sql.append(" AND '" + endTime + "'");
        }
        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
        if (null != managerBOs && managerBOs.size() > 0) {
            List<Long> dimissionNums = managerBOs.stream().map(ManagerBO::getDimissionNum).collect(Collectors.toList());
            return dimissionNums.get(0);
        }
        return 0l;
    }

    //查询待离职人员数
    private Long findWaitDimissionNum(String startTime, String endTime) throws SerException {
        String[] fields = new String[]{"waitDimissionNum"};
        StringBuilder sql = new StringBuilder(" select count(id) as waitDimissionNum FROM organize_position_detail_user ");
        sql.append(" WHERE staffStatus = 2 ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" AND modifyTime BETWEEN '" + startTime + "' ");
            sql.append(" AND '" + endTime + "'");
        }
        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
        if (null != managerBOs && managerBOs.size() > 0) {
            List<Long> waitDimissionNums = managerBOs.stream().map(ManagerBO::getWaitDimissionNum).collect(Collectors.toList());
            return waitDimissionNums.get(0);
        }
        return 0l;
    }

    private ManagerBO setManagerBO(String startTime, String endTime) throws SerException {
        ManagerBO managerBO = new ManagerBO();
        // TODO: 17-9-13 商务合同管理－预估金额
        managerBO.setArea(departmentDetailSer.getAreaNum(startTime, endTime));
        managerBO.setDepartment(departmentDetailSer.getDepartmentNum(startTime, endTime));
        managerBO.setProjectNum(getProjectNum(startTime, endTime));
        managerBO.setPositionNum(positionDetailSer.getPositionNum(startTime, endTime));
        managerBO.setNoEmployeesNum(findNoEmployeesNum(startTime, endTime));
        managerBO.setPartjobNum(findPartjobNum(startTime, endTime));
        managerBO.setJobDescriptionNum(findJobDescriptionNum(startTime, endTime));
        managerBO.setNoJobNum(findNoJobNum(startTime, endTime));
        // TODO: 17-9-13  被调动人数
        // TODO: 17-9-13 通过（轮岗人数）
        // TODO: 17-9-13 待入职人数
        managerBO.setEntryNum(findEntryNum(startTime, endTime));
        managerBO.setDimissionNum(findDimissionNum(startTime, endTime));
        managerBO.setWaitDimissionNum(findWaitDimissionNum(startTime, endTime));
        return managerBO;
    }

    //获取某一个月的第一天
    private String findFirstDayOfMonth(String month) throws SerException {
        Date date = null;
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = fmt.parse(month);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDayOfMonth = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = sdf.format(firstDayOfMonth);
        return startTime;
    }

    //获取某一个月的最后一天
    private String findEndDayOfMonth(String month) throws SerException {
        Date date = null;
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = fmt.parse(month);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDayOfMonth = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String endTime = sdf.format(lastDayOfMonth);
        return endTime;
    }


}
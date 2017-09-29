package com.bjike.goddess.businesscommission.service;

import com.bjike.goddess.businesscommission.bo.ProportionBO;
import com.bjike.goddess.businesscommission.bo.ProportionRatioBO;
import com.bjike.goddess.businesscommission.dto.ProportionConfirmDTO;
import com.bjike.goddess.businesscommission.dto.ProportionDTO;
import com.bjike.goddess.businesscommission.dto.ProportionRatioDTO;
import com.bjike.goddess.businesscommission.entity.Proportion;
import com.bjike.goddess.businesscommission.entity.ProportionConfirm;
import com.bjike.goddess.businesscommission.entity.ProportionRatio;
import com.bjike.goddess.businesscommission.enums.GuideAddrStatus;
import com.bjike.goddess.businesscommission.excel.ProportionExcel;
import com.bjike.goddess.businesscommission.excel.ProportionTemplateExcel;
import com.bjike.goddess.businesscommission.excel.SonPermissionObject;
import com.bjike.goddess.businesscommission.to.GuidePermissionTO;
import com.bjike.goddess.businesscommission.to.ProportionExcelTO;
import com.bjike.goddess.businesscommission.to.ProportionRatioTO;
import com.bjike.goddess.businesscommission.to.ProportionTO;
import com.bjike.goddess.businessproject.api.SiginManageAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 业务提成分配比例表业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-23 11:29 ]
 * @Description: [ 业务提成分配比例表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businesscommissionSerCache")
@Service
public class ProportionSerImpl extends ServiceImpl<Proportion, ProportionDTO> implements ProportionSer {
    @Autowired
    private ProportionRatioSer proportionRatioSer;
    @Autowired
    private SiginManageAPI siginManageAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ProportionConfirmSer proportionConfirmSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private QuotaSer quotaSer;

    @Override
    public Long getTotal(ProportionDTO dto) throws SerException {
        searchCondition(dto);
        return super.count(dto);
    }

    @Override
    public ProportionBO getOneById(String id) throws SerException {
        Proportion entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据为空");
        }
        ProportionBO proportionBO = BeanTransform.copyProperties(entity, ProportionBO.class, false);
        ProportionRatioDTO proportionRatioDTO = new ProportionRatioDTO();
        proportionRatioDTO.getConditions().add(Restrict.eq("proportionId", id));
        List<ProportionRatio> proportionRatios = proportionRatioSer.findByCis(proportionRatioDTO);
        List<ProportionRatioBO> proportionRatioBOs = BeanTransform.copyProperties(proportionRatios, ProportionRatioBO.class);
        proportionBO.setProportionRatioBOs(proportionRatioBOs);
        return proportionBO;
    }

    @Override
    public List<ProportionBO> listProportion(ProportionDTO dto) throws SerException {
        searchCondition(dto);
        List<Proportion> proportions = super.findByPage(dto);
        List<ProportionBO> proportionBOs = BeanTransform.copyProperties(proportions, ProportionBO.class, false);
        for (ProportionBO proportionBO : proportionBOs) {
            ProportionRatioDTO proportionRatioDTO = new ProportionRatioDTO();
            proportionRatioDTO.getConditions().add(Restrict.eq("proportionId", proportionBO.getId()));
            List<ProportionRatio> proportionRatios = proportionRatioSer.findByCis(proportionRatioDTO);
            List<ProportionRatioBO> proportionRatioBOs = BeanTransform.copyProperties(proportionRatios, ProportionRatioBO.class);
            proportionBO.setProportionRatioBOs(proportionRatioBOs);
        }
        return proportionBOs;
    }

    @Transactional
    @Override
    public void addProportion(ProportionTO to) throws SerException {
        if (isExist(to.getProjectName())) {
            throw new SerException("该项目名称已存在");
        }
        Proportion entity = BeanTransform.copyProperties(to, Proportion.class, true);
        entity.setTime(LocalDateTime.now());
//        SiginManageBO siginManageBO = siginManageAPI.findByProject(entity.getProjectName());
//        entity.setArea(siginManageBO.getArea());
//        entity.setDepartment(siginManageBO.getProjectGroup());
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);

        entity.setNotConfirmed(to.getConsultants());
        entity = super.save(entity);

        //参与协商人
        List<ProportionConfirm> proportionConfirms = getConfirm(entity);
        proportionRatioSer.save(setFactor(to, entity));
    }

    @Transactional
    @Override
    public void editProportion(ProportionTO to) throws SerException {
        if (isExist(to.getProjectName())) {
            throw new SerException("该项目名称已存在");
        }
        Proportion entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据为空");
        }
        BeanTransform.copyProperties(to, entity);

        //参与协商人
        ProportionConfirmDTO proportionConfirmDTO = new ProportionConfirmDTO();
        proportionConfirmDTO.getConditions().add(Restrict.eq("proportionId", entity.getId()));
//        proportionConfirmSer.remove(proportionConfirmSer.findByCis(proportionConfirmDTO));
        getConfirm(entity);
        List<ProportionConfirm> proportionConfirms = proportionConfirmSer.findByCis(proportionConfirmDTO);
        //添加时已有的人员
        List<String> names = proportionConfirms.stream().map(ProportionConfirm::getConsultants).distinct().collect(Collectors.toList());

        String[] consultants = entity.getConsultants().split(",");
        List<String> newName = new ArrayList<>(0);
        for (String consultant : consultants) {
            if (names.contains(consultant)) {
                proportionConfirms.stream().filter(obj -> consultant.equals(obj.getConsultants())).forEach(obj -> {
                    obj.setTar(true);
                });
            } else {
                newName.add(consultant);
            }
        }
        //删除更新后不存在的参与协商人
        proportionConfirmSer.remove(proportionConfirms.stream().filter(obj -> obj.getTar() == false).collect(Collectors.toList()));
        //增加更新后新增的协商人
        List<ProportionConfirm> newProportionConfirmList = new ArrayList<>(0);
        for (String name : newName) {
            ProportionConfirm proportionConfirm = new ProportionConfirm();
            proportionConfirm.setProportionId(entity.getId());
            proportionConfirm.setConfirm(false);
            proportionConfirm.setTar(false);
            proportionConfirm.setConsultants(name);
            newProportionConfirmList.add(proportionConfirm);
        }
        proportionConfirmSer.save(newProportionConfirmList);

        List<String> consultantList = new ArrayList<>(0);
        List<ProportionConfirm> proportionConfirmList = new ArrayList<>(0);
        if (null != consultants && consultants.length > 0) {
            Arrays.stream(consultants).forEach(obj -> {
                ProportionConfirm proportionConfirm = new ProportionConfirm();
                proportionConfirm.setConsultants(obj.toString());
                consultantList.add(obj.toString());
                proportionConfirms.add(proportionConfirm);
            });

            ProportionRatioDTO proportionRatioDTO = new ProportionRatioDTO();
            proportionRatioDTO.getConditions().add(Restrict.eq("proportionId", entity.getId()));
            List<ProportionRatio> proportionRatios = new ArrayList<>(0);

            for (ProportionRatioTO proportionRatioTO : to.getProportionRatioTOs()) {
                Double messageProportion = 0d;
                Double businessProportion = 0d;
                Double talkProportion = 0d;
                Double maintainProportion = 0d;

                ProportionRatio proportionRatio = proportionRatioSer.findById(proportionRatioTO.getId());
                BeanTransform.copyProperties(proportionRatioTO, proportionRatio);
                if ("节点影响因素权重".equals(proportionRatio.getFactors())) {
                    proportionRatio.setTypeFactors(null);
                    proportionRatio.setSurplusProportion(100 - proportionRatio.getBusinessProportion() - proportionRatio.getMaintainProportion() - proportionRatio.getMessageProportion() - proportionRatio.getTalkProportion());
                    messageProportion = proportionRatio.getMessageProportion();
                    businessProportion = proportionRatio.getBusinessProportion();
                    talkProportion = proportionRatio.getTalkProportion();
                    maintainProportion = proportionRatio.getMaintainProportion();
                    if (messageProportion + businessProportion + talkProportion + maintainProportion > 100) {
                        throw new SerException("信息提供人+介绍关系揽接+出面接洽+维护加起来的占比不得超过100");
                    }
                    proportionRatios.add(proportionRatio);
                } else {
                    if (proportionRatio.getTypeFactors() > 100) {
                        throw new SerException("类型影响因素权重占比不得超过100");
                    }
                    proportionRatio.setMessageProportion((messageProportion / 100) * (proportionRatio.getTypeFactors() / 100));
                    proportionRatio.setBusinessProportion((businessProportion / 100) * (proportionRatio.getTypeFactors() / 100));
                    proportionRatio.setTalkProportion((talkProportion / 100) * (proportionRatio.getTypeFactors() / 100));
                    proportionRatio.setMaintainProportion((maintainProportion / 100) * (proportionRatio.getTypeFactors() / 100));
                    proportionRatio.setSurplusProportion(100 - proportionRatio.getBusinessProportion() - proportionRatio.getMaintainProportion() - proportionRatio.getMessageProportion() - proportionRatio.getTalkProportion());
                    proportionRatios.add(proportionRatio);
                }
            }
            entity.setModifyTime(LocalDateTime.now());

            super.update(entity);
            proportionRatios.stream().forEach(obj -> {
                obj.setModifyTime(LocalDateTime.now());
            });
            proportionRatioSer.update(proportionRatios);

        }
    }

    @Transactional
    @Override
    public void deleteProportion(String id) throws SerException {
        Proportion entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据对象不能为空");
        }
        ProportionRatioDTO proportionRatioDTO = new ProportionRatioDTO();
        proportionRatioDTO.getConditions().add(Restrict.eq("proportionId", entity.getId()));
        List<ProportionRatio> proportionRatios = proportionRatioSer.findByCis(proportionRatioDTO);
        ProportionConfirmDTO proportionConfirmDTO = new ProportionConfirmDTO();
        proportionConfirmDTO.getConditions().add(Restrict.eq("proportionId", entity.getId()));
        List<ProportionConfirm> proportionConfirms = proportionConfirmSer.findByCis(proportionConfirmDTO);
        proportionConfirmSer.remove(proportionConfirms);
        proportionRatioSer.remove(proportionRatios);
        super.remove(entity);
    }

    @Override
    public void confirm(Boolean tar) throws SerException {
        UserBO userBO = userAPI.currentUser();
        List<ProportionConfirm> proportionConfirms = proportionConfirmSer.findAll();
        for (ProportionConfirm proportionConfirm : proportionConfirms) {
            if (userBO.getUsername().equals(proportionConfirm.getConsultants())) {
                proportionConfirm.setConfirm(true);
                proportionConfirm.setModifyTime(LocalDateTime.now());
            }
        }
        proportionConfirmSer.update(proportionConfirms);
        List<Proportion> proportionList = new ArrayList<>(0);
        //更新确认人
        List<Proportion> proportions = super.findAll();
        for (Proportion entity : proportions) {
            String name = "";
            ProportionConfirmDTO proportionConfirmDTO = new ProportionConfirmDTO();
            proportionConfirmDTO.getConditions().add(Restrict.eq("proportionId", entity.getId()));
            proportionConfirmDTO.getConditions().add(Restrict.eq("confirm", 1));
            List<ProportionConfirm> proportionConfirms1 = proportionConfirmSer.findByCis(proportionConfirmDTO);
            if (null != proportionConfirms1 && proportionConfirms1.size() > 0) {
                List<String> list = proportionConfirms1.stream().map(ProportionConfirm::getConsultants).distinct().collect(Collectors.toList());
                String[] names = (String[]) list.toArray(new String[list.size()]);
                name = StringUtils.join(names, ",");
            }
            entity.setConfirmed(name);
            entity.setModifyTime(LocalDateTime.now());
            proportionList.add(entity);
        }
        //更新未确认人
        for (Proportion entity : proportions) {
            String name = "";
            ProportionConfirmDTO proportionConfirmDTO = new ProportionConfirmDTO();
            proportionConfirmDTO.getConditions().add(Restrict.eq("proportionId", entity.getId()));
            proportionConfirmDTO.getConditions().add(Restrict.eq("confirm", 0));
            List<ProportionConfirm> proportionConfirms1 = proportionConfirmSer.findByCis(proportionConfirmDTO);
            if (null != proportionConfirms1 && proportionConfirms1.size() > 0) {
                List<String> list = proportionConfirms1.stream().map(ProportionConfirm::getConsultants).distinct().collect(Collectors.toList());
                String[] names = (String[]) list.toArray(new String[list.size()]);
                name = StringUtils.join(names, ",");
            }
            entity.setNotConfirmed(name);
            entity.setModifyTime(LocalDateTime.now());
            proportionList.add(entity);
        }
        super.update(proportionList);
    }

    /**
     * 核对查看权限（部门级别）
     */

    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }


    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("proportion");
        obj.setDescribesion("业务提成分配比例");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = quotaSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("quota");
        obj.setDescribesion("业务提成权重分配");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);
        return list;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Transactional
    @Override
    public void importExcel(List<ProportionExcelTO> tos) throws SerException {
        if (null != tos && tos.size() > 0) {
            List<String> hasProjects = new ArrayList<>(0);
            List<String> newProjects = new ArrayList<>(0);

            newProjects = tos.stream().map(ProportionExcelTO::getProjectName).collect(Collectors.toList());
            //判断传入的数据有没有相同的项目名
//            int num = newProjects.size();
//            if (num != newProjects.stream().distinct().collect(Collectors.toList()).size()) {
//                throw new SerException("传入的数据中不能含有相同的项目名");
//            }


            List<Proportion> proportions = super.findAll();
            if (null != proportions && proportions.size() > 0) {
                hasProjects = proportions.stream().map(Proportion::getProjectName).distinct().collect(Collectors.toList());
                for (String name : newProjects) {
                    if (hasProjects.contains(name)) {
                        throw new SerException("该项目名称已存在");
                    }
                }
            }
//            String project = "";
            Proportion entity1 = null;
            for (ProportionExcelTO to : tos) {
                Proportion entity = BeanTransform.copyProperties(to, Proportion.class);
                entity.setTime(LocalDateTime.now());
                if (null == entity1 || entity1 != null && !entity1.getProjectName().equals(entity.getProjectName())) {
                    entity = super.save(entity);
                }else{
                    entity = entity1;
                }

                ProportionRatio proportionRatio = BeanTransform.copyProperties(to, ProportionRatio.class);
                proportionRatio.setProportionId(entity.getId());
                proportionRatioSer.save(proportionRatio);

                String[] confirmeds = null;
                String[] consultants = to.getConsultants().split(",");
                if (StringUtils.isNotBlank(to.getConfirmed())) {
                    confirmeds = to.getConfirmed().split(",");
                }
                List<String> consultantList = Arrays.stream(consultants).collect(Collectors.toList());
                List<String> confirmedsList = new ArrayList<>(0);
                if (null != confirmeds && confirmeds.length > 0) {
                    confirmedsList = Arrays.stream(confirmeds).collect(Collectors.toList());
                }
                for (String str : consultantList) {
                    ProportionConfirm proportionConfirm = new ProportionConfirm();
                    proportionConfirm.setConsultants(str);
                    proportionConfirm.setProportionId(entity.getId());

                    if (null != confirmedsList && confirmedsList.size() > 0 && confirmedsList.contains(str)) {
                        proportionConfirm.setConfirm(true);
                    } else {
                        proportionConfirm.setConfirm(false);
                    }

                    proportionConfirm.setTar(false);
                    proportionConfirmSer.save(proportionConfirm);
                }
                entity1 = entity;
            }
            List<Proportion> proportionList = super.findAll();
            int num0 = proportionList.stream().map(Proportion::getProjectName).collect(Collectors.toList()).size();
            int num1 = proportionList.stream().map(Proportion::getProjectName).distinct().collect(Collectors.toList()).size();
            if (num0 != num1) {
                throw new SerException("传入的数据中不能含有相同的项目名");
            }
        }
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<ProportionTemplateExcel> quotaTemplateExcels = new ArrayList<>();

        ProportionTemplateExcel excel = new ProportionTemplateExcel();
        excel.setArea("广州");
        excel.setDepartment("研发部");
        excel.setProjectName("项目1");
        excel.setFactors("一手包揽");
        excel.setTypeFactors(0.5);
        excel.setMessageProportion(0.4);
        excel.setBusinessProportion(0.1);
        excel.setTalkProportion(0.1);
        excel.setMaintainProportion(0.1);
        excel.setSurplusProportion(0.1);
        excel.setConsultants("人员1,人员2,人员3,人员4");
        excel.setConfirm("否");
        excel.setConfirmed("人员1,人员4");
        excel.setNotConfirmed("人员2,人员3");
        quotaTemplateExcels.add(excel);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(quotaTemplateExcels, exce);
        return bytes;
    }

    @Override
    public byte[] exportExcel(ProportionDTO dto) throws SerException {
        List<Integer> maxList = new ArrayList<>(0);
        if (StringUtils.isNotBlank(dto.getProjectName())) {
            dto.getConditions().add(Restrict.eq("projectName", dto.getProjectName()));
        }

        List<Proportion> list = super.findByCis(dto);
        List<ProportionRatio> proportionRatios = new ArrayList<>(0);
        for (Proportion entity : list) {
            ProportionRatioDTO proportionRatioDTO = new ProportionRatioDTO();
            proportionRatioDTO.getConditions().add(Restrict.eq("proportionId", entity.getId()));
            proportionRatios = proportionRatioSer.findByCis(proportionRatioDTO);
            maxList.add(proportionRatios.size());
        }

        List<ProportionExcel> proportionExcelList = new ArrayList<>();

        for (Proportion entity : list) {
            int i = 0;
            ProportionExcel proportionExcel = BeanTransform.copyProperties(entity, ProportionExcel.class, false, "confirm");
            if (entity.getConfirm()) {
                proportionExcel.setConfirm("是");
            } else {
                proportionExcel.setConfirm("否");
            }
            for (ProportionRatio proportionRatio : proportionRatios) {
                ProportionExcel proportionExcel1 = new ProportionExcel();
                BeanTransform.copyProperties(proportionRatio, proportionExcel1);
                BeanTransform.copyProperties(proportionExcel, proportionExcel1);
                proportionExcelList.add(proportionExcel1);
            }
        }

//        Excel excel = new Excel(0, 2);
//        byte[] bytes = ExcelUtil.clazzToExcel(proportionExcelList, excel);
//        return bytes;

        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(proportionExcelList, excel);
        XSSFWorkbook wb = null;
        ByteArrayOutputStream os = null;

        try {
            InputStream is = new ByteArrayInputStream(bytes);
            wb = new XSSFWorkbook(is);
            XSSFSheet sheet;
            sheet = wb.getSheetAt(0);
//            XSSFCellStyle style = wb.createCellStyle();
//            style.setAlignment(HorizontalAlignment.FILL);
            int rowSize = maxList.size();
            List<Field> fields = ClazzUtils.getFields(ProportionExcel.class); //获得列表对象属性
            List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);
            for (int j = 0; j < rowSize; j++) {
                int mergeRowCount = maxList.get(j);
                int firstRow = mergeRowCount * j + 1;
                int lastRow = mergeRowCount * j == 0 ? mergeRowCount : mergeRowCount * (j + 1);
                for (int i = 0; i < 4; i++) {
                    sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, i, i));
                }
                for (int i = 11; i < 15; i++) {
                    sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, i, i));
                }
            }
            os = new ByteArrayOutputStream();
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return os.toByteArray();
//        return wb.
    }

    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    public void searchCondition(ProportionDTO dto) throws SerException {
        /**
         * 地区
         */
        if (dto.getArea() != null) {
            dto.getConditions().add(Restrict.eq("area", dto.getArea()));
        }
        /**
         * 部门
         */
        if (dto.getDepartment() != null) {
            dto.getConditions().add(Restrict.eq("department", dto.getDepartment()));
        }
        /**
         * 项目名称
         */
        if (StringUtils.isNotBlank(dto.getProjectName())) {
            dto.getConditions().add(Restrict.like("projectName", dto.getProjectName()));
        }
    }

    private ProportionBO transFrom(Proportion entity, ProportionRatio proportionRatio) throws SerException {
        ProportionBO proportionBO = BeanTransform.copyProperties(entity, ProportionBO.class, false);
        ProportionRatioBO proportionRatioBO = BeanTransform.copyProperties(proportionRatio, ProportionRatioBO.class);
        List<ProportionRatioBO> proportionRatioBOs = new ArrayList<>(0);
        proportionRatioBOs.add(proportionRatioBO);
        proportionBO.setProportionRatioBOs(proportionRatioBOs);
        return proportionBO;
    }

    //参与协商人
    private List<ProportionConfirm> getConfirm(Proportion entity) throws SerException {
        String[] consultants = entity.getConsultants().split(",");
        List<String> consultantList = new ArrayList<>(0);
        List<ProportionConfirm> proportionConfirms = new ArrayList<>(0);
        if (null != consultants && consultants.length > 0) {
            Arrays.stream(consultants).forEach(obj -> {
                ProportionConfirm proportionConfirm = new ProportionConfirm();
                proportionConfirm.setConsultants(obj.toString());
                consultantList.add(obj.toString());
                proportionConfirms.add(proportionConfirm);
            });
            proportionConfirms.stream().forEach(obj -> {
                obj.setProportionId(entity.getId());
                obj.setConfirm(false);
            });


//            if (null != proportionConfirms && proportionConfirms.size() > 0) {
//                if (consultantList.contains(userBO.getUsername()) && to.getConfirm()) {
//                    proportionConfirms.stream().filter(obj -> userBO.getUsername().equals(obj.getConsultants())).collect(Collectors.toList()).get(0).setConfirm(true);
//                } else if (consultantList.contains(userBO.getUsername()) && !to.getConfirm()) {
//                    proportionConfirms.stream().filter(obj -> userBO.getUsername().equals(obj.getConsultants())).collect(Collectors.toList()).get(0).setConfirm(false);
//                }
//            }
        }
        proportionConfirmSer.save(proportionConfirms);
        return proportionConfirms;
    }

    //影响因素设置
    private List<ProportionRatio> setFactor(ProportionTO to, Proportion entity) throws SerException {
        to.setId(entity.getId());
        List<ProportionRatio> proportionRatios = new ArrayList<>(0);

        //节点影响因素权重
        List<ProportionRatioTO> proportionRatioTOs = to.getProportionRatioTOs().stream().filter(obj -> "节点影响因素权重".equals(obj.getFactors())).distinct().collect(Collectors.toList());
        if (null == proportionRatioTOs || proportionRatioTOs.size() <= 0) {
            throw new SerException("影响因素为节点影响因素权重的数据不能为空");
        }

        //非节点影响因素权重
        List<ProportionRatioTO> proportionRatioTOs1 = to.getProportionRatioTOs().stream().filter(obj -> !"节点影响因素权重".equals(obj.getFactors())).distinct().collect(Collectors.toList());
        if (null != proportionRatioTOs1 && proportionRatioTOs1.size() > 0) {
            for (ProportionRatioTO obj : proportionRatioTOs1) {
                if (obj.getTypeFactors() > 100) {
                    throw new SerException("类型影响因素权重占比不得超过100");
                }
                ProportionRatio proportionRatio = new ProportionRatio();
                proportionRatio.setProportionId(to.getId());
                BeanTransform.copyProperties(obj, proportionRatio);
                proportionRatio.setMessageProportion((proportionRatio.getTypeFactors() / 100) * (proportionRatioTOs.get(0).getMessageProportion() / 100));
                proportionRatio.setBusinessProportion((proportionRatio.getTypeFactors() / 100) * (proportionRatioTOs.get(0).getBusinessProportion() / 100));
                proportionRatio.setTalkProportion((proportionRatio.getTypeFactors() / 100) * (proportionRatioTOs.get(0).getTalkProportion() / 100));
                proportionRatio.setMaintainProportion((proportionRatio.getTypeFactors() / 100) * (proportionRatioTOs.get(0).getMaintainProportion() / 100));
                proportionRatio.setSurplusProportion(100 - proportionRatio.getBusinessProportion() - proportionRatio.getMaintainProportion() - proportionRatio.getMessageProportion() - proportionRatio.getTalkProportion());
                proportionRatios.add(proportionRatio);
            }
        }
        List<ProportionRatio> proportionRatioList = BeanTransform.copyProperties(proportionRatioTOs, ProportionRatio.class);
        ProportionRatio proportionRatio = proportionRatioList.get(0);
        Double messageProportion = proportionRatio.getMessageProportion();
        Double businessProportion = proportionRatio.getBusinessProportion();
        Double talkProportion = proportionRatio.getTalkProportion();
        Double maintainProportion = proportionRatio.getMaintainProportion();
        if (messageProportion + businessProportion + talkProportion + maintainProportion > 100) {
            throw new SerException("信息提供人+介绍关系揽接+出面接洽+维护加起来的占比不得超过100");
        }
        proportionRatio.setProportionId(entity.getId());
        proportionRatio.setTypeFactors(null);
        proportionRatio.setSurplusProportion(100 - proportionRatio.getBusinessProportion() - proportionRatio.getMaintainProportion() - proportionRatio.getMessageProportion() - proportionRatio.getTalkProportion());
        proportionRatios.add(proportionRatio);
        return proportionRatios;
    }

    //判断数据库中是否已有该项目名称
    private Boolean isExist(String project) throws SerException {
        Boolean tar = false;
        List<Proportion> proportions = super.findAll();
        if (null != proportions && proportions.size() > 0) {
            List<String> list = proportions.stream().map(Proportion::getProjectName).distinct().collect(Collectors.toList());
            if (list.contains(project)) {
                tar = true;
            }
        }
        return tar;
    }

}
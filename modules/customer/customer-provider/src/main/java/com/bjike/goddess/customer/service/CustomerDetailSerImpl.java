package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.customer.bo.CusFamilyMemberBO;
import com.bjike.goddess.customer.bo.CustomerBaseInfoBO;
import com.bjike.goddess.customer.bo.CustomerDetailBO;
import com.bjike.goddess.customer.bo.CustomerLevelBO;
import com.bjike.goddess.customer.dto.CusFamilyMemberDTO;
import com.bjike.goddess.customer.dto.CustomerBaseInfoDTO;
import com.bjike.goddess.customer.dto.CustomerDetailDTO;
import com.bjike.goddess.customer.entity.CusFamilyMember;
import com.bjike.goddess.customer.entity.CustomerBaseInfo;
import com.bjike.goddess.customer.entity.CustomerDetail;
import com.bjike.goddess.customer.enums.*;
import com.bjike.goddess.customer.excel.CustomerDetailExport;
import com.bjike.goddess.customer.to.CusFamilyMemberTO;
import com.bjike.goddess.customer.to.CustomerDetailTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户详细信息业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T09:41:13.476 ]
 * @Description: [ 客户详细信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class CustomerDetailSerImpl extends ServiceImpl<CustomerDetail, CustomerDetailDTO> implements CustomerDetailSer {

    @Autowired
    private CustomerBaseInfoSer customerBaseInfoAPI;
    @Autowired
    private CusFamilyMemberSer cusFamilyMemberAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;


    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission(flagId);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission(flagId);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission(flagId);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission(flagId);
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity("1");
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity("4");
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity("1");
                break;
            case ADD:
                flag = guideAddIdentity("4");
                break;
            case EDIT:
                flag = guideAddIdentity("4");
                break;
            case AUDIT:
                flag = guideAddIdentity("4");
                break;
            case DELETE:
                flag = guideAddIdentity("4");
                break;
            case EXPORT:
                flag = guideAddIdentity("4");
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }


    @Override
    public Long countCustomerDetail(CustomerDetailDTO customerDetailDTO) throws SerException {
        Long count = super.count(customerDetailDTO);
        return count;
    }

    @Override
    public List<CustomerDetailBO> listCustomerDetail(CustomerDetailDTO customerDetailDTO) throws SerException {
        checkSeeIdentity("1");

        customerDetailDTO.getSorts().add("sortWord=desc");
        List<CustomerDetail> list = super.findByCis(customerDetailDTO, true);
        List<CustomerDetailBO> customerDetailBOArrayList = new ArrayList<>();
        for (CustomerDetail str : list) {
            CustomerLevelBO customerLevelBO = BeanTransform.copyProperties(str.getCustomerBaseInfo().getCustomerLevel(), CustomerLevelBO.class);
            CustomerBaseInfoBO customerBaseInfoBO = BeanTransform.copyProperties(str.getCustomerBaseInfo(), CustomerBaseInfoBO.class);
            customerBaseInfoBO.setCustomerLevelBO(customerLevelBO);
            CustomerDetailBO customerDetailBO = BeanTransform.copyProperties(str, CustomerDetailBO.class);
            customerDetailBO.setCustomerBaseInfoBO(customerBaseInfoBO);

            //获取家庭信息
            CusFamilyMemberDTO familyMemberDTO = new CusFamilyMemberDTO();
            familyMemberDTO.getConditions().add(Restrict.eq("customerDetail.id", str.getId()));
            List<CusFamilyMember> familyMembers = cusFamilyMemberAPI.findByCis(familyMemberDTO);
            List<CusFamilyMemberBO> cusFamilyMemberBOList = BeanTransform.copyProperties(familyMembers, CusFamilyMemberBO.class);
            customerDetailBO.setCusFamilyMemberBOList(cusFamilyMemberBOList);
            customerDetailBOArrayList.add(customerDetailBO);
        }
        List<CustomerDetailBO> boList = BeanTransform.copyProperties(customerDetailBOArrayList, CustomerDetailBO.class);

//        if( boList != null && boList.size()>0 ){
//            Collections.sort(boList,new Comparator<CustomerDetailBO>(){
//                @Override
//                public int compare(CustomerDetailBO o1, CustomerDetailBO o2) {
//                    int o1Num = Integer.parseInt(o1.getCustomerNum().substring(4,o1.getCustomerNum().length()));
//                    int o2Num = Integer.parseInt(o2.getCustomerNum().substring(4,o2.getCustomerNum().length()));
//                    if( o1Num < o2Num ){
//                        return 1;
//                    }else if( o1Num == o2Num ){
//                        return 0;
//                    }else {
//                        return -1;
//                    }
//                }
//
//            });
//        }

        return boList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerDetailBO addCustomerDetail(CustomerDetailTO customerDetailTO) throws SerException {
        //商务模块添加权限
//        checkAddIdentity("4");
        if (StringUtils.isBlank(customerDetailTO.getCustomerNum())) {
            throw new SerException("客户编号不能为空");
        }
        String baseInfoNum = customerDetailTO.getCustomerNum();
        CustomerBaseInfoDTO baseInfoDTO = new CustomerBaseInfoDTO();
        baseInfoDTO.getConditions().add(Restrict.eq("customerNum", baseInfoNum));
        CustomerBaseInfo baseInfo = customerBaseInfoAPI.findOne(baseInfoDTO);

        CustomerDetail customerDetail = BeanTransform.copyProperties(customerDetailTO, CustomerDetail.class, true);
        customerDetail.setCreateTime(LocalDateTime.now());
        customerDetail.setCustomerBaseInfo(baseInfo);
        customerDetail.setSortWord(Double.parseDouble(baseInfoNum.substring(4, baseInfoNum.length())));
        customerDetail = super.save(customerDetail);

        //添加家庭信息4条家庭信息
        if (customerDetailTO.getCusFamilyMemberTOList() != null && customerDetailTO.getCusFamilyMemberTOList().size() > 0) {
//            CusFamilyMemberDTO cusFamilyMemberDTO = new CusFamilyMemberDTO();
//            cusFamilyMemberDTO.getConditions().add(Restrict.eq("customerNum",baseInfoNum));
//            Long countFamily =  cusFamilyMemberAPI.count( cusFamilyMemberDTO );
//
//            if( countFamily < 4 ){
            List<CusFamilyMemberTO> familyMemberTOList = customerDetailTO.getCusFamilyMemberTOList();
            List<CusFamilyMember> cusFamilyMemberList = BeanTransform.copyProperties(familyMemberTOList, CusFamilyMember.class, true);
            for (CusFamilyMember temp : cusFamilyMemberList) {
                temp.setCreateTime(LocalDateTime.now());
                temp.setCustomerDetail(customerDetail);
            }
            cusFamilyMemberAPI.save(cusFamilyMemberList);
//            }
        }

        return BeanTransform.copyProperties(customerDetail, CustomerDetailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerDetailBO editCustomerDetail(CustomerDetailTO customerDetailTO) throws SerException {
        //商务模块编辑权限
        checkAddIdentity("4");

        //先查一遍详细
        CustomerDetailDTO dto = new CustomerDetailDTO();
        dto.getConditions().add(Restrict.eq("customerNum", customerDetailTO.getCustomerNum()));
        CustomerDetail cusDetail = super.findOne(dto);

        CustomerDetail customerDetail = BeanTransform.copyProperties(customerDetailTO, CustomerDetail.class, true);
        BeanUtils.copyProperties(customerDetail, cusDetail, "customerNum", "id", "createTime", "customerBaseInfo");
        cusDetail.setModifyTime(LocalDateTime.now());

        super.update(cusDetail);

        //修改家庭信息  先删除再重新添加
        CusFamilyMemberDTO cusFamilyMemberDTO = new CusFamilyMemberDTO();
        cusFamilyMemberDTO.getConditions().add(Restrict.eq("customerDetail.id", cusDetail.getId()));
        List<CusFamilyMember> cfamilyList = cusFamilyMemberAPI.findByCis(cusFamilyMemberDTO);
        if (cfamilyList != null && cfamilyList.size() > 0) {
            cusFamilyMemberAPI.remove(cfamilyList);
        }
        //重新添加家庭信息
        List<CusFamilyMemberTO> familyMemberTOList = customerDetailTO.getCusFamilyMemberTOList();
        if (familyMemberTOList != null && familyMemberTOList.size() > 0) {
            List<CusFamilyMember> cusFamilyMemberList = BeanTransform.copyProperties(familyMemberTOList, CusFamilyMember.class, true);
            for (CusFamilyMember temp : cusFamilyMemberList) {
                temp.setId(null);
                temp.setCreateTime(LocalDateTime.now());
                temp.setCustomerDetail(cusDetail);
            }
            cusFamilyMemberAPI.save(cusFamilyMemberList);
        }


        return BeanTransform.copyProperties(cusDetail, CustomerDetailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCustomerDetail(String id) throws SerException {
        //商务模块删除权限
        checkAddIdentity("4");

        CustomerDetail customerDetail = super.findById(id);

        CusFamilyMemberDTO cusFamilyMemberDTO = new CusFamilyMemberDTO();
        cusFamilyMemberDTO.getConditions().add(Restrict.eq("customerDetail.id", customerDetail.getId()));
        List<CusFamilyMember> cfamilyList = cusFamilyMemberAPI.findByCis(cusFamilyMemberDTO);
        if (cfamilyList != null && cfamilyList.size() > 0) {
            //先删除家庭成员
            cusFamilyMemberAPI.remove(cfamilyList);
        }
        try {
            super.remove(customerDetail);
        } catch (SerException e) {
            e.printStackTrace();
        }
    }


    @Override
    public CustomerDetailBO getCustomerDetailById(String id) throws SerException {
        CustomerDetail customerDetail = super.findById(id);

        CustomerBaseInfo customerBaseInfo = customerDetail.getCustomerBaseInfo();
        CustomerLevelBO customerLevelBO = BeanTransform.copyProperties(customerBaseInfo.getCustomerLevel(), CustomerLevelBO.class);
        CustomerBaseInfoBO customerBaseInfoBO = BeanTransform.copyProperties(customerDetail.getCustomerBaseInfo(), CustomerBaseInfoBO.class);
        customerBaseInfoBO.setCustomerLevelBO(customerLevelBO);
        CustomerDetailBO customerDetailBO = BeanTransform.copyProperties(customerDetail, CustomerDetailBO.class);
        customerDetailBO.setCustomerBaseInfoBO(customerBaseInfoBO);
        //查找家庭信息
        CusFamilyMemberDTO cusFamilyMemberDTO = new CusFamilyMemberDTO();
        cusFamilyMemberDTO.getConditions().add(Restrict.eq("customerDetail.id", id));
        List<CusFamilyMember> cfamilyList = cusFamilyMemberAPI.findByCis(cusFamilyMemberDTO);
        List<CusFamilyMemberBO> cusFamilyMemberBOList = BeanTransform.copyProperties(cfamilyList, CusFamilyMemberBO.class);

        customerDetailBO.setCusFamilyMemberBOList(cusFamilyMemberBOList);

        return customerDetailBO;
    }


    @Override
    public CustomerDetailBO getCustomerDetailByNum(String customerNum) throws SerException {
        CustomerBaseInfoDTO cBaseInfoDTO = new CustomerBaseInfoDTO();
        cBaseInfoDTO.getConditions().add(Restrict.eq("customerNum", customerNum));
        CustomerBaseInfo customerBaseInfo = customerBaseInfoAPI.findOne(cBaseInfoDTO);
        CustomerBaseInfoBO customerBaseInfoBO = BeanTransform.copyProperties(customerBaseInfo, CustomerBaseInfoBO.class);
        CustomerLevelBO customerLevelBO = BeanTransform.copyProperties(customerBaseInfo.getCustomerLevel(), CustomerLevelBO.class);
        customerBaseInfoBO.setCustomerLevelBO(customerLevelBO);

        CustomerDetailDTO cdDTO = new CustomerDetailDTO();
        cdDTO.getConditions().add(Restrict.eq("customerNum", customerNum));
        CustomerDetail customerDetail = super.findOne(cdDTO);
        CustomerDetailBO customerDetailBO = BeanTransform.copyProperties(customerDetail, CustomerDetailBO.class);
        //查找家庭信息
        CusFamilyMemberDTO cusFamilyMemberDTO = new CusFamilyMemberDTO();
        cusFamilyMemberDTO.getConditions().add(Restrict.eq("customerDetail.id", customerDetail.getId()));
        List<CusFamilyMember> cfamilyList = cusFamilyMemberAPI.findByCis(cusFamilyMemberDTO);
        List<CusFamilyMemberBO> cusFamilyMemberBOList = BeanTransform.copyProperties(cfamilyList, CusFamilyMemberBO.class);

        customerDetailBO.setCustomerBaseInfoBO(customerBaseInfoBO);
        customerDetailBO.setCusFamilyMemberBOList(cusFamilyMemberBOList);

        return customerDetailBO;
    }

    public static final String[] EXCELHEAD = {"客户信息编号", "客户级别", "客户类别", "客户状态",
            "客户来源", "关系程度", "客户姓名", "性别", "年龄", "出生年月日", "地区", "邮箱", "手机号", "座机", "微信",
            "QQ号", "组织机构名称", "组织机构规模", "岗位", "职级", "职权", "生活地区", "成长地区", "以往工作地区", "工作经历",
            "求学经历", "爱好", "性格评价", "家庭成员称谓", "家庭成员姓名", "家庭成员联系方式", "家庭成员性格爱好", "家庭成员工作单位",
            "家庭成员职位"};

    @Override
    public byte[] exportInfo(CustomerDetailDTO customerDetailDTO) throws SerException {
        List<CustomerDetailExport> list = new ArrayList<>();
        CustomerBaseInfoDTO baseDto = new CustomerBaseInfoDTO();
        if (null != customerDetailDTO.getAreas() && customerDetailDTO.getAreas().length > 0) {
            baseDto.getConditions().add(Restrict.in("area", customerDetailDTO.getAreas()));
        }
        if (null != customerDetailDTO.getCustomerNames() && customerDetailDTO.getCustomerNames().length > 0) {
            baseDto.getConditions().add(Restrict.in("customerName", customerDetailDTO.getCustomerNames()));
        }
        baseDto.getSorts().add("customerNum=asc");
        List<CustomerBaseInfo> baseList = customerBaseInfoAPI.findByCis(baseDto);
        if (baseList != null && baseList.size() > 0) {
            for (CustomerBaseInfo str : baseList) {
                String customerNum = str.getCustomerNum();//客户信息编号
                String level = str.getCustomerLevel().getName();//客户级别
                String customerType = covertCustomerType(str.getCustomerType());//客户类别
                String customerStatus = covertCustomerStatus(str.getCustomerStatus());//客户状态
                Double relation = str.getRelation();//关系程度
                String customerName = str.getCustomerName();//客户姓名
                String customerSex = covertCustomerSex(str.getCustomerSex());//性别
                String origin = covertOrigin(str.getOrigin());//客户来源
//                Integer age = str.getArea();//年龄
//                String customerName = str.getCustomerName();//出生年月日
                String area = str.getArea();//地区
                String cusEmail = str.getCusEmail();//邮箱
                String tel = str.getTel();//手机号
                String phone = str.getPhone();//座机
                String weChart = str.getWeChart();//微信
                String qq = str.getQq();//QQ号
                String origanizion = str.getOriganizion();//组织机构名称
                String origanizationSize = str.getOriganizationSize();//组织机构规模
                String workPosition = str.getWorkPosition();//岗位
                String workLevel = str.getWorkLevel();//职级
                WorkRight workRight = str.getWorkRight();//职权
                String lifeArea = str.getLifeArea();//生活地区
                String grouthArea = str.getGrouthArea();//成长地区
                String oldWorkPlace = str.getOldWorkPlace();//以往工作地区

                CustomerDetailDTO detailDto = new CustomerDetailDTO();
                detailDto.getConditions().add(Restrict.eq("customerNum", customerNum));
                List<CustomerDetail> detailList = super.findByCis(detailDto);
                if (detailList != null && detailList.size() > 0) {
                    for (CustomerDetail customerDetail : detailList) {
                        Integer age = customerDetail.getAge();//年龄
                        String birthday = (null == customerDetail.getBirthday() ? "" : customerDetail.getBirthday().toString());//出生年月日
                        String workExperience = customerDetail.getWorkExperience();//工作经历
                        String studyExperience = customerDetail.getStudyExperience();//求学经历
                        String love = customerDetail.getLove();//爱好
                        String characterEvaluation = customerDetail.getCharacterEvaluation();//性格评价
//                        String workExperience = customerDetail.getch();//家庭成员

                        CusFamilyMemberDTO familyDto = new CusFamilyMemberDTO();
                        familyDto.getConditions().add(Restrict.eq("customerDetail.id", customerDetail.getId()));
                        List<CusFamilyMember> familyList = cusFamilyMemberAPI.findByCis(familyDto);
                        String title = "";
                        String name = "";
                        String relationWay = "";
                        String charactLove = "";
                        String workPlace = "";
                        String jobPost = "";
                        if (familyList != null && familyList.size() > 0) {
                            for (CusFamilyMember familyMember : familyList) {
                                title = familyMember.getTitle();//  家庭成员称谓
                                name = familyMember.getName();//  家庭成员姓名
                                relationWay = familyMember.getRelationWay();//  家庭成员联系方式
                                charactLove = familyMember.getCharactLove();//  家庭成员性格爱好
                                workPlace = familyMember.getWorkPlace();//  家庭成员工作单位
                                jobPost = familyMember.getJobPost();//  家庭成员职位

                                CustomerDetailExport exportEntity = new CustomerDetailExport();
                                exportEntity.setCustomerNum(StringUtils.isBlank(customerNum) ? "" : customerNum);
                                exportEntity.setLevel(StringUtils.isBlank(level) ? "" : level);
                                exportEntity.setCustomerType(StringUtils.isBlank(customerType) ? "" : customerType);
                                exportEntity.setCustomerStatus(StringUtils.isBlank(customerStatus) ? "" : customerStatus);
                                exportEntity.setOrigin(StringUtils.isBlank(origin) ? "" : origin);
                                exportEntity.setRelation(null == relation ? 0 : relation);
                                exportEntity.setCustomerName(StringUtils.isBlank(customerName) ? "" : customerName);
                                exportEntity.setCustomerSex(StringUtils.isBlank(customerSex) ? "" : customerSex);
                                exportEntity.setAge(null == age ? 0 : age);
                                exportEntity.setBirthday(StringUtils.isBlank(birthday) ? "" : birthday);
                                exportEntity.setArea(StringUtils.isBlank(area) ? "" : area);
                                exportEntity.setCusEmail(StringUtils.isBlank(cusEmail) ? "" : cusEmail);
                                exportEntity.setTel(StringUtils.isBlank(tel) ? "" : tel);
                                exportEntity.setPhone(StringUtils.isBlank(phone) ? "" : phone);
                                exportEntity.setWeChart(StringUtils.isBlank(weChart) ? "" : weChart);
                                exportEntity.setQq(StringUtils.isBlank(qq) ? "" : qq);
                                exportEntity.setOriganizion(StringUtils.isBlank(origanizion) ? "" : origanizion);
                                exportEntity.setOriganizationSize(StringUtils.isBlank(origanizationSize) ? "" : origanizationSize);
                                exportEntity.setWorkPosition(StringUtils.isBlank(workPosition) ? "" : workPosition);
                                exportEntity.setWorkLevel(StringUtils.isBlank(workLevel) ? "" : workLevel);
                                exportEntity.setWorkRight(workRight);
                                exportEntity.setLifeArea(StringUtils.isBlank(lifeArea) ? "" : lifeArea);
                                exportEntity.setGrouthArea(StringUtils.isBlank(grouthArea) ? "" : grouthArea);
                                exportEntity.setOldWorkPlace(StringUtils.isBlank(oldWorkPlace) ? "" : oldWorkPlace);
                                exportEntity.setWorkExperience(StringUtils.isBlank(workExperience) ? "" : workExperience);
                                exportEntity.setStudyExperience(StringUtils.isBlank(studyExperience) ? "" : studyExperience);
                                exportEntity.setLove(StringUtils.isBlank(love) ? "" : love);
                                exportEntity.setCharacterEvaluation(StringUtils.isBlank(characterEvaluation) ? "" : characterEvaluation);
                                exportEntity.setTitle(StringUtils.isBlank(title) ? "" : title);
                                exportEntity.setName(StringUtils.isBlank(name) ? "" : name);
                                exportEntity.setRelationWay(StringUtils.isBlank(relationWay) ? "" : relationWay);
                                exportEntity.setCharactLove(StringUtils.isBlank(charactLove) ? "" : charactLove);
                                exportEntity.setWorkPlace(StringUtils.isBlank(workPlace) ? "" : workPlace);
                                exportEntity.setJobPost(StringUtils.isBlank(jobPost) ? "" : jobPost);
                                list.add(exportEntity);
                            }
                        } else {
                            CustomerDetailExport exportEntity = new CustomerDetailExport();
                            exportEntity.setCustomerNum(StringUtils.isBlank(customerNum) ? "" : customerNum);
                            exportEntity.setLevel(StringUtils.isBlank(level) ? "" : level);
                            exportEntity.setCustomerType(StringUtils.isBlank(customerType) ? "" : customerType);
                            exportEntity.setCustomerStatus(StringUtils.isBlank(customerStatus) ? "" : customerStatus);
                            exportEntity.setOrigin(StringUtils.isBlank(origin) ? "" : origin);
                            exportEntity.setRelation(null == relation ? 0 : relation);
                            exportEntity.setCustomerName(StringUtils.isBlank(customerName) ? "" : customerName);
                            exportEntity.setCustomerSex(StringUtils.isBlank(customerSex) ? "" : customerSex);
                            exportEntity.setAge(null == age ? 0 : age);
                            exportEntity.setBirthday(StringUtils.isBlank(birthday) ? "" : birthday);
                            exportEntity.setArea(StringUtils.isBlank(area) ? "" : area);
                            exportEntity.setCusEmail(StringUtils.isBlank(cusEmail) ? "" : cusEmail);
                            exportEntity.setTel(StringUtils.isBlank(tel) ? "" : tel);
                            exportEntity.setPhone(StringUtils.isBlank(phone) ? "" : phone);
                            exportEntity.setWeChart(StringUtils.isBlank(weChart) ? "" : weChart);
                            exportEntity.setQq(StringUtils.isBlank(qq) ? "" : qq);
                            exportEntity.setOriganizion(StringUtils.isBlank(origanizion) ? "" : origanizion);
                            exportEntity.setOriganizationSize(StringUtils.isBlank(origanizationSize) ? "" : origanizationSize);
                            exportEntity.setWorkPosition(StringUtils.isBlank(workPosition) ? "" : workPosition);
                            exportEntity.setWorkLevel(StringUtils.isBlank(workLevel) ? "" : workLevel);
                            exportEntity.setWorkRight(workRight);
                            exportEntity.setLifeArea(StringUtils.isBlank(lifeArea) ? "" : lifeArea);
                            exportEntity.setGrouthArea(StringUtils.isBlank(grouthArea) ? "" : grouthArea);
                            exportEntity.setOldWorkPlace(StringUtils.isBlank(oldWorkPlace) ? "" : oldWorkPlace);
                            exportEntity.setWorkExperience(StringUtils.isBlank(workExperience) ? "" : workExperience);
                            exportEntity.setStudyExperience(StringUtils.isBlank(studyExperience) ? "" : studyExperience);
                            exportEntity.setLove(StringUtils.isBlank(love) ? "" : love);
                            exportEntity.setCharacterEvaluation(StringUtils.isBlank(characterEvaluation) ? "" : characterEvaluation);
                            exportEntity.setTitle(StringUtils.isBlank(title) ? "" : title);
                            exportEntity.setName(StringUtils.isBlank(name) ? "" : name);
                            exportEntity.setRelationWay(StringUtils.isBlank(relationWay) ? "" : relationWay);
                            exportEntity.setCharactLove(StringUtils.isBlank(charactLove) ? "" : charactLove);
                            exportEntity.setWorkPlace(StringUtils.isBlank(workPlace) ? "" : workPlace);
                            exportEntity.setJobPost(StringUtils.isBlank(jobPost) ? "" : jobPost);
                            list.add(exportEntity);
                        }
                    }
                }
            }
        }

        Excel excel = new Excel(0, 2);
        String tempString = excel.getSheetName();
        XSSFWorkbook wb = new XSSFWorkbook();//创建一个Excel文件
        XSSFSheet sheet = wb.createSheet(tempString);//创建报销明细工作薄
        sheet.setDefaultRowHeight((short) 300);//设置默认行高
        // 设置execl工作簿中的列名
        String[] excelHeader = EXCELHEAD;//Excel表头
        XSSFCellStyle contentStyle = getStyle(wb, excel.getContentBGColor()); // 设置标题样式
        XSSFRow row = sheet.createRow(0);//创建第一行
        row.setHeight((short) 400);//设置第一行单元格的高度

        for (int i = 0, length = excelHeader.length; i < length; i++) {
            XSSFCell cell = row.createCell(i);
            if (i == 0 || i == 4 || i == 8) {
                sheet.setColumnWidth(i, 2000); //设置单元格的宽
            } else if (i == 18) {
                sheet.setColumnWidth(i, 5000);
            } else {
                sheet.setColumnWidth(i, 4000);
            }
            cell.setCellValue(excelHeader[i]);//设置单元格的值
            cell.setCellStyle(contentStyle);   //设置样式
        }

        if (list != null && list.size() > 0) {
            createRowDetail(list, row, sheet);//填充数据

        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wb.write(os);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return os.toByteArray();
    }

    private String covertCustomerType(CustomerType customerType) throws SerException {
        String str = "";
        switch (customerType) {
            case VIP:
                str = "VIP客户";
                break;
            case ORDINARY:
                str = "普通客户";
                break;
            case COOPERATOR:
                str = "合作伙伴";
                break;
            case OLD:
                str = "老客户";
                break;
            case OTHER:
                str = "其他";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    private String covertOrigin(Origin origin) throws SerException {
        String str = "";
        switch (origin) {
            case CUSTOMERINTROD:
                str = "客户介绍";
                break;
            case MARKETFOR:
                str = "市场招待";
                break;
            case BUSSNEGOTIATION:
                str = "商务洽谈";
                break;
            case TENDERFOR:
                str = "招投标";
                break;
            case WEBSITE:
                str = "网站";
                break;
            case STAFFINTRODUCED:
                str = "员工介绍";
                break;
            case OTHERSOURCES:
                str = "其他来源";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    private String covertCustomerStatus(CustomerStatus customerStatus) throws SerException {
        String str = "";
        switch (customerStatus) {
            case COMPLETEPROJECT:
                str = "已完成项目客户";
                break;
            case PROJECTING:
                str = "现项目客户";
                break;
            case POTENTIAL:
                str = "潜在客户";
                break;
            case FAILURECUSTOMER:
                str = "失败客户";
                break;
            case LOSTCUSTOMER:
                str = "已流失客户";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    private String covertCustomerSex(CustomerSex customerSex) throws SerException {
        String str = "";
        switch (customerSex) {
            case NONE:
                str = "无";
                break;
            case MAN:
                str = "男";
                break;
            case WOMAN:
                str = "女";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }


    /**
     * @param list  客户详细信息集合
     * @param row
     * @param sheet Excel表单
     * @description 创建数据行
     */
    public void createRowDetail(List<CustomerDetailExport> list, XSSFRow row, XSSFSheet sheet) {

        int firstRow = 0;
        int lastRow = 0;
        int firstCol = 0;
        int lastCol = 0;
        String customerNum = list.get(0).getCustomerNum();
        if (list != null && list.size() > 0) {
            firstRow = 1;
            lastRow = 1;
            firstCol = 0;
            lastCol = 0;
        }
        int index = 0;
        int showFlag = 0;
        for (CustomerDetailExport exportEntity : list) {
            lastRow = index;

//            String ss = exportEntity.getCustomerNum();
//            String ss1 = customerNum;
//            int f = firstRow;
//            int l = lastRow;
//            if (!exportEntity.getCustomerNum().equals(customerNum) && firstRow == lastRow) {
//                //不合并
//                firstRow ++ ;
//                showFlag = 1;
//            }else if (exportEntity.getCustomerNum().equals(customerNum) && firstRow == lastRow) {
//                //合并
//                if( index <= list.size()-1 && !customerNum.equals(list.get(index+1).getCustomerNum())  ){
//                    sheet = assiableMergeData(sheet, firstRow - showFlag, lastRow);
//                }
//                firstRow++;
//                customerNum = exportEntity.getCustomerNum();
//            }

            if (!exportEntity.getCustomerNum().equals(customerNum) && firstRow == lastRow) {
                //不合并
                firstRow++;
                showFlag = 1;
            } else if (exportEntity.getCustomerNum().equals(customerNum) && firstRow == lastRow) {
                //合并
                if (index != 0 && index < list.size() - 1 && !customerNum.equals(list.get(index + 1).getCustomerNum())) {
//                    sheet = assiableMergeData(sheet, firstRow - showFlag, lastRow);
                    sheet = assiableMergeData(sheet, lastRow + 1 - showFlag, lastRow + 1);

                }
                if (index == list.size() - 1) {
                    sheet = assiableMergeData(sheet, lastRow + 1 - showFlag, lastRow + 1);
                }
                firstRow++;
                customerNum = exportEntity.getCustomerNum();
            }

            if (exportEntity.getCustomerNum().equals(customerNum)) {
                showFlag++;
            } else if (!exportEntity.getCustomerNum().equals(customerNum)) {
                customerNum = exportEntity.getCustomerNum();
            }

            ++index;
            row = sheet.createRow(index);    // 每循环一次创建一行
            int callIndex = 0;
//            row.createCell(callIndex++).setCellValue(index);//设置行的索引
            row.createCell(callIndex++).setCellValue(exportEntity.getCustomerNum());
            row.createCell(callIndex++).setCellValue(exportEntity.getLevel());
            row.createCell(callIndex++).setCellValue(exportEntity.getCustomerType());
            row.createCell(callIndex++).setCellValue(exportEntity.getCustomerStatus());
            row.createCell(callIndex++).setCellValue(exportEntity.getOrigin());
            row.createCell(callIndex++).setCellValue(exportEntity.getRelation());
            row.createCell(callIndex++).setCellValue(exportEntity.getCustomerName());
            row.createCell(callIndex++).setCellValue(exportEntity.getCustomerSex());
            row.createCell(callIndex++).setCellValue(exportEntity.getAge());
            row.createCell(callIndex++).setCellValue(exportEntity.getBirthday());
            row.createCell(callIndex++).setCellValue(exportEntity.getArea());
            row.createCell(callIndex++).setCellValue(exportEntity.getCusEmail());
            row.createCell(callIndex++).setCellValue(exportEntity.getTel());
            row.createCell(callIndex++).setCellValue(exportEntity.getPhone());
            row.createCell(callIndex++).setCellValue(exportEntity.getWeChart());
            row.createCell(callIndex++).setCellValue(exportEntity.getQq());
            row.createCell(callIndex++).setCellValue(exportEntity.getOriganizion());
            row.createCell(callIndex++).setCellValue(exportEntity.getOriganizationSize());
            row.createCell(callIndex++).setCellValue(exportEntity.getWorkPosition());
            row.createCell(callIndex++).setCellValue(exportEntity.getWorkLevel());
            row.createCell(callIndex++).setCellValue(WorkRight.enumToString(exportEntity.getWorkRight()));
            row.createCell(callIndex++).setCellValue(exportEntity.getLifeArea());
            row.createCell(callIndex++).setCellValue(exportEntity.getGrouthArea());
            row.createCell(callIndex++).setCellValue(exportEntity.getOldWorkPlace());
            row.createCell(callIndex++).setCellValue(exportEntity.getWorkExperience());
            row.createCell(callIndex++).setCellValue(exportEntity.getStudyExperience());
            row.createCell(callIndex++).setCellValue(exportEntity.getLove());
            row.createCell(callIndex++).setCellValue(exportEntity.getCharacterEvaluation());
            row.createCell(callIndex++).setCellValue(exportEntity.getTitle());
            row.createCell(callIndex++).setCellValue(exportEntity.getName());
            row.createCell(callIndex++).setCellValue(exportEntity.getRelationWay());
            row.createCell(callIndex++).setCellValue(exportEntity.getCharactLove());
            row.createCell(callIndex++).setCellValue(exportEntity.getWorkPlace());
            row.createCell(callIndex++).setCellValue(exportEntity.getJobPost());


        }
    }

    private XSSFSheet assiableMergeData(XSSFSheet sheet, int firstRow, int lastRow) {
        CellRangeAddress cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 0, 0);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 1, 1);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 2, 2);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 3, 3);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 4, 4);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 5, 5);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 6, 6);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 7, 7);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 8, 8);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 9, 9);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 10, 10);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 11, 11);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 12, 12);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 13, 13);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 14, 14);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 15, 15);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 16, 16);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 17, 17);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 18, 18);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 19, 19);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 20, 20);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 21, 21);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 22, 22);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 23, 23);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 24, 24);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 25, 25);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 26, 26);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 27, 27);
        sheet.addMergedRegion(cellRangeAddress);
        return sheet;
    }


    /**
     * 获取样式
     *
     * @param wb
     * @param color
     * @return
     */
    private static XSSFCellStyle getStyle(XSSFWorkbook wb, short color) {
        // 内容的样式
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); //水平布局：居中
        if (color != IndexedColors.WHITE.getIndex()) {
            style.setFillForegroundColor(color);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND); //设置单元格颜色
            style.setBorderLeft(BorderStyle.THIN); // 单元格边框粗细
            style.setBorderRight(BorderStyle.THIN);// 单元格边框粗细
            style.setBorderTop(BorderStyle.THIN);// 单元格边框假粗细
            style.setBorderBottom(BorderStyle.THIN);// 单元格边框粗细
        }
        return style;
    }

}
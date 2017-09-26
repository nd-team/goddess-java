package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.bo.SecureCaseBO;
import com.bjike.goddess.secure.bo.SecureCaseCollectBO;
import com.bjike.goddess.secure.dto.AttachedDTO;
import com.bjike.goddess.secure.dto.EmployeeSecureDTO;
import com.bjike.goddess.secure.dto.SecureCaseDTO;
import com.bjike.goddess.secure.entity.Attached;
import com.bjike.goddess.secure.entity.EmployeeSecure;
import com.bjike.goddess.secure.entity.SecureCase;
import com.bjike.goddess.secure.enums.GuideAddrStatus;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.SecureCaseCollectTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 社保购买情况（汇总明细表）业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-23 02:59 ]
 * @Description: [ 社保购买情况（汇总明细表）业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class SecureCaseSerImpl extends ServiceImpl<SecureCase, SecureCaseDTO> implements SecureCaseSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private AddEmployeeSer addEmployeeSer;
    @Autowired
    private AttachedSer attachedSer;
    @Autowired
    private EmployeeSecureSer employeeSecureSer;
    @Autowired
    private RemoveEmployeeSer removeEmployeeSer;
    @Autowired
    private ReplaceRegisterSer replaceRegisterSer;
    @Autowired
    private DrawRegisterSer drawRegisterSer;


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
                throw new SerException("您不是相应部门的人员，不可以操作");
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

    /**
     * 核对查看权限（部门级别）
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
     * 核对添加修改删除审核权限（岗位级别）
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

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
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
            case SEE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }


    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<SecureCaseBO> list(SecureCaseDTO dto) throws SerException {
        SecureCase secureCase = null;
        Set<String> secureNames = employeeSecureSer.allName();
        Set<String> addNames = addEmployeeSer.allName();
        Set<String> attachedNames = attachedSer.allName();
        Set<String> removeNames = removeEmployeeSer.allName();
        Set<String> drawNames = drawRegisterSer.allName();
        Set<String> replaceNames = replaceRegisterSer.allName();
        Set<String> allNames = new HashSet<>();
        allNames.addAll(secureNames);
        allNames.addAll(addNames);
        allNames.addAll(attachedNames);
        Set<String> names = allNames;
        for (String name : names) {
            SecureCaseDTO dto1 = new SecureCaseDTO();
            dto1.getConditions().add(Restrict.eq("name", name));
            secureCase = super.findOne(dto1);
            if (null == secureCase) {
                secureCase = new SecureCase();
                secureCase.setName(name);
                secureCase.setCreateTime(LocalDateTime.now());
                super.save(secureCase);
            }
        }
        EmployeeSecureDTO employeeSecureDTO = new EmployeeSecureDTO();
        List<EmployeeSecure> employeeSecures = employeeSecureSer.findByCis(employeeSecureDTO);
        LocalDate secureTime = null;
        for (EmployeeSecure employeeSecure : employeeSecures) {
            secureTime = employeeSecure.getSecureTime();
        }
        AttachedDTO attachedDTO = new AttachedDTO();
        List<Attached> attacheds = attachedSer.findByCis(attachedDTO);
        String reason = null;
        Boolean affiliated = null;
        for (Attached attached : attacheds) {
            reason = attached.getReason();
            affiliated = attached.getAffiliated();
        }
        List<SecureCase> secureCases = super.findByCis(dto);
        for (SecureCase secure : secureCases) {
            if (secureNames.contains(secure.getName())) {
                secure.setBuySecureTime(secureTime);
            } else {
                secure.setBuySecureTime(null);
            }
            if (addNames.contains(secure.getName())) {
                secure.setHaveBuySecure(true);
                secure.setAbandonSecure(true);
                secure.setAddSecure(true);
                secure.setDimission(true);
                secure.setBuySecure(true);
                secure.setModifyTime(LocalDateTime.now());
                super.update(secure);
            } else {
                secure.setHaveBuySecure(false);
                secure.setAbandonSecure(false);
                secure.setAddSecure(false);
                secure.setDimission(false);
                secure.setBuySecure(false);
                secure.setModifyTime(LocalDateTime.now());
                super.update(secure);
            }

            if (attachedNames.contains(secure.getName())) {
                secure.setCause(reason);
                secure.setAffiliated(affiliated);
                secure.setNeedAttached(true);
                secure.setModifyTime(LocalDateTime.now());
                super.update(secure);
            } else {
                secure.setNeedAttached(false);
                secure.setModifyTime(LocalDateTime.now());
                super.update(secure);
            }
            if (removeNames.contains(secure.getName())) {
                secure.setAddAttrition(true);
                secure.setModifyTime(LocalDateTime.now());
                super.update(secure);
            } else {
                secure.setAddAttrition(false);
                secure.setModifyTime(LocalDateTime.now());
                super.update(secure);
            }
            if (drawNames.contains(secure.getName())) {
                secure.setBackSecureCard(true);
                secure.setDrawSecureCard(true);
                secure.setModifyTime(LocalDateTime.now());
                super.update(secure);
            } else {
                secure.setBackSecureCard(false);
                secure.setDrawSecureCard(false);
                secure.setModifyTime(LocalDateTime.now());
                super.update(secure);
            }
            if (replaceNames.contains(secure.getName())) {
                secure.setReplaceSecureCard(true);
                secure.setModifyTime(LocalDateTime.now());
                super.update(secure);
            } else {
                secure.setReplaceSecureCard(false);
                secure.setModifyTime(LocalDateTime.now());
                super.update(secure);
            }

        }
        List<SecureCaseBO> secureCaseBOS = BeanTransform.copyProperties(secureCases, SecureCaseBO.class);
        return secureCaseBOS;
    }

    @Override
    public List<SecureCaseCollectBO> collect(SecureCaseCollectTO to) throws SerException {
        List<SecureCaseCollectBO> boList = new ArrayList<>();
        long buySecure = 0;//购买社保人数
        long haveBuySecure = 0;//已购买社保人数
        long addNum = 0;//增员人数
        long attritionNum = 0;//减员人数
        long attachedNum = 0;//挂靠人数
        long notbuySecure = 0;//不购买社保人数
        long backSecureCard = 0;//需领回的社保卡数
        long drawNum = 0;//社保购买者已领取的社保卡数
        long replaceNum = 0;//需补办社保卡数
        String startTime = to.getStartTime();
        String endTime = to.getEndTime();
        String[] condi = new String[]{startTime, endTime};
        SecureCaseDTO dto = new SecureCaseDTO();
        if (null != to.getArea()) {
            dto.getConditions().add(Restrict.in("area", to.getArea()));
        }
        if (null != to.getProjectGroup()) {
            dto.getConditions().add(Restrict.in("projectGroup", to.getProjectGroup()));
        }
        if (null != to.getUnit()) {
            dto.getConditions().add(Restrict.in("unit", to.getUnit()));
        }
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            dto.getConditions().add(Restrict.between("buySecureTime", condi));
        }
        List<SecureCase> secureCases = super.findByCis(dto);
        if (null != to.getArea() && null != to.getProjectGroup() && null != to.getUnit()) {
            for (String area : to.getArea()) {
                for (String project : to.getProjectGroup()) {
                    for (String unit : to.getUnit()) {
                        buySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getBuySecure()) && secureCase.getBuySecure() && area.equals(secureCase.getArea()) && project.equals(secureCase.getProjectGroup()) && unit.equals(secureCase.getUnit())).count();
                        haveBuySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getHaveBuySecure()) && secureCase.getHaveBuySecure() && area.equals(secureCase.getArea()) && project.equals(secureCase.getProjectGroup()) && unit.equals(secureCase.getUnit())).count();
                        addNum = secureCases.stream().filter(secureCase -> (null != secureCase.getAddSecure()) && secureCase.getAddSecure() && area.equals(secureCase.getArea()) && project.equals(secureCase.getProjectGroup()) && unit.equals(secureCase.getUnit())).count();
                        attritionNum = secureCases.stream().filter(secureCase -> (null != secureCase.getAddAttrition()) && secureCase.getAddAttrition() && area.equals(secureCase.getArea()) && project.equals(secureCase.getProjectGroup()) && unit.equals(secureCase.getUnit())).count();
                        attachedNum = secureCases.stream().filter(secureCase -> (null != secureCase.getNeedAttached()) && secureCase.getNeedAttached() && area.equals(secureCase.getArea()) && project.equals(secureCase.getProjectGroup()) && unit.equals(secureCase.getUnit())).count();
                        notbuySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getAbandonSecure()) && secureCase.getAbandonSecure() && area.equals(secureCase.getArea()) && project.equals(secureCase.getProjectGroup()) && unit.equals(secureCase.getUnit())).count();
                        backSecureCard = secureCases.stream().filter(secureCase -> (null != secureCase.getBackSecureCard()) && secureCase.getBackSecureCard() && area.equals(secureCase.getArea()) && project.equals(secureCase.getProjectGroup()) && unit.equals(secureCase.getUnit())).count();
                        drawNum = secureCases.stream().filter(secureCase -> (null != secureCase.getDrawSecureCard()) && secureCase.getDrawSecureCard() && area.equals(secureCase.getArea()) && project.equals(secureCase.getProjectGroup()) && unit.equals(secureCase.getUnit())).count();
                        replaceNum = secureCases.stream().filter(secureCase -> (null != secureCase.getReplaceSecureCard()) && secureCase.getReplaceSecureCard() && area.equals(secureCase.getArea()) && project.equals(secureCase.getProjectGroup()) && unit.equals(secureCase.getUnit())).count();
                        SecureCaseCollectBO bo = new SecureCaseCollectBO();
                        bo.setArea(area);
                        bo.setProjectGroup(project);
                        bo.setUnit(unit);
                        bo.setBuySecure(Integer.parseInt(buySecure + ""));
                        bo.setHaveBuySecure(Integer.parseInt(haveBuySecure + ""));
                        bo.setAddNum(Integer.parseInt(addNum + ""));
                        bo.setAttritionNum(Integer.parseInt(attritionNum + ""));
                        bo.setAttachedNum(Integer.parseInt(attachedNum + ""));
                        bo.setNotbuySecure(Integer.parseInt(notbuySecure + ""));
                        bo.setBackSecureCard(Integer.parseInt(backSecureCard + ""));
                        bo.setDrawNum(Integer.parseInt(drawNum + ""));
                        bo.setReplaceNum(Integer.parseInt(replaceNum + ""));
                        boList.add(bo);
                    }
                }
            }
        }
        if (null != to.getArea() && null != to.getProjectGroup()) {
            for (String area : to.getArea()) {
                for (String projectGroup : to.getProjectGroup()) {
                    buySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getBuySecure()) && secureCase.getBuySecure() && area.equals(secureCase.getArea()) && projectGroup.equals(secureCase.getProjectGroup())).count();
                    haveBuySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getHaveBuySecure()) && secureCase.getHaveBuySecure() && area.equals(secureCase.getArea()) && projectGroup.equals(secureCase.getProjectGroup())).count();
                    addNum = secureCases.stream().filter(secureCase -> (null != secureCase.getAddSecure()) && secureCase.getAddSecure() && area.equals(secureCase.getArea()) && projectGroup.equals(secureCase.getProjectGroup())).count();
                    attritionNum = secureCases.stream().filter(secureCase -> (null != secureCase.getAddAttrition()) && secureCase.getAddAttrition() && area.equals(secureCase.getArea()) && projectGroup.equals(secureCase.getProjectGroup())).count();
                    attachedNum = secureCases.stream().filter(secureCase -> (null != secureCase.getNeedAttached()) && secureCase.getNeedAttached() && area.equals(secureCase.getArea()) && projectGroup.equals(secureCase.getProjectGroup())).count();
                    notbuySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getAbandonSecure()) && secureCase.getAbandonSecure() && area.equals(secureCase.getArea()) && projectGroup.equals(secureCase.getProjectGroup())).count();
                    backSecureCard = secureCases.stream().filter(secureCase -> (null != secureCase.getBackSecureCard()) && secureCase.getBackSecureCard() && area.equals(secureCase.getArea()) && projectGroup.equals(secureCase.getProjectGroup())).count();
                    drawNum = secureCases.stream().filter(secureCase -> (null != secureCase.getDrawSecureCard()) && secureCase.getDrawSecureCard() && area.equals(secureCase.getArea()) && projectGroup.equals(secureCase.getProjectGroup())).count();
                    replaceNum = secureCases.stream().filter(secureCase -> (null != secureCase.getReplaceSecureCard()) && secureCase.getReplaceSecureCard() && area.equals(secureCase.getArea()) && projectGroup.equals(secureCase.getProjectGroup())).count();
                    SecureCaseCollectBO bo = new SecureCaseCollectBO();
                    bo.setArea(area);
                    bo.setProjectGroup(projectGroup);
                    bo.setBuySecure(Integer.parseInt(buySecure + ""));
                    bo.setHaveBuySecure(Integer.parseInt(haveBuySecure + ""));
                    bo.setAddNum(Integer.parseInt(addNum + ""));
                    bo.setAttritionNum(Integer.parseInt(attritionNum + ""));
                    bo.setAttachedNum(Integer.parseInt(attachedNum + ""));
                    bo.setNotbuySecure(Integer.parseInt(notbuySecure + ""));
                    bo.setBackSecureCard(Integer.parseInt(backSecureCard + ""));
                    bo.setDrawNum(Integer.parseInt(drawNum + ""));
                    bo.setReplaceNum(Integer.parseInt(replaceNum + ""));
                    boList.add(bo);
                }
            }
        }
        if (null != to.getArea() && null != to.getUnit()) {
            for (String area : to.getArea()) {
                for (String unit : to.getUnit()) {
                    buySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getBuySecure()) && secureCase.getBuySecure() && area.equals(secureCase.getArea()) && unit.equals(secureCase.getUnit())).count();
                    haveBuySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getHaveBuySecure()) && secureCase.getHaveBuySecure() && area.equals(secureCase.getArea()) && unit.equals(secureCase.getUnit())).count();
                    addNum = secureCases.stream().filter(secureCase -> (null != secureCase.getAddSecure()) && secureCase.getAddSecure() && area.equals(secureCase.getArea()) && unit.equals(secureCase.getUnit())).count();
                    attritionNum = secureCases.stream().filter(secureCase -> (null != secureCase.getAddAttrition()) && secureCase.getAddAttrition() && area.equals(secureCase.getArea()) && unit.equals(secureCase.getUnit())).count();
                    attachedNum = secureCases.stream().filter(secureCase -> (null != secureCase.getNeedAttached()) && secureCase.getNeedAttached() && area.equals(secureCase.getArea()) && unit.equals(secureCase.getUnit())).count();
                    notbuySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getAbandonSecure()) && secureCase.getAbandonSecure() && area.equals(secureCase.getArea()) && unit.equals(secureCase.getUnit())).count();
                    backSecureCard = secureCases.stream().filter(secureCase -> (null != secureCase.getBackSecureCard()) && secureCase.getBackSecureCard() && area.equals(secureCase.getArea()) && unit.equals(secureCase.getUnit())).count();
                    drawNum = secureCases.stream().filter(secureCase -> (null != secureCase.getDrawSecureCard()) && secureCase.getDrawSecureCard() && area.equals(secureCase.getArea()) && unit.equals(secureCase.getUnit())).count();
                    replaceNum = secureCases.stream().filter(secureCase -> (null != secureCase.getReplaceSecureCard()) && secureCase.getReplaceSecureCard() && area.equals(secureCase.getArea()) && unit.equals(secureCase.getUnit())).count();
                    SecureCaseCollectBO bo = new SecureCaseCollectBO();
                    bo.setArea(area);
                    bo.setUnit(unit);
                    bo.setBuySecure(Integer.parseInt(buySecure + ""));
                    bo.setHaveBuySecure(Integer.parseInt(haveBuySecure + ""));
                    bo.setAddNum(Integer.parseInt(addNum + ""));
                    bo.setAttritionNum(Integer.parseInt(attritionNum + ""));
                    bo.setAttachedNum(Integer.parseInt(attachedNum + ""));
                    bo.setNotbuySecure(Integer.parseInt(notbuySecure + ""));
                    bo.setBackSecureCard(Integer.parseInt(backSecureCard + ""));
                    bo.setDrawNum(Integer.parseInt(drawNum + ""));
                    bo.setReplaceNum(Integer.parseInt(replaceNum + ""));
                    boList.add(bo);
                }
            }
        }
        if (null != to.getProjectGroup() && null != to.getUnit()) {
            for (String projectGroup : to.getProjectGroup()) {
                for (String unit : to.getUnit()) {
                    buySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getBuySecure()) && secureCase.getBuySecure() && projectGroup.equals(secureCase.getProjectGroup()) && unit.equals(secureCase.getUnit())).count();
                    haveBuySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getHaveBuySecure()) && secureCase.getHaveBuySecure() && projectGroup.equals(secureCase.getProjectGroup()) && unit.equals(secureCase.getUnit())).count();
                    addNum = secureCases.stream().filter(secureCase -> (null != secureCase.getAddSecure()) && secureCase.getAddSecure() && projectGroup.equals(secureCase.getProjectGroup()) && unit.equals(secureCase.getUnit())).count();
                    attritionNum = secureCases.stream().filter(secureCase -> (null != secureCase.getAddAttrition()) && secureCase.getAddAttrition() && projectGroup.equals(secureCase.getProjectGroup()) && unit.equals(secureCase.getUnit())).count();
                    attachedNum = secureCases.stream().filter(secureCase -> (null != secureCase.getNeedAttached()) && secureCase.getNeedAttached() && projectGroup.equals(secureCase.getProjectGroup()) && unit.equals(secureCase.getUnit())).count();
                    notbuySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getAbandonSecure()) && secureCase.getAbandonSecure() && projectGroup.equals(secureCase.getProjectGroup()) && unit.equals(secureCase.getUnit())).count();
                    backSecureCard = secureCases.stream().filter(secureCase -> (null != secureCase.getBackSecureCard()) && secureCase.getBackSecureCard() && projectGroup.equals(secureCase.getProjectGroup()) && unit.equals(secureCase.getUnit())).count();
                    drawNum = secureCases.stream().filter(secureCase -> (null != secureCase.getDrawSecureCard()) && secureCase.getDrawSecureCard() && projectGroup.equals(secureCase.getProjectGroup()) && unit.equals(secureCase.getUnit())).count();
                    replaceNum = secureCases.stream().filter(secureCase -> (null != secureCase.getReplaceSecureCard()) && secureCase.getReplaceSecureCard() && projectGroup.equals(secureCase.getProjectGroup()) && unit.equals(secureCase.getUnit())).count();

                    SecureCaseCollectBO bo = new SecureCaseCollectBO();
                    bo.setProjectGroup(projectGroup);
                    bo.setUnit(unit);
                    bo.setBuySecure(Integer.parseInt(buySecure + ""));
                    bo.setHaveBuySecure(Integer.parseInt(haveBuySecure + ""));
                    bo.setAddNum(Integer.parseInt(addNum + ""));
                    bo.setAttritionNum(Integer.parseInt(attritionNum + ""));
                    bo.setAttachedNum(Integer.parseInt(attachedNum + ""));
                    bo.setNotbuySecure(Integer.parseInt(notbuySecure + ""));
                    bo.setBackSecureCard(Integer.parseInt(backSecureCard + ""));
                    bo.setDrawNum(Integer.parseInt(drawNum + ""));
                    bo.setReplaceNum(Integer.parseInt(replaceNum + ""));
                    boList.add(bo);
                }
            }
        }
        if (null != to.getArea()) {
            for (String area : to.getArea()) {
                buySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getBuySecure()) && secureCase.getBuySecure() && area.equals(secureCase.getArea())).count();
                haveBuySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getHaveBuySecure()) && secureCase.getHaveBuySecure() && area.equals(secureCase.getArea())).count();
                addNum = secureCases.stream().filter(secureCase -> (null != secureCase.getAddSecure()) && secureCase.getAddSecure() && area.equals(secureCase.getArea())).count();
                attritionNum = secureCases.stream().filter(secureCase -> (null != secureCase.getAddAttrition()) && secureCase.getAddAttrition() && area.equals(secureCase.getArea())).count();
                attachedNum = secureCases.stream().filter(secureCase -> (null != secureCase.getNeedAttached()) && secureCase.getNeedAttached() && area.equals(secureCase.getArea())).count();
                notbuySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getAbandonSecure()) && secureCase.getAbandonSecure() && area.equals(secureCase.getArea())).count();
                backSecureCard = secureCases.stream().filter(secureCase -> (null != secureCase.getBackSecureCard()) && secureCase.getBackSecureCard() && area.equals(secureCase.getArea())).count();
                drawNum = secureCases.stream().filter(secureCase -> (null != secureCase.getDrawSecureCard()) && secureCase.getDrawSecureCard() && area.equals(secureCase.getArea())).count();
                replaceNum = secureCases.stream().filter(secureCase -> (null != secureCase.getReplaceSecureCard()) && secureCase.getReplaceSecureCard() && area.equals(secureCase.getArea())).count();
                SecureCaseCollectBO bo = new SecureCaseCollectBO();
                bo.setArea(area);
                bo.setBuySecure(Integer.parseInt(buySecure + ""));
                bo.setHaveBuySecure(Integer.parseInt(haveBuySecure + ""));
                bo.setAddNum(Integer.parseInt(addNum + ""));
                bo.setAttritionNum(Integer.parseInt(attritionNum + ""));
                bo.setAttachedNum(Integer.parseInt(attachedNum + ""));
                bo.setNotbuySecure(Integer.parseInt(notbuySecure + ""));
                bo.setBackSecureCard(Integer.parseInt(backSecureCard + ""));
                bo.setDrawNum(Integer.parseInt(drawNum + ""));
                bo.setReplaceNum(Integer.parseInt(replaceNum + ""));
                boList.add(bo);
            }
        }
        if (null != to.getProjectGroup()) {
            for (String projectGroup : to.getProjectGroup()) {
                buySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getBuySecure()) && secureCase.getBuySecure() && projectGroup.equals(secureCase.getProjectGroup())).count();
                haveBuySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getHaveBuySecure()) && secureCase.getHaveBuySecure() && projectGroup.equals(secureCase.getProjectGroup())).count();
                addNum = secureCases.stream().filter(secureCase -> (null != secureCase.getAddSecure()) && secureCase.getAddSecure() && projectGroup.equals(secureCase.getProjectGroup())).count();
                attritionNum = secureCases.stream().filter(secureCase -> (null != secureCase.getAddAttrition()) && secureCase.getAddAttrition() && projectGroup.equals(secureCase.getProjectGroup())).count();
                attachedNum = secureCases.stream().filter(secureCase -> (null != secureCase.getNeedAttached()) && secureCase.getNeedAttached() && projectGroup.equals(secureCase.getProjectGroup())).count();
                notbuySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getAbandonSecure()) && secureCase.getAbandonSecure() && projectGroup.equals(secureCase.getProjectGroup())).count();
                backSecureCard = secureCases.stream().filter(secureCase -> (null != secureCase.getBackSecureCard()) && secureCase.getBackSecureCard() && projectGroup.equals(secureCase.getProjectGroup())).count();
                drawNum = secureCases.stream().filter(secureCase -> (null != secureCase.getDrawSecureCard()) && secureCase.getDrawSecureCard() && projectGroup.equals(secureCase.getProjectGroup())).count();
                replaceNum = secureCases.stream().filter(secureCase -> (null != secureCase.getReplaceSecureCard()) && secureCase.getReplaceSecureCard() && projectGroup.equals(secureCase.getProjectGroup())).count();

                SecureCaseCollectBO bo = new SecureCaseCollectBO();
                bo.setProjectGroup(projectGroup);
                bo.setBuySecure(Integer.parseInt(buySecure + ""));
                bo.setHaveBuySecure(Integer.parseInt(haveBuySecure + ""));
                bo.setAddNum(Integer.parseInt(addNum + ""));
                bo.setAttritionNum(Integer.parseInt(attritionNum + ""));
                bo.setAttachedNum(Integer.parseInt(attachedNum + ""));
                bo.setNotbuySecure(Integer.parseInt(notbuySecure + ""));
                bo.setBackSecureCard(Integer.parseInt(backSecureCard + ""));
                bo.setDrawNum(Integer.parseInt(drawNum + ""));
                bo.setReplaceNum(Integer.parseInt(replaceNum + ""));
                boList.add(bo);
            }
        }
        if (null != to.getUnit()) {
            for (String unit : to.getUnit()) {
                buySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getBuySecure()) && secureCase.getBuySecure() && unit.equals(secureCase.getUnit())).count();
                haveBuySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getHaveBuySecure()) && secureCase.getHaveBuySecure() && unit.equals(secureCase.getUnit())).count();
                addNum = secureCases.stream().filter(secureCase -> (null != secureCase.getAddSecure()) && secureCase.getAddSecure() && unit.equals(secureCase.getUnit())).count();
                attritionNum = secureCases.stream().filter(secureCase -> (null != secureCase.getAddAttrition()) && secureCase.getAddAttrition() && unit.equals(secureCase.getUnit())).count();
                attachedNum = secureCases.stream().filter(secureCase -> (null != secureCase.getNeedAttached()) && secureCase.getNeedAttached() && unit.equals(secureCase.getUnit())).count();
                notbuySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getAbandonSecure()) && secureCase.getAbandonSecure() && unit.equals(secureCase.getUnit())).count();
                backSecureCard = secureCases.stream().filter(secureCase -> (null != secureCase.getBackSecureCard()) && secureCase.getBackSecureCard() && unit.equals(secureCase.getUnit())).count();
                drawNum = secureCases.stream().filter(secureCase -> (null != secureCase.getDrawSecureCard()) && secureCase.getDrawSecureCard() && unit.equals(secureCase.getUnit())).count();
                replaceNum = secureCases.stream().filter(secureCase -> (null != secureCase.getReplaceSecureCard()) && secureCase.getReplaceSecureCard() && unit.equals(secureCase.getUnit())).count();

                SecureCaseCollectBO bo = new SecureCaseCollectBO();
                bo.setUnit(unit);
                bo.setBuySecure(Integer.parseInt(buySecure + ""));
                bo.setHaveBuySecure(Integer.parseInt(haveBuySecure + ""));
                bo.setAddNum(Integer.parseInt(addNum + ""));
                bo.setAttritionNum(Integer.parseInt(attritionNum + ""));
                bo.setAttachedNum(Integer.parseInt(attachedNum + ""));
                bo.setNotbuySecure(Integer.parseInt(notbuySecure + ""));
                bo.setBackSecureCard(Integer.parseInt(backSecureCard + ""));
                bo.setDrawNum(Integer.parseInt(drawNum + ""));
                bo.setReplaceNum(Integer.parseInt(replaceNum + ""));
                boList.add(bo);
            }
        }
        buySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getBuySecure()) && secureCase.getBuySecure()).count();
        haveBuySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getHaveBuySecure()) && secureCase.getHaveBuySecure()).count();
        addNum = secureCases.stream().filter(secureCase -> (null != secureCase.getAddSecure()) && secureCase.getAddSecure()).count();
        attritionNum = secureCases.stream().filter(secureCase -> (null != secureCase.getAddAttrition()) && secureCase.getAddAttrition()).count();
        attachedNum = secureCases.stream().filter(secureCase -> (null != secureCase.getNeedAttached()) && secureCase.getNeedAttached()).count();
        notbuySecure = secureCases.stream().filter(secureCase -> (null != secureCase.getAbandonSecure()) && secureCase.getAbandonSecure()).count();
        backSecureCard = secureCases.stream().filter(secureCase -> (null != secureCase.getBackSecureCard()) && secureCase.getBackSecureCard()).count();
        drawNum = secureCases.stream().filter(secureCase -> (null != secureCase.getDrawSecureCard()) && secureCase.getDrawSecureCard()).count();
        replaceNum = secureCases.stream().filter(secureCase -> (null != secureCase.getReplaceSecureCard()) && secureCase.getReplaceSecureCard()).count();
        SecureCaseCollectBO bo = new SecureCaseCollectBO();
        bo.setBuySecure(Integer.parseInt(buySecure + ""));
        bo.setHaveBuySecure(Integer.parseInt(haveBuySecure + ""));
        bo.setAddNum(Integer.parseInt(addNum + ""));
        bo.setAttritionNum(Integer.parseInt(attritionNum + ""));
        bo.setAttachedNum(Integer.parseInt(attachedNum + ""));
        bo.setNotbuySecure(Integer.parseInt(notbuySecure + ""));
        bo.setBackSecureCard(Integer.parseInt(backSecureCard + ""));
        bo.setDrawNum(Integer.parseInt(drawNum + ""));
        bo.setReplaceNum(Integer.parseInt(replaceNum + ""));
        boList.add(bo);
        return boList;
    }

    @Override
    public Set<String> allArea() throws SerException {
        Set<String> areas = new HashSet<>();
        List<SecureCase> list = super.findAll();
        for (SecureCase entity : list) {
            areas.add(entity.getArea());
        }
        return areas;
    }

    @Override
    public Set<String> allProjectGroup() throws SerException {
        Set<String> projectGroups = new HashSet<>();
        List<SecureCase> list = super.findAll();
        for (SecureCase entity : list) {
            projectGroups.add(entity.getProjectGroup());
        }
        return projectGroups;
    }

    @Override
    public Set<String> allUnit() throws SerException {
        Set<String> units = new HashSet<>();
        List<SecureCase> list = super.findAll();
        for (SecureCase entity : list) {
            units.add(entity.getUnit());
        }
        return units;

    }
}
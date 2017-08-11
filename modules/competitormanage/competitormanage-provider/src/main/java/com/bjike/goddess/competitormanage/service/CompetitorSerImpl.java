package com.bjike.goddess.competitormanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.competitormanage.bo.CompetitorBO;
import com.bjike.goddess.competitormanage.bo.OrganizationBO;
import com.bjike.goddess.competitormanage.dto.CompetitorDTO;
import com.bjike.goddess.competitormanage.entity.Competitor;
import com.bjike.goddess.competitormanage.enums.GuideAddrStatus;
import com.bjike.goddess.competitormanage.excel.CompetitorExcel;
import com.bjike.goddess.competitormanage.excel.SonPermissionObject;
import com.bjike.goddess.competitormanage.to.CompetitorOrganizaeTO;
import com.bjike.goddess.competitormanage.to.CompetitorTO;
import com.bjike.goddess.competitormanage.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 竞争对手信息业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-21 04:49 ]
 * @Description: [ 竞争对手信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "competitormanageSerCache")
@Service
public class CompetitorSerImpl extends ServiceImpl<Competitor, CompetitorDTO> implements CompetitorSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CompetitorCollectSer competitorCollectSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public CompetitorBO saveCompetitor(CompetitorTO to) throws SerException {

        getCusPermission();

        Competitor model = BeanTransform.copyProperties(to, Competitor.class, true);
        setMarkInfoCode(model);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, CompetitorBO.class);
    }

    public void setMarkInfoCode(Competitor model) throws SerException {
        //save前查询当前数据库最新的编号
        CompetitorDTO dto = new CompetitorDTO();
        dto.getSorts().add("createTime=desc");
        List<Competitor> last = super.findByCis(dto);
        if (last != null && !last.isEmpty()) {
            //设置最新编号
            String codeNumStr = last.get(0).getMarkInfoCode().substring(3);
            Integer codeNumInt = Integer.parseInt(codeNumStr);
            Integer newCodeNumInt = codeNumInt + 1;
            String newCodeNumStr = newCodeNumInt + "";
            StringBuffer code = new StringBuffer();
            if (newCodeNumStr.length() > 7) {
                throw new SerException("添加失败，市场信息收集序号溢出!");
            } else {
                code.append("MI-");
                //编码不超过 7 位数
                for (int i = 0; i < 7 - newCodeNumStr.length(); i++) {
                    code.append("0");
                }
                code.append(newCodeNumStr);
            }
            model.setMarkInfoCode(code.toString());
        } else {
            model.setMarkInfoCode("MI-0000001");
        }
    }


    @Override
    @Transactional(rollbackFor = SerException.class)
    public CompetitorBO editCompetitor(CompetitorTO to) throws SerException {

        getCusPermission();

        updateModel(to);
        return BeanTransform.copyProperties(to, CompetitorBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public CompetitorBO editOrganization(CompetitorOrganizaeTO to) throws SerException {
        Competitor competitor = BeanTransform.copyProperties(to, Competitor.class);
        updateModel(competitor);
        return BeanTransform.copyProperties(to, CompetitorBO.class);
    }


    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<CompetitorBO> pageList(CompetitorDTO dto) throws SerException {

        getCusPermission();

        dto.getSorts().add("createTime=desc");
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        List<Competitor> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, CompetitorBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void delete(String id) throws SerException {
        getCusPermission();

        //考虑到编号完整性，删除只冻结数据状态
        Competitor model = super.findById(id);
        if (model != null) {
            model.setStatus(Status.CONGEAL);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("删除对象不存在!");
        }
    }

    @Override
    public void leadExcel(List<CompetitorTO> toList) throws SerException {
        List<Competitor> list = BeanTransform.copyProperties(toList, Competitor.class);

        for (Competitor model : list) {
            setMarkInfoCode(model);
            super.save(model);
        }
    }

    @Override
    public byte[] exportExcel(String startDate, String endDate) throws SerException {
        CompetitorDTO dto = new CompetitorDTO();
        if (!StringUtils.isEmpty(startDate)) {
            dto.getConditions().add(Restrict.gt("createTime", startDate));
        }
        if (!StringUtils.isEmpty(endDate)) {
            dto.getConditions().add(Restrict.lt("createTime", endDate));
        }
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        List<Competitor> list = super.findByCis(dto);
        List<CompetitorExcel> excelList = new ArrayList<CompetitorExcel>();
        if (!CollectionUtils.isEmpty(list)) {
            for (Competitor model : list) {
                CompetitorExcel excel = new CompetitorExcel();
                BeanUtils.copyProperties(model, excel);
                excelList.add(excel);
            }
        } else {
            excelList.add(new CompetitorExcel());
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(excelList, excel);
        return bytes;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {

        List<SonPermissionObject> list = new ArrayList<>();

        String userToken = RpcTransmit.getUserToken();
        Boolean flagAddSign = guideSeeIdentity();
        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("competitor");
        obj.setDescribesion("竞争对手信息");
        if (flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = competitorCollectSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("collect");
        obj.setDescribesion("竞争对手管理汇总");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = to.getGuideAddrStatus();
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
            case ORGANIZE:
                flag = guideAddIdentity();
                break;
            case DELETE:
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

    @Override
    public byte[] exportExcelModule() throws SerException {
        Excel excel = new Excel(0, 2);
        List<CompetitorExcel> list = new ArrayList<CompetitorExcel>();
        list.add(new CompetitorExcel());
        byte[] bytes = ExcelUtil.clazzToExcel(list, excel);
        return bytes;
    }

    @Override
    public List<CompetitorBO> areas() throws SerException {

        StringBuilder sql = new StringBuilder();
        //查询解冻状态的地区
        sql.append("select distinct area from competitormanage_competitor where status = 0 ");

        return super.findBySql(sql.toString(), CompetitorBO.class, new String[]{"area"});
    }

    @Override
    public OrganizationBO organizeList(String id) throws SerException {
        if(StringUtils.isEmpty(id)){
            throw new SerException("竞争对手id不能为空");
        }
        Competitor competitor = this.findById(id);
        OrganizationBO organizationBO = new OrganizationBO();
        if(competitor != null){
            organizationBO.setDirectDepartment(competitor.getDirectDepartment());
            organizationBO.setDirector(competitor.getDirector());
            organizationBO.setDirectAuthority(competitor.getDirectAuthority());
            organizationBO.setChargeItems(competitor.getChargeItems());
            organizationBO.setCustomerInfoCode(competitor.getCustomerInfoCode());
            organizationBO.setBranchedDepartment(competitor.getBranchedDepartment());
            organizationBO.setChargeMan(competitor.getChargeMan());
            organizationBO.setChargeManAuthority(competitor.getChargeManAuthority());
            organizationBO.setInterfaceMan(competitor.getInterfaceMan());
        }
        return organizationBO;
    }

    /**
     * 导航栏核对查看权限（岗位级别）
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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 更新数据（编辑、审核）
     *
     * @param to 竞争对手信息
     */
    public void updateModel(Competitor to) throws SerException {

        Competitor model = super.findById(to.getId());
        if (model != null) {
            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("更新对象不能为空");
        }
    }

    /**
     * 更新数据（编辑、审核）
     *
     * @param to 竞争对手信息
     */
    public void updateModel(CompetitorTO to) throws SerException {

        Competitor model = super.findById(to.getId());
        if (model != null) {
            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("更新对象不能为空");
        }
    }

    public void getCusPermission() throws SerException {
        //zhuangkaiqin
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if ("admin".equals(userBO.getUsername())) {
            permission = true;
        }

        if (!permission) {
            throw new SerException("该模块只有商务模块负责人可操作，您的帐号尚无权限");
        }
    }

    @Override
    //chenjunhao
    public List<CompetitorBO> findByBusinessType(String businessType) throws SerException {
        CompetitorDTO dto = new CompetitorDTO();
        dto.getConditions().add(Restrict.like("businessType", businessType));
        List<Competitor> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, CompetitorBO.class);
    }

    @Override
    //chenjunhao
    public List<CompetitorBO> findByOrganization(String organization) throws SerException {
        CompetitorDTO dto = new CompetitorDTO();
        dto.getConditions().add(Restrict.eq("organization", organization));
        List<Competitor> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, CompetitorBO.class);
    }

    @Override
    public List<String> findCompeName() throws SerException {
        List<Competitor> competitors = super.findAll();
        if (CollectionUtils.isEmpty(competitors)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (Competitor competitor : competitors){
            String name = competitor.getCompetitor();
            if (StringUtils.isNotBlank(competitor.getCompetitor())) {
                set.add(name);
            }
        }
        return new ArrayList<>(set);
    }
}
package com.bjike.goddess.market.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.assemble.api.ModuleAssembleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.competitormanage.api.CompetitorAPI;
import com.bjike.goddess.customer.api.CustomerBaseInfoAPI;
import com.bjike.goddess.customer.bo.CustomerNameNumBO;
import com.bjike.goddess.market.bo.MarketInfoBO;
import com.bjike.goddess.market.dto.MarketInfoDTO;
import com.bjike.goddess.market.entity.MarketInfo;
import com.bjike.goddess.market.enums.GuideAddrStatus;
import com.bjike.goddess.market.enums.MarketProjectNature;
import com.bjike.goddess.market.enums.MarketWorkType;
import com.bjike.goddess.market.enums.Scale;
import com.bjike.goddess.market.excel.MarketInfoExport;
import com.bjike.goddess.market.excel.SonPermissionObject;
import com.bjike.goddess.market.to.GuidePermissionTO;
import com.bjike.goddess.market.to.MarketInfoTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 市场信息管理业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-21T11:00:01.568 ]
 * @Description: [ 市场信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketSerCache")
@Service
public class MarketInfoSerImpl extends ServiceImpl<MarketInfo, MarketInfoDTO> implements MarketInfoSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CollectEmailSer collectEmailSer;
    @Autowired
    private CustomerBaseInfoAPI customerBaseInfoAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private CompetitorAPI competitorAPI;

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

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeInfo = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddInfo = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("marketinfo");
        obj.setDescribesion("市场信息");
        if (flagSeeInfo || flagAddInfo) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail = collectEmailSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("collectemail");
        obj.setDescribesion("市场信息邮件发送");
        if (flagSeeEmail) {
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

    /**
     * 核对时间格式(年月日)
     */
    private void checkDate(MarketInfoTO marketInfoTO) throws SerException {
        try {
            DateUtil.parseDate(marketInfoTO.getInfoCollectionDate());
            DateUtil.parseDate(marketInfoTO.getStartTime());
            DateUtil.parseDateTime(marketInfoTO.getImportantPoint());
            DateUtil.parseDate(marketInfoTO.getEndTime());
        } catch (Exception e) {
            throw new SerException("输入的日期格式有误");
        }
    }


    @Override
    public Long countMarketInfo(MarketInfoDTO marketInfoDTO) throws SerException {
        marketInfoDTO.getSorts().add("createTime=desc");
        Long count = super.count(marketInfoDTO);
        return count;
    }

    @Override
    public MarketInfoBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        MarketInfo marketInfo = super.findById(id);
        return BeanTransform.copyProperties(marketInfo, MarketInfoBO.class);
    }

    @Override
    public List<MarketInfoBO> findListMarketInfo(MarketInfoDTO marketInfoDTO) throws SerException {
        checkSeeIdentity();
        List<MarketInfo> marketInfos = super.findByCis(marketInfoDTO, true);
        List<MarketInfoBO> marketInfoBOS = BeanTransform.copyProperties(marketInfos, MarketInfoBO.class);
        return marketInfoBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketInfoBO insertMarketInfo(MarketInfoTO marketInfoTO) throws SerException {
        checkAddIdentity();
        checkDate(marketInfoTO);
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if (!permission) {
            throw new SerException("您不是商务人员，没有权限");
        }
        MarketInfo marketInfo = BeanTransform.copyProperties(marketInfoTO, MarketInfo.class, true);
        try {
            //判断是否为有效信息
            if (marketInfo.getEffective()) {
                //判断是否为新项目
                if (marketInfo.getProjectNature().equals(MarketProjectNature.NEWPROJECT)) {
                    marketInfo.setEffective(true);
                } else if (marketInfo.getProjectNature().equals(MarketProjectNature.OLDPROJECT)) {
                    marketInfo.setEffective(false);
                }
            }
            marketInfo.setCreateTime(LocalDateTime.now());
            super.save(marketInfo);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
        return BeanTransform.copyProperties(marketInfo, MarketInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketInfoBO editMarketInfo(MarketInfoTO marketInfoTO) throws SerException {
        if (StringUtils.isBlank(marketInfoTO.getId())) {
            throw new SerException("id不能为空");
        }
        checkAddIdentity();
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if (!permission) {
            throw new SerException("您不是商务人员，没有权限");
        }
        MarketInfo marketInfo = super.findById(marketInfoTO.getId());
        checkDate(marketInfoTO);
        BeanTransform.copyProperties(marketInfoTO, marketInfo, true);
        marketInfo.setModifyTime(LocalDateTime.now());
        super.update(marketInfo);
        return BeanTransform.copyProperties(marketInfoTO, MarketInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeMarketInfo(String id) throws SerException {
        checkAddIdentity();
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if (!permission) {
            throw new SerException("您不是商务人员，没有权限");
        }
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }

    }
    @Override
    public List<String> getCustomerName() throws SerException {
        String[] fields = new String[]{"customerName"};
        List<MarketInfoBO> marketInfoBOS = super.findBySql("select distinct customerName from market_marketinfo group by customerName order by customerName asc ", MarketInfoBO.class, fields);

        List<String> collectList = marketInfoBOS.stream().map(MarketInfoBO::getCustomerName)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return collectList;
    }

    @Override
    public byte[] exportExcel(MarketInfoDTO dto) throws SerException {
        if (null != dto.getCustomerName()) {
            dto.getConditions().add(Restrict.in("customerName", dto.getCustomerName()));
        }
        List<MarketInfo> list = super.findByCis(dto);

        List<MarketInfoExport> exports = new ArrayList<>();
        list.stream().forEach(str -> {
            MarketInfoExport export = BeanTransform.copyProperties(str, MarketInfoExport.class, "projectNature","scale", "workType",  "effective");
            export.setProjectNature(MarketProjectNature.exportStrConvert(str.getProjectNature()));
            export.setScale(Scale.exportStrConvert(str.getScale()));
            export.setWorkType(MarketWorkType.exportStrConvert(str.getWorkType()));
            if (str.getEffective().equals(true)) {
                export.setEffective("是");
            } else {
                export.setEffective("否");
            }
            exports.add(export);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(exports, excel);
        return bytes;
    }

    @Override
    //chenjunhao
    public List<MarketInfoBO> findByOriganizion(String origanizion) throws SerException {
        MarketInfoDTO dto = new MarketInfoDTO();
        dto.getConditions().add(Restrict.eq("origanizion", origanizion));
        return BeanTransform.copyProperties(super.findByCis(dto), MarketInfoBO.class);
    }
    @Override
    //zhuangkaiqin
    public List<MarketInfoBO> getCollecting(String area, String projectName) throws SerException {
        MarketInfoDTO dto = new MarketInfoDTO();
        dto.getConditions().add(Restrict.eq("area",area));
        dto.getConditions().add(Restrict.eq("projectName",projectName));

        return BeanTransform.copyProperties(super.findByCis(dto),MarketInfoBO.class);
    }

    @Override
    public List<CustomerNameNumBO> getNameNum() throws SerException {
        List<CustomerNameNumBO> customerNameNumBOS = new ArrayList<>(0);
        if(moduleAPI.isCheck("customer")){  //判断关联模块该模块是否被勾选
            customerNameNumBOS = customerBaseInfoAPI.findNameNum();
        }
        return customerNameNumBOS;
    }

    @Override
    public List<String> getCompetName() throws SerException {
        List<String> competName = new ArrayList<>(0);
        if(moduleAPI.isCheck("competitormanage")){  //判断关联模块该模块是否被勾选
            competName = competitorAPI.findCompeName();
        }
        return competName;
    }
}
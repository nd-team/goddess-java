package com.bjike.goddess.employeecontract.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.employeecontract.bo.*;
import com.bjike.goddess.employeecontract.dto.ContractCollectDTO;
import com.bjike.goddess.employeecontract.dto.ContractInformationDTO;
import com.bjike.goddess.employeecontract.entity.ContractCollect;
import com.bjike.goddess.employeecontract.entity.ContractInformation;
import com.bjike.goddess.employeecontract.enums.AgreeProbationTime;
import com.bjike.goddess.employeecontract.enums.ContractStatus;
import com.bjike.goddess.employeecontract.enums.GuideAddStatus;
import com.bjike.goddess.employeecontract.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 员工合同管理汇总业务实现
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-11-10 03:22 ]
 * @Description: [ 员工合同管理汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "employeecontractSerCache")
@Service
public class ContractCollectSerImpl extends ServiceImpl<ContractCollect, ContractCollectDTO> implements ContractCollectSer {
    @Autowired
    private ContractInformationSer contractInformationSer;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private UserAPI userAPI;

    @Override
    public List<ContractAreaCollectBO> monthCollect(Integer year, Integer month) throws SerException {
        LocalDate[] collectDate = null;
        if (year != null && month != null) {
            LocalDate startDate = DateUtil.getStartDayOfMonth(year, month);
            LocalDate endDate = DateUtil.getEndDaYOfMonth(year, month);
            collectDate = new LocalDate[]{startDate, endDate};
        } else {
            LocalDate startDate = DateUtil.getStartMonth();
            LocalDate endDate = DateUtil.getEndMonth();
            collectDate = new LocalDate[]{startDate, endDate};
        }
        return collect(collectDate);
    }

    @Override
    public List<ContractAreaCollectBO> allCollect(String date) throws SerException {
        LocalDate[] collectDate = null;
        if (StringUtils.isNotBlank(date)) {
            LocalDate startDate = DateUtil.parseDate("1901-01-01");
            LocalDate endDate = DateUtil.parseDate(date);
            collectDate = new LocalDate[]{startDate, endDate};
        } else {
            LocalDate startDate = DateUtil.parseDate("1901-01-01");
            LocalDate endDate = LocalDate.now();
            collectDate = new LocalDate[]{startDate, endDate};
        }
        return collect(collectDate);
    }

    private List<ContractAreaCollectBO> collect(LocalDate[] collectDate) throws SerException {
        ContractInformationDTO dto = new ContractInformationDTO();
        List<ContractInformation> contractInformations = contractInformationSer.findAll();
        Set<String> areas = contractInformations.stream().map(p -> p.getArea()).collect(Collectors.toSet());
        List<ContractAreaCollectBO> contractAreaCollectBOS = new ArrayList<>();
        for (String area : areas) {
            ContractAreaCollectBO contractAreaCollectBO = new ContractAreaCollectBO();
            ContractInformationDTO contractInformationDTO = new ContractInformationDTO();
            contractInformationDTO.getConditions().add(Restrict.eq("area", area));
            List<ContractInformation> contractInformations1 = contractInformationSer.findByCis(contractInformationDTO);
            Set<String> departments = contractInformations1.stream().map(p -> p.getDepartment()).collect(Collectors.toSet());
            List<ContractCollectBO> contractCollectBOS = new ArrayList<>();
            for (String department : departments) {
                ContractCollectBO contractCollectBO = new ContractCollectBO();
                ContractInformationDTO contractInformationDTO1 = new ContractInformationDTO();
                contractInformationDTO1.getConditions().add(Restrict.eq("area", area));
                contractInformationDTO1.getConditions().add(Restrict.eq("department", department));
                contractInformationDTO1.getConditions().add(Restrict.between("entryDate", collectDate));
                List<ContractInformation> contractInformations2 = contractInformationSer.findByCis(contractInformationDTO1);
                //注册用户名数量
                Integer enrollUserNumber = 0;
                //在职时长=0-1个月数
                Integer onJobNumber = 0;
                //劳动合同已签订数
                Integer contractAlreadySignNumber = 0;
                //待签订数
                Integer waitSignContract = 0;
                //已到期未续签
                Integer alreadyDeadLineNotRenew = 0;
                //离职人数
                Integer dimissionNumber = 0;
                //解除数
                Integer relieveNumber = 0;
                //第一次续签是否需要合同变更数
                Integer firstRenewIfNeedContract = 0;
                //第二次续签是否需要合同变更数
                Integer secondRenewIfNeedContract = 0;
                if (contractInformations2 != null && contractInformations2.size() > 0) {
                    enrollUserNumber = contractInformations2.size();
                    dimissionNumber = (int) contractInformations2.stream().filter(p -> p.getDimissionDate() != null).map(p -> p.getDimissionDate()).count();
                }
                contractInformationDTO1.getConditions().add(Restrict.eq("agreeProbationTime", AgreeProbationTime.ONEMONTH));
                List<ContractInformation> contractInformations3 = contractInformationSer.findByCis(contractInformationDTO1);
                if (contractInformations3 != null && contractInformations3.size() > 0) {
                    onJobNumber = contractInformations3.size();
                }

                ContractInformationDTO contractInformationDTO2 = new ContractInformationDTO();
                contractInformationDTO2.getConditions().add(Restrict.eq("status", ContractStatus.ALREADYSIGN));
                contractInformationDTO2.getConditions().add(Restrict.eq("area", area));
                contractInformationDTO2.getConditions().add(Restrict.eq("department", department));
                contractInformationDTO2.getConditions().add(Restrict.between("entryDate", collectDate));

                List<ContractInformation> contractInformations4 = contractInformationSer.findByCis(contractInformationDTO2);

                if (contractInformations4 != null && contractInformations4.size() > 0) {
                    contractAlreadySignNumber = contractInformations4.size();
                }

                ContractInformationDTO contractInformationDTO3 = new ContractInformationDTO();
                contractInformationDTO3.getConditions().add(Restrict.eq("status", ContractStatus.WAITSIGN));
                contractInformationDTO3.getConditions().add(Restrict.eq("area", area));
                contractInformationDTO3.getConditions().add(Restrict.eq("department", department));
                contractInformationDTO3.getConditions().add(Restrict.between("entryDate", collectDate));
                List<ContractInformation> contractInformations5 = contractInformationSer.findByCis(contractInformationDTO3);

                if (contractInformations5 != null && contractInformations5.size() > 0) {
                    waitSignContract = contractInformations5.size();
                }

                ContractInformationDTO contractInformationDTO4 = new ContractInformationDTO();
                contractInformationDTO4.getConditions().add(Restrict.eq("ifRenew", false));
                contractInformationDTO4.getConditions().add(Restrict.eq("area", area));
                contractInformationDTO4.getConditions().add(Restrict.eq("department", department));
                contractInformationDTO4.getConditions().add(Restrict.between("entryDate", collectDate));

                List<ContractInformation> contractInformations6 = contractInformationSer.findByCis(contractInformationDTO4);

                if (contractInformations6 != null && contractInformations6.size() > 0) {
                    alreadyDeadLineNotRenew = contractInformations6.size();
                }

                ContractInformationDTO contractInformationDTO5 = new ContractInformationDTO();
                contractInformationDTO5.getConditions().add(Restrict.eq("area", area));
                contractInformationDTO5.getConditions().add(Restrict.eq("department", department));
                contractInformationDTO5.getConditions().add(Restrict.between("relieveContractDate", collectDate));

                List<ContractInformation> contractInformations7 = contractInformationSer.findByCis(contractInformationDTO5);

                if (contractInformations7 != null && contractInformations7.size() > 0) {
                    relieveNumber = contractInformations7.size();
                }

                ContractInformationDTO contractInformationDTO6 = new ContractInformationDTO();
                contractInformationDTO6.getConditions().add(Restrict.eq("area", area));
                contractInformationDTO6.getConditions().add(Restrict.eq("department", department));
                contractInformationDTO6.getConditions().add(Restrict.eq("ifNeedContractChangeFirst", true));
                contractInformationDTO6.getConditions().add(Restrict.between("entryDate", collectDate));

                List<ContractInformation> contractInformations8 = contractInformationSer.findByCis(contractInformationDTO6);

                if (contractInformations8 != null && contractInformations8.size() > 0) {
                    firstRenewIfNeedContract = contractInformations8.size();
                }

                ContractInformationDTO contractInformationDTO7 = new ContractInformationDTO();
                contractInformationDTO7.getConditions().add(Restrict.eq("area", area));
                contractInformationDTO7.getConditions().add(Restrict.eq("department", department));
                contractInformationDTO7.getConditions().add(Restrict.eq("ifNeedContractChangeSecond", true));
                contractInformationDTO7.getConditions().add(Restrict.between("entryDate", collectDate));

                List<ContractInformation> contractInformations9 = contractInformationSer.findByCis(contractInformationDTO7);

                if (contractInformations9 != null && contractInformations9.size() > 0) {
                    secondRenewIfNeedContract = contractInformations9.size();
                }

                contractCollectBO.setAlreadyDeadLineNotRenew(alreadyDeadLineNotRenew);
                contractCollectBO.setContractAlreadySignNumber(contractAlreadySignNumber);
                contractCollectBO.setDepartment(department);
                contractCollectBO.setDimissionNumber(dimissionNumber);
                contractCollectBO.setEnrollUserNumber(enrollUserNumber);
                contractCollectBO.setFirstRenewIfNeedContract(firstRenewIfNeedContract);
                contractCollectBO.setOnJobNumber(onJobNumber);
                contractCollectBO.setRelieveNumber(relieveNumber);
                contractCollectBO.setSecondRenewIfNeedContract(secondRenewIfNeedContract);
                contractCollectBO.setWaitSignContract(waitSignContract);

                contractCollectBOS.add(contractCollectBO);
            }
            contractAreaCollectBO.setArea(area);
            contractAreaCollectBO.setContractCollect(contractCollectBOS);
            contractAreaCollectBOS.add(contractAreaCollectBO);
        }
        return contractAreaCollectBOS;

    }

    @Override
    public OptionBO figureShowMonth(Integer year, Integer month) throws SerException {
        LocalDate[] collectDate = null;
        if (year != null && month != null) {
            LocalDate startDate = DateUtil.getStartDayOfMonth(year, month);
            LocalDate endDate = DateUtil.getEndDaYOfMonth(year, month);
            collectDate = new LocalDate[]{startDate, endDate};
        }
        String text_1 = "员工合月同汇总";
        return imageCollect(collectDate,text_1);
    }

    @Override
    public OptionBO figureShowTotal(String date) throws SerException {
        LocalDate[] collectDate = null;
        if (StringUtils.isNotBlank(date)) {
            LocalDate startDate = DateUtil.parseDate("1901-01-01");
            LocalDate endDate = DateUtil.parseDate(date);
            collectDate = new LocalDate[]{startDate, endDate};
        } else {
            LocalDate startDate = DateUtil.parseDate("1901-01-01");
            LocalDate endDate = LocalDate.now();
            collectDate = new LocalDate[]{startDate, endDate};
        }
        String text_1 = "员工合同年汇总";
        return imageCollect(collectDate,text_1);
    }

    private OptionBO imageCollect(LocalDate[] collectDate, String text_1) throws SerException {
        ContractInformationDTO dto = new ContractInformationDTO();
        List<ContractInformation> contractInformations = contractInformationSer.findAll();
        Set<String> departments = contractInformations.stream().map(p -> p.getDepartment()).collect(Collectors.toSet());
        List<ContractCollectBO> contractCollectBOS = new ArrayList<>();
        for (String department : departments) {
            ContractCollectBO contractCollectBO = new ContractCollectBO();
            ContractInformationDTO contractInformationDTO1 = new ContractInformationDTO();
            contractInformationDTO1.getConditions().add(Restrict.eq("department", department));
            contractInformationDTO1.getConditions().add(Restrict.between("entryDate", collectDate));
            List<ContractInformation> contractInformations2 = contractInformationSer.findByCis(contractInformationDTO1);
            //注册用户名数量
            Integer enrollUserNumber = 0;
            //在职时长=0-1个月数
            Integer onJobNumber = 0;
            //劳动合同已签订数
            Integer contractAlreadySignNumber = 0;
            //待签订数
            Integer waitSignContract = 0;
            //已到期未续签
            Integer alreadyDeadLineNotRenew = 0;
            //离职人数
            Integer dimissionNumber = 0;
            //解除数
            Integer relieveNumber = 0;
            //第一次续签是否需要合同变更数
            Integer firstRenewIfNeedContract = 0;
            //第二次续签是否需要合同变更数
            Integer secondRenewIfNeedContract = 0;
            if (contractInformations2 != null && contractInformations2.size() > 0) {
                enrollUserNumber = contractInformations2.size();
                dimissionNumber = (int) contractInformations2.stream().filter(p -> p.getDimissionDate() != null).map(p -> p.getDimissionDate()).count();
            }
            contractInformationDTO1.getConditions().add(Restrict.eq("agreeProbationTime", AgreeProbationTime.ONEMONTH));
            List<ContractInformation> contractInformations3 = contractInformationSer.findByCis(contractInformationDTO1);
            if (contractInformations3 != null && contractInformations3.size() > 0) {
                onJobNumber = contractInformations3.size();
            }

            ContractInformationDTO contractInformationDTO2 = new ContractInformationDTO();
            contractInformationDTO2.getConditions().add(Restrict.eq("status", ContractStatus.ALREADYSIGN));
            contractInformationDTO2.getConditions().add(Restrict.eq("department", department));
            contractInformationDTO2.getConditions().add(Restrict.between("entryDate", collectDate));

            List<ContractInformation> contractInformations4 = contractInformationSer.findByCis(contractInformationDTO2);

            if (contractInformations4 != null && contractInformations4.size() > 0) {
                contractAlreadySignNumber = contractInformations4.size();
            }

            ContractInformationDTO contractInformationDTO3 = new ContractInformationDTO();
            contractInformationDTO3.getConditions().add(Restrict.eq("status", ContractStatus.WAITSIGN));
            contractInformationDTO3.getConditions().add(Restrict.eq("department", department));
            contractInformationDTO3.getConditions().add(Restrict.between("entryDate", collectDate));
            List<ContractInformation> contractInformations5 = contractInformationSer.findByCis(contractInformationDTO3);

            if (contractInformations5 != null && contractInformations5.size() > 0) {
                waitSignContract = contractInformations5.size();
            }

            ContractInformationDTO contractInformationDTO4 = new ContractInformationDTO();
            contractInformationDTO4.getConditions().add(Restrict.eq("ifRenew", false));
            contractInformationDTO4.getConditions().add(Restrict.eq("department", department));
            contractInformationDTO4.getConditions().add(Restrict.between("entryDate", collectDate));

            List<ContractInformation> contractInformations6 = contractInformationSer.findByCis(contractInformationDTO4);

            if (contractInformations6 != null && contractInformations6.size() > 0) {
                alreadyDeadLineNotRenew = contractInformations6.size();
            }

            ContractInformationDTO contractInformationDTO5 = new ContractInformationDTO();
            contractInformationDTO5.getConditions().add(Restrict.eq("department", department));
            contractInformationDTO5.getConditions().add(Restrict.between("relieveContractDate", collectDate));

            List<ContractInformation> contractInformations7 = contractInformationSer.findByCis(contractInformationDTO5);

            if (contractInformations7 != null && contractInformations7.size() > 0) {
                relieveNumber = contractInformations7.size();
            }

            ContractInformationDTO contractInformationDTO6 = new ContractInformationDTO();
            contractInformationDTO6.getConditions().add(Restrict.eq("department", department));
            contractInformationDTO6.getConditions().add(Restrict.eq("ifNeedContractChangeFirst", true));
            contractInformationDTO6.getConditions().add(Restrict.between("entryDate", collectDate));

            List<ContractInformation> contractInformations8 = contractInformationSer.findByCis(contractInformationDTO6);

            if (contractInformations8 != null && contractInformations8.size() > 0) {
                firstRenewIfNeedContract = contractInformations8.size();
            }

            ContractInformationDTO contractInformationDTO7 = new ContractInformationDTO();
            contractInformationDTO7.getConditions().add(Restrict.eq("department", department));
            contractInformationDTO7.getConditions().add(Restrict.eq("ifNeedContractChangeSecond", true));
            contractInformationDTO7.getConditions().add(Restrict.between("entryDate", collectDate));

            List<ContractInformation> contractInformations9 = contractInformationSer.findByCis(contractInformationDTO7);

            if (contractInformations9 != null && contractInformations9.size() > 0) {
                secondRenewIfNeedContract = contractInformations9.size();
            }

            contractCollectBO.setAlreadyDeadLineNotRenew(alreadyDeadLineNotRenew);
            contractCollectBO.setContractAlreadySignNumber(contractAlreadySignNumber);
            contractCollectBO.setDepartment(department);
            contractCollectBO.setDimissionNumber(dimissionNumber);
            contractCollectBO.setEnrollUserNumber(enrollUserNumber);
            contractCollectBO.setFirstRenewIfNeedContract(firstRenewIfNeedContract);
            contractCollectBO.setOnJobNumber(onJobNumber);
            contractCollectBO.setRelieveNumber(relieveNumber);
            contractCollectBO.setSecondRenewIfNeedContract(secondRenewIfNeedContract);
            contractCollectBO.setWaitSignContract(waitSignContract);

            contractCollectBOS.add(contractCollectBO);
        }

        //标题
        TitleBO titleBO = new TitleBO();


        //横坐标描述
        LegendBO legendBO = new LegendBO();
        List<String> text_list2 = new ArrayList<>();


        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();

        ToolTipBO toolTipBO = new ToolTipBO();

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        String[] text_3 = new String[]{"注册用户数（入职人数）", "在职时长＝０－１个月数", "劳动合同已签订数", "待签订", "已到期未续签"
                , "离职人数", "解除数", "第一次续签是否需要合同变更数", "第二次续签是否需要合同变更数"};
        xAxisBO.setData(text_3);
        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (contractCollectBOS != null && contractCollectBOS.size() > 0) {
            for (ContractCollectBO contractCollectBO : contractCollectBOS) {
                text_list2.add(contractCollectBO.getDepartment());

                //柱状图数据
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(contractCollectBO.getDepartment());
                seriesBO.setType("bar");

                Integer[] number = new Integer[]{contractCollectBO.getEnrollUserNumber(),contractCollectBO.getOnJobNumber(),contractCollectBO.getContractAlreadySignNumber()
                ,contractCollectBO.getWaitSignContract(),contractCollectBO.getAlreadyDeadLineNotRenew(),contractCollectBO.getDimissionNumber()
                ,contractCollectBO.getFirstRenewIfNeedContract(),contractCollectBO.getSecondRenewIfNeedContract()};
                seriesBO.setData(number);
                seriesBOList.add(seriesBO);
            }
        }

        String[] text_2 = new String[text_list2.size()];
        text_2 = text_list2.toArray(text_2);

        SeriesBO[] text_4 = new SeriesBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        legendBO.setData(text_2);
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);
        optionBO.setTooltip(toolTipBO);

        optionBO.setSeries(text_4);
        return optionBO;
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
            flag = cusPermissionSer.getCusPermission("2");
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
        GuideAddStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
}
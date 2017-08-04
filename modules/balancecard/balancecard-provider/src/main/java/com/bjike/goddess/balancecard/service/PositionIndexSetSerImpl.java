package com.bjike.goddess.balancecard.service;

import com.bjike.goddess.balancecard.bo.PositionIndexSetBO;
import com.bjike.goddess.balancecard.entity.*;
import com.bjike.goddess.balancecard.enums.GuideAddrStatus;
import com.bjike.goddess.balancecard.enums.SeparateStatus;
import com.bjike.goddess.balancecard.enums.SeperateComeStatus;
import com.bjike.goddess.balancecard.excel.PositionIndexSetExcel;
import com.bjike.goddess.balancecard.to.ExportExcelPositTO;
import com.bjike.goddess.balancecard.to.GuidePermissionTO;
import com.bjike.goddess.balancecard.to.PositionIndexSetTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.balancecard.dto.PositionIndexSetDTO;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 岗位指标设置业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:38 ]
 * @Description: [ 岗位指标设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "balancecardSerCache")
@Service
public class PositionIndexSetSerImpl extends ServiceImpl<PositionIndexSet, PositionIndexSetDTO> implements PositionIndexSetSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private DepartMonIndexSetSer departMonIndexSetSer;
    @Autowired
    private DepartYearIndexSetSer departYearIndexSetSer;
    @Autowired
    private YearIndexSetSer yearIndexSetSer;

    @Autowired
    private BalancecardPermissionSer cusPermissionSer;


    /**
     * 核对是否为管理层权限（部门级别）
     */
    private Boolean checkManageIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                RpcTransmit.transmitUserToken(userToken);
                return false;
            }
        }
        RpcTransmit.transmitUserToken(userToken);
        return true;
    }

    /**
     * 核对是否为执行层权限（部门级别）
     */
    private Boolean checkExecutiveIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
            if (!flag) {
                RpcTransmit.transmitUserToken(userToken);
                return false;
            }
        }
        RpcTransmit.transmitUserToken(userToken);
        return true;
    }
    @Override
    public Long countPositionIndexSet(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        if (StringUtils.isNotBlank(positionIndexSetDTO.getIndexName())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("indexName", positionIndexSetDTO.getIndexName()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getYear())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("year", positionIndexSetDTO.getYear()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getMonth())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("month", positionIndexSetDTO.getMonth()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getPositioner())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("positioner", positionIndexSetDTO.getPositioner()));
        }
        Long count = super.count(positionIndexSetDTO);
        return count;
    }

    @Override
    public PositionIndexSetBO getOneById(String id) throws SerException {

        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        PositionIndexSet temp = super.findById(id);
        PositionIndexSetBO bo = BeanTransform.copyProperties(temp, PositionIndexSetBO.class);
        return bo;
    }

    /**
     * 根据他的职位来查询他可以看到的信息
     * @param positionIndexSetDTO
     * @return
     * @throws SerException
     */
    @Override
    public List<PositionIndexSetBO> listPositionIndexSet(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        if(checkManageIdentity()) {
            if (StringUtils.isNotBlank(positionIndexSetDTO.getIndexName())) {
                positionIndexSetDTO.getConditions().add(Restrict.like("indexName", positionIndexSetDTO.getIndexName()));
            }
            if (StringUtils.isNotBlank(positionIndexSetDTO.getYear())) {
                positionIndexSetDTO.getConditions().add(Restrict.like("year", positionIndexSetDTO.getYear()));
            }
            if (StringUtils.isNotBlank(positionIndexSetDTO.getMonth())) {
                positionIndexSetDTO.getConditions().add(Restrict.like("month", positionIndexSetDTO.getMonth()));
            }
            if (StringUtils.isNotBlank(positionIndexSetDTO.getPositioner())) {
                positionIndexSetDTO.getConditions().add(Restrict.like("positioner", positionIndexSetDTO.getPositioner()));
            }

            List<PositionIndexSet> list = super.findByCis(positionIndexSetDTO, true);
            List<PositionIndexSetBO> listBO = BeanTransform.copyProperties(list, PositionIndexSetBO.class);
            return listBO;
        }else{
            //如果他为执行层,获取他当前的工号来查询
            UserBO user = userAPI.currentUser();
            String positionerNumber = user.getEmployeeNumber();
            if(positionerNumber != "" && positionerNumber != null)
            {
                positionIndexSetDTO.getConditions().add(Restrict.eq("positionerNumber",positionerNumber));
            }
            List<PositionIndexSet> positionIndexSetList = super.findByCis(positionIndexSetDTO,true);
            List<PositionIndexSetBO> listBo = BeanTransform.copyProperties(positionIndexSetList,PositionIndexSetBO.class);
            return  listBo;
        }
    }
    //添加岗位指标
    @Override
    public PositionIndexSetBO addPositionIndexSet(PositionIndexSetTO positionIndexSetTO) throws SerException {
        UserBO userBO = userAPI.currentUser();
        PositionIndexSet temp = BeanTransform.copyProperties(positionIndexSetTO, PositionIndexSet.class);
        //添加
        temp.setWeightSum(0d);
        temp.setIndexNumber(0);
        temp.setYearIndexNumber(0);
        temp.setMonthIndexNumber(0);
        String postIndexNumber = super.findByMaxField("postIndexNumber",PositionIndexSet.class);
        if(postIndexNumber != null)
        {
            temp.setPostIndexNumber(Integer.parseInt(postIndexNumber)+1);
        }else{
            temp.setPostIndexNumber(1);
        }
        temp.setSeperateComeStatus(SeperateComeStatus.FILL);
        temp.setWritePerson(userBO.getUsername());
        temp.setWhetherStandar(positionIndexSetTO.getComplete() > positionIndexSetTO.getTarget() ? "是" : "否");
        temp.setStandardRate(positionIndexSetTO.getComplete() / positionIndexSetTO.getWager());
        temp.setExamScore(positionIndexSetTO.getComplete() * positionIndexSetTO.getWeight());
        temp.setPosionIndexPersion(userBO.getUsername());
        temp.setPosionIndexTime(LocalDate.now());
//        temp.setSeperateComeStatus(null);
        temp.setDepartMonIndexSetId(null);
        super.save(temp);
        return BeanTransform.copyProperties(temp, PositionIndexSetBO.class);
    }

    @Override
    public PositionIndexSetBO editPositionIndexSet(PositionIndexSetTO positionIndexSetTO) throws SerException {
        if (StringUtils.isBlank(positionIndexSetTO.getId())) {
            throw new SerException("id不能为空");
        }
        PositionIndexSet temp = super.findById(positionIndexSetTO.getId());
        Double oldComplete = temp.getComplete();

        temp.setIndexName(positionIndexSetTO.getIndexName());
        temp.setYear(positionIndexSetTO.getYear());
        temp.setMonth(positionIndexSetTO.getMonth());
        temp.setIndexType(positionIndexSetTO.getIndexType());
        temp.setDimension(positionIndexSetTO.getDimension());
        temp.setDescribtion(positionIndexSetTO.getDescribtion());
        temp.setDepartment(positionIndexSetTO.getDepartment());
        temp.setDepartYearWeight(positionIndexSetTO.getDepartYearWeight());
        temp.setDepartYearWager(positionIndexSetTO.getDepartYearWager());
        temp.setPosition(positionIndexSetTO.getPosition());
        temp.setPositioner(positionIndexSetTO.getPositioner());
        temp.setWeight(positionIndexSetTO.getWeight());
        temp.setWeightSum(positionIndexSetTO.getWeightSum());
        temp.setTarget(positionIndexSetTO.getTarget());
        temp.setWager(positionIndexSetTO.getWager());
        temp.setComplete(positionIndexSetTO.getComplete());
        temp.setExamWay(positionIndexSetTO.getExamWay());
        temp.setWhetherStandar(positionIndexSetTO.getComplete() > positionIndexSetTO.getTarget() ? "是" : "否");
        temp.setStandardRate(positionIndexSetTO.getComplete() / positionIndexSetTO.getWager());
        temp.setExamScore(positionIndexSetTO.getComplete() * positionIndexSetTO.getWeight());
        temp.setExamDepart(positionIndexSetTO.getExamDepart());
        temp.setDataOrigin(positionIndexSetTO.getDataOrigin());
        temp.setExamDuring(positionIndexSetTO.getExamDuring());
        temp.setModifyTime(LocalDateTime.now());

        //修改上面的完成值
        if (temp.getSeperateComeStatus().equals(SeperateComeStatus.DEPARTMONTH)) {
            //TODO:未做
            //修改部门月指标完成值
            //修改部门年指标完成值
            //修改年指标完成值
            editDepartMonComplete(oldComplete, temp);


        }

        super.update(temp);

        return BeanTransform.copyProperties(temp, PositionIndexSetBO.class);
    }

    private void editDepartMonComplete(Double oldComplete, PositionIndexSet temp) throws SerException {
        //新的岗位完成值
        Double newComplete = temp.getComplete();
        //部门月度id
        String monId = temp.getDepartMonIndexSetId();
        DepartMonIndexSet dMon = departMonIndexSetSer.findById(monId);
        //部门月度旧完成值
        Double oldMonComplete = dMon.getComplete();

        dMon.setComplete(dMon.getComplete() - oldComplete + newComplete);
        dMon.setWhetherStandar(dMon.getComplete() > dMon.getTarget() ? "是" : "否");
        dMon.setStandardRate(dMon.getComplete() / dMon.getWager());
        dMon.setExamScore(dMon.getStandardRate() * dMon.getWeight());
        dMon.setModifyTime(LocalDateTime.now());

        //修改部门年度完成指标
        editDepartYearComplete(oldMonComplete, dMon);

        departMonIndexSetSer.update(dMon);
    }

    private void editDepartYearComplete(Double oldMonComplete, DepartMonIndexSet dMon) throws SerException {
        //新的部门月度完成值
        Double newComplete = dMon.getComplete();
        //部门年度id
        String yearId = dMon.getDepartYearIndexSetId();
        DepartYearIndexSet dYear = departYearIndexSetSer.findById(yearId);
        //部门月度旧完成值
        Double oldYearComplete = dYear.getComplete();

        dYear.setComplete(dYear.getComplete() - oldMonComplete + newComplete);
        dYear.setWhetherStandar(dYear.getComplete() > dYear.getTarget() ? "是" : "否");
        dYear.setStandardRate(dYear.getComplete() / dYear.getWager());
        dYear.setExamScore(dYear.getStandardRate() * dYear.getDepartYearWeight());
        dYear.setModifyTime(LocalDateTime.now());

        //修改年指标完成值
        editYearComplete(oldYearComplete, dYear);

        departYearIndexSetSer.update(dYear);

    }

    private void editYearComplete(Double oldYearComplete, DepartYearIndexSet dYear) throws SerException {
        //新的部门年度完成值
        Double newComplete = dYear.getComplete();
        //年度id
        String yearId = dYear.getYearIndexSetId();
        YearIndexSet year = yearIndexSetSer.findById(yearId);

        year.setComplete(year.getComplete() - oldYearComplete + newComplete);
        year.setModifyTime(LocalDateTime.now());

        yearIndexSetSer.update(year);

    }

    @Override

    public void deletePositionIndexSet(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);

    }


    @Override
    public Long countNow(PositionIndexSetDTO positionIndexSetDTO) throws SerException {

        LocalDate now = LocalDate.now();
        String year = now.getYear()+"";
        String month = now.getMonthValue()+"";
        positionIndexSetDTO.getConditions().add(Restrict.eq("year", year));
        positionIndexSetDTO.getConditions().add(Restrict.eq("month", month));
        Long count = super.count(positionIndexSetDTO);
        return count;
    }


    @Override
    public List<PositionIndexSetBO> listNow(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        LocalDate now = LocalDate.now();
        String year = now.getYear()+"";
        String month = now.getMonthValue()+"";
        positionIndexSetDTO.getConditions().add(Restrict.eq("year", year));
        positionIndexSetDTO.getConditions().add(Restrict.eq("month", month));

        List<PositionIndexSet> list = super.findByCis(positionIndexSetDTO, true);
        List<PositionIndexSetBO> listBO = BeanTransform.copyProperties(list, PositionIndexSetBO.class);
        return listBO;
    }


    @Override
    public Long countSelf(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        UserBO userBO = userAPI.currentUser();
        if (StringUtils.isNotBlank(positionIndexSetDTO.getIndexName())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("indexName", positionIndexSetDTO.getIndexName()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getYear())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("year", positionIndexSetDTO.getYear()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getMonth())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("month", positionIndexSetDTO.getMonth()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getPositioner())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("positioner", positionIndexSetDTO.getPositioner()));
        }
        //当前责任人只可以查看自己的
        positionIndexSetDTO.getConditions().add(Restrict.eq("positioner", userBO.getUsername()));
        Long count = super.count(positionIndexSetDTO);
        return count;
    }

    @Override
    public List<PositionIndexSetBO> listSelf(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        UserBO userBO = userAPI.currentUser();
        if (StringUtils.isNotBlank(positionIndexSetDTO.getIndexName())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("indexName", positionIndexSetDTO.getIndexName()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getYear())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("year", positionIndexSetDTO.getYear()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getMonth())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("month", positionIndexSetDTO.getMonth()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getPositioner())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("positioner", positionIndexSetDTO.getPositioner()));
        }
        //当前责任人只可以查看自己的
        positionIndexSetDTO.getConditions().add(Restrict.eq("positioner", userBO.getUsername()));

        List<PositionIndexSet> list = super.findByCis(positionIndexSetDTO, true);
        List<PositionIndexSetBO> listBO = BeanTransform.copyProperties(list, PositionIndexSetBO.class);
        return listBO;
    }

    @Override
    public PositionIndexSetBO addSelf(PositionIndexSetTO positionIndexSetTO) throws SerException {
        UserBO userBO = userAPI.currentUser();
        PositionIndexSet temp = BeanTransform.copyProperties(positionIndexSetTO, PositionIndexSet.class);
        //添加
        temp.setPositioner(userBO.getUsername());
        temp.setWeightSum(0d);
        temp.setWritePerson(userBO.getUsername());
        temp.setWhetherStandar(positionIndexSetTO.getComplete() > positionIndexSetTO.getTarget() ? "是" : "否");
        temp.setStandardRate(positionIndexSetTO.getComplete() / positionIndexSetTO.getWager());
        temp.setExamScore(positionIndexSetTO.getComplete() * positionIndexSetTO.getWeight());
        temp.setPosionIndexPersion(userBO.getUsername());
        temp.setPosionIndexTime(LocalDate.now());
        temp.setSeperateComeStatus(null);
        temp.setDepartMonIndexSetId(null);
        super.save(temp);
        return BeanTransform.copyProperties(temp, PositionIndexSetBO.class);
    }

    @Override
    public PositionIndexSetBO editSelf(PositionIndexSetTO positionIndexSetTO) throws SerException {
        if (StringUtils.isBlank(positionIndexSetTO.getId())) {
            throw new SerException("id不能为空");
        }
        PositionIndexSet temp = super.findById(positionIndexSetTO.getId());
        Double oldComplete = temp.getComplete();


        temp.setIndexName(positionIndexSetTO.getIndexName());
        temp.setYear(positionIndexSetTO.getYear());
        temp.setMonth(positionIndexSetTO.getMonth());
        temp.setIndexType(positionIndexSetTO.getIndexType());
        temp.setDimension(positionIndexSetTO.getDimension());
        temp.setDescribtion(positionIndexSetTO.getDescribtion());
        temp.setDepartment(positionIndexSetTO.getDepartment());
        temp.setDepartYearWeight(positionIndexSetTO.getDepartYearWeight());
        temp.setDepartYearWager(positionIndexSetTO.getDepartYearWager());
        temp.setPosition(positionIndexSetTO.getPosition());
        temp.setWeight(positionIndexSetTO.getWeight());
        temp.setWeightSum(positionIndexSetTO.getWeightSum());
        temp.setTarget(positionIndexSetTO.getTarget());
        temp.setWager(positionIndexSetTO.getWager());
        temp.setComplete(positionIndexSetTO.getComplete());
        temp.setExamWay(positionIndexSetTO.getExamWay());
        temp.setWhetherStandar(positionIndexSetTO.getComplete() > positionIndexSetTO.getTarget() ? "是" : "否");
        temp.setStandardRate(positionIndexSetTO.getComplete() / positionIndexSetTO.getWager());
        temp.setExamScore(positionIndexSetTO.getComplete() * positionIndexSetTO.getWeight());
        temp.setExamDepart(positionIndexSetTO.getExamDepart());
        temp.setDataOrigin(positionIndexSetTO.getDataOrigin());
        temp.setExamDuring(positionIndexSetTO.getExamDuring());
        temp.setModifyTime(LocalDateTime.now());


        //修改上面的完成值
        if (temp.getSeperateComeStatus().equals(SeperateComeStatus.DEPARTMONTH)) {
            //TODO:未做
            //修改部门月指标完成值
            //修改部门年指标完成值
            //修改年指标完成值
            editDepartMonComplete(oldComplete, temp);


        }

        super.update(temp);

        return BeanTransform.copyProperties(temp, PositionIndexSetBO.class);
    }

    /**
     * 导入 excel
     * @param toList
     * @throws SerException
     */
    @Override
    public void leadExcel(List<PositionIndexSetTO> toList) throws SerException {
        UserBO userBO = userAPI.currentUser();
        for (int i = 1; i <= toList.size(); i++) {
            isExist(toList.get(i - 1), i);
        }
        String postIndexNumber = super.findByMaxField("postIndexNumber",PositionIndexSet.class);
        List<PositionIndexSet> list = BeanTransform.copyProperties(toList, PositionIndexSet.class, true);
        list.stream().forEach(str->{
            str.setIndexNumber(0);
            str.setPosionIndexPersion(userBO.getUsername());
            str.setYearIndexNumber(0);
            str.setMonthIndexNumber(0);
            str.setPosionIndexTime(LocalDate.now());
            str.setSeperateComeStatus(SeperateComeStatus.FILL);
            if(postIndexNumber != null)
            {
                str.setPostIndexNumber(Integer.parseInt(postIndexNumber)+1);
            }else if(list.size() >1){
                str.setPostIndexNumber(list.size()+1);
            }else {
                str.setPostIndexNumber(1);
            }
            str.setComplete(null == str.getComplete() ? 0d : str.getComplete());
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });

        super.save(list);
    }

    /**
     * 所有岗位导出  excel
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    public byte[] positionReport(ExportExcelPositTO to) throws SerException {
        PositionIndexSetDTO dto = new PositionIndexSetDTO();
        if(StringUtils.isNotBlank(to.getPost())){
            dto.getConditions().add(Restrict.eq("position", to.getPost() ));
        }
        if(StringUtils.isNotBlank(to.getIndexType())){
            dto.getConditions().add(Restrict.eq("indexType", to.getIndexType() ));
        }
        if(StringUtils.isNotBlank(to.getDimension())){
            dto.getConditions().add(Restrict.eq("dimension", to.getDimension() ));
        }
        if ( StringUtils.isNotBlank(to.getStartTime()) && StringUtils.isNotBlank(to.getEndTime()) ) {
            LocalDate start  = LocalDate.parse(to.getStartTime());
            LocalDate end = LocalDate.parse(to.getEndTime());
            String startYear = String.valueOf(start.getYear());
            String endYear = String.valueOf(end.getYear());
            String startMon = String.valueOf(start.getMonthValue());
            String endMon = String.valueOf(end.getMonthValue());
            String [] years = new String[]{startYear,endYear};
            String [] months = new String[]{startMon,endMon};
            dto.getConditions().add(Restrict.between("year", years ));
            dto.getConditions().add(Restrict.between("month", months ));
        }

        List<PositionIndexSet> list = super.findByCis(dto);
        List<PositionIndexSetExcel> toList = new ArrayList<PositionIndexSetExcel>();
        for (PositionIndexSet model : list) {
            PositionIndexSetExcel excel = new PositionIndexSetExcel();
            BeanUtils.copyProperties(model, excel);
            toList.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    /**
     * 个人岗位导出 excel
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    public byte[] personReport(ExportExcelPositTO to) throws SerException {

        PositionIndexSetDTO dto = new PositionIndexSetDTO();
        if(StringUtils.isNotBlank(to.getPositioner())){
            dto.getConditions().add(Restrict.eq("positioner", to.getPositioner() ));
        }
        if(StringUtils.isNotBlank(to.getIndexType())){
            dto.getConditions().add(Restrict.eq("indexType", to.getIndexType() ));
        }
        if(StringUtils.isNotBlank(to.getDimension())){
            dto.getConditions().add(Restrict.eq("dimension", to.getDimension() ));
        }
        if ( StringUtils.isNotBlank(to.getStartTime()) && StringUtils.isNotBlank(to.getEndTime()) ) {
            LocalDate start  = LocalDate.parse(to.getStartTime());
            LocalDate end = LocalDate.parse(to.getEndTime());
            String startYear = String.valueOf(start.getYear());
            String endYear = String.valueOf(end.getYear());
            String startMon = String.valueOf(start.getMonthValue());
            String endMon = String.valueOf(end.getMonthValue());
            String [] years = new String[]{startYear,endYear};
            String [] months = new String[]{startMon,endMon};
            dto.getConditions().add(Restrict.between("year", years ));
            dto.getConditions().add(Restrict.between("month", months ));
        }


        List<PositionIndexSet> list = super.findByCis(dto);
        List<PositionIndexSetExcel> toList = new ArrayList<PositionIndexSetExcel>();
        for (PositionIndexSet model : list) {
            PositionIndexSetExcel excel = new PositionIndexSetExcel();
            BeanUtils.copyProperties(model, excel);
            toList.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = gudieSeeIdentity2();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
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
            return true;
        }
        return flag;
    }

    //功能导航权限
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
                flag = gudieSeeIdentity2();
                break;
            case EDIT:
                flag = gudieSeeIdentity2();
                break;
            case AUDIT:
                flag = gudieSeeIdentity2();
                break;
            case DELETE:
                flag = gudieSeeIdentity2();
                break;
            case CONGEL:
                flag = gudieSeeIdentity2();
                break;
            case THAW:
                flag = gudieSeeIdentity2();
                break;
            case COLLECT:
                flag = gudieSeeIdentity2();
                break;
            case IMPORT:
                flag = gudieSeeIdentity2();
                break;
            case EXPORT:
                flag = gudieSeeIdentity2();
                break;
            case UPLOAD:
                flag = gudieSeeIdentity2();
                break;
            case DOWNLOAD:
                flag = gudieSeeIdentity2();
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

    /**
     * 查看是否为执行层（岗位级别）
     */
    private Boolean gudieSeeIdentity2() throws SerException {
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

    //校验字段是否存在
    private void isExist(PositionIndexSetTO to, Integer row) throws SerException {
        if(StringUtils.isBlank( to.getIndexName() )){
            throw new SerException("第" + row + "行的指标名称不能为空" );
        }
        if(StringUtils.isBlank( to.getYear() )){
            throw new SerException("第" + row + "行的年份不能为空" );
        }
        if(null== to.getDescribtion()){
            throw new SerException("第" + row + "行的指标权重不能为空" );
        }
        if ( null== to.getTarget()) {
            throw new SerException("第" + row + "行的本月目标值不能为空");
        }
    }

    @Override
    public List<PositionIndexSetBO> dendrogram(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        DepartMonIndexSet temp = departMonIndexSetSer.findById(id);
        PositionIndexSetDTO dto = new PositionIndexSetDTO();
        if(temp.getSeparateStatus() == SeparateStatus.SEPERATE)
        {
            dto.getConditions().add(Restrict.eq("indexNumber",temp.getIndexNumber()));
        }

        List<PositionIndexSet> positionIndexSetList = super.findByCis(dto,true);
        List<PositionIndexSetBO> boList = BeanTransform.copyProperties(temp, PositionIndexSetBO.class);
        return boList;
    }


    @Override
    public byte[] templateExport() throws SerException {
        List<PositionIndexSetExcel> yearIndexExports = new ArrayList<>();

        PositionIndexSetExcel excel = new PositionIndexSetExcel();
        excel.setIndexName("指标名称");
        excel.setYear( "年份" );
        excel.setMonth("月份");
        excel.setIndexType("指标类型");
        excel.setDimension("维度");
        excel.setDepartment("责任部门");
        excel.setDescribtion(12d);
        excel.setDepartYearWeight(13d);
        excel.setDepartYearWager(12d);
        excel.setPosition("责任岗位");
        excel.setPositioner("责任人");
        excel.setPositionerNumber("责任人工号");
        excel.setWeight(11d);
        excel.setWeightSum(10d);
        excel.setTarget(10d);
        excel.setWager(10d);
        excel.setComplete(10d);
        excel.setExamWay("考核方式");
        excel.setWhetherStandar("是否达标");
        excel.setStandardRate(0.1d);
        excel.setExamScore(19d);
        excel.setWritePerson("填报人员");
        excel.setExamDepart("考核部门");
        excel.setDataOrigin("数据来源");
        excel.setExamDuring("考核周期" );
        yearIndexExports.add( excel );

        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(yearIndexExports, exce);
        return bytes;
    }
}
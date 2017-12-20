package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.marketdevelopment.bo.CustomerBO;
import com.bjike.goddess.marketdevelopment.dto.CustomerDTO;
import com.bjike.goddess.marketdevelopment.entity.Customer;
import com.bjike.goddess.marketdevelopment.entity.FilesData;
import com.bjike.goddess.marketdevelopment.entity.WeekFiles;
import com.bjike.goddess.marketdevelopment.enums.GuideAddrStatus;
import com.bjike.goddess.marketdevelopment.enums.Status;
import com.bjike.goddess.marketdevelopment.excel.CustomerExportExcel;
import com.bjike.goddess.marketdevelopment.excel.CustomerImportExcel;
import com.bjike.goddess.marketdevelopment.to.CustomerTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客户接触阶段业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-29 09:37 ]
 * @Description: [ 客户接触阶段业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class CustomerSerImpl extends ServiceImpl<Customer, CustomerDTO> implements CustomerSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private MarPermissionSer marPermissionSer;
    @Autowired
    private FilesDataSer filesDataSer;
    @Autowired
    private WeekFilesSer weekFilesSer;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";

    private static final String planManage = "plan-manage";

    private static final String planCheck = "plan-check";


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
            flag = marPermissionSer.getMarPermission(marketCheck);
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
            flag = marPermissionSer.getMarPermission(marketManage);
        } else {
            flag = true;
        }
        return flag;
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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public List<String> findStage() throws SerException {
        List<Customer> customers = super.findAll();
        if (null != customers && customers.size() > 0) {
            List<String> list = customers.stream().map(Customer::getStage).distinct().collect(Collectors.toList());
            return list;
        }
        return null;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = marPermissionSer.getMarPermission(marketCheck);
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = marPermissionSer.getMarPermission(marketManage);
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<CustomerBO> maps(CustomerDTO dto) throws SerException {
        dto.getSorts().add("code=asc");
        dto.getSorts().add("createTime=asc");
        List<Customer> customers = super.findByPage(dto);
        List<CustomerBO> bos = BeanTransform.copyProperties(customers, CustomerBO.class);
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void save(CustomerTO to) throws SerException {
        //如存在不能保存
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.getConditions().add(Restrict.eq("stage", to.getStage()));
        List<Customer> customers1 = super.findByCis(customerDTO);
        if (null != customers1 && customers1.size() > 0) {
            throw new SerException("该阶段已存在");
        }

        Customer entity = BeanTransform.copyProperties(to, Customer.class);
        char code = 'A';
        List<Customer> customers = super.findAll();
        if (null != customers && customers.size() > 0) {
            List<String> stringList = customers.stream().map(Customer::getStage).distinct().collect(Collectors.toList());
            code = (char) (code + stringList.size());
        }
        entity.setStatus(Status.THAW);
        entity.setCode("YWJD-" + String.valueOf(code));
        int num = 0;
        try {
            num = entity.getResults().split("、").length;
        } catch (Exception e) {
            e.printStackTrace();
        }
        entity.setStageNum(String.valueOf(num));
        super.save(entity);

        //保存到表头数据
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.getSorts().add("code=asc");
        List<Customer> customers2 = super.findByCis(customerDTO1);

        int i = 0;
        for (Customer customer : customers2) {
            FilesData filesData = new FilesData();
            filesData.setTableIndex(i);
            filesData.setTableName(customer.getStage() + customer.getCode());
            filesData.setFatherId("1");
            filesDataSer.save(filesData);

            WeekFiles weekFiles = new WeekFiles();
            weekFiles.setTableIndex(i);
            weekFiles.setTableName(customer.getStage() + customer.getCode());
            weekFiles.setFatherId("1");
            weekFilesSer.save(weekFiles);
            i++;
        }


    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void update(CustomerTO to) throws SerException {
        char code = 'A';
        Customer entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }

        CustomerDTO dto = new CustomerDTO();
        dto.getConditions().add(Restrict.eq("stage", to.getStage()));
        List<Customer> customers = super.findByCis(dto);
        if (null != customers && customers.size() > 0) {
            String id = customers.get(0).getId();
            if (!id.equals(entity.getId())) {
                throw new SerException("不可将阶段改为已经存在的阶段");
            }
        }
        BeanTransform.copyProperties(to, entity);
//        entity.setCode(customers.get(0).getCode());
        entity.setModifyTime(LocalDateTime.now());
        int num = 0;
        try {
            num = entity.getResults().split("、").length;
        } catch (Exception e) {
            e.printStackTrace();
        }
        entity.setStageNum(String.valueOf(num));
        super.update(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congeal(CustomerTO to) throws SerException {
        Customer entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        entity.setStatus(Status.CONGEAL);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thaw(CustomerTO to) throws SerException {
        Customer entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        entity.setStatus(Status.THAW);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(CustomerTO to) throws SerException {
        Customer entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        super.remove(entity);
        List<Customer> customers = super.findAll();
        if (null != customers && customers.size() > 0) {
            List<Customer> customers1 = customers.stream().filter(obj -> obj.getCode().substring(obj.getCode().indexOf("-") + 1, obj.getCode().length()).toCharArray()[0] > entity.getCode().substring(entity.getCode().indexOf("-") + 1, entity.getCode().length()).toCharArray()[0]).sorted(Comparator.comparing(Customer::getCode)).collect(Collectors.toList());
            if (null != customers1 && customers1.size() > 0) {
                for (Customer customer : customers1) {
                    customer.setCode(customer.getCode().substring(0, customer.getCode().indexOf("-") + 1) + String.valueOf(((char) ((customer.getCode().substring(customer.getCode().indexOf("-") + 1, customer.getCode().length())).toCharArray()[0] - 1))));
                    customer.setModifyTime(LocalDateTime.now());
                    super.update(customer);
                }
            }
        }
    }

    @Override
    public CustomerBO getById(String id) throws SerException {
        Customer entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }

        CustomerBO bo = BeanTransform.copyProperties(entity, CustomerBO.class);
        return bo;
    }

    @Override
    public Long getTotal(CustomerDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public byte[] templateExcel() throws SerException {
        CustomerImportExcel customerImportExcel = new CustomerImportExcel();
        List<CustomerImportExcel> list = new ArrayList<>(0);
        list.add(customerImportExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(list, excel);
        return bytes;
    }

    @Override
    public byte[] exportExcel(CustomerDTO dto) throws SerException {
        dto.getSorts().add("code=asc");
        List<Customer> customers = super.findByCis(dto);
        List<CustomerExportExcel> exportExcels = BeanTransform.copyProperties(customers, CustomerExportExcel.class);

        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(exportExcels, excel);
        return bytes;
    }

    @Override
    public void upload(List<CustomerImportExcel> tos) throws SerException {
        for (CustomerImportExcel excel : tos) {
            CustomerTO to = BeanTransform.copyProperties(excel, CustomerTO.class);
            save(to);
        }
    }
}
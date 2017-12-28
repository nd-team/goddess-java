package com.bjike.goddess.subjectcollect.action.subjectcollect;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.subjectcollect.api.SubjectCollectAPI;
import com.bjike.goddess.subjectcollect.bo.FirstSubjectBO;
import com.bjike.goddess.subjectcollect.dto.SubjectCollectsDTO;
import com.bjike.goddess.subjectcollect.to.ExportSubjectCollectTO;
import com.bjike.goddess.subjectcollect.vo.FirstSubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
* 科目汇总
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-26 02:42 ]
* @Description:	[ 科目汇总 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("subjectcollect")
public class SubjectCollectAction extends BaseFileAction{
    @Autowired
    private SubjectCollectAPI subjectCollectAPI;

    /**
     * 列表
     * @param subjectCollectsDTO 更新条件
     * @return class FirstSubjectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(SubjectCollectsDTO subjectCollectsDTO) throws ActException{
        try {
            List<FirstSubjectBO> bos = subjectCollectAPI.collect( subjectCollectsDTO );
            List<FirstSubjectVO> vos = BeanTransform.copyProperties(bos,FirstSubjectVO.class);
            return ActResult.initialize(vos);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出
     * @param to 导出条件
     * @param response
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/exprot")
    public Result exportExcel(ExportSubjectCollectTO to, HttpServletResponse response) throws ActException {
        try {
            String fileName = "科目汇总.xlsx";
            super.writeOutFile(response, subjectCollectAPI.exportExcel(to), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

 }
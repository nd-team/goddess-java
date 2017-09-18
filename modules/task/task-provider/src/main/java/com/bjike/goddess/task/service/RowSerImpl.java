package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.task.dto.RowDTO;
import com.bjike.goddess.task.entity.*;
import com.bjike.goddess.task.util.CglibBeanUtil;
import com.bjike.goddess.task.util.WbUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 行业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class RowSerImpl extends ServiceImpl<Row, RowDTO> implements RowSer {
    @Autowired
    private FieldSer fieldSer;
    @Autowired
    private TableSer tableSer;
    @Autowired
    private RowSer rowSer;
    @Autowired
    private GridSer gridSer;
    @Autowired
    private ValSer valSer;

    @Override
    public List<Object> list(RowDTO dto) throws SerException {
        List<CglibBeanUtil> beans = this.getCglibBean(dto);
        List<Object> objects = new ArrayList<>(beans.size());
        for (CglibBeanUtil bean : beans) {
            objects.add(bean.getObject());
        }
        return objects;
    }

    @Transactional
    @Override
    public void add(Map<String, String> fieldValMap, String tableId, String node) throws SerException {

        List<Field> fields = fieldSer.list(tableId, node);  // 表列
        Row row = new Row();
        row.setTable(tableSer.findById(tableId));
        row.setSeq(rowSer.getSeq(tableId) + 1);
        rowSer.save(row);
        List<Grid> grids = new ArrayList<>();
        for (Map.Entry entry : fieldValMap.entrySet()) { // 表单列&值
            for (Field field : fields) {
                if (field.getName().equals(entry.getKey())) {
                    Val val = new Val();
                    val.setVal(String.valueOf(entry.getValue()));
                    valSer.save(val);
                    Grid grid = new Grid();
                    grid.setField(field);
                    grid.setRow(row);
                    grid.setVal(val);
                    grids.add(grid);
                    break;
                }
            }
        }
        gridSer.save(grids);
    }

    @Override
    public void excelImport(List<InputStream> inputStreams, RowDTO dto) throws SerException {
        String tableId = dto.getTableId();
        String node = dto.getNode();
        if (null != inputStreams && inputStreams.size() > 0) {

            Object o_file = inputStreams.get(1);
            InputStream is = new ByteArrayInputStream((byte[]) o_file);
            XSSFWorkbook wb = null;
            try {
                wb = ExcelUtil.getWb(is);
            } catch (IOException e) {
                throw new SerException("解析excel文件错误,请检查文件是否正确");
            }
            XSSFSheet sheet = wb.getSheetAt(0);
            List<Field> fields = fieldSer.list(tableId, node);
            XSSFRow titleRow = sheet.getRow(0);
            int cellCount = titleRow.getLastCellNum();
            WbUtil.validate(titleRow, fields); //表头对应验证
            Map<String, String> fieldValMap = new HashMap<>();
            int rowCount = sheet.getLastRowNum();
            for (int i = 0; i < rowCount; i++) {
                XSSFRow row = sheet.getRow(i);
                try {
                    for (int j = 0; j < cellCount; j++) {
                        String title = ExcelUtil.getCellValue(titleRow.getCell(j), null);
                        String val = ExcelUtil.getCellValue(row.getCell(j), null);
                        if (StringUtils.isNotBlank(val)) {
                            fieldValMap.put(title, val);
                        }
                    }
                } catch (Exception e) {
                    throw new SerException("获取excel内容错误");
                }

                this.add(fieldValMap, tableId, node);
            }

        } else {
            throw new SerException("没解析到任何上传文件");
        }
    }

    /**
     * Object 无法序列化
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public ByteArrayOutputStream excelExport(RowDTO dto) throws SerException {
        XSSFWorkbook wb = new XSSFWorkbook(); // 创建一个工作execl文档
        Table table = tableSer.findById(dto.getTableId());
        XSSFSheet sheet = wb.createSheet(table.getName() + DateUtil.dateToString(LocalDate.now()));
        List<Field> fields = fieldSer.list(dto.getTableId(), dto.getNode());
        WbUtil.initHeader(wb, sheet, fields); //初始化表头
        List<CglibBeanUtil> beans = this.getCglibBean(dto);
        WbUtil.initRows(wb, sheet, fields, beans); //初始化内容
        ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
        try {
            wb.write(os);
        } catch (IOException e) {
            throw new SerException("文件写入错误");
        }
        return os;
    }

    @Override
    public Integer getSeq(String tableId) throws SerException {
        String sql = "SELECT IFNULL(MAX(seq),0) as seq FROM task_row WHERE tid = '%s' ";
        sql = String.format(sql, tableId);
        List<Object> objs = super.findBySql(sql);
        return Integer.parseInt(String.valueOf(objs.get(0)));
    }

    /**
     * 构建查询sql
     *
     * @param fields
     * @param dto
     * @return
     * @throws SerException
     */
    private String getSql(List<Field> fields, RowDTO dto) throws SerException {
        String cond = "";
        String tableId = dto.getTableId();
        if (StringUtils.isNotBlank(tableId)) {
            if (dto.isPage()) {
                int start = dto.getPage() - 1 * dto.getLimit();
                start = start > 0 ? start : 0;
                int limit = dto.getLimit();
                cond = "limit " + start + "," + limit + "";
            }
            StringBuilder header = new StringBuilder(" SELECT ");
            for (int i = 0; i < fields.size(); i++) {
                String fieldName = fields.get(i).getName();
                header.append(" MAX(CASE name WHEN '" + fieldName + "' THEN name ELSE '' END ) '" + fieldName + "',");
            }
            String tmp_header = header.toString().substring(0, header.toString().length() - 1);
            header = new StringBuilder(tmp_header);
            header.append(" from(");
            header.append("  select name  from task_field where tid='" + tableId + "' order by seq asc) a ");
            header.append(" union all ");

            StringBuilder sb = new StringBuilder(header.toString() + " SELECT ");
            for (int i = 0; i < fields.size(); i++) {
                String fieldName = fields.get(i).getName();
                sb.append(" MAX(CASE name WHEN '" + fieldName + "' THEN value ELSE '' END ) '" + fieldName + "',");
            }
            String str = sb.toString().substring(0, sb.toString().length() - 1);
            sb = new StringBuilder(str);
            sb.append(" FROM( ");
            sb.append(" ( ");
            sb.append("  SELECT name,value,fid,rid,seq  FROM (SELECT b.*  FROM task_table a,task_field b ");
            sb.append("  WHERE a.id='" + tableId + "' AND a.id = b.tid ");
            sb.append("  )a,( ");
            sb.append("   SELECT a.fid,b.id AS rid,c.val AS value  FROM task_grid a , ");
            sb.append("    (select * from task_row order by seq  " + cond + ") b,task_val c WHERE b.tid ='" + tableId + "' ");
            sb.append("     AND a.rid=b.id AND c.id=a.vid) b WHERE a.id=b.fid ORDER BY seq ASC ");
            sb.append("     ) ");
            sb.append("     )a GROUP BY rid ");
            return sb.toString();
        } else {
            throw new SerException("所属表不能为空");
        }

    }

    /**
     * 构建动态bean
     *
     * @param dto
     * @return
     * @throws SerException
     */
    private List<CglibBeanUtil> getCglibBean(RowDTO dto) throws SerException {
        String tableId = dto.getTableId();
        String node = dto.getNode();
        List<Field> fields = fieldSer.list(tableId, node);
        String sql = this.getSql(fields, dto);
        HashMap fieldMap = new HashMap();
        List<CglibBeanUtil> results = new ArrayList<>();
        try {
            //设置表头
            for (Field field : fields) {
                // 1--name 2--seq
                fieldMap.put(String.valueOf(field.getSeq()), Class.forName("java.lang.String"));
            }
            CglibBeanUtil fieldBean = new CglibBeanUtil(fieldMap);
            for (Field field : fields) {
                fieldBean.setValue(String.valueOf(field.getSeq()), field.getName());
            }
            //设置表值
            List<Object> objects = super.findBySql(sql);
            HashMap valMap = new HashMap();
            for (Field field : fields) {
                valMap.put(field.getName(), Class.forName("java.lang.String"));
            }
            Object[] titles = (Object[]) objects.get(0);
            for (int i = 1; i < objects.size(); i++) {
                Object[] values = (Object[]) objects.get(i);
                CglibBeanUtil bean = new CglibBeanUtil(valMap);
                for (int j = 0; j < titles.length; j++) {
                    String val = null != values[j] ? String.valueOf(values[j]) : "";
                    bean.setValue(String.valueOf(titles[j]), val);
                }
                results.add(bean);
            }

        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
        return results;
    }
}

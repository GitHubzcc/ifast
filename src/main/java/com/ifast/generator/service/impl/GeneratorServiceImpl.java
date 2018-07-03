package com.ifast.generator.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifast.common.utils.GenUtils;
import com.ifast.generator.dao.GeneratorMapper;
import com.ifast.generator.service.GeneratorService;

/**
 * <pre>
 * </pre>
 * <small> 2018年3月23日 | Aron</small>
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {
    @Autowired
    GeneratorMapper generatorMapper;

    @Override
    public List<Map<String, Object>> list() {
        List<Map<String, Object>> list = generatorMapper.list();
        return list;
    }

    @Override
    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            // 查询表信息
            Map<String, String> table = new HashMap<>();//= generatorMapper.get(tableName);
            table.put("tableName","sys_wocao");
            table.put("engine","InnoDB");
            table.put("tableComment","");
            table.put("createTime","2018-06-27 08:54:58");
            // 查询列信息
            List<Map<String, String>> columns = new ArrayList<>();//generatorMapper.listColumns(tableName);
            Map<String,String> maps = new HashMap<>();
            maps.put("columnName","id");
            maps.put("dataType","bigint");
            maps.put("columnComment","");
            maps.put("columnKey","PRI");
            maps.put("extra","auto_increment");
            Map<String,String> maps2 = new HashMap<>();
            maps2.put("columnName","mobile");
            maps2.put("dataType","varchar");
            maps2.put("columnComment","");
            maps2.put("columnKey","");
            maps2.put("extra","");
            Map<String,String> maps3 = new HashMap<>();
            maps3.put("columnName","name");
            maps3.put("dataType","varchar");
            maps3.put("columnComment","");
            maps3.put("columnKey","");
            maps3.put("extra","");
            Map<String,String> maps4 = new HashMap<>();
            maps4.put("columnName","passwd");
            maps4.put("dataType","varchar");
            maps4.put("columnComment","");
            maps4.put("columnKey","");
            maps4.put("extra","");
            columns.add(maps);
            columns.add(maps2);
            columns.add(maps3);
            columns.add(maps4);

            // 生成代码
            GenUtils.generatorCode(table, columns, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

}

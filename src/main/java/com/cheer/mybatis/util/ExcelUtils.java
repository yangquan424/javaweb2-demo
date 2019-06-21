package com.cheer.mybatis.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sun.applet.resources.MsgAppletViewer;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {
    /**
     * 从指定路径读取Excel文件，返回类型为List<Map<String,String>>
     */
    public String[] readExcel(String path) throws Exception {
        //ArrayList<Map<String, String>> mapList = new ArrayList<>();
        //Map<String,String[]>
        String[] str = null;
        File file = new File(path);
        //判断文件是否存在
        if (file.isFile() && file.exists()) {
            //System.err.println(file.getPath());
            //获取文件的后缀名 \\ .是特殊字符
            String[] split = file.getName().split("\\.");
            //System.err.println(split[1]);
            Workbook wb;
            //根据文件后缀（xls/xlsx）进行判断
            if ("xls".equals(split[1])) {
//              //获取文件流对象
                FileInputStream inputStream = new FileInputStream(file);
                wb = new HSSFWorkbook(inputStream);
            } else if ("xlsx".equals(split[1])) {
                wb = new XSSFWorkbook(file);
            } else {
                System.out.println("文件类型错误");
                return null;
            }

            //开始解析
            Sheet sheet = wb.getSheetAt(0);
            //第一行是列名，所以从第二行开始遍历
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();
            str = new String[lastRowNum];
            int i = 0;
            //遍历行
            for (int rIndex = firstRowNum; rIndex <= lastRowNum; rIndex++) {
                Map map = new HashMap();
                //获取当前行的内容
                Row row = sheet.getRow(rIndex);
                if (row != null) {
                    int firstCellNum = row.getFirstCellNum();
                    int lastCellNum = row.getLastCellNum();
                    for (int cIndex = firstCellNum; cIndex < lastCellNum; cIndex++) {

                        //获取单元格的值
                        String value = row.getCell(cIndex).getStringCellValue();
                        str[i] = value;
                        //System.out.println(value);
                        //获取此单元格对应第一行的值
                        //String key = i+"";
                        //sheet.getRow(0).getCell(cIndex).getStringCellValue();
                        //System.out.println(key);
                        //第一行中的作为键，第n行的作为值
                        //map.put(key, value);
                        //System.out.println(map);
                        i++;
                    }
                }
                //mapList.add(map);
                //System.out.println("读取成功");
            }
        }
        return str;
    }

}

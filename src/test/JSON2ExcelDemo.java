package test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import xyz.hui_yi.keywords.utils.poi.JSON2ExcelUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

public class JSON2ExcelDemo {
//    public static void main(String[] args) throws IOException {
//        Set<String> keys = null;
//        // 创建HSSFWorkbook对象
//        HSSFWorkbook wb = new HSSFWorkbook();
//        // 创建HSSFSheet对象
//        HSSFSheet sheet = wb.createSheet("sheet0");
//
//        String str = "[" +
//                " {\n" +
//                "      \"c_id\": \"6336\",\n" +
//                "      \"industry\": \"综合\",\n" +
//                "      \"name\": \"\",\n" +
//                "      \"state\": \"1\",\n" +
//                "      \"stockcode\": \"SH603060\",\n" +
//                "      \"stockname\": \"ST慧球\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"c_id\": \"6335\",\n" +
//                "      \"industry\": \"综合\",\n" +
//                "      \"name\": \"\",\n" +
//                "      \"state\": \"1\",\n" +
//                "      \"stockcode\": \"SH600884\",\n" +
//                "      \"stockname\": \"杉杉股份\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"c_id\": \"6334\",\n" +
//                "      \"industry\": \"综合\",\n" +
//                "      \"name\": \"\",\n" +
//                "      \"state\": \"1\",\n" +
//                "      \"stockcode\": \"SH600846\",\n" +
//                "      \"stockname\": \"同济科技\"\n" +
//                "    }" +
//                "]";
//
//
//        JSONArray jsonArray = JSONArray.fromObject(str);
//        int roleNo = 0;
//        int rowNo = 0;
//        for (int j = 0; j < jsonArray.size(); j++) {
//            JSONObject jsonObject = jsonArray.getJSONObject(j);
//            // 创建HSSFRow对象
//            HSSFRow row = sheet.createRow(roleNo++);
//            // 创建HSSFCell对象
//            if (keys == null) {
//                //标题
//                keys = jsonObject.keySet();
//                for (String s : keys) {
//                    HSSFCell cell = row.createCell(rowNo++);
//                    cell.setCellValue(s);
//                }
//                rowNo = 0;
//                row = sheet.createRow(roleNo++);
//            }
//
//            for (String s : keys) {
//                HSSFCell cell = row.createCell(rowNo++);
//                cell.setCellValue(jsonObject.getString(s));
//            }
//            rowNo = 0;
//            System.out.println(rowNo);
//        }
//
//        // 输出Excel文件
//        FileOutputStream output = new FileOutputStream("d://test.xls");
//        wb.write(output);
//        wb.close();
//        output.flush();
//        output.close();
//    }

//    public static void main(String[] args) {
//        String str = "[" +
//                " {\n" +
//                "      \"c_id\": \"6336\",\n" +
//                "      \"industry\": \"综合\",\n" +
//                "      \"name\": \"\",\n" +
//                "      \"state\": \"1\",\n" +
//                "      \"stockcode\": \"SH603060\",\n" +
//                "      \"stockname\": \"ST慧球\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"c_id\": \"6335\",\n" +
//                "      \"industry\": \"综合\",\n" +
//                "      \"name\": \"\",\n" +
//                "      \"state\": \"1\",\n" +
//                "      \"stockcode\": \"SH600884\",\n" +
//                "      \"stockname\": \"杉杉股份\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"c_id\": \"6334\",\n" +
//                "      \"industry\": \"综合\",\n" +
//                "      \"name\": \"\",\n" +
//                "      \"state\": \"1\",\n" +
//                "      \"stockcode\": \"SH600846\",\n" +
//                "      \"stockname\": \"同济科技\"\n" +
//                "    }" +
//                "]";
//        JSONArray jsonArray = JSONArray.fromObject(str);
//        JSON2ExcelUtils.JSON2Excel(jsonArray,"d:/test.xls");
//    }

    public static void main(String[] args) {
        String starttime = "2018-12-22 15:02:05.0";
        starttime = starttime.replaceAll(":",".");
        System.out.println(starttime);
    }
}
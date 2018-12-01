package xyz.hui_yi.keywords.utils.poi;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Set;

public class JSON2ExcelUtils {
    /**
     * JSON2Excel
     * @param jsonArray json数组
     * @param filename 文件名 D:/test.xls
     * @return
     */
    public static File JSON2Excel(JSONArray jsonArray, String filename) {
        Set<String> keys = null;
        // 创建HSSFWorkbook对象
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建HSSFSheet对象
        HSSFSheet sheet = wb.createSheet("sheet0");
        int roleNo = 0;
        int rowNo = 0;
        for (int j = 0; j < jsonArray.size(); j++) {
            JSONObject jsonObject = jsonArray.getJSONObject(j);
            // 创建HSSFRow对象
            HSSFRow row = sheet.createRow(roleNo++);
            // 创建HSSFCell对象
            if (keys == null) {
//                表头
                keys = jsonObject.keySet();
                for (String s : keys) {
                    HSSFCell cell = row.createCell(rowNo++);
                    cell.setCellValue(s);
                }
                rowNo = 0;
                row = sheet.createRow(roleNo++);
            }
            for (String s : keys) {
                HSSFCell cell = row.createCell(rowNo++);
                cell.setCellValue(jsonObject.getString(s));
            }
            rowNo = 0;
        }
        // 输出Excel文件
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(filename);
//            output = new FileOutputStream("test.xls");
            wb.write(output);
            wb.close();
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new File(filename);
    }
}

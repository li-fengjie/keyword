package test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xyz.hui_yi.keywords.bean.CompanyBean;
import xyz.hui_yi.keywords.bean.CompanyPageBean;
import xyz.hui_yi.keywords.dao.CompanyDao;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class JsonpDemo {
    public static void main(String[] args) throws IOException {
        String url = "https://xueqiu.com/stock/search.json?code=";
        String fileDir = "D:\\文件\\项目文档\\关键词\\pdf\\";
        String cookie = "aliyungf_tc=AQAAALYzAl40CQ0AKk60Pf0rWFfyxvWF; device_id=dd7c108c19ce824d4cf4af50d0ddd9db; _ga=GA1.2.247964988.1542345374; __utmc=1; xq_a_token=6125633fe86dec75d9edcd37ac089d8aed148b9e; xq_a_token.sig=CKaeIxP0OqcHQf2b4XOfUg-gXv0; xq_r_token=335505f8d6608a9d9fa932c981d547ad9336e2b5; xq_r_token.sig=i9gZwKtoEEpsL9Ck0G7yUGU42LY; u=891543482395923; Hm_lvt_1db88642e346389874251b5a1eded6e3=1542345430,1542961268,1542962201,1543482396; _gid=GA1.2.1578910268.1543482396; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1543486945; __utma=1.247964988.1542345374.1543486946.1543486946.1; __utmz=1.1543486946.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmt=1; __utmb=1.1.10.154348694";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36";
        /**
         * 获取股票代码
         */
        CompanyDao companyDao = new CompanyDao();
        CompanyPageBean companyPageBean = companyDao.queryCompanyPageBean();
        List<CompanyBean> companyBeans = companyPageBean.getCompanyBeans();
        for (CompanyBean c :
                companyBeans) {
            String stockname = c.getStockname();
            String Url = url + stockname;
            String result = getURLContent(Url,cookie,userAgent);
            JSONObject jsonObject = JSONObject.fromObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("stocks");
            String c_id = c.getC_id();
            if(jsonArray.size() > 0){
                String code = (String) jsonArray.getJSONObject(0).get("code");
                companyDao.updateCompanyBean(c_id,code);
                System.out.println(code);
            }else {
                System.out.println(c_id + "空");
                continue;
            }

        }
    }

    public static String getURLContent(String urlPath,String cookie,String userAgent) throws IOException {
        URL url = new URL(urlPath);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("Cookie", cookie);
        conn.setRequestProperty("User-Agent",userAgent);
        conn.setDoInput(true);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        System.out.println("请求响应结果："+sb);
        return sb.toString();
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}

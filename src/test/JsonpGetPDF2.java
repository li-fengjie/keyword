package test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xyz.hui_yi.keywords.bean.CompanyBean;
import xyz.hui_yi.keywords.bean.CompanyPageBean;
import xyz.hui_yi.keywords.dao.CompanyDao;
import xyz.hui_yi.keywords.dao.FileDao;
import xyz.hui_yi.keywords.utils.commons.CommonsUtils;
import xyz.hui_yi.keywords.utils.pdf.Pdf2String;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class JsonpGetPDF2 {
    public static void main(String[] args) throws IOException {
//        String url = "https://xueqiu.com/statuses/stock_timeline.json?" +
//                "count=20" +
//                "&source=%E5%85%AC%E5%91%8A" +
//                "&page=1" +
//                "&symbol_id=SH600556";
        String fileDir = "D:\\文件\\项目文档\\关键词\\pdf";
        String cookie = "aliyungf_tc=AQAAALYzAl40CQ0AKk60Pf0rWFfyxvWF; device_id=dd7c108c19ce824d4cf4af50d0ddd9db; _ga=GA1.2.247964988.1542345374; __utmc=1; xq_a_token=6125633fe86dec75d9edcd37ac089d8aed148b9e; xq_a_token.sig=CKaeIxP0OqcHQf2b4XOfUg-gXv0; xq_r_token=335505f8d6608a9d9fa932c981d547ad9336e2b5; xq_r_token.sig=i9gZwKtoEEpsL9Ck0G7yUGU42LY; u=891543482395923; Hm_lvt_1db88642e346389874251b5a1eded6e3=1542345430,1542961268,1542962201,1543482396; _gid=GA1.2.1578910268.1543482396; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1543486945; __utma=1.247964988.1542345374.1543486946.1543486946.1; __utmz=1.1543486946.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmt=1; __utmb=1.1.10.154348694";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36";

        CompanyDao companyDao = new CompanyDao();
        FileDao fileDao = new FileDao();
        CompanyPageBean companyPageBean = companyDao.queryCompanyPageBean();
        List<CompanyBean> companyBeans = companyPageBean.getCompanyBeans();
        for (int k = companyBeans.size() - 1; k > 0; k--) {
            try {
                CompanyBean c = companyBeans.get(k);
                if(c.getState() != null && c.getState().equals("1")){
                    continue;
                }
                if(Integer.parseInt(c.getC_id()) > 6258){
                    continue;
                }
                companyDao.stopCompanyBean(Integer.parseInt(c.getC_id()));
                System.out.println("第" + c.getC_id());
                String stockcode = c.getStockcode();
                String url = "https://xueqiu.com/statuses/stock_timeline.json?" +
                        "count=20" +
                        "&source=%E5%85%AC%E5%91%8A";
                url += "&page=1&symbol_id=" + stockcode;
                String result = getURLContent(url, cookie, userAgent);
                JSONObject jsonObject = JSONObject.fromObject(result);
                int maxPage = jsonObject.getInt("maxPage");
                for (int j = 1; j <= maxPage; j++) {
                    url = "https://xueqiu.com/statuses/stock_timeline.json?" +
                            "count=20" +
                            "&source=%E5%85%AC%E5%91%8A" +
                            "&page=" + j +
                            "&symbol_id=" + stockcode;
                    result = getURLContent(url, cookie, userAgent);
                    jsonObject = JSONObject.fromObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("list");
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONArray jsonArray1 = (JSONArray) jsonArray.getJSONObject(i).get("quote_cards");
                        String filename = ((String) jsonArray.getJSONObject(i).get("description")).split("\\s")[0];
                        JSONObject jsonObject1 = (JSONObject) jsonArray1.get(0);
                        String pdfUrl = (String) jsonObject1.get("target_url");
                        System.out.println(filename + " " + pdfUrl);
                        String name = CommonsUtils.getUUID() + "_" + filename;
                        String path = fileDir + "/" + name + ".PDF";
                        System.out.println(path);
                        saveData(path, filename, pdfUrl);
                        String content = Pdf2String.pdf2String(path);
                        fileDao.insertToFileBean(null, filename, content, c.getC_id(), path);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
//                break;
                continue;
            }

        }
    }

    private static void saveData(String path, String name, String url) {
        HttpURLConnection conn = null;
        try {
            URL url1 = new URL(url);
            conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream inputStream = conn.getInputStream();
                //得到图片的二进制数据，以二进制封装得到数据，具有通用性
                byte[] data = readInputStream(inputStream);
//                String path = fileDir +"/";
                File file = new File(path);
                FileOutputStream outStream = new FileOutputStream(file);
                outStream.write(data);
                outStream.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getURLContent(String urlPath, String cookie, String userAgent) throws IOException {
        URL url = new URL(urlPath);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("Cookie", cookie);
        conn.setRequestProperty("User-Agent", userAgent);
        conn.setDoInput(true);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
//        System.out.println("请求响应结果：" + sb);
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

package myTestProject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 李政
 * 2017/8/11
 */
public class ProcessFile {


    private static void processFile() {

        try {
            List<File> files = getFiles("C:\\Users\\李政\\Desktop\\ASR\\TiNetAsr\\2315003");

            List<JSONObject> allAsr = new ArrayList<>(10000);
            for (File f : files) {

                BufferedReader in = new BufferedReader(new FileReader(f));
                String str;
                StringBuilder stringBuilder = new StringBuilder();
                while ((str = in.readLine()) != null) {
                    stringBuilder.append(str);
                }

                in.close();
                JSONObject jsonObject = JSON.parseObject(stringBuilder.toString());
                allAsr.add(jsonObject);

            }

            // 新方法效率高
            Map<String, Map<String, String>> stringMapMap2 = new HashMap<>();
            for (JSONObject one : allAsr) {
                String uniqueId = one.getString("uniqueId");
                String recordSide = one.getString("recordSide");
                Integer callType = one.getInteger("callType");

                String result = one.getJSONObject("jsonResult").getJSONArray("result").toJSONString();

                String data;
                if (callType == 1 || callType == 2) {
                    if (recordSide.equals("OUT")) {
                        data = "坐席侧：" + result;
                    } else {
                        data = "客户侧：" + result;
                    }
                } else {
                    if (recordSide.equals("OUT")) {
                        data = "客户侧：" + result;
                    } else {
                        data = "坐席侧：" + result;
                    }
                }


                Map<String, String> inner;
                if (!stringMapMap2.containsKey(uniqueId)) {
                    inner = new HashMap<>();
                    inner.put(recordSide, data);
                } else {
                    inner = stringMapMap2.get(uniqueId);
                    inner.put(recordSide, data);
                }
                stringMapMap2.put(uniqueId, inner);
            }

            System.out.println("new method size：" + stringMapMap2.size());


            File file = new File("C:\\Users\\李政\\Desktop\\AsrResult3005132.txt");
            file.createNewFile();
            FileWriter fileWritter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWritter);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("排队时长", "3分钟");
            jsonObject.put("通话时长", "2分钟");
            jsonObject.put("访客姓名", "默认访客姓名");
            jsonObject.put("客服工号", "000000");
            jsonObject.put("会话id", "111111");
            jsonObject.put("访客id", "222222");
            jsonObject.put("访客性别", "男");
            jsonObject.put("用户评分", "满意");
            jsonObject.put("会话开始时间", "1516089131");
            jsonObject.put("会话结束时间", "1516089136");
//            jsonObject.put("排队时长","");
//            jsonObject.put("排队时长","");

            int lineNume = 1;
            for (Map<String, String> inAndOut : stringMapMap2.values()) {

                for (String asr : inAndOut.values()) {
                    //bufferedWriter.write(lineNume + ":" + asr);
                    if (asr.contains("客户侧：")) {
                        jsonObject.put("访客内容", getTextDevideBySpace(asr));
                    } else {
                        jsonObject.put("客服内容", getTextDevideBySpace(asr));
                    }
                    bufferedWriter.newLine();
                }
                bufferedWriter.write(jsonObject.toJSONString());
                lineNume++;
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.getStackTrace();
        }


    }

    private static List<File> getFiles(String path) {
        File root = new File(path);
        List<File> files = new ArrayList<File>();
        if (!root.isDirectory()) {
            files.add(root);
        } else {
            File[] subFiles = root.listFiles();
            for (File f : subFiles) {
                files.addAll(getFiles(f.getAbsolutePath()));
            }
        }
        return files;
    }

    private static String getTextDevideBySpace(String asrArrayStringWithPrefix) {
        String asrArrayString = asrArrayStringWithPrefix.substring(4);
        JSONArray jsonArray = JSON.parseArray(asrArrayString);

        StringBuilder stringBuilder = new StringBuilder();

        for (Object sentence : jsonArray) {
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(sentence));
            stringBuilder.append(jsonObject.getString("text"));
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }


    public static void main(String[] args) {

        processFile();
    }
}

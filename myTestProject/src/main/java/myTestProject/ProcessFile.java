package myTestProject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
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
            List<File> files = getFiles("C:\\Users\\李政\\Desktop\\TiNetAsr\\2315003");

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


            // 旧方法效率低
            /*Map<String, Map<String, String>> stringMapMap = new HashMap<>();
            for (JSONObject one : allAsr) {
                String uniqueId = one.getString("uniqueId");
                String recordSide = one.getString("recordSide");

                String result = one.getJSONObject("jsonResult").getJSONArray("result").toJSONString();
                String data = recordSide + result;


                Map<String, String> inner = new HashMap<>();
                inner.put(recordSide, data);


                for (JSONObject two : allAsr) {
                    String uniqueId2 = two.getString("uniqueId");
                    String recordSide2 = two.getString("recordSide");
                    if (uniqueId.equals(uniqueId2) && !recordSide.equals(recordSide2)) {
                        String result2 = two.getJSONObject("jsonResult").getJSONArray("result").toJSONString();
                        String data2 = recordSide2 + result2;

                        inner.put(recordSide2, data2);

                    }
                }
                stringMapMap.put(uniqueId, inner);
            }*/

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


            File file = new File("C:\\Users\\李政\\Desktop\\TiNetAsr\\2315003\\AsrResult2315003.txt");
            file.createNewFile();
            FileWriter fileWritter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWritter);
            int lineNume = 1;
            for (Map<String, String> inAndOut : stringMapMap2.values()) {

                for (String asr : inAndOut.values()) {
                    bufferedWriter.write(lineNume + ":" + asr);
                    bufferedWriter.newLine();
                }
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


    public static void main(String[] args) {

        processFile();
    }
}

package myTestProject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 李政
 * 2017/8/11
 */
public class ProcessFile {


    private static void processFile() {

        try {
            List<File> files = getFiles("C:\\Users\\李政\\Desktop\\3003802");
            int lineNume = 1;
            File file = new File("C:\\Users\\李政\\Desktop\\3003802\\AsrResult.txt");


            file.createNewFile();

            FileWriter fileWritter = new FileWriter(file, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWritter);

            for (File f : files) {

                BufferedReader in = new BufferedReader(new FileReader(f));
                String str;
                StringBuilder stringBuilder = new StringBuilder();
                while ((str = in.readLine()) != null) {
                    stringBuilder.append(str);
                }

                in.close();
                JSONObject jsonObject = JSON.parseObject(stringBuilder.toString());
                String result = jsonObject.getJSONObject("jsonResult").getJSONArray("result").toJSONString();
                System.out.println(lineNume);
                String data = lineNume + ":" + result;
                bufferedWriter.write(data);
                bufferedWriter.newLine();
                lineNume++;

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

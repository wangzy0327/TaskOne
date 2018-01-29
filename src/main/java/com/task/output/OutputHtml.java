package com.task.output;

import com.task.util.ContentUtil;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class OutputHtml{
    public void way_1(Map<String,String> map){
        InputStream inputStream;//接收字节输入流
        InputStreamReader inputStreamReader;//将字节输入流转换成字符输入流
        BufferedReader bufferedReader;//为字符输入流加缓冲
        FileOutputStream fileOutputStream;//字节输出流
        OutputStreamWriter outputStreamWriter;//将字节输出流转换成字符输出流

        for(Map.Entry<String,String> entry:map.entrySet()){
            String title = entry.getKey();
            String content = entry.getValue();

            URL url = null;
            try {
                url = new URL(content);
                inputStream = url.openStream();
                inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                bufferedReader = new BufferedReader(inputStreamReader);
                String s;
                File file = new File("src/main/webapp/"+title+".html");
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
                fileOutputStream = new FileOutputStream(file);
                outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
                while ((s = bufferedReader.readLine()) != null) {
                    outputStreamWriter.write(s);
                }

                outputStreamWriter.close();
                fileOutputStream.close();
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

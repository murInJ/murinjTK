package IO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @title: File
 * @Author MurInj
 * @Date: 2022/8/21 15:00
 * @Version 1.0
 */

public class FileTK{
    public static boolean check(String Path) throws IOException{
        File file = new File(Path);

        if(!file.exists()){
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }

            if(isDirectory(Path)){
                return file.mkdirs();
            }
            else{
                return file.createNewFile();
            }
        }

        return true;
    }

    public static boolean check(String Path,boolean isCreate) throws IOException{
        File file = new File(Path);

        if(!file.exists()){
            if(isCreate){
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
                if(isDirectory(Path)){
                    return file.mkdirs();
                }
                else{
                    return file.createNewFile();
                }
            }
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean isDirectory(String Path){
        String[] splitPath = Path.split("[/(\\\\)]");
        int len = splitPath.length;
        String[] sp = new StringBuilder(splitPath[len - 1]).append("F").substring(1).split("\\.");
        return sp.length == 1;
    }

    public static String readFile(String jsonFile){
        StringBuilder strbuilder = new StringBuilder();
        try(BufferedReader br= Files.newBufferedReader(Paths.get(jsonFile))) {
            String str;
            while ((str=br.readLine())!=null){
                strbuilder.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strbuilder.toString();
    }

    public static String readFile(String jsonFile,boolean isBuffer){
        StringBuilder strbuilder = new StringBuilder();
        try(BufferedReader br= isBuffer ? new BufferedReader(new FileReader(jsonFile)) : Files.newBufferedReader(Paths.get(jsonFile))) {
            String str;
            while ((str=br.readLine())!=null){
                strbuilder.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strbuilder.toString();
    }

    public static void writeFile(String filepath, String content) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath))) {
            bufferedWriter.write(content);
        }
    }
}

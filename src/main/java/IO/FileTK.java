package IO;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
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

    public static String readFile(String filePath){
        StringBuilder strbuilder = new StringBuilder();
        try(BufferedReader br= Files.newBufferedReader(Paths.get(filePath))) {
            String str;
            while ((str=br.readLine())!=null){
                strbuilder.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strbuilder.toString();
    }

    public static String readFile(String filePath,boolean isBuffer){
        StringBuilder strbuilder = new StringBuilder();
        try(BufferedReader br= isBuffer ? new BufferedReader(new FileReader(filePath)) : Files.newBufferedReader(Paths.get(filePath))) {
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

    public static byte[] readBinFile(String filename) throws IOException {

        FileChannel fc = null;
        try {
            fc = new RandomAccessFile(filename, "r").getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0,
                    fc.size()).load();
            System.out.println(byteBuffer.isLoaded());
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                // System.out.println("remain");
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                fc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void execFile(String filePath){
        if (java.awt.Desktop.isDesktopSupported()) {
            try {
                java.awt.Desktop dp = java.awt.Desktop.getDesktop();
                if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    dp.open(new File(filePath));

                }
            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package src.main.java;

import java.io.File;
import java.io.IOException;

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
            if(file.isFile()){
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
                return file.createNewFile();
            }
            if(file.isDirectory()){
                return file.mkdirs();
            }
        }

        return true;
    }

    public static boolean check(String Path,boolean isCreate) throws IOException{
        File file = new File(Path);

        if(!file.exists()){
            if(isCreate){
                if(file.isFile()){
                    if(!file.getParentFile().exists()){
                        file.getParentFile().mkdirs();
                    }
                    return file.createNewFile();
                }
                if(file.isDirectory()){
                    return file.mkdirs();
                }
            }
            return false;
        }
        else{
            return true;
        }
    }
}

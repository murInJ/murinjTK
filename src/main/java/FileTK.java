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
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }

            if(file.isDirectory()){
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
                if(file.isDirectory()){
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
}

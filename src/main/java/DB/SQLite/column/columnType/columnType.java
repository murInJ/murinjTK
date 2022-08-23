package DB.SQLite.column.columnType;

import java.util.Locale;

/**
 * @title: columnType
 * @Author MurInj
 * @Date: 2022/8/21 21:29
 * @Version 1.0
 */

public abstract class columnType {
    public static columnType str2ColType(String str) throws Exception {
        str = str.toUpperCase(Locale.ROOT);
        if(str.equals("INTEGER")){
            return new INTEGER();
        }
        else if(str.substring(0,7).equals("VARCHAR")){
            String cap;
            cap = str.replaceFirst("[^0-9]","");
            return new VARCHAR(Integer.parseInt(cap));
        }
        else {
            throw new Exception("unkonwn column type");
        }
    }
}

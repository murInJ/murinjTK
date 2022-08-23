package DB.SQLite.column.columnDefinition;

import java.util.Locale;

/**
 * @title: columnDefinition
 * @Author MurInj
 * @Date: 2022/8/21 21:08
 * @Version 1.0
 */

public abstract class columnDefinition {
    public static columnDefinition str2colDef(String str) throws Exception {
        str = str.toUpperCase(Locale.ROOT);
        if(str.equals("PRIMARY")){
            return new DB.SQLite.column.columnDefinition.PRIMARY_KEY();
        }
        else if(str.equals("AUTO_INCREMENT")){
            return new DB.SQLite.column.columnDefinition.AUTO_INCREMENT();
        }
        else{
            throw new Exception("unknown def");
        }
    }
}


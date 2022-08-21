package src.main.java.DB.SQLite.column.columnType;

/**
 * @title: VARCHAR
 * @Author MurInj
 * @Date: 2022/8/21 21:30
 * @Version 1.0
 */

public class VARCHAR extends columnType{
    private final int cap;
    public VARCHAR(int cap){
        this.cap = cap;
    }
    @Override
    public String toString() {
        return "VARCHAR("+ cap +")";
    }
}

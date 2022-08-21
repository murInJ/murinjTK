package src.main.java.DB.SQLite;

import src.main.java.DB.SQLite.column.DBcolumn;
import src.main.java.DB.SQLite.column.columnDefinition.columnDefinition;
import src.main.java.FileTK;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @title: SQLiteTK
 * @Author MurInj
 * @Date: 2022/8/21 16:30
 * @Version 1.0
 */

public class SQLiteTK extends SQLite {
    public SQLiteTK(String Path,boolean isCreate) throws SQLException, IOException, ClassNotFoundException {
        super(Path,isCreate);
    }

    public static boolean CreateDB(String Path,String DBname) throws IOException {
        String fileName = Path + "\\" + DBname + ".db";
        return FileTK.check(fileName);
    }

    public void CreateTable(String tableName) throws SQLException, ClassNotFoundException {
        String sql = "create table if not exists " + tableName + "(id integer primary key autoincrement)";
        executeUpdate(sql);
    }

    public int Insert(String tableName,String column,String value) throws SQLException, ClassNotFoundException {
        String sql = "insert into "+tableName+"("+column+") values ("+value+")";
        return executeUpdate(sql);
    }

    public void AddColumns(String tableName,DBcolumn... cols) throws SQLException, ClassNotFoundException {
        String[] sqls = new String[cols.length];
        for(int index = 0; index < cols.length; ++index){
            sqls[index] = "alter table " + tableName + " add " + cols[index].getName() + " " + cols[index].getDefinition();
        }
        executeUpdate(sqls);
    }

    public void AddColumn(String tableName,DBcolumn col) throws SQLException, ClassNotFoundException {
        String sql = "alter table " + tableName + " add " + col.getName() + " " + col.getDefinition();
        executeUpdate(sql);
    }
}

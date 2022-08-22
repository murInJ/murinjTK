package src.main.java.DB.SQLite;

import org.jetbrains.annotations.NotNull;
import src.main.java.DB.SQLite.column.DBcolumn;
import src.main.java.DB.SQLite.column.columnDefinition.columnDefinition;
import src.main.java.FileTK;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @title: SQLiteTK
 * @Author MurInj
 * @Date: 2022/8/21 16:30
 * @Version 1.1.3
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

    public int Insert(String tableName, String @NotNull [] columns, String @NotNull [] values) throws SQLException, ClassNotFoundException {
        assert columns.length == values.length;
        StringBuilder col = new StringBuilder(columns[0]),val = new StringBuilder(values[0]);
        for(int index = 1;index < columns.length;index++){
            col.append(",").append(columns[index]);
            val.append(",").append(values[index]);
        }
        String sql = "insert into "+tableName+"("+ col +") values ("+ val +")";
        return executeUpdate(sql);
    }

    public int Delete(String tableName,String where) throws SQLException, ClassNotFoundException {
        String sql = "delete from " + tableName + " where " + where;
        return executeUpdate(sql);
    }

    public void AddColumns(String tableName,DBcolumn @NotNull ... cols) throws SQLException, ClassNotFoundException {
        String[] sqls = new String[cols.length];
        for(int index = 0; index < cols.length; ++index){
            sqls[index] = "alter table " + tableName + " add " + cols[index].getName() + " " + cols[index].getDefinition();
        }
        executeUpdate(sqls);
    }

    public void AddColumn(String tableName, @NotNull DBcolumn col) throws SQLException, ClassNotFoundException {
        String sql = "alter table " + tableName + " add " + col.getName() + " " + col.getDefinition();
        executeUpdate(sql);
    }

    public List<Object> Query(String tableName, String @NotNull [] columnsName, String where) throws SQLException, ClassNotFoundException {
        StringBuilder cols = new StringBuilder(columnsName[0]);
        for(int index = 1; index < columnsName.length;++index){
            cols.append(",").append(columnsName[index]);
        }
        String sql = "select " + cols + " from " + tableName + " where " + where;

        return executeQuery(sql, rs -> {
            return (List<Object>) new ArrayList<Object>((Collection<?>) rs);
        });
    }

    public List<Object> Query(String tableName,String columnName,String where) throws SQLException, ClassNotFoundException {
        String sql = "select " + columnName + " from " + tableName + " where " + where;

        return executeQuery(sql, rs -> {
            return (List<Object>) new ArrayList<Object>((Collection<?>) rs);
        });
    }

    public List<Object> Query(String tableName,String @NotNull [] columnsName) throws SQLException, ClassNotFoundException {
        StringBuilder cols = new StringBuilder(columnsName[0]);
        for(int index = 1; index < columnsName.length;++index){
            cols.append(",").append(columnsName[index]);
        }
        String sql = "select " + cols + " from " + tableName;

        return executeQuery(sql, rs -> {
            return (List<Object>) new ArrayList<Object>((Collection<?>) rs);
        });
    }

    public List<Object> Query(String tableName,String columnName) throws SQLException, ClassNotFoundException {
        String sql = "select " + columnName + " from " + tableName;

        return executeQuery(sql, rs -> {
            return (List<Object>) new ArrayList<Object>((Collection<?>) rs);
        });
    }

    public int Update(String tableName,String[] columnsName,String[] values,String where) throws SQLException, ClassNotFoundException {
        StringBuilder sql = new StringBuilder("update " + tableName + " set ");
        sql.append(",").append(columnsName[0]).append("=").append(values[0]);
        for(int index= 1;index < columnsName.length;++index){
            sql.append(",").append(columnsName[index]).append("=").append(values[index]);
        }
        sql.append(" where ").append(where);
        return executeUpdate(sql.toString());
    }

    public int Update(String tableName,String[] columnsName,String[] values) throws SQLException, ClassNotFoundException {
        StringBuilder sql = new StringBuilder("update " + tableName + " set ");
        sql.append(",").append(columnsName[0]).append("=").append(values[0]);
        for(int index= 1;index < columnsName.length;++index){
            sql.append(",").append(columnsName[index]).append("=").append(values[index]);
        }
        return executeUpdate(sql.toString());
    }

    public int Update(String tableName,String columnName,String value,String where) throws SQLException, ClassNotFoundException {
        String sql = "update "+tableName+" set " + columnName + "=" + value + " where " + where;
        return executeUpdate(sql.toString());
    }

    public int Update(String tableName,String columnName,String value) throws SQLException, ClassNotFoundException {
        String sql = "update "+tableName+" set " + columnName + "=" + value;
        return executeUpdate(sql.toString());
    }
}

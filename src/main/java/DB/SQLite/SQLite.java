package DB.SQLite;



import IO.FileTK;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @title: SQLite
 * @Author MurInj
 * @Date: 2022/8/21 15:33
 * @Version 1.0
 */

public class SQLite {
    protected Connection connection;
    protected Statement statement;
    protected ResultSet resultSet;
    protected String dbFilePath;

    /**
     * 构造函数
     * @param dbFilePath sqlite db 文件路径
     * @param isCreate 如果路径不存在db文件，是否新建
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public SQLite(String dbFilePath, boolean isCreate) throws ClassNotFoundException, SQLException, IOException {
        FileTK.check(dbFilePath,isCreate);
        this.dbFilePath = dbFilePath;
        connection = getConnection(dbFilePath);
    }

    /**
     * 获取数据库连接
     * @param dbFilePath db文件路径
     * @return 数据库连接
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConnection(String dbFilePath) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        // 1、加载驱动
        Class.forName("org.sqlite.JDBC");
        // 2、建立连接
        // NOTIC:如果后面的 dbFilePath 路径太深或者名称太长，则建立连接会失败
        conn = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath);
        return conn;
    }

    /**
     * 执行sql查询
     * @param sql sql select 语句
     * @param rse 结果集处理类对象
     * @return 查询结果
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public <T> T executeQuery(String sql, DB.SQLite.ResultSetExtractor<T> rse) throws SQLException, ClassNotFoundException {
        try {
            resultSet = getStatement().executeQuery(sql);
            T rs = rse.extractData(resultSet);
            return rs;
        } finally {
            destroyed();
        }
    }

    /**
     * 执行select查询，返回结果列表
     *
     * @param sql sql select 语句
     * @param rm 结果集的行数据处理类对象
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public <T> List<T> executeQuery(String sql, DB.SQLite.RowMapper<T> rm) throws SQLException, ClassNotFoundException {
        List<T> rsList = new ArrayList<T>();
        try {
            resultSet = getStatement().executeQuery(sql);
            while (resultSet.next()) {
                rsList.add(rm.mapRow(resultSet, resultSet.getRow()));
            }
        } finally {
            destroyed();
        }
        return rsList;
    }

    /**
     * 执行数据库更新sql语句
     * @param sql
     * @return 更新行数
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public  int executeUpdate(String sql) throws SQLException, ClassNotFoundException {
        try {
            return getStatement().executeUpdate(sql);
        } finally {
            destroyed();
        }

    }

    /**
     * 执行多个sql更新语句
     * @param sqls
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void executeUpdate(String...sqls) throws SQLException, ClassNotFoundException {
        try {
            for (String sql : sqls) {
                getStatement().executeUpdate(sql);
            }
        } finally {
            destroyed();
        }
    }

    /**
     * 执行数据库更新 sql List
     * @param sqls sql列表
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void executeUpdate(List<String> sqls) throws SQLException, ClassNotFoundException {
        try {
            for (String sql : sqls) {
                getStatement().executeUpdate(sql);
            }
        } finally {
            destroyed();
        }
    }



    private Connection getConnection() throws ClassNotFoundException, SQLException {
        if (null == connection) connection = getConnection(dbFilePath);
        return connection;
    }

    private  Statement getStatement() throws SQLException, ClassNotFoundException {
        if (null == statement) statement = getConnection().createStatement();
        return statement;
    }

    /**
     * 数据库资源关闭和释放
     */
    public void destroyed() {
        try {
            if (null != connection) {
                connection.close();
                connection = null;
            }

            if (null != statement) {
                statement.close();
                statement = null;
            }

            if (null != resultSet) {
                resultSet.close();
                resultSet = null;
            }
        } catch (SQLException e) {
            System.out.println("Sqlite数据库关闭时异常 "+ e);
        }
    }

    /**
     * 是否自动提交事务
     */
    public void setAutoCommit(Boolean status) throws SQLException {
        connection.setAutoCommit(status);
    }


}


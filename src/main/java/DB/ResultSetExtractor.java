package src.main.java.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetExtractor<T> {

    public abstract T extractData(ResultSet rs) throws SQLException;

}

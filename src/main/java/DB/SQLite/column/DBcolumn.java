package src.main.java.DB.SQLite.column;

import src.main.java.DB.SQLite.column.columnDefinition.columnDefinition;
import src.main.java.DB.SQLite.column.columnType.columnType;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @title: DBcolumn
 * @Author MurInj
 * @Date: 2022/8/21 21:28
 * @Version 1.0
 */

public class DBcolumn{
    private String name;
    private columnType type;
    private Set<columnDefinition>definition;

    public DBcolumn(String name,columnType type){
        this.name = name;
        this.type = type;
    }

    public DBcolumn(String name,columnType type,columnDefinition... definitions){
        this.name = name;
        this.type = type;
        definition = new HashSet<>();
        definition.addAll(List.of(definitions));
    }

    public void AddDefinition(columnDefinition def){
        if(definition == null) definition = new HashSet<>();
        definition.add(def);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type.toString();
    }

    public String getDefinition() {
        if(definition == null) return "";
        Iterator<columnDefinition> it =  definition.iterator();
        StringBuilder def = new StringBuilder(it.next().toString());
        while(it.hasNext()){
            def.append(" ").append(it.next().toString());
        }
        return def.toString();
    }

}
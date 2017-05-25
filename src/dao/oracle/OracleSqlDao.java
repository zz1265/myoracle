package dao.oracle;

import dao.base.BaseDao;
import dao.base.OracleKey;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Archibald on 1/5/2017.
 */
public class OracleSqlDao {
    private String sql;
    private ArrayList<String[]> myResultSet=new ArrayList<>();
    private ArrayList<String[]> desc=new ArrayList<>();
    private OracleKey oracleKey;
    private BaseDao baseDao=new BaseDao();
    private boolean isQuery;
    private String error;
    private boolean success;
    private boolean autoSub=true;
    private Connection connection;

    public ArrayList<String[]> getDesc() {
        return desc;
    }

    public void setAutoSub(boolean autoSub) {
        this.autoSub = autoSub;
    }

    public ArrayList<String[]> getMyResultSet() {
        return myResultSet;
    }

    public boolean GetIsQuery() {
        return isQuery;
    }

    public String getError() {
        return error;
    }


    public boolean isSuccess() {
        return success;
    }

    public OracleSqlDao(OracleKey oracleKey){
        this.oracleKey = oracleKey;
        try {
            connection=baseDao.getCon(oracleKey);
        } catch (Exception e) {
        }
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public void execute() throws Exception {
        success=true;
        connection.setAutoCommit(autoSub);
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        try{
            isQuery=preparedStatement.execute();
        }catch (Exception e){
            error=e.getMessage();
            success=false;
        }
        if(!success){
            return;
        }
        if(!isQuery){
            return;
        }
        DatabaseMetaData ppp=connection.getMetaData();
        ResultSetMetaData pp = preparedStatement.getMetaData();
        int length=pp.getColumnCount();
        String[] top={"fieldName","fieldType","fieldLength"};
        desc.clear();
        desc.add(top);
        String[] filedName=new String[length];
        String[] filedType=new String[length];
        for(int i=1;i<=length;i++){
            filedName[i-1]=pp.getColumnName(i);
            filedType[i-1]=pp.getColumnTypeName(i)+"("+pp.getColumnDisplaySize(i)+")";
            String[] contect=new String[3];
            contect[0]=pp.getColumnName(i);
            contect[1]=pp.getColumnTypeName(i);
            contect[2]=pp.getColumnDisplaySize(i)+"";
            desc.add(contect);
        }
        System.out.println(filedName[0]);
        myResultSet.clear();
        myResultSet.add(filedName);
        myResultSet.add(filedType);
        ResultSet resultSet=preparedStatement.getResultSet();
        while (resultSet.next()){
            String[] temp=new String[length];
            for(int i=1;i<=length;i++){
                temp[i-1]=resultSet.getString(i);
            }
            myResultSet.add(temp);
        }
    }
    public void commit() throws SQLException {
        connection.commit();
    }
    public void rollback() throws SQLException {
        connection.rollback();
    }
}

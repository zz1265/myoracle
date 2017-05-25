package action.oracle;

import action.base.BaseAction;
import dao.base.OracleKey;
import dao.oracle.OracleSqlDao;
import org.apache.struts2.ServletActionContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Archibald on 1/5/2017.
 */
public class OracleSqlAction extends BaseAction{
    private String sql;
    private ArrayList<String[]> myResultSet;
    private String message;
    private String autoSub;
    private String table;
    private boolean canUpdate;

    public boolean isCanUpdate() {
        return canUpdate;
    }

    public void setCanUpdate(boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
    private OracleSqlDao oracleSqlDao;
    public String getAutoSub() {
        return autoSub;
    }

    public void setAutoSub(String autoSub) {
        this.autoSub = autoSub;
    }

    public String getSql() {
        return sql;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public ArrayList<String[]> getMyResultSet() {
        return myResultSet;
    }

    public void setMyResultSet(ArrayList<String[]> myResultSet) {
        this.myResultSet = myResultSet;
    }

    public String executeSql(){
        //解析查看表
        canUpdate=false;
        if(table!=null){
            if(!table.equals("zz1265_null")){
                sql="select * from "+table;
                canUpdate=true;
            }
        }
        //
        ServletActionContext.getRequest().getSession().setAttribute("table",table);
        boolean isAuto=true;
        if(autoSub==null){
            isAuto=false;
        }
        oracleSqlDao= (OracleSqlDao) ServletActionContext.getRequest().getSession().getAttribute("oracleSqlDao");
        if(oracleSqlDao==null){
            OracleKey oracleKey= (OracleKey) getSession().getAttribute("theKey");
            oracleSqlDao=new OracleSqlDao(oracleKey);
            ServletActionContext.getRequest().getSession().setAttribute("oracleSqlDao",oracleSqlDao);
        }
        oracleSqlDao.setAutoSub(isAuto);
        if(sql.equals("commit")){
            try {
                oracleSqlDao.commit();
            } catch (SQLException e) {
                message=e.getMessage();
            }
            return "showMessage";
        }
        if(sql.equals("rollback")){
            try {
                oracleSqlDao.rollback();
            } catch (SQLException e) {
                message=e.getMessage();
            }
            return "showMessage";
        }
        //解析desc
        boolean isDesc=false;
        String sql2=sql;
        sql2=sql.replace("desc","select * from");
        if(sql2.length()!=sql.length()){
            sql=sql2;
            isDesc=true;
        }
        //
        //去掉最后的分号

        //
        oracleSqlDao.setSql(sql);
        try {
            oracleSqlDao.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean success=oracleSqlDao.isSuccess();
        if(success){
            if(isDesc){
                myResultSet=oracleSqlDao.getDesc();
            }else {
                myResultSet=oracleSqlDao.getMyResultSet();
                ServletActionContext.getRequest().getSession().setAttribute("myResultSet",myResultSet);
            }
            boolean isQuery=oracleSqlDao.GetIsQuery();
            System.out.println("isQuery= "+isQuery);
            if(!isQuery){
                message="执行成功";
                return "showMessage";
            }
            return "showResult";
        }else {
            message=oracleSqlDao.getError();
            return "showMessage";
        }

    }
}

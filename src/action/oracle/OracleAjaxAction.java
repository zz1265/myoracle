package action.oracle;

import action.base.BaseAction;
import dao.base.OracleKey;
import dao.oracle.OracleSqlDao;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Archibald on 1/8/2017.
 */
public class OracleAjaxAction extends BaseAction{
    private int x;
    private int y;
    private String value;
    private String updated;

    public String getTheTable() {
        return theTable;
    }

    public void setTheTable(String theTable) {
        this.theTable = theTable;
    }

    private String theTable;
    private OracleSqlDao oracleSqlDao;

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    private void executeSql(String sql){
        oracleSqlDao= (OracleSqlDao)getSession().getAttribute("oracleSqlDao");
        if(oracleSqlDao==null){
            OracleKey oracleKey= (OracleKey) getSession().getAttribute("theKey");
            oracleSqlDao=new OracleSqlDao(oracleKey);
            getSession().setAttribute("oracleSqlDao",oracleSqlDao);
        }
        oracleSqlDao.setAutoSub(false);
        oracleSqlDao.setSql(sql);
        try {
            oracleSqlDao.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String update(){
        ArrayList<String[]> list= (ArrayList<String[]>)getSession().getAttribute("myResultSet");
        String table= (String)getSession().getAttribute("table");
        String[] date=list.get(x);
        String[] fieldName=list.get(0);
        String sql="update "+table+" set "+fieldName[y]+" = '"+value+"' where ";
        for(int i=0;i<date.length;i++){
            if(i==y){
                continue;
            }
            sql+=fieldName[i]+" = '"+date[i] +"' and ";
        }
        sql+="1=1";
        executeSql(sql);
        return "updated";
    }
    public String insert(){
        HttpServletRequest request=ServletActionContext.getRequest();
        String[] mm=request.getParameterValues("insert");
        String table= (String)getSession().getAttribute("table");
        theTable=table;
        String sql="insert into "+table+" values(";
        for(String m:mm){
            sql+="'"+m+"',";
        }
        sql=sql.substring(0,sql.length()-1);
        sql+=")";
        executeSql(sql);
        return "insert";
    }
}

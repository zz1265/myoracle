package action.oracle;

import action.base.BaseAction;
import dao.base.OracleKey;
import dao.base.SqlKey;
import dao.oracle.OracleInfoDao;

import java.util.ArrayList;

/**
 * Created by Archibald on 1/5/2017.
 */
public class OracleInfoAction extends BaseAction{
    private String name;
    private String password;
    private String port;
    private String ipAddress;
    private String serverName;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String findBaseInfo(){
        OracleKey oracleKey=new OracleKey();
        oracleKey.setIpAddress(ipAddress);
        oracleKey.setPort(port);
        oracleKey.setName(name);
        oracleKey.setPassword(password);
        oracleKey.setServerName(serverName);
        getSession().setAttribute("theKey",oracleKey);
        OracleInfoDao oracleInfoDao=new OracleInfoDao(oracleKey);
        try {
            ArrayList<String> tables=oracleInfoDao.findObjects(OracleInfoDao.table);
            ArrayList<String> views=oracleInfoDao.findObjects(OracleInfoDao.view);
            ArrayList<String> sequences=oracleInfoDao.findObjects(OracleInfoDao.sequence);
            ArrayList<String> indexs=oracleInfoDao.findObjects(OracleInfoDao.index);
            getSession().setAttribute("tables",tables);
            getSession().setAttribute("views",views);
            getSession().setAttribute("sequences",sequences);
            getSession().setAttribute("indexs",indexs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        oracleInfoDao.conclose();
        return "oracleMain";
    }
}

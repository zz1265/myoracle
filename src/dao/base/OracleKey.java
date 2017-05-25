package dao.base;

/**
 * Created by Archibald on 1/5/2017.
 */
public class OracleKey extends SqlKey{
    private String ipAddress="localhost";
    private String port="1521";
    private String serverName="orcl";

    public OracleKey() {
        this.setDriverName("oracle.jdbc.driver.OracleDriver");
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        setUrl("jdbc:oracle:thin:@"+ipAddress+":"+port+":"+serverName);
    }

    public void setPort(String port) {
        this.port = port;
        setUrl("jdbc:oracle:thin:@"+ipAddress+":"+port+":"+serverName);
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
        setUrl("jdbc:oracle:thin:@"+ipAddress+":"+port+":"+serverName);
    }
}

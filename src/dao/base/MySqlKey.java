package dao.base;

/**
 * Created by Archibald on 1/5/2017.
 */
public class MySqlKey extends SqlKey {
    private String ipAddress;
    private String port;
    private String dataBaseName;
    public MySqlKey() {
        this.setDriverName("com.mysql.jdbc.Driver");
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        setUrl("jdbc:mysql://"+ipAddress+":"+this.port+"/"+this.dataBaseName);
    }

    public void setPort(String port) {
        this.port = port;
        setUrl("jdbc:mysql://"+this.ipAddress+":"+port+"/"+this.dataBaseName);
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
        setUrl("jdbc:mysql://"+this.ipAddress+":"+this.port+"/"+dataBaseName);
    }
}

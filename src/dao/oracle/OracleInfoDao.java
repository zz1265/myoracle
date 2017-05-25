package dao.oracle;

import dao.base.BaseDao;
import dao.base.SqlKey;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Archibald on 1/5/2017.
 */
public class OracleInfoDao {
    private SqlKey sqlKey;
    private BaseDao baseDao=new BaseDao();
    public static final String table="TABLE";
    public static final String index="INDEX";
    public static final String sequence="SEQUENCE";
    public static final String view="VIEW";
    public OracleInfoDao(SqlKey sqlKey) {
        this.sqlKey = sqlKey;
    }
    public ArrayList<String> findObjects(String objectName) throws Exception {
        ArrayList<String> objects=new ArrayList<>();
        String sql="select object_name from  user_objects where object_type=?";
        PreparedStatement preparedStatement=baseDao.getCon(sqlKey).prepareStatement(sql);
        preparedStatement.setString(1,objectName);
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            objects.add(resultSet.getString(1));
        }
        return objects;
    }
    public void conclose(){
        baseDao.conclose();
    }
}

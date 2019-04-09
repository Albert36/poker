package dao;

import com.db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OpponentDao
{
    private static Connection conn = null;

    public List<String> queryRandom(int player_num, int card)
    {
        conn = DB.getConnection();
        List<String> list = new ArrayList<>();
        try
        {
            String sql = "SELECT * FROM player" + player_num +" WHERE card = '" + card + "' and id >= (SELECT floor(RAND() * (SELECT MAX(id) FROM player" +  player_num + ")) + 1) ORDER BY id LIMIT 1;";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            String action = resultSet.getString("decision");
            String decide_time = resultSet.getString("decide_time");
            list.add(String.valueOf(card));
            list.add(action);
            list.add(decide_time);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        DB.close(conn);
        return list;
    }

    public void insert(int player, String amazonID, String card, String decision, String decide_time)
    {
        conn = DB.getConnection();
        String sql = "insert into player" + player + " (amazonID,card,decision,decide_time) values(?,?,?,?)";
        try
        {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,amazonID);
            preparedStatement.setString(2,card);
            preparedStatement.setString(3,decision);
            preparedStatement.setString(4,decide_time);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public List<String> queryID(int player_num)
    {
        conn = DB.getConnection();
        List<String> list = new ArrayList<>();
        try
        {
            String sql = "SELECT distinct amazonID FROM player" + player_num + ";";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next())
            {
                String amazonID = resultSet.getString("amazonID");
                list.add(amazonID);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        DB.close(conn);
        return list;
    }
}

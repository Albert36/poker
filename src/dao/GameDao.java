package dao;

import com.db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class GameDao {
    private static Connection conn = null;

    public void insert(String player1_card, String player2_card, String player1_decision, String player2_decision, String player1_time, String player2_time)
    {
        conn = DB.getConnection();
        String sql = "insert into game (player1_card,player2_card,player1_decision,player2_decision,player1_time,player2_time) values(?,?,?,?,?,?)";
        try
        {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,player1_card);
            preparedStatement.setString(2,player2_card);
            preparedStatement.setString(3,player1_decision);
            preparedStatement.setString(4,player2_decision);
            preparedStatement.setString(5,player1_time);
            preparedStatement.setString(6,player2_time);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

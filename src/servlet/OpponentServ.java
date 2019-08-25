package servlet;

import config.Global;
import dao.GameDao;
import dao.OpponentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="opponentServ", urlPatterns="*.do")
public class OpponentServ extends HttpServlet
{
    private static OpponentDao opponentDao = new OpponentDao();
    private static GameDao gameDao = new GameDao();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        String reqURL = req.getServletPath();
        if(reqURL.equals("/check.do"))
        {
            check(req, res);
        }
        else if(reqURL.equals("/bet.do"))
        {
            bet(req, res);
        }
        else if(reqURL.equals("/fold.do"))
        {
            fold(req, res);
        }
        else if(reqURL.equals("/call.do"))
        {
            call(req, res);
        }
        else if(reqURL.equals("/next.do"))
        {
            next(req, res);
        }
        else
        {
            System.out.println("wrong link");
        }
    }

    public void check(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        long endTime =  System.currentTimeMillis();
        long startTime = (long)req.getSession().getAttribute("time");
        double time = endTime - startTime;

        int player1_card = (Integer)req.getSession().getAttribute("player1_card");
        int player2_card = (Integer)req.getSession().getAttribute("player2_card");

        //save player's info
        String amazonID = String.valueOf(req.getSession().getAttribute("amazonID"));
        String card = String.valueOf(req.getSession().getAttribute("player1_card"));
        String decision = "check";

        gameDao.insert(String.valueOf(player1_card), String.valueOf(player2_card), decision, "checked", String.valueOf(time), "0", amazonID);

        //determine who wins
        int points = 0;
        if(player1_card > player2_card)
        {
            points = Global.CHECK_POINT;
            String win = "Your card is larger, yon earn " + points;
            req.getSession().setAttribute("winInfo", win);
            req.getSession().setAttribute("points", points);
            req.getRequestDispatcher("win.jsp").forward(req,res);
        }
        else
        {
            points = -Global.CHECK_POINT;
            String win = "You card is smaller, yon earn " + points;
            req.getSession().setAttribute("winInfo", win);
            req.getSession().setAttribute("points", points);
            req.getRequestDispatcher("win.jsp").forward(req,res);
        }
        opponentDao.insert(1, amazonID, card, decision, String.valueOf(time), String.valueOf(points));
    }

    public void bet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        long endTime =  System.currentTimeMillis();
        long startTime = (long)req.getSession().getAttribute("time");
        double time = endTime - startTime;

        int player1_card = (Integer)req.getSession().getAttribute("player1_card");
        int player2_card = (Integer)req.getSession().getAttribute("player2_card");

        String amazonID = String.valueOf(req.getSession().getAttribute("amazonID"));
        String card = String.valueOf(req.getSession().getAttribute("player1_card"));
        String decision = "bet";

        String player2_decision = String.valueOf(req.getSession().getAttribute("player2_decision"));
        String player2_time = String.valueOf(req.getSession().getAttribute("player2_time"));
        gameDao.insert(String.valueOf(player1_card), String.valueOf(player2_card), decision, player2_decision, String.valueOf(time), player2_time, amazonID);

        int points = 0;
        if(player2_decision.equals("fold"))
        {
            points = Global.FOLD_POINT;
            String win = "Your opponent choose to fold and you win " + points + " point";
            req.getSession().setAttribute("winInfo", win);
            req.getSession().setAttribute("points", points);
            req.getRequestDispatcher("win.jsp").forward(req,res);
        }
        else
        {
            if(player1_card > player2_card)
            {
                points = Global.CALL_POINT;
                String win = "Your opponent choose to bet, but Your card is larger, you win " + points + " points";
                req.getSession().setAttribute("winInfo", win);
                req.getSession().setAttribute("points", points);
                req.getRequestDispatcher("win.jsp").forward(req,res);
            }
            else
            {
                points = -Global.CALL_POINT;
                String win = "Your opponent choose to bet, and his card is larger, you lost " + points + " points";

                req.getSession().setAttribute("winInfo", win);
                req.getSession().setAttribute("points", points);
                req.getRequestDispatcher("win.jsp").forward(req,res);
            }
        }
        opponentDao.insert(1, amazonID, card, decision, String.valueOf(time), String.valueOf(points));
    }


    public void fold(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        long endTime =  System.currentTimeMillis();
        long startTime = (long)req.getSession().getAttribute("time");
        double time = endTime - startTime;

        String amazonID = String.valueOf(req.getSession().getAttribute("amazonID"));
        String card = String.valueOf(req.getSession().getAttribute("player2_card"));
        String decision = "fold";

        String player1_time = String.valueOf(req.getSession().getAttribute("player1_time"));
        String player1_card = String.valueOf(req.getSession().getAttribute("player1_card"));
        String player1_decision = String.valueOf(req.getSession().getAttribute("player1_decision"));
        gameDao.insert(player1_card, String.valueOf(card), player1_decision, decision, player1_time, String.valueOf(time), amazonID);

        int points = -Global.FOLD_POINT;
        String win = "Since you folded, you lose " + points + " point";
        req.getSession().setAttribute("winInfo", win);
        req.getSession().setAttribute("points", points);
        req.getRequestDispatcher("win.jsp").forward(req,res);
        opponentDao.insert(2, amazonID, card, decision, String.valueOf(time), String.valueOf(points));
    }

    public void call(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        long endTime =  System.currentTimeMillis();
        long startTime = (long)req.getSession().getAttribute("time");
        double time = endTime - startTime;

        int player2_card = (Integer)req.getSession().getAttribute("player2_card");
        int player1_card = (Integer)req.getSession().getAttribute("player1_card");

        String amazonID = String.valueOf(req.getSession().getAttribute("amazonID"));
        String card = String.valueOf(req.getSession().getAttribute("player2_card"));
        String decision = "call";

        String player1_time = String.valueOf(req.getSession().getAttribute("player1_time"));
        String player1_decision = String.valueOf(req.getSession().getAttribute("player1_decision"));
        gameDao.insert(String.valueOf(player1_card), String.valueOf(player2_card), player1_decision, decision, player1_time, String.valueOf(time), amazonID);

        int points = 0;
        if(player2_card > player1_card)
        {
            points = Global.CALL_POINT;
            String win = "Your card is larger and You win " + points + " points";
            req.getSession().setAttribute("winInfo", win);
            req.getSession().setAttribute("points", points);
            req.getRequestDispatcher("win.jsp").forward(req,res);
        }
        else
        {
            points = -Global.CALL_POINT;
            String win = "His card is larger and You lost " + points + " points";
            req.getSession().setAttribute("winInfo", win);
            req.getSession().setAttribute("points", points);
            req.getRequestDispatcher("win.jsp").forward(req,res);
        }

        opponentDao.insert(2, amazonID, card, decision, String.valueOf(time), String.valueOf(points));
    }

    public void next(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        String who = String.valueOf(req.getSession().getAttribute("who"));
        if(who.equals("2"))
            req.getRequestDispatcher("waitingForP1.jsp").forward(req,res);
        else
            req.getRequestDispatcher("player1.jsp").forward(req,res);
    }
}

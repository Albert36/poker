package servlet;

import dao.OpponentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet(name="mainServ", urlPatterns = "*.login")
public class MainServ extends HttpServlet
{
    private static OpponentDao opponentDao = new OpponentDao();

    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        String reqURL = req.getServletPath();
        if(reqURL.equals("/login.login"))           //to deal with users' login
        {
            //check if this amazonID has signed
            String amazonID = String.valueOf(req.getParameter("amazonID"));
            List<String> list_player1 = opponentDao.queryID(1);
            List<String> list_player2 = opponentDao.queryID(2);
            Iterator<String> iterator = list_player1.iterator();
            while(iterator.hasNext())
            {
                if(amazonID.equals(iterator.next()))
                    req.getRequestDispatcher("error.jsp").forward(req,res);
            }
            Iterator<String> iterator1 = list_player2.iterator();
            while(iterator1.hasNext())
            {
                if(amazonID.equals(iterator1.next()))
                    req.getRequestDispatcher("error.jsp").forward(req,res);
            }

            //forward into Info page to inform users of their roles
            int gameNum = 0;
            int score = 0;
            req.getSession().setAttribute("gameNum", gameNum);
            req.getSession().setAttribute("score", score);
            int player = Math.random()>0.5?1:0;
            if(player == 1)
                req.getSession().setAttribute("who", "1");
            else
                req.getSession().setAttribute("who", "2");

            req.getRequestDispatcher("Info.jsp").forward(req,res);
        }
        else if(reqURL.equals("/continue.login"))               //to deal with button "continue"
        {
            String who = String.valueOf(req.getSession().getAttribute("who"));
            if(who.equals("1"))
                req.getRequestDispatcher("player1.jsp").forward(req,res);
            else
                req.getRequestDispatcher("waitingForP1.jsp").forward(req,res);
        }
    }
}

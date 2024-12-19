package controller.StaffController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Room;
import model.bean.User;
import model.bo.RoomBO;
import model.bo.UserBO;

import java.io.IOException;
import java.io.Serial;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/StaffController/index")
public class _index extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null) {
        	action = "userindex";
        }
        switch(action){
            case "userindex":
                userindex(req, resp);
                break;
            case "roomindex":
                roomindex(req, resp);
        }
    }

    public void userindex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        	System.out.println("alo");
            UserBO userBO = new UserBO();
            List<User> arrayList = userBO.getAllUsers();
            System.out.println("Size: " + arrayList.size());
            if(!arrayList.isEmpty()){;
                String destination = "/staffjsp/manageUser/index.jsp";
                req.setAttribute("user", arrayList);
                req.getRequestDispatcher(destination).forward(req, resp);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void roomindex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RoomBO roomBO = new RoomBO();
            List<Room> arrayList = roomBO.getAllRooms();
            System.out.println("Size: " + arrayList.size());
            if(!arrayList.isEmpty()){;
                String destination = "/staffjsp/manageRoom/index.jsp";
                req.setAttribute("room", arrayList);
                req.getRequestDispatcher(destination).forward(req, resp);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

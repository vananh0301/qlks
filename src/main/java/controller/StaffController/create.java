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
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/StaffController/create")
public class create extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch(action){
            case "usercreate":
                req.getRequestDispatcher("/staffjsp/manageUser/create.jsp").forward(req, resp);
                break;
            case "roomcreate":
                req.getRequestDispatcher("/staffjsp/manageRoom/create.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch(action){
            case "usercreate":
                usercreate(req, resp);
                break;
            case "roomcreate":
                roomcreate(req, resp);
        }
    }
    public void usercreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String hoten = req.getParameter("hoten");
        String sdt = req.getParameter("sdt");
        User user = new User(username, password, role, hoten, sdt);
        try {
            System.out.println("doPost nhanvien/create");
            System.out.println(req.getContextPath());
            System.out.println(req.getServletPath());
            UserBO nhanVienBo = new UserBO();
            User a = nhanVienBo.addUser(user);
            if(a.getHoten().equals(user.getHoten())){
                req.setAttribute("user", a);
                req.getRequestDispatcher("/staffjsp/manageUser/detail.jsp").forward(req, resp);
            }
            else System.out.println("Cannot");

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void roomcreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        String status = req.getParameter("status");
        Room room = new Room(id, name, price, status);
        try {
            RoomBO roomBO = new RoomBO();
            Room a = roomBO.addRoom(room);
            if(a.getId()==(room.getId())){
                req.setAttribute("room", a);
                req.getRequestDispatcher("/staffjsp/manageRoom/detail.jsp").forward(req, resp);
            }
            else System.out.println("Cannot");

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

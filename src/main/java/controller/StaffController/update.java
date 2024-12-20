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
import java.util.Objects;

@WebServlet("/StaffController/update")
public class update extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "userupdate":
                String username = req.getParameter("username");
                try {
                    UserBO userBO = new UserBO();
                    User user = userBO.getUserDetail(username);
                    req.setAttribute("user", user);
                    req.getRequestDispatcher("/staffjsp/manageUser/update.jsp").forward(req, resp);
//                    if (Objects.equals(user.getUsername(), username)) {
//                        req.setAttribute("user", user);
//                        req.getRequestDispatcher("/staffjsp/manageUser/detail.jsp").forward(req, resp);
//                    } else System.out.println("Cannot");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "roomupdate":
            	 String id = req.getParameter("id");
                 try {
                     RoomBO roomBO = new RoomBO();
                     Room room = roomBO.getRoomDetail(Integer.parseInt(id));
                     req.setAttribute("room", room);
                     req.getRequestDispatcher("/staffjsp/manageRoom/update.jsp").forward(req, resp);
                     
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "userupdate":
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                String role = req.getParameter("role");
                String hoten = req.getParameter("hoten");
                String sdt = req.getParameter("sdt");
                try {
                    UserBO userBO = new UserBO();
                    User user = new User(username, password, role, hoten, sdt);
                    int update = userBO.updateUser(user);
                    
                    if (update>=1) {
                    	req.setAttribute("updateMessage", "Cap nhat thanh cong");
                    	req.setAttribute("user", user);
                        req.getRequestDispatcher("/staffjsp/manageUser/update.jsp").forward(req, resp);
                    }
                    else {
                    	req.setAttribute("updateMessage", "Cap nhat that bai");
                    	req.setAttribute("user", user);
                        req.getRequestDispatcher("/staffjsp/manageUser/update.jsp").forward(req, resp);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "roomupdate":
                int id = Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("name");
                BigDecimal price = new BigDecimal(req.getParameter("price"));
                String status = req.getParameter("status");
                try {
                    RoomBO roomBO = new RoomBO();
                    Room room = new Room(id, name, price, status);
                    int update = roomBO.updateRoom(room);
                    if (update>=1) {
                    	req.setAttribute("updateMessage", "Cap nhat thanh cong");
                        req.setAttribute("room", room);
                        req.getRequestDispatcher("/staffjsp/manageRoom/update.jsp").forward(req, resp);
                    }
                    else {
                    	req.setAttribute("updateMessage", "Cap nhat that bai");
                        req.setAttribute("room", room);
                        req.getRequestDispatcher("/staffjsp/manageRoom/update.jsp").forward(req, resp);
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
}

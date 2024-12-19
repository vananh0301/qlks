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
import java.util.Objects;

@WebServlet("/StaffController/detail")
public class detail extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "userdetail":
                String username = req.getParameter("username");
                try {
                    System.out.println("doGet user with username: " + username);
                    UserBO userBO = new UserBO();
                    User user = userBO.getUserDetail(username);
                    System.out.println("user: " + user.getHoten());
                    req.setAttribute("user", user);
                    req.getRequestDispatcher("/staffjsp/manageUser/detail.jsp").forward(req, resp);
//                    if (Objects.equals(user.getUsername(), username)) {
//                        req.setAttribute("user", user);
//                        req.getRequestDispatcher("/staffjsp/manageUser/detail.jsp").forward(req, resp);
//                    } else System.out.println("Cannot");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "roomdetail":
                String id = req.getParameter("id");
                try {
                    System.out.println("doGet nhanvien with id: " + id);
                    RoomBO roomBO = new RoomBO();
                    Room room = roomBO.getRoomDetail(Integer.parseInt(id));
                    req.setAttribute("room", room);
                    req.getRequestDispatcher("/staffjsp/manageRoom/detail.jsp").forward(req, resp);
//                    System.out.println("room: " + room.getId());
//                    if (Objects.equals(room.getId(), id)) {
//                        req.setAttribute("room", room);
//                        req.getRequestDispatcher("/staffjsp/manageRoom/detail.jsp").forward(req, resp);
//                    } else System.out.println("Cannot");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
}

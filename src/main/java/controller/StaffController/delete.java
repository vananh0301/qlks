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

@WebServlet("/StaffController/delele")
public class delete extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch(action){
            case "userdelete":
                try {
                    UserBO userBO = new UserBO();
                    List<User> arrayList = userBO.getAllUsers();
                    System.out.println("Size: " + arrayList.size());
                    if(!arrayList.isEmpty()){;
                        String destination = "/staffisp/manageUser/delete.jsp";
                        req.setAttribute("nhanvien", arrayList);
                        req.getRequestDispatcher(destination).forward(req, resp);
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "roomdelete":
                try {
                    RoomBO roomBO = new RoomBO();
                    List<Room> arrayList = roomBO.getAllRooms();
                    System.out.println("Size: " + arrayList.size());
                    if(!arrayList.isEmpty()){;
                        String destination = "/staffisp/manageRoom/delete.jsp";
                        req.setAttribute("room", arrayList);
                        req.getRequestDispatcher(destination).forward(req, resp);
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "userdelete":
                System.out.println("Full request parameters: ");
                req.getParameterMap().forEach((key, value) -> System.out.println(key + ": " + String.join(",", value)));
                String[] selectedIds = req.getParameterValues("selectedIds");
                if (selectedIds != null) {
                    try {
                        UserBO userBO = new UserBO();
                        for (int i = 0; i < selectedIds.length - 1; i++) {
                            System.out.println(selectedIds[i]);
                            userBO.deleteUser(selectedIds[i]);
                        }
                        resp.sendRedirect(req.getContextPath() + "/StaffController/index");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case "roomdelete":
                System.out.println("Full request parameters: ");
                req.getParameterMap().forEach((key, value) -> System.out.println(key + ": " + String.join(",", value)));
                String[] selectedIds1 = req.getParameterValues("selectedIds");
                if (selectedIds1 != null) {
                    try {
                        RoomBO roomBO = new RoomBO();
                        for (int i = 0; i < selectedIds1.length - 1; i++) {
                            System.out.println(selectedIds1[i]);
                            roomBO.deleteRoom(Integer.parseInt(selectedIds1[i]));
                        }
                        resp.sendRedirect(req.getContextPath() + "/StaffController/index");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
        }
    }
}
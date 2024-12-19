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

@WebServlet("/StaffController/delete")
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
                    if(!arrayList.isEmpty()){;
                        String destination = "/staffjsp/manageUser/delete.jsp";
                        req.setAttribute("user", arrayList);
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
                    if(!arrayList.isEmpty()){;
                        String destination = "/staffjsp/manageRoom/delete.jsp";
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
                req.getParameterMap().forEach((key, value) -> System.out.println(key + ": " + String.join(",", value)));
                String[] selectedIds1 = req.getParameterValues("selectedIds");
                if (selectedIds1 != null) {
                    try {
                        RoomBO roomBO = new RoomBO();
                        for (int i = 0; i < selectedIds1.length - 1; i++) {
                            System.out.println(selectedIds1[i]);
                            roomBO.deleteRoom(Integer.parseInt(selectedIds1[i]));
                        }
                        List<Room> arrayList = roomBO.getAllRooms();
                        if(!arrayList.isEmpty()){;
                            String destination = "/staffjsp/manageRoom/index.jsp";
                            req.setAttribute("room", arrayList);
                            req.getRequestDispatcher(destination).forward(req, resp);
                        }
//                        resp.sendRedirect(req.getContextPath() + "/StaffController/manageRoom/");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
        }
    }
}

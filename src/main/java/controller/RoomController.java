package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bo.RoomBO;

@WebServlet("/RoomController")
public class RoomController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RoomBO roomBO;

    public void init() throws ServletException {
        try {
            roomBO = new RoomBO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("rooms", roomBO.getAllRooms());
        request.getRequestDispatcher("/rooms.jsp").forward(request, response);
    }
}

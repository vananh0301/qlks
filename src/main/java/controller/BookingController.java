package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Booking;
import model.bo.BookingBO;
import model.bo.RoomBO;

@WebServlet("/BookingController")
public class BookingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RoomBO roomBO;
    private BookingBO bookingBO;

    public void init() throws ServletException {
        try {
            roomBO = new RoomBO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            try {
                addBooking(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void addBooking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String username = (String) request.getSession().getAttribute("currentUser");
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        Timestamp ngayDat = Timestamp.valueOf(request.getParameter("ngaydat").replace("T", " ") + ":00");
        Timestamp ngayTra = Timestamp.valueOf(request.getParameter("ngaytra").replace("T", " ") + ":00");

        if (bookingBO.isRoomAvailable(roomId, ngayDat, ngayTra)) {
            Booking booking = new Booking(username, roomId, ngayDat, ngayTra, "occupied");
            bookingBO.addBooking(booking);
//            if (bookingBO.addBooking(booking)) {
//                response.sendRedirect("RoomController?action=list");
//            } else {
//                request.setAttribute("errorMessage", "Booking failed.");
//                request.getRequestDispatcher("booking.jsp").forward(request, response);
//            }
        } else {
            request.setAttribute("errorMessage", "Room not available for the selected period.");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
        }
    }
}

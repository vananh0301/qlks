//package controller;
//
//import java.io.IOException;
//import java.rmi.ServerException;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import model.bean.Booking;
//import model.bean.User;
//import model.bo.RoomBO;
//import model.bo.UserBO;
//
//@WebServlet("/GuestController")
//public class GuestController extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private RoomBO roomBO;
//    private UserBO userBO;
//
//    @Override
//    public void init() throws ServletException {
//        try {
//            roomBO = new RoomBO();
//            userBO = new UserBO();
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException("Error initializing RoomBO", e);
//        }
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServerException, IOException, ServletException {
//        String action = request.getParameter("action");
//        if (action == null) {
//            action = "listPhong";
//        }
//        switch (action) {
//            case "listPhong":
//                show(request, response);
//                break;
//            case "phongDetail":
//                int id = Integer.parseInt(request.getParameter("id"));
//                showDetail(request, response, id);
//                break;
//            case "datPhong":
//                int id1 = Integer.parseInt(request.getParameter("id"));
//                datPhong(request, response, id1);
//                break;
//            default:
//                show(request, response);
//                break;
//        }
//    }
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServerException, IOException, ServletException {
//        String action = request.getParameter("action");
//        switch (action) {
//            case "login":
//                String username = request.getParameter("username");
//                String password = request.getParameter("password");
//                login(request, response, username, password);
//                break;
//            case "register":
//                String username1 = request.getParameter("username");
//                String password1 = request.getParameter("password");
//                String ten = request.getParameter("ten");
//                String sdt = request.getParameter("sdt");
//                register(request, response, username1, password1, ten, sdt);
//                break;
//
//        }
//    }
//    private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setAttribute("danhSachPhong", roomBO.getAllRooms());
//        request.getRequestDispatcher("/xemthongtinPhong.jsp").forward(request, response);
//    }
//
//    private void login(HttpServletRequest request, HttpServletResponse response, String username, String password)
//            throws ServletException, IOException {
//        boolean isSuccess = userBO.login(username, password);
//        if (isSuccess) {
//            request.getSession().setAttribute("currentUser", username);
//            request.setAttribute("danhSachPhong", roomBO.getAllRooms());
//            request.getRequestDispatcher("/xemthongtinPhong.jsp").forward(request, response);
//        } else {
//            request.setAttribute("errorMessage", "Tài khoản hoặc mật khẩu không đúng.");
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//        }
//    }
//
//    private void register(HttpServletRequest request, HttpServletResponse response, String username, String password,
//                          String ten, String sdt) throws ServletException, IOException {
//        User ngdung = new User();
//        ngdung.setUsername(username);
//        ngdung.setPassword(password);
//        ngdung.setHoten(ten);
//        ngdung.setSdt(sdt);
//        ngdung.setRole("customer");
//        boolean isSuccess = userBO.register(ngdung);
//        if (isSuccess) {
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//        } else {
//            request.setAttribute("errorMessage", "Đăng ký tài khoản không thành công.");
//            request.getRequestDispatcher("register.jsp").forward(request, response);
//        }
//    }
//
//
//    private void showDetail(HttpServletRequest request, HttpServletResponse response, int id)
//            throws ServletException, IOException {
//        request.setAttribute("phongDetail", roomBO.getRoomDetail(id));
//        request.getRequestDispatcher("/phongDetail.jsp").forward(request, response);
//    }
//
//
//
//}

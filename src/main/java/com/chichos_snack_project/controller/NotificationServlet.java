package com.chichos_snack_project.controller;
import com.chichos_snack_project.model.Notification;
import com.chichos_snack_project.service.NotificationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet(name = "NotificationServlet", value = "/Notifications")
public class NotificationServlet extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       List<Notification> notificationList = NotificationService.getNotifications();
       request.setAttribute("notificationList", notificationList);
       request.getRequestDispatcher("notifications.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
    public void destroy() {
    }
}

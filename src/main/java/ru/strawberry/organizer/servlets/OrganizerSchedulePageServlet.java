package ru.strawberry.organizer.servlets;

import ru.strawberry.organizer.services.ScheduleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/schedule")
public class OrganizerSchedulePageServlet extends HttpServlet {

    public ScheduleService scheduleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/schedule.jsp").forward(req, resp);
    }
}

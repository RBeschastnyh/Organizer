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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ScheduleService scheduleService = new ScheduleService();
        req.setAttribute("belTime", scheduleService.getTime("bel"));
        req.setAttribute("begTime", scheduleService.getTime("beg"));
        req.setAttribute("testTime", scheduleService.getTime("test"));
        req.setAttribute("filiTime", scheduleService.getTime("fili"));
        req.setAttribute("sbTime", scheduleService.getTime("sb"));
        req.setAttribute("kuntsTime", scheduleService.getTime("kunts"));
        req.setAttribute("setunTime", scheduleService.getTime("setun"));
        req.getRequestDispatcher("/schedule.jsp").forward(req, resp);
    }

}

package ru.strawberry.organizer.servlets;

import ru.strawberry.organizer.helpers.WeatherHelper;
import ru.strawberry.organizer.services.WeatherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/weather")
public class OrganizerWeatherPageServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WeatherService weatherService = new WeatherService(new WeatherHelper());
        req.setAttribute("currentTemperature", weatherService.getCurrentTemperature() + "&degC");
        req.setAttribute("currentTemperatureAsFeels", weatherService.getCurrentTemperatureAsFeels() + "&degC");
        req.getRequestDispatcher("/weather.jsp").forward(req, resp);
    }

}

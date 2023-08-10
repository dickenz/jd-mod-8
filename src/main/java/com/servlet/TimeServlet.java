package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");

        String timeZone = request.getParameter("timezone");
        ZoneId zoneId;

        if (timeZone != null && !timeZone.isEmpty() && !timeZone.equals("UTC")) {
            zoneId = ZoneId.of(timeZone.replace(" ", "+"));
        } else {
            zoneId = ZoneId.of("UTC");
        }

        ZonedDateTime currentTime = ZonedDateTime.now(zoneId);
        String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " " + zoneId;

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Current Time</title></head>");
        out.println("<body>");
        out.println("<h1>Current Time</h1>");
        if (!zoneId.getId().equals("UTC")) {
            assert timeZone != null;
            out.println("<p>Timezone: " + (timeZone.replace(" ", "+") + "</p>"));
        }
        out.println("<p>" + formattedTime + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}



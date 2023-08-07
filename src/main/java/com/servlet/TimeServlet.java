package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String timeZoneParam = request.getParameter("timezone");
        TimeZone timeZone;

        if (timeZoneParam != null && !timeZoneParam.isEmpty()) {
            timeZone = TimeZone.getTimeZone(timeZoneParam);
        } else {
            timeZone = TimeZone.getTimeZone("UTC");
        }

        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(timeZone);
        String formattedTime = sdf.format(currentTime);

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Current Time</title></head>");
        out.println("<body>");
        out.println("<h1>Current Time</h1>");
        out.println("<p>Timezone: " + timeZone.getID() + "</p>");
        out.println("<p>" + formattedTime + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}

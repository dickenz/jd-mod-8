package com.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.TimeZone;

@WebFilter(value = "/time", servletNames = "TimeServlet")
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String timeZoneParam = request.getParameter("timezone");

        if (timeZoneParam != null && !timeZoneParam.isEmpty()) {
            if (!isValidTimezone(timeZoneParam)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("text/plain");
                response.getWriter().write("Invalid timezone");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isValidTimezone(String timezone) {

        if (timezone.matches("^GMT[+-]\\d+$")) {
            return true;
        }

        if (timezone.matches("^GMT[+-]\\d{2}:\\d{2}$")) {
            return true;
        }

        if (timezone.matches("^GMT[+-]\\d{2}\\d{2}$")) {
            return true;
        }

        TimeZone timeZone = TimeZone.getTimeZone(timezone);
        return timeZone.getID().equals(timezone);
    }
}
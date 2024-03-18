package ru.sug4chy.demo6.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.sug4chy.demo6.service.AuthService;

import java.io.IOException;

@WebServlet("/")
public class LoginServlet extends HttpServlet {

    private final AuthService authService = new AuthService();

    //Метод для получения страницы
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    //Login метод
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login.isEmpty() || password.isEmpty()) {
            resp.setContentType("text/json;charset=utf8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Введите логин и пароль");
            return;
        }

        var user = authService.getUserByLogin(login);
        if (user == null || !user.getPassword().equals(password)) {
            resp.setContentType("text/json;charset=utf8");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().println("Логин или пароль введены неверно");
            return;
        }

        req.getSession().setAttribute("login", login);
        req.getSession().setAttribute("password", password);

        String url = req.getRequestURL().toString();
        resp.sendRedirect( url.substring(0, url.lastIndexOf("/")) + "/files");
    }
}

package ru.sug4chy.demo6.servlet;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ru.sug4chy.demo6.service.FileService;

import static ru.sug4chy.demo6.service.FileService.usersHomeDirectoriesPath;

@WebServlet("/files")
public class FilesServlet extends HttpServlet {

    private final FileService fileService = new FileService();

    //Метод получения страницы с файлами
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String login = (String) request.getSession().getAttribute("login");
        String password = (String) request.getSession().getAttribute("password");
        if (login == null || password == null) {
            String url = request.getRequestURL().toString();
            response.sendRedirect( url.substring(0, url.lastIndexOf("/")) + "/login");
            return;
        }

        String path = request.getParameter("path");
        if (path == null || !path.startsWith(usersHomeDirectoriesPath + "\\" + login)) {
            path = usersHomeDirectoriesPath + "\\" + login;
        }
        request.setAttribute("path", path);

        File[] directories = fileService.getDirectories(path);
        if (directories == null) {
            directories = new File[0];
        }

        File[] files = fileService.getFiles(path);
        if (files == null) {
            files = new File[0];
        }

        request.setAttribute("directories", directories);
        request.setAttribute("files", files);
        var dispatcher = request.getRequestDispatcher("files.jsp");
        dispatcher.forward(request, response);
    }

    //Logout метод
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.getSession().removeAttribute("login");
        req.getSession().removeAttribute("password");

        String url = req.getRequestURL().toString();
        resp.sendRedirect( url.substring(0, url.lastIndexOf("/")) + "/login");
    }
}
package ru.sug4chy.demo6.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {


    //Метод для скачивания файла
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String filePath = request.getParameter("path");
        if (filePath == null) {
            return;
        }

        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        response.setContentLengthLong(file.length());
        response.setHeader("Content-Disposition", "attachment; filename=\""
                + URLEncoder.encode(file.getName(), StandardCharsets.UTF_8) + "\"");
        try(var fis = new FileInputStream(file)) {
            try(var out = response.getOutputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) > 0) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        }
    }
}

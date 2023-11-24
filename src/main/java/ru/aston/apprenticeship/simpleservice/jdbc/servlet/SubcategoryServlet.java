package ru.aston.apprenticeship.simpleservice.jdbc.servlet;

import com.google.gson.Gson;
import ru.aston.apprenticeship.simpleservice.jdbc.DAO.SubcategoryDAO;
import ru.aston.apprenticeship.simpleservice.jdbc.model.Subcategory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/subcategories")
public class SubcategoryServlet extends HttpServlet {
    private SubcategoryDAO subcategoryDAO;

    @Override
    public void init() {subcategoryDAO = new SubcategoryDAO();}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Subcategory subcategory = subcategoryDAO.getSubcategoryById(id);
            String json = new Gson().toJson(subcategory);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } else {
            List<Subcategory> subcategories = subcategoryDAO.getAllSubcategories();
            String json = new Gson().toJson(subcategories);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        Subcategory subcategory = new Subcategory();
        subcategory.setName(name);
        subcategoryDAO.saveSubcategory(subcategory);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Subcategory subcategory = new Subcategory();
        subcategory.setId(id);
        subcategory.setName(name);
        subcategoryDAO.updateSubcategory(subcategory);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        subcategoryDAO.deleteSubcategory(id);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
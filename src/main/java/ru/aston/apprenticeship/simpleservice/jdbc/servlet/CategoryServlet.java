package ru.aston.apprenticeship.simpleservice.jdbc.servlet;

import com.google.gson.Gson;
import ru.aston.apprenticeship.simpleservice.jdbc.DAO.CategoryDAO;
import ru.aston.apprenticeship.simpleservice.jdbc.model.Category;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {
    private CategoryDAO categoryDAO;

    @Override
    public void init() {categoryDAO = new CategoryDAO();}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Category category = categoryDAO.getCategoryById(id);
            String json = new Gson().toJson(category);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } else {
            List<Category> categories = categoryDAO.getAllCategories();
            String json = new Gson().toJson(categories);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        Category category = new Category();
        category.setName(name);
        categoryDAO.saveCategory(category);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        categoryDAO.updateCategory(category);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        categoryDAO.deleteCategory(id);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
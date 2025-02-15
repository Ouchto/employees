package org.example.employeesmanager.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.employeesmanager.dao.PersonDAO;


import java.io.IOException;

@WebServlet("/deletePerson")
public class DeleteServlet extends HttpServlet {
    private PersonDAO personDAO;

    @Override
    public void init() {
        personDAO = new PersonDAO();
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        personDAO.deletePerson(id);

        response.sendRedirect("readPerson");
    }
}

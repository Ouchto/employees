package org.example.employeesmanager.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.employeesmanager.dao.PersonDAO;
import org.example.employeesmanager.model.Person;


import java.io.IOException;
import java.util.List;

@WebServlet("/readPerson")
public class ReadServlet extends HttpServlet {
    private PersonDAO personDAO;

    @Override
    public void init() {
        personDAO = new PersonDAO();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Person> personList = personDAO.getAllPersons();
        request.setAttribute("personList", personList);
        request.getRequestDispatcher("personList.jsp").forward(request, response);
    }
}

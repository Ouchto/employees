package org.example.employeesmanager.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.employeesmanager.dao.PersonDAO;
import org.example.employeesmanager.model.Person;

import java.io.IOException;

@WebServlet("/createPerson")
public class CreateServlet extends HttpServlet {
    private PersonDAO personDAO;

    @Override
    public void init() {
        personDAO = new PersonDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("createPersonForm.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String address = request.getParameter("address");
        String tel = request.getParameter("tel");

        Person person = new Person(name, age, address, tel);
        personDAO.createPerson(person);

        response.sendRedirect("readPerson");

    }
}

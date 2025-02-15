package org.example.employeesmanager.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.employeesmanager.dao.PersonDAO;
import org.example.employeesmanager.model.Person;

import java.io.IOException;

@WebServlet("/updatePerson")
public class UpdateServlet extends HttpServlet {
    private PersonDAO personDAO;

    @Override
    public void init() {
        personDAO = new PersonDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Person person = personDAO.getPersonById(id);
        request.setAttribute("person", person);
        request.getRequestDispatcher("updatePersonForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String address = request.getParameter("address");
        String tel = request.getParameter("tel");

        Person person = new Person(name, age, address, tel);
        person.setId(id);
        personDAO.updatePerson(person);

        response.sendRedirect("readPerson");
    }
}

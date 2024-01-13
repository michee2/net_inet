package ci.aho.demo.controllers.equipements;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import ci.aho.demo.models.enums.Etat;
import ci.aho.demo.models.enums.Site;
import ci.aho.demo.models.repositories.EquipementRepository;
import ci.aho.demo.models.entities.Equipement;


@WebServlet("/")
public class EquipementController extends HttpServlet {
    private EquipementRepository equipementRepository;

    public void init() {
        equipementRepository = new EquipementRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertEquipement(request, response);
                    break;
                case "/delete":
                    deleteEquipement(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateEquipement(request, response);
                    break;
                default:
                    listEquipement(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listEquipement(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Equipement> listEquipement = equipementRepository.getAll();
        request.setAttribute("listEquipement", listEquipement);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/equipement-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/equipement-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Equipement existingEquipement = equipementRepository.getById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/equipement-form.jsp");
        request.setAttribute("equipement", existingEquipement);
        dispatcher.forward(request, response);
    }

    private void insertEquipement(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String site = request.getParameter("site");
        String etat = request.getParameter("etat");
        Equipement newEquipement = new Equipement(site, etat);
        equipementRepository.create(newEquipement);
        response.sendRedirect("list");
    }

    private void updateEquipement(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Equipement updateEquipement = equipementRepository.getById(id);
        updateEquipement.setEtat(request.getParameter("etat"));
        updateEquipement.setSite(request.getParameter("site"));

        equipementRepository.update(updateEquipement);

        response.sendRedirect("list");
    }

    private void deleteEquipement(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        boolean bool = equipementRepository.delete(equipementRepository.getById(id));

        if (bool) {
            response.sendRedirect("list");
        }

    }
}
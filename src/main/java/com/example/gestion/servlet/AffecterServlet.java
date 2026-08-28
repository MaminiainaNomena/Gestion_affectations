package com.example.gestion.servlet;

import com.example.gestion.dao.*;
import com.example.gestion.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/affectations/*")
public class AffecterServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AffecterServlet.class.getName());
    private final AffecterDAO dao = new AffecterDAO();
    private final EmployeDAO employeDAO = new EmployeDAO();
    private final LieuDAO lieuDAO = new LieuDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("activePage", "affectations");
        String p = req.getPathInfo();
        if (p == null || p.equals("/")) {
            req.setAttribute("affectations", dao.findAll());
            req.getRequestDispatcher("/WEB-INF/views/affectations.jsp").forward(req, resp);
            return;
        }
        if (p.equals("/new")) {
            req.setAttribute("employes", employeDAO.findAll());
            req.setAttribute("lieux", lieuDAO.findAll());
            req.getRequestDispatcher("/WEB-INF/views/affecter-form.jsp").forward(req, resp);
            return;
        }
        if (p.equals("/edit")) {
            Affecter a = dao.findById(req.getParameter("codeemp"), req.getParameter("codelieu"));
            req.setAttribute("affecter", a);
            req.setAttribute("employes", employeDAO.findAll());
            req.setAttribute("lieux", lieuDAO.findAll());
            req.getRequestDispatcher("/WEB-INF/views/affecter-form.jsp").forward(req, resp);
            return;
        }
        if (p.equals("/delete")) {
            try {
                dao.delete(req.getParameter("codeemp"), req.getParameter("codelieu"));
                flash(req, "success", "Affectation supprimée.");
            } catch (RuntimeException ex) {
                flash(req, "error", "Suppression impossible.");
            }
            resp.sendRedirect(req.getContextPath() + "/affectations");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String oldE = req.getParameter("oldCodeemp"), oldL = req.getParameter("oldCodelieu");
        try {
            String codeemp = req.getParameter("codeemp");
            String codelieu = req.getParameter("codelieu");
            String dateValue = req.getParameter("date");
            if (codeemp == null || codeemp.isBlank() || codelieu == null || codelieu.isBlank()
                    || dateValue == null || dateValue.isBlank()) {
                throw new IllegalArgumentException("Employé, lieu et date sont obligatoires");
            }

            Employe e = employeDAO.findById(codeemp);
            Lieu l = lieuDAO.findById(codelieu);
            if (e == null || l == null) {
                throw new IllegalArgumentException("Employé ou lieu introuvable");
            }

            Affecter a = new Affecter(e, l, LocalDate.parse(dateValue));
            if (oldE == null || oldE.isBlank()) {
                if (dao.findById(codeemp, codelieu) != null) {
                    throw new IllegalArgumentException("Affectation déjà existante");
                }
                dao.save(a);
                flash(req, "success", "Affectation créée.");
            } else {
                if (!oldE.equals(e.getCodeemp()) || !oldL.equals(l.getCodelieu())) {
                    if (dao.findById(codeemp, codelieu) != null) {
                        throw new IllegalArgumentException("Affectation déjà existante");
                    }
                    dao.delete(oldE, oldL);
                    dao.save(a);
                } else
                    dao.update(a);
                flash(req, "success", "Affectation modifiée.");
            }
        } catch (RuntimeException ex) {
            LOGGER.log(Level.SEVERE, "Échec de l'enregistrement de l'affectation", ex);
            String message = ex instanceof IllegalArgumentException ? ex.getMessage() : "Enregistrement impossible.";
            flash(req, "error", message == null || message.isBlank() ? "Enregistrement impossible." : message);
        }
        resp.sendRedirect(req.getContextPath() + "/affectations");
    }

    private void flash(HttpServletRequest req, String type, String message) {
        req.getSession().setAttribute("notificationType", type);
        req.getSession().setAttribute("notificationMessage", message);
    }
}

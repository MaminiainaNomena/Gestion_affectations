package com.example.gestion.servlet;

import com.example.gestion.dao.*;
import com.example.gestion.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/affectations/*")
public class AffecterServlet extends HttpServlet {
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
        Employe e = employeDAO.findById(req.getParameter("codeemp"));
        Lieu l = lieuDAO.findById(req.getParameter("codelieu"));
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        String oldE = req.getParameter("oldCodeemp"), oldL = req.getParameter("oldCodelieu");
        Affecter a = new Affecter(e, l, date);
        try {
            if (oldE == null || oldE.isBlank()) {
                dao.save(a);
                flash(req, "success", "Affectation créée.");
            } else {
                if (!oldE.equals(e.getCodeemp()) || !oldL.equals(l.getCodelieu())) {
                    dao.delete(oldE, oldL);
                    dao.save(a);
                } else
                    dao.update(a);
                flash(req, "success", "Affectation modifiée.");
            }
        } catch (RuntimeException ex) {
            flash(req, "error", "Enregistrement impossible.");
        }
        resp.sendRedirect(req.getContextPath() + "/affectations");
    }

    private void flash(HttpServletRequest req, String type, String message) {
        req.getSession().setAttribute("notificationType", type);
        req.getSession().setAttribute("notificationMessage", message);
    }
}

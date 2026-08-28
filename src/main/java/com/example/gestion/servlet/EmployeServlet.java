package com.example.gestion.servlet;

import com.example.gestion.dao.EmployeDAO;
import com.example.gestion.model.Employe;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/employes/*")
public class EmployeServlet extends HttpServlet {
    private final EmployeDAO dao = new EmployeDAO();
    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.setAttribute("activePage", "employes");
        String path=req.getPathInfo();
        if(path==null||path.equals("/")){String q=req.getParameter("q");var employes=q==null||q.isBlank()?dao.findAll():dao.search(q);req.setAttribute("employes",employes);if(q!=null&&!q.isBlank()&&employes.isEmpty()){req.setAttribute("notificationType","info");req.setAttribute("notificationMessage","Aucun employé trouvé.");}req.getRequestDispatcher("/WEB-INF/views/employes.jsp").forward(req,resp);return;}
        if(path.equals("/new")){req.getRequestDispatcher("/WEB-INF/views/employe-form.jsp").forward(req,resp);return;}
        if(path.equals("/edit")){req.setAttribute("employe",dao.findById(req.getParameter("id")));req.getRequestDispatcher("/WEB-INF/views/employe-form.jsp").forward(req,resp);return;}
        if(path.equals("/delete")){try{dao.delete(req.getParameter("id"));flash(req,"success","Employé supprimé.");}catch(RuntimeException ex){flash(req,"error","Suppression impossible.");}resp.sendRedirect(req.getContextPath()+"/employes");}
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.setCharacterEncoding("UTF-8");
        String old=req.getParameter("oldCode");
        Employe e=new Employe(req.getParameter("codeemp"),req.getParameter("nom"),req.getParameter("prenom"),req.getParameter("poste"));
        try { if(old==null||old.isBlank()){dao.save(e);flash(req,"success","Employé créé.");}else { if(!old.equals(e.getCodeemp())){dao.delete(old);dao.save(e);}else dao.update(e);flash(req,"success","Employé modifié.");} } catch(RuntimeException ex) { flash(req,"error","Enregistrement impossible."); }
        resp.sendRedirect(req.getContextPath()+"/employes");
    }

    private void flash(HttpServletRequest req,String type,String message){req.getSession().setAttribute("notificationType",type);req.getSession().setAttribute("notificationMessage",message);}
}

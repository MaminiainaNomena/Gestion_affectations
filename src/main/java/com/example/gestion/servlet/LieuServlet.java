package com.example.gestion.servlet;

import com.example.gestion.dao.LieuDAO;
import com.example.gestion.model.Lieu;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/lieux/*")
public class LieuServlet extends HttpServlet {
    private final LieuDAO dao=new LieuDAO();
    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        String p=req.getPathInfo();
        if(p==null||p.equals("/")){req.setAttribute("lieux",dao.findAll());req.getRequestDispatcher("/WEB-INF/views/lieux.jsp").forward(req,resp);return;}
        if(p.equals("/new")){req.getRequestDispatcher("/WEB-INF/views/lieu-form.jsp").forward(req,resp);return;}
        if(p.equals("/edit")){req.setAttribute("lieu",dao.findById(req.getParameter("id")));req.getRequestDispatcher("/WEB-INF/views/lieu-form.jsp").forward(req,resp);return;}
        if(p.equals("/delete")){dao.delete(req.getParameter("id"));resp.sendRedirect(req.getContextPath()+"/lieux");}
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.setCharacterEncoding("UTF-8");
        String old=req.getParameter("oldCode");
        Lieu l=new Lieu(req.getParameter("codelieu"),req.getParameter("designation"),req.getParameter("province"));
        if(old==null||old.isBlank())dao.save(l);else{if(!old.equals(l.getCodelieu())){dao.delete(old);dao.save(l);}else dao.update(l);}
        resp.sendRedirect(req.getContextPath()+"/lieux");
    }
}

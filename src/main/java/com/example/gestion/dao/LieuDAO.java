package com.example.gestion.dao;

import com.example.gestion.model.Lieu;
import com.example.gestion.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class LieuDAO {
    public List<Lieu> findAll() { try (Session s = HibernateUtil.getSessionFactory().openSession()) { return s.createQuery("from Lieu l order by l.designation", Lieu.class).list(); } }
    public Lieu findById(String id) { try (Session s = HibernateUtil.getSessionFactory().openSession()) { return s.get(Lieu.class, id); } }
    public void save(Lieu e) { execute(s -> s.persist(e)); }
    public void update(Lieu e) { execute(s -> s.merge(e)); }
    public void delete(String id) { execute(s -> { Lieu e=s.get(Lieu.class,id); if(e!=null)s.remove(e); }); }
    private void execute(java.util.function.Consumer<Session> action) {
        Transaction tx=null; try(Session s=HibernateUtil.getSessionFactory().openSession()){ tx=s.beginTransaction(); action.accept(s); tx.commit(); } catch(RuntimeException ex){ if(tx!=null)tx.rollback(); throw ex; }
    }
}

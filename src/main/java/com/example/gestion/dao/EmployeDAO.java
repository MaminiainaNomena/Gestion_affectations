package com.example.gestion.dao;

import com.example.gestion.model.Employe;
import com.example.gestion.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class EmployeDAO {
    public List<Employe> findAll() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Employe e order by e.nom, e.prenom", Employe.class).list();
        }
    }

    public Employe findById(String id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Employe.class, id);
        }
    }

    public List<Employe> search(String q) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Employe e where lower(e.codeemp) like :q or lower(e.nom) like :q order by e.nom",
                    Employe.class)
                    .setParameter("q", "%" + q.toLowerCase() + "%").list();
        }
    }

    public void save(Employe e) {
        execute(s -> s.persist(e));
    }

    public void update(Employe e) {
        execute(s -> s.merge(e));
    }

    public void delete(String id) {
        execute(s -> {
            Employe e = s.get(Employe.class, id);
            if (e != null)
                s.remove(e);
        });
    }

    private void execute(java.util.function.Consumer<Session> action) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            action.accept(s);
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx != null)
                tx.rollback();
            throw ex;
        }
    }
}

package com.example.gestion.dao;

import com.example.gestion.model.*;
import com.example.gestion.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class AffecterDAO {
    public List<Affecter> findAll() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("select a from Affecter a join fetch a.employe join fetch a.lieu order by a.date desc",
                    Affecter.class).list();
        }
    }

    public Affecter findById(String codeemp, String codelieu) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                    "select a from Affecter a join fetch a.employe join fetch a.lieu where a.id.codeemp=:e and a.id.codelieu=:l",
                    Affecter.class)
                    .setParameter("e", codeemp).setParameter("l", codelieu).uniqueResult();
        }
    }

    public void save(Affecter a) {
        execute(s -> {
            a.setEmploye(s.getReference(Employe.class, a.getEmploye().getCodeemp()));
            a.setLieu(s.getReference(Lieu.class, a.getLieu().getCodelieu()));
            s.persist(a);
        });
    }

    public void update(Affecter a) {
        execute(s -> s.merge(a));
    }

    public void delete(String e, String l) {
        execute(s -> {
            Affecter a = s.get(Affecter.class, new AffecterId(e, l));
            if (a != null)
                s.remove(a);
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

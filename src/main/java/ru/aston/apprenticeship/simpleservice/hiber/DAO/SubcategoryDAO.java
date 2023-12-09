package ru.aston.apprenticeship.simpleservice.hiber.DAO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.aston.apprenticeship.simpleservice.hiber.entity.Subcategory;

import java.util.List;

public class SubcategoryDAO {
    private final SessionFactory sessionFactory;

    public SubcategoryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Subcategory> getAllSubcategories() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Subcategory> query = builder.createQuery(Subcategory.class);
            query.from(Subcategory.class);
            return session.createQuery(query).getResultList();
        }
    }

    public Subcategory getSubcategoryById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Subcategory.class, id);
        }
    }

    public void addSubcategory(Subcategory subcategory) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(subcategory);
            transaction.commit();
        }
    }

    public void updateSubcategory(Subcategory subcategory) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(subcategory);
            transaction.commit();
        }
    }

    public void deleteSubcategory(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Subcategory subcategory = session.get(Subcategory.class, id);
            if (subcategory != null) {
                session.remove(subcategory);
            }
            transaction.commit();
        }
    }
}

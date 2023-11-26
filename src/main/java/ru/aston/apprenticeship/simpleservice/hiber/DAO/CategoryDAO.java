package ru.aston.apprenticeship.simpleservice.hiber.DAO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.aston.apprenticeship.simpleservice.hiber.entity.Category;

import java.util.List;

public class CategoryDAO {
    private final SessionFactory sessionFactory;

    public CategoryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Category> getAllCategories() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Category> query = builder.createQuery(Category.class);
            query.from(Category.class);
            return session.createQuery(query).getResultList();
        }
    }

    public Category getCategoryById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Category.class, id);
        }
    }

    public void addCategory(Category category) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        }
    }

    public void updateCategory(Category category) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(category);
            transaction.commit();
        }
    }

    public void deleteCategory(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Category category = session.get(Category.class, id);
            if (category != null) {
                session.delete(category);
            }
            transaction.commit();
        }
    }
}

package ru.aston.apprenticeship.simpleservice.hiber.DAO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.aston.apprenticeship.simpleservice.hiber.entity.Product;

import java.util.List;

public class ProductDAO {
    private final SessionFactory sessionFactory;

    public ProductDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Product> getAllProducts() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = builder.createQuery(Product.class);
            query.from(Product.class);
            return session.createQuery(query).getResultList();
        }
    }

    public Product getProductById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Product.class, id);
        }
    }

    public void addProduct(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        }
    }

    public void updateProduct(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        }
    }

    public void deleteProduct(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                session.delete(product);
            }
            transaction.commit();
        }
    }
}

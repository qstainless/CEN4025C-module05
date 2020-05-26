package gce.module05.model;

import gce.module05.controller.HibernateController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Singleton class to create, read, and delete the to-do items in a database.
 */
public class Data {

    private static final Data instance = new Data();

    // Where the to-do items will be stored in memory
    private ObservableList<Item> items;

    /**
     * Class constructor
     */
    private Data() {
    }

    // Getters
    public static Data getInstance() {
        return instance;
    }

    public ObservableList<Item> getItems() {
        return items;
    }

    // Add a new to-do item to the database and the Data model
    public void addItem(Item item) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateController.getSession();
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error writing to database. Item not added.");
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (Exception e) {
                System.out.println("Error closing database connection.");
                e.printStackTrace();
            }

            // Add item to Data model if successfully saved in the database
            items.add(item);
        }
    }

    /**
     * Loads the to-do items from the database. If the table does not exist,
     * hibernate will create it and the to-do list will be empty. Once to-do
     * items are created, they are first saved to the database. If successfully
     * saved, they will be added to the Data model for display in the GUI.
     */
    public void loadItems() {
        // Must use an observableArrayList to populate the GUI ListView
        items = FXCollections.observableArrayList();

        Session session = HibernateController.getSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Item> itemCriteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root<Item> itemRoot = itemCriteriaQuery.from(Item.class);
        CriteriaQuery<Item> allItems = itemCriteriaQuery.select(itemRoot);

        TypedQuery<Item> allQuery = session.createQuery(allItems);

        List<Item> itemData = allQuery.getResultList();

        items.addAll(itemData);
    }

    /**
     * Deletes an item from the database and removes it from the Data model
     *
     * @param item The item to delete
     */
    public void deleteItem(Item item) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateController.getSession();
            transaction = session.beginTransaction();
            session.delete(item);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error deleting from database. Item not deleted.");
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (Exception e) {
                System.out.println("Error closing database connection.");
                e.printStackTrace();
            }

            // Remove item from Data model if successfully deleted from the database
            items.remove(item);
        }
    }
}

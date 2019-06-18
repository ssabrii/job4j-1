package db.tracker;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.models.Item;
import ru.job4j.tracker.ITracker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

/**
 * TrackerSQL.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/11/2019
 */
public class TrackerSQL implements ITracker, AutoCloseable {
    /**
     * field logger.
     */
    private static final Logger LOG = LogManager.getLogger(
            TrackerSQL.class.getName());

    /**
     * field the path prop file.
     */
    private static final String PROPS = "jdbc.properties";
    /**
     * field the path prop file.
     */
    private static final String SCHEMA = "schema.sql";
    /**
     * field connection to db.
     */
    private final Connection connection;

    /**
     * Constructor.
     *
     * @param aConnection connection
     */
    public TrackerSQL(final Connection aConnection) {
        this.connection = aConnection;

    }

    /**
     * Method get and set connection to db.
     *
     * @return status connection
     */
    public final Connection init() {
        try (InputStream is = TrackerSQL.class.getClassLoader()
                .getResourceAsStream(PROPS)) {
            Properties props = new Properties();
            props.load(Objects.requireNonNull(is));
            Class.forName(props.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    props.getProperty("url"),
                    props.getProperty("username"),
                    props.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException("Invalid config file " + PROPS);
        }
    }


    @Override
    public final Item add(final Item item) {
        try (PreparedStatement pst = this.connection.prepareStatement(
                "INSERT INTO item (id_item, name_item, desc_item)"
                        + " VALUES (?,?,?)")) {
            final var three = 3;
            pst.setString(1, item.getId());
            pst.setString(2, item.getName());
            pst.setString(three, item.getDescription());
            pst.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }

    @Override
    public final boolean replace(final String id, final Item item) {
        boolean result = true;
        try (PreparedStatement ps = this.connection.prepareStatement(
                "UPDATE item SET name_item=?,desc_item=? WHERE id_item=?")) {
            final var three = 3;
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setString(three, id);
            if (ps.executeUpdate() != 1) {
                System.out.println("Item " + id + "not exist.");
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public final boolean delete(final String id) {
        boolean result = true;
        try (PreparedStatement pst = this.connection.prepareStatement(
                "DELETE  FROM item WHERE id_item=?")) {
            pst.setString(1, id);
            if (pst.executeUpdate() == 0) {
                System.out.println("ID = " + id + " not exist.");
                result = false;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public final Item[] findAll() {
        ArrayList<Item> items = new ArrayList<>();
        try (PreparedStatement ps = this.connection.prepareStatement(
                "SELECT * FROM item")) {
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                items.add(new Item(
                        rs.getString("id_item"),
                        rs.getString("name_item"),
                        rs.getString("desc_item")));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return items.toArray(new Item[0]);
    }

    @Override
    public final Item[] findByName(final String key) {
        ArrayList<Item> items = new ArrayList<>();
        try (PreparedStatement ps = this.connection.prepareStatement(
                "SELECT * FROM item WHERE name_item =?")) {
            ps.setString(1, key);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                items.add(new Item(
                        rs.getString("id_item"),
                        rs.getString("name_item"),
                        rs.getString("desc_item")));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return items.toArray(new Item[0]);
    }

    @Override
    public final Item findById(final String id) {
        ArrayList<Item> items = new ArrayList<>();
        try (PreparedStatement ps = this.connection.prepareStatement(
                "SELECT * FROM item WHERE id_item =?")) {
            ps.setString(1, id);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                items.add(new Item(
                        rs.getString("id_item"),
                        rs.getString("name_item"),
                        rs.getString("desc_item")));
                return items.get(0);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public final void close() {
        if (this.connection == null) {
            return;
        }
        try {
            this.connection.close();
        } catch (SQLException ex) {
            LOG.error("Connection to database don't close", ex);
        }
    }
}

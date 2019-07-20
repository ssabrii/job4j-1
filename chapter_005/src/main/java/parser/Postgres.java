package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

import static parser.SchedulerParser.getParam;


/**
 * ConnectPostgres.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 7/12/2019
 */
public class Postgres implements AutoCloseable {
    /**
     * field logger.
     */
    private static final Logger LOG = LogManager
            .getLogger(Postgres.class.getName());
    /**
     * field connection to postgres.
     */
    private final Connection connection;

    /**
     * Constructor.
     *
     * @param aConnection connection
     */
    public Postgres(final Connection aConnection) {
        this.connection = aConnection;
    }

    /**
     * field Set.
     */
    private static final Set<Vacancy> SET = new LinkedHashSet<>();

    /**
     * Getter a Set.
     *
     * @return the Set
     */
    public final Set<Vacancy> getSet() {
        return SET;
    }

    /**
     * field the path prop file.
     */
    public static final String SCHEMA = "vacancy.sql";

    /**
     * Method get and set connection to postgres.
     *
     * @return the data of connection
     */
    public static Connection init() {
        final String param = getParam();
        try (InputStream is = Postgres.class.
                getClassLoader().
                getResourceAsStream(param)) {
            Properties props = new Properties();
            props.load(Objects.requireNonNull(is));
            Class.forName(props.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    props.getProperty("url"),
                    props.getProperty("username"),
                    props.getProperty("password")

            );
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new IllegalStateException("Invalid config file " + param, e);
        }

    }

    /**
     * Method drop table in main.
     */
    public final void dropTable() {
        try (final PreparedStatement ps = this.connection
                .prepareStatement("DROP TABLE IF EXISTS vacancy")) {
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Method to get data from sql file.
     *
     * @return the data from sql file
     */
    private String getSqlCreateTable() {
        StringBuilder sb = new StringBuilder();
        try (var in = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(Postgres.class.getClassLoader()
                        .getResourceAsStream(SCHEMA))))) {
            in.lines().forEach(sb::append);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return sb.toString();
    }

    /**
     * Method create table in main.
     */
    public final void createTable() {
        try (final PreparedStatement ps = this.connection
                .prepareStatement(this.getSqlCreateTable())) {
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Method to check empty or not a vacancy.
     *
     * @return if empty return "", if not to return the last date
     */
    public final String getLastDateVacancy() {
        String date = "";
        try (final PreparedStatement ps = this.connection
                .prepareStatement("SELECT date_vacancy FROM vacancy"
                        + " WHERE id =?")) {
            ps.setInt(1, 1);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                date = rs.getString("date_vacancy");
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return date;
    }

    /**
     * Method to get last id of the vacancy.
     *
     * @return the last id of the vacancy
     */
    public final int getCountRowsInVacancy() {
        int id = -1;
        try (final PreparedStatement ps = this.connection
                .prepareStatement("SELECT COUNT(*) FROM vacancy")) {
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return id;
    }

    /**
     * Method add the set of vacancy to Vacancy.
     *
     * @param vacancies the Set of vacancies
     */
    public final void add(final Set<Vacancy> vacancies) {
        for (Vacancy vac : vacancies) {
            try (PreparedStatement pst = this.connection.prepareStatement(
                    "INSERT INTO vacancy (date_vacancy,"
                            + "name_vacancy, desc_vacancy, link_vacancy)"
                            + " VALUES (?,?,?,?)")) {
                final int one = 1;
                final int two = 2;
                final int three = 3;
                final int four = 4;
                pst.setString(one, vac.getDate());
                pst.setString(two, vac.getName());
                pst.setString(three, vac.getDescription());
                pst.setString(four, vac.getLink());
                pst.execute();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Method find vacancy by id.
     *
     * @param id the id of vacancy
     * @return the vacancy
     */
    public final Vacancy findVacancyById(final int id) {
        try (PreparedStatement ps = this.connection.prepareStatement(
                "SELECT * FROM vacancy WHERE id =?")) {
            ps.setInt(1, id);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Vacancy(
                        rs.getString("id"),
                        rs.getString("date_vacancy"),
                        rs.getString("name_vacancy"),
                        rs.getString("desc_vacancy"),
                        rs.getString("link_vacancy"));
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

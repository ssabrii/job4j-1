package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
public class Sqlite implements AutoCloseable {
    /**
     * field logger.
     */
    private static final Logger LOG = LogManager
            .getLogger(Sqlite.class.getName());
    /**
     * field connection to postgres.
     */
    private final Connection connection;

    /**
     * Constructor.
     *
     * @param aConnection connection
     */
    @Contract(pure = true)
    Sqlite(final Connection aConnection) {
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
    @Contract(pure = true)
    public final Set<Vacancy> getSet() {
        return SET;
    }

    /**
     * field the path prop file.
     */
    private static final String SCHEMA = "vacancy.sql";

    /**
     * Method get and set connection to postgres.
     *
     * @return the data of connection
     */
    static Connection init() {
        Connection result;
        final String param = getParam();
        try (InputStream is = Sqlite.class.
                getClassLoader().
                getResourceAsStream(param)) {
            Properties props = new Properties();
            props.load(Objects.requireNonNull(is));
            Class.forName(props.getProperty("driver-class-name"));
            result = DriverManager.getConnection(
                    props.getProperty("url"),
                    props.getProperty("username"),
                    props.getProperty("password")

            );
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new IllegalStateException("Invalid config file " + param, e);
        }
        return result;
    }

    /**
     * Method drop table in main.
     */
    final void dropTable() {
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
    @NotNull
    private String getSqlCreateTable() {
        StringBuilder sb = new StringBuilder();
        try (var in = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(Sqlite.class.getClassLoader()
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
    final void createTable() {
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
    final String getLastDateVacancy() {
        String date = "";
        try (final PreparedStatement ps = this.connection
                .prepareStatement("SELECT date_vacancy FROM vacancy"
                        + " WHERE ROWID =?")) {
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
    final int getCountRowsInVacancy() {
        int id = -1;
        try (final PreparedStatement ps = this.connection
                .prepareStatement("SELECT COUNT(ROWID) FROM vacancy")) {
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
    public final void add(@NotNull final Set<Vacancy> vacancies) {
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
    @Nullable
    final Vacancy findVacancyById(final int id) {
        try (PreparedStatement ps = this.connection.prepareStatement(
                "SELECT * FROM vacancy WHERE ROWID =?")) {
            ps.setInt(1, id);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Vacancy(
                        String.valueOf(id),
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

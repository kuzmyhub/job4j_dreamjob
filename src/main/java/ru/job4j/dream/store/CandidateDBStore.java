package ru.job4j.dream.store;

import net.jcip.annotations.ThreadSafe;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.dream.model.Candidate;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ThreadSafe
@Repository
public class CandidateDBStore {

    private static final String FIND_ALL = "SELECT * FROM candidate";

    private static final String ADD = "INSERT INTO candidate("
            + "name, description, created, visible, photo, city_id"
            + ")"
            + " VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE candidate SET name = (?),"
            + " description = (?),"
            + " created = (?),"
            + " visible = (?),"
            + " photo = (?),"
            + " city_id = (?)"
            + " WHERE id = (?)";

    private static final String FIND_BY_ID = "SELECT * FROM candidate WHERE id = ?";

    private static final Logger LOG
            = LoggerFactory.getLogger(CandidateDBStore.class.getName());

    private final BasicDataSource pool;

    public CandidateDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Candidate> findAll() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     FIND_ALL
             )) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    candidates.add(new Candidate(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("description"),
                            it.getTimestamp("created").toLocalDateTime(),
                            it.getBoolean("visible"),
                            it.getBytes("photo"),
                            it.getInt("city_id")
                    ));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
        return candidates;
    }

    public Candidate add(Candidate candidate) {
        try (Connection cn = pool.getConnection();
        PreparedStatement ps = cn.prepareStatement(
                ADD,
                PreparedStatement.RETURN_GENERATED_KEYS
        )) {
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getDesc());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setBoolean(4, candidate.isVisible());
            ps.setBytes(5, candidate.getPhoto());
            ps.setInt(6, candidate.getCity().getId());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                while (id.next()) {
                    candidate.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in log example");
        }
        return candidate;
    }

    public void update(Candidate candidate) {
        try (Connection cn = pool.getConnection();
        PreparedStatement ps = cn.prepareStatement(
                UPDATE)) {
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getDesc());
            ps.setTimestamp(3, Timestamp.valueOf(candidate.getCreated()));
            ps.setBoolean(4, candidate.isVisible());
            ps.setBytes(5, candidate.getPhoto());
            ps.setInt(6, candidate.getCity().getId());
            ps.setInt(7, candidate.getId());
            ps.execute();
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
    }

    public Candidate findById(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     FIND_BY_ID
             )) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return new Candidate(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("description"),
                            it.getTimestamp("created").toLocalDateTime(),
                            it.getBoolean("visible"),
                            it.getBytes("photo"),
                            it.getInt("city_id")
                    );
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
        return null;
    }
}

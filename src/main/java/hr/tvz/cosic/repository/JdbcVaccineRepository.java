package hr.tvz.cosic.repository;

import hr.tvz.cosic.vaccine.Vaccine;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Primary
@Repository
public class JdbcVaccineRepository implements VaccineRepository {

    private static final String SELECT_ALL = "SELECT id, research_name, manufacturer_name, numberOfShots, quantity, vaccine_type FROM vaccine";

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;

    public JdbcVaccineRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
        this.inserter = new SimpleJdbcInsert(jdbc)
                .withTableName("vaccine")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Set<Vaccine> findaAll() {
        return Set.copyOf(jdbc.query(SELECT_ALL, this::mapRowToVaccine));
    }

    private Vaccine mapRowToVaccine(ResultSet resultSet, int rowNum) throws SQLException {
        return new Vaccine(
                resultSet.getLong("id"),
                resultSet.getString("research_name"),
                resultSet.getString("manufacturer_name"),
                resultSet.getInt("numberOfShots"),
                resultSet.getInt("quantity"),
                Vaccine.VaccineType.valueOf(resultSet.getString("vaccine_type"))
        );
    }

    @Override
    public Optional<Vaccine> findVaccineByResearchName(String researchName) {
        try{
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + " WHERE research_name = ?", this::mapRowToVaccine, researchName)
            );
        } catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<Vaccine> findVaccineBoolean(Boolean aBoolean) {
        return null;
    }

    @Override
    public Optional<Vaccine> save(Vaccine vaccine) {
        try{
            vaccine.setId(saveVaccineDetails(vaccine));
            return Optional.of(vaccine);
        }catch(DuplicateKeyException e){
            return Optional.empty();
        }
    }

    @Override
    public void deleteByResearchName(String researchName) {
        jdbc.update("DELETE FROM vaccine_side_effect WHERE vaccine_id = ?", 1);
        jdbc.update("DELETE FROM vaccine WHERE research_name = ?", researchName);
    }

    @Override
    public Optional<Vaccine> findByResearchName(String researchName) {
        try{
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + " WHERE research_name = ?", this::mapRowToVaccine, researchName)
            );
        } catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Vaccine> update(String researchName, Vaccine updatedVaccineQuantityInStorage) {
        return Optional.empty();
    }

    private long saveVaccineDetails(Vaccine vaccine){
        Map<String, Object> values = new HashMap<>();

        values.put("research_name", vaccine.getResearchName());
        values.put("manufacturer_name", vaccine.getManufacturerName());
        values.put("numberOfShots", vaccine.getNumberOfShots());
        values.put("quantity", vaccine.getQuantityInStorage());
        values.put("vaccine_type", vaccine.getVaccineType());

        return inserter.executeAndReturnKey(values).longValue();
    }
}

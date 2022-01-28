package pl.pja.database.repos;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.pja.database.model.Flight;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Component
public class FlightRepo {
    private final EntityManager entityManager;

    @Autowired
    public FlightRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Flight> getFlightsByPage(String query) {
        return (List<Flight>) entityManager.createNativeQuery(query, Flight.class).getResultList();
    }

    @Transactional
    public HttpStatus deleteFlightById(int id) {
        try {
            entityManager.joinTransaction();
            entityManager.createNativeQuery("delete from flight where flight_id=?")
                    .setParameter(1, id)
                    .executeUpdate();
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.NOT_MODIFIED;
        }
    }

    public Flight getFlightById(int id) {
        return (Flight) entityManager.createNativeQuery("select * from flight where flight_id=?", Flight.class)
                .setParameter(1, id).getSingleResult();
    }

    @Transactional
    public HttpStatus updateFlight(String query, int id) {
        try {
            entityManager.joinTransaction();
            entityManager.createNativeQuery("update flight set " + query + " where flight_id=?")
                    .setParameter(1, id)
                    .executeUpdate();
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.NOT_MODIFIED;
        }

    }
}

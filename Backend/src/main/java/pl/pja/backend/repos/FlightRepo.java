package pl.pja.backend.repos;

import pl.pja.backend.model.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FlightRepo {
    private final EntityManager entityManager;

    public List<Flight> getFlightsByPage(String query){
        return (List<Flight>) entityManager.createNativeQuery(query,Flight.class).getResultList();
    }

    @Transactional
    public HttpStatus deleteFlightById(int id){
        try {
            entityManager.joinTransaction();
            entityManager.createNativeQuery("delete from flight where flight_id=?")
                    .setParameter(1,id)
                    .executeUpdate();
            return HttpStatus.OK;
        }catch (Exception e){
            return HttpStatus.NOT_MODIFIED;
        }
    }
}

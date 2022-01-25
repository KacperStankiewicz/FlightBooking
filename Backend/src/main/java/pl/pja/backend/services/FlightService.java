package pl.pja.backend.services;

import pl.pja.backend.DTO.FlightDto;
import pl.pja.backend.mappers.DtoMapper;
import pl.pja.backend.repos.FlightRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FlightService {
    private final FlightRepo flightRepo;
    private final DtoMapper dtoMapper;

    private String appendToQuery(FlightDto flight){
        String str = "";
        if(flight.getOrigin() != null && !Objects.equals(flight.getOrigin(), "")){
            str+="origin='"+flight.getOrigin()+ "' and ";
        }

        if(flight.getDestination() != null && !Objects.equals(flight.getDestination(), "")){
            str+="destination='"+flight.getDestination()+ "' and ";
        }

        if(flight.getDepartureTime() != null){
            str+="departure_time="+flight.getDepartureTime()+ " and ";
        }

        if(flight.getArrivalTime() != null){
            str+="arrival_time="+flight.getArrivalTime()+ " and ";
        }

        if(flight.getPrice() != null){
            str+="price="+flight.getPrice()+ " and ";
        }

        if(!str.equals("")){
            return "where "+str+"true";
        }else return str;
    }


    public List<FlightDto> getFlightsByPage(Integer page, FlightDto flightDto){
        String query = "Select * from flight "+appendToQuery(flightDto) +
                " limit 10 " +
                "offset "+(page-1)*10;

        return flightRepo.getFlightsByPage(query).stream().map(dtoMapper::mapToFlightDto).collect(Collectors.toList());
    }


    public HttpStatus deleteFlight(int id){
        return flightRepo.deleteFlightById(id);
    }
}

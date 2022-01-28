package pl.pja.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.pja.database.contracts.FlightDto;
import pl.pja.database.mappers.DtoMapper;
import pl.pja.database.repos.FlightRepo;

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

    private String makeUpdateQuery(FlightDto f){
        String str = "";
        if(f.getDestination() != null && !Objects.equals(f.getDestination(), "")){
            str+="destination='"+f.getDestination()+ "',";
        }

        if(f.getOrigin() != null && !Objects.equals(f.getOrigin(), "")){
            str+="origin='"+f.getOrigin()+ "',";
        }

        if(f.getDepartureTime() != null){
            str+="departure_time='"+f.getDepartureTime()+ "',";
        }

        if(f.getArrivalTime() != null){
            str+="arrival_time='"+f.getArrivalTime()+ "',";
        }

        if(f.getPrice() != null && !Objects.equals(f.getPrice(), (double) 0)){
            str+="price='"+f.getPrice()+ "',";
        }

        if(!str.equals("")){
            return str.substring(0,str.length()-1);
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

    public HttpStatus updateFlight(FlightDto flightDto){
        return flightRepo.updateFlight(makeUpdateQuery(flightDto),flightDto.getId());
    }

}

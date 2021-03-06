package pl.fastus.spacestation.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.dto.RequestDTO;
import pl.fastus.spacestation.domain.dto.ResponseDTO;
import pl.fastus.spacestation.domain.dto.StationPassesRequestDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IssPassMapperTest {
    private static final double LATITUDE = 25.2;
    private static final double LONGITUDE = 12.1;
    private static final int PASSES = 5;
    private static final int ALTITUDE = 1500;
    private static final long DAY_TIME = 123456L;

    IssPassesMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new IssPassesMapperImpl();
    }

    @Test
    void toIssPassesRequest() {
        RequestDTO requestDTO = RequestDTO.builder()
                .latitude( LATITUDE )
                .longitude( LONGITUDE )
                .passes( PASSES )
                .altitude( ALTITUDE )
                .dateTime( DAY_TIME ).build();

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setDuration( 10 );
        responseDTO.setRiseTime( 12356L );

        StationPassesRequestDTO request = new StationPassesRequestDTO();
        request.setRequestDTO(requestDTO);
        request.setResponseDTO(List.of(responseDTO));


        final IssPassesRequest converted = mapper.toIssPassesRequest( request );

        assertAll( ()->assertEquals( LATITUDE, converted.getLatitude() ),
                   ()->assertEquals( LONGITUDE, converted.getLongitude()),
                   ()->assertEquals( PASSES, converted.getPasses()),
                   ()->assertEquals( ALTITUDE, converted.getAltitude()),
                   ()->assertEquals( DAY_TIME, converted.getDatetime())
        );
    }
}

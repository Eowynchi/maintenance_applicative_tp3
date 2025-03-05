package agencyTests;

import org.junit.jupiter.api.Test;

import agency.Car;
import agency.UnknownVehicleException;
import agency.Vehicle;

import static org.assertj.core.api.Assertions.*;

class UnknownVehicleExceptionTest {

    @Test
    void testUnknownVehicleException() {
        Vehicle unknownVehicle = new Car("Tesla", "Model S", 2022, 5);
        UnknownVehicleException exception = new UnknownVehicleException(unknownVehicle);
        
        // V�rification que le message d'exception contient le nom du v�hicule
        assertThat(exception.getMessage()).contains("Tesla Model S 2022");
        
        // V�rification que la m�thode getVehicle() retourne bien le v�hicule
        assertThat(exception.getVehicle()).isEqualTo(unknownVehicle);
    }
}

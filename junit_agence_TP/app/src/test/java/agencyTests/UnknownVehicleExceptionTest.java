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
        
        // Vérification que le message d'exception contient le nom du véhicule
        assertThat(exception.getMessage()).contains("Tesla Model S 2022");
        
        // Vérification que la méthode getVehicle() retourne bien le véhicule
        assertThat(exception.getVehicle()).isEqualTo(unknownVehicle);
    }
}

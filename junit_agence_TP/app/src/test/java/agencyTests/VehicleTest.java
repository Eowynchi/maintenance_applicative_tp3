package agencyTests;

import org.junit.jupiter.api.Test;

import agency.Car;
import agency.Motorbike;
import agency.Vehicle;

import static org.assertj.core.api.Assertions.*;

class VehicleTest {

    @Test
    void testCarMethods() {
        Vehicle car = new Car("Toyota", "Corolla", 2020, 5);
        
        // V�rification des getters
        assertThat(car.getBrand()).isEqualTo("Toyota");
        assertThat(car.getModel()).isEqualTo("Corolla");
        assertThat(car.getProductionYear()).isEqualTo(2020);

        // V�rification du prix de location
        assertThat(car.dailyRentalPrice()).isEqualTo(40 * 5); // 40� par si�ge, 5 si�ges
        
        // V�rification de la m�thode toString()
        assertThat(car.toString()).contains("Toyota Corolla 2020 (5 seats) : ");
    }

    @Test
    void testMotorbikeMethods() {
        Vehicle motorbike = new Motorbike("Yamaha", "YZF-R1", 2019, 1000);
        
        // V�rification des getters
        assertThat(motorbike.getBrand()).isEqualTo("Yamaha");
        assertThat(motorbike.getModel()).isEqualTo("YZF-R1");
        assertThat(motorbike.getProductionYear()).isEqualTo(2019);

        // V�rification du prix de location
        assertThat(motorbike.dailyRentalPrice()).isEqualTo(0.25 * 1000); // 0.25� par cm�, 1000cm�
        
        // V�rification de la m�thode toString()
        assertThat(motorbike.toString()).contains("Yamaha YZF-R1 2019 (1000cm�) : ");
    }
}

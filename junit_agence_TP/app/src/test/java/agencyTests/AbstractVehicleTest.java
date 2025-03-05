package agencyTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import agency.AbstractVehicle;

import static org.assertj.core.api.Assertions.*;
import util.TimeProvider;

/**
 * Tests unitaires pour la classe AbstractVehicle.
 */
public class AbstractVehicleTest {

    private TestVehicle vehicle1;
    private TestVehicle vehicle2;
    private int currentYear;

    /**
     * Classe de test concrète pour tester AbstractVehicle.
     */
    private static class TestVehicle extends AbstractVehicle {
        public TestVehicle(String brand, String model, int productionYear) {
            super(brand, model, productionYear);
        }

        @Override
        public double dailyRentalPrice() {
            return 50.0;  // Prix fictif pour les tests
        }
    }

    @BeforeEach
    public void setup() {
        currentYear = TimeProvider.currentYearValue();
        vehicle1 = new TestVehicle("Toyota", "Corolla", 2020);
        vehicle2 = new TestVehicle("Toyota", "Corolla", 2020);
    }

    @Test
    public void shouldCreateVehicleWithValidProductionYear() {
        assertThat(vehicle1.getBrand()).isEqualTo("Toyota");
        assertThat(vehicle1.getModel()).isEqualTo("Corolla");
        assertThat(vehicle1.getProductionYear()).isEqualTo(2020);
    }

    @Test
    public void shouldThrowExceptionForInvalidProductionYear() {
        assertThatThrownBy(() -> new TestVehicle("Honda", "Civic", 1800))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("L'année de production 1800 est invalide.");

        assertThatThrownBy(() -> new TestVehicle("Honda", "Civic", currentYear + 1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("L'année de production " + (currentYear + 1) + " est invalide.");
    }

    @Test
    public void shouldConsiderVehiclesEqualIfAttributesMatch() {
        assertThat(vehicle1).isEqualTo(vehicle2);
    }

    @Test
    public void shouldNotConsiderVehiclesEqualIfDifferentAttributes() {
        TestVehicle differentVehicle = new TestVehicle("Honda", "Civic", 2020);
        assertThat(vehicle1).isNotEqualTo(differentVehicle);
    }

    @Test
    public void toStringShouldReturnCorrectFormat() {
        assertThat(vehicle1.toString()).isEqualTo("TestVehicle Toyota Corolla 2020");
    }

    @Test
    public void shouldReturnCorrectDailyRentalPrice() {
        assertThat(vehicle1.dailyRentalPrice()).isEqualTo(50.0);
    }
}

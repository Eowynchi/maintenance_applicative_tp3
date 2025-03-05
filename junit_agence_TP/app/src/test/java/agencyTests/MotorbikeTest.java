package agencyTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import agency.Motorbike;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests unitaires pour la classe {@link Motorbike}.
 */
class MotorbikeTest {

    private Motorbike motorbike;

    @BeforeEach
    void setUp() {
        // Crée une moto pour les tests
        motorbike = new Motorbike("Yamaha", "MT-07", 2020, 689); // Cylindrée de 689cm³
    }

    @Test
    void testMotorbikeDailyRentalPrice() {
        // Teste que le prix de location est bien calculé pour une moto avec une cylindrée spécifique
        double expectedPrice = 0.25 * 689;
        assertThat(motorbike.dailyRentalPrice()).isEqualTo(expectedPrice);
    }

    @Test
    void testMotorbikeToString() {
        // Teste la méthode toString
        String expectedString = "Motorbike Yamaha MT-07 2020 (689cm³) : " + motorbike.dailyRentalPrice() + "€";
        assertThat(motorbike.toString()).isEqualTo(expectedString);
    }

    @Test
    void testMotorbikeInvalidCylinderCapacity() {
        // Teste qu'une exception est lancée si la cylindrée est trop faible
        assertThatThrownBy(() -> new Motorbike("Yamaha", "YZF-R1", 2020, 40)) // Cylindrée trop faible
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("La cylindrée 40cm³ est trop faible.");
    }

    @Test
    void testMotorbikeValidCylinderCapacity() {
        // Teste la création d'une moto avec une cylindrée valide
        Motorbike validMotorbike = new Motorbike("Honda", "CBR500R", 2021, 500);
        assertThat(validMotorbike).isNotNull();
        assertThat(validMotorbike.dailyRentalPrice()).isEqualTo(0.25 * 500);
    }
}

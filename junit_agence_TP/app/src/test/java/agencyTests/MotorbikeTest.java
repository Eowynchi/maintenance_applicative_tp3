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
        // Cr�e une moto pour les tests
        motorbike = new Motorbike("Yamaha", "MT-07", 2020, 689); // Cylindr�e de 689cm�
    }

    @Test
    void testMotorbikeDailyRentalPrice() {
        // Teste que le prix de location est bien calcul� pour une moto avec une cylindr�e sp�cifique
        double expectedPrice = 0.25 * 689;
        assertThat(motorbike.dailyRentalPrice()).isEqualTo(expectedPrice);
    }

    @Test
    void testMotorbikeToString() {
        // Teste la m�thode toString
        String expectedString = "Motorbike Yamaha MT-07 2020 (689cm�) : " + motorbike.dailyRentalPrice() + "�";
        assertThat(motorbike.toString()).isEqualTo(expectedString);
    }

    @Test
    void testMotorbikeInvalidCylinderCapacity() {
        // Teste qu'une exception est lanc�e si la cylindr�e est trop faible
        assertThatThrownBy(() -> new Motorbike("Yamaha", "YZF-R1", 2020, 40)) // Cylindr�e trop faible
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("La cylindr�e 40cm� est trop faible.");
    }

    @Test
    void testMotorbikeValidCylinderCapacity() {
        // Teste la cr�ation d'une moto avec une cylindr�e valide
        Motorbike validMotorbike = new Motorbike("Honda", "CBR500R", 2021, 500);
        assertThat(validMotorbike).isNotNull();
        assertThat(validMotorbike.dailyRentalPrice()).isEqualTo(0.25 * 500);
    }
}

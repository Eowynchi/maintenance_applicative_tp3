package agencyTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import agency.BrandCriterion;
import agency.Car;
import agency.Vehicle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests unitaires pour la classe {@link BrandCriterion}.
 */
class BrandCriterionTest {

    private Vehicle toyotaCar;
    private Vehicle hondaCar;

    @BeforeEach
    void setUp() {
        toyotaCar = new Car("Toyota", "Corolla", 2020, 5);
        hondaCar = new Car("Honda", "Civic", 2018, 4);
    }

    @Test
    void testVehicleMatchesBrand() {
        BrandCriterion toyotaCriterion = new BrandCriterion("Toyota");

        // Vérifie que le véhicule de marque Toyota correspond au critère
        assertThat(toyotaCriterion.test(toyotaCar)).isTrue();
    }

    @Test
    void testVehicleDoesNotMatchBrand() {
        BrandCriterion toyotaCriterion = new BrandCriterion("Toyota");

        // Vérifie que le véhicule de marque Honda ne correspond pas au critère Toyota
        assertThat(toyotaCriterion.test(hondaCar)).isFalse();
    }

    @Test
    void testCaseInsensitiveMatching() {
        BrandCriterion toyotaCriterion = new BrandCriterion("toYoTa");

        // Vérifie que la casse n'affecte pas le résultat (Toyota vs toYoTa)
        assertThat(toyotaCriterion.test(toyotaCar)).isTrue();
    }

    @Test
    void testNullVehicleThrowsException() {
        BrandCriterion criterion = new BrandCriterion("Toyota");

        // Vérifie que passer un véhicule null génère une exception NullPointerException
        assertThatThrownBy(() -> criterion.test(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Cannot invoke");
    }
}

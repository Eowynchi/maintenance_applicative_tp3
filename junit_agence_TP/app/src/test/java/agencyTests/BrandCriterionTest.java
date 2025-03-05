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

        // V�rifie que le v�hicule de marque Toyota correspond au crit�re
        assertThat(toyotaCriterion.test(toyotaCar)).isTrue();
    }

    @Test
    void testVehicleDoesNotMatchBrand() {
        BrandCriterion toyotaCriterion = new BrandCriterion("Toyota");

        // V�rifie que le v�hicule de marque Honda ne correspond pas au crit�re Toyota
        assertThat(toyotaCriterion.test(hondaCar)).isFalse();
    }

    @Test
    void testCaseInsensitiveMatching() {
        BrandCriterion toyotaCriterion = new BrandCriterion("toYoTa");

        // V�rifie que la casse n'affecte pas le r�sultat (Toyota vs toYoTa)
        assertThat(toyotaCriterion.test(toyotaCar)).isTrue();
    }

    @Test
    void testNullVehicleThrowsException() {
        BrandCriterion criterion = new BrandCriterion("Toyota");

        // V�rifie que passer un v�hicule null g�n�re une exception NullPointerException
        assertThatThrownBy(() -> criterion.test(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Cannot invoke");
    }
}

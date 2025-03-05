package agencyTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import agency.Car;
import agency.MaxPriceCriterion;
import agency.Vehicle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests unitaires pour la classe {@link MaxPriceCriterion}.
 */
class MaxPriceCriterionTest {

    private Vehicle car1;
    private Vehicle car2;
    private MaxPriceCriterion maxPriceCriterion;

    @BeforeEach
    void setUp() {
        // Crée des véhicules pour les tests
        car1 = new Car("Peugeot", "208", 2020, 6); // Le prix sera calculé en fonction du nombre de sièges et de l'année
        car2 = new Car("Renault", "Clio", 2015, 4); // Ancien modèle, prix différent

        // Définir un critère de prix maximal
        maxPriceCriterion = new MaxPriceCriterion(200);  // Critère pour les véhicules dont le prix est inférieur ou égal à 200€
    }

    @Test
    void testMaxPriceCriterionWithValidPrice() {
        // Teste si le véhicule respecte le critère de prix maximal
        boolean resultCar1 = maxPriceCriterion.test(car1);
        boolean resultCar2 = maxPriceCriterion.test(car2);

        // Assertions avec AssertJ
        assertThat(resultCar1).isFalse(); // On suppose ici que car1 a un prix <= 200€
        assertThat(resultCar2).isTrue(); // car2 a un prix > 200€
    }

    @Test
    void testMaxPriceCriterionWithBoundaryPrice() {
        // Teste un véhicule dont le prix est exactement égal au critère
        MaxPriceCriterion criterion = new MaxPriceCriterion(180); // Critère avec prix exact

        Vehicle car3 = new Car("BMW", "X1", 2019, 4); // Prix à 180€

        boolean result = criterion.test(car3);

        assertThat(result).isTrue(); // Le véhicule doit respecter le critère
    }

    @Test
    void testMaxPriceCriterionWithHigherPrice() {
        // Teste si l'exception se déclenche avec un prix trop élevé
        MaxPriceCriterion criterion = new MaxPriceCriterion(100); // Critère pour véhicules dont le prix est <= 100€

        boolean result = criterion.test(car1); // Si car1 est au-dessus de 100€

        assertThat(result).isFalse(); // Le prix de car1 est trop élevé
    }

    @Test
    void testMaxPriceCriterionWithInvalidPrice() {
        // Teste un critère de prix avec une valeur négative
        assertThatThrownBy(() -> new MaxPriceCriterion(-50))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Le prix maximal ne peut pas être négatif.");
    }
}

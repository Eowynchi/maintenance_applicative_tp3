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
        // Cr�e des v�hicules pour les tests
        car1 = new Car("Peugeot", "208", 2020, 6); // Le prix sera calcul� en fonction du nombre de si�ges et de l'ann�e
        car2 = new Car("Renault", "Clio", 2015, 4); // Ancien mod�le, prix diff�rent

        // D�finir un crit�re de prix maximal
        maxPriceCriterion = new MaxPriceCriterion(200);  // Crit�re pour les v�hicules dont le prix est inf�rieur ou �gal � 200�
    }

    @Test
    void testMaxPriceCriterionWithValidPrice() {
        // Teste si le v�hicule respecte le crit�re de prix maximal
        boolean resultCar1 = maxPriceCriterion.test(car1);
        boolean resultCar2 = maxPriceCriterion.test(car2);

        // Assertions avec AssertJ
        assertThat(resultCar1).isFalse(); // On suppose ici que car1 a un prix <= 200�
        assertThat(resultCar2).isTrue(); // car2 a un prix > 200�
    }

    @Test
    void testMaxPriceCriterionWithBoundaryPrice() {
        // Teste un v�hicule dont le prix est exactement �gal au crit�re
        MaxPriceCriterion criterion = new MaxPriceCriterion(180); // Crit�re avec prix exact

        Vehicle car3 = new Car("BMW", "X1", 2019, 4); // Prix � 180�

        boolean result = criterion.test(car3);

        assertThat(result).isTrue(); // Le v�hicule doit respecter le crit�re
    }

    @Test
    void testMaxPriceCriterionWithHigherPrice() {
        // Teste si l'exception se d�clenche avec un prix trop �lev�
        MaxPriceCriterion criterion = new MaxPriceCriterion(100); // Crit�re pour v�hicules dont le prix est <= 100�

        boolean result = criterion.test(car1); // Si car1 est au-dessus de 100�

        assertThat(result).isFalse(); // Le prix de car1 est trop �lev�
    }

    @Test
    void testMaxPriceCriterionWithInvalidPrice() {
        // Teste un crit�re de prix avec une valeur n�gative
        assertThatThrownBy(() -> new MaxPriceCriterion(-50))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Le prix maximal ne peut pas �tre n�gatif.");
    }
}

package agencyTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import agency.Car;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests unitaires pour la classe {@link Car}.
 */
class CarTest {

    private Car carNewModel;
    private Car carOldModel;
    private Car carWithOneSeat;
    private Car carWithMultipleSeats;

    @BeforeEach
    void setUp() {
        // Voiture avec un modèle récent (2020)
        carNewModel = new Car("Toyota", "Corolla", 2020, 5);

        // Voiture avec un modèle plus ancien (2015)
        carOldModel = new Car("Honda", "Civic", 2015, 5);
        
        carWithOneSeat = new Car("Toyota", "Corolla", 2018, 1);
        carWithMultipleSeats = new Car("Honda", "Civic", 2020, 5);
    }

    @Test
    void testCarCreationWithValidSeats() {
        // Crée une voiture avec un nombre valide de sièges
        Car car = new Car("Ford", "Focus", 2019, 4);

        // Vérifie que la voiture est bien créée avec les sièges spécifiés
        assertThat(car).isNotNull();
        assertThat(car.dailyRentalPrice()).isEqualTo(80);  
    }

    @Test
    void testCarCreationWithInvalidSeats() {
        // Teste la création d'une voiture avec un nombre de sièges invalide
        assertThatThrownBy(() -> new Car("Ford", "Focus", 2020, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Le nombre de sièges 0 est invalide.");
    }

    @Test
    void testDailyRentalPriceNewModel() {
        // Vérifie que le prix pour un modèle récent (2020) est correct
        assertThat(carNewModel.dailyRentalPrice()).isEqualTo(200); // 40€ * 5 sièges
    }

    @Test
    void testDailyRentalPriceOldModel() {
        // Vérifie que le prix pour un modèle ancien (2015) est correct
        assertThat(carOldModel.dailyRentalPrice()).isEqualTo(100); // 20€ * 5 sièges
    }

    @Test
    void testToStringSingleSeat() {
        // Vérifie que la méthode toString() retourne la bonne chaîne pour un véhicule avec 1 siège
        String expected = "Car Toyota Corolla 2018 (1 seat) : " + carWithOneSeat.dailyRentalPrice() + "€";
        assertThat(carWithOneSeat.toString()).isEqualTo(expected);
    }

    @Test
    void testToStringMultipleSeats() {
        // Vérifie que la méthode toString() retourne la bonne chaîne pour un véhicule avec plusieurs sièges
        String expected = "Car Honda Civic 2020 (5 seats) : " + carWithMultipleSeats.dailyRentalPrice() + "€";
        assertThat(carWithMultipleSeats.toString()).isEqualTo(expected);
    }

    @Test
    void testToStringWithNewModel() {
        // Vérifie que la méthode toString() inclut bien le prix de location pour un modèle récent
        double expectedPrice = 40 * 5; // Prix pour 5 sièges pour un modèle récent
        String expected = "Car Honda Civic 2020 (5 seats) : " + expectedPrice + "€";
        assertThat(carWithMultipleSeats.toString()).isEqualTo(expected);
    }

    @Test
    void testToStringWithOldModel() {
        // Simule une ancienne année de production (ex : avant 2015) pour avoir un prix réduit
        Car oldCar = new Car("Peugeot", "208", 2014, 5);
        double expectedPrice = 20 * 5; // Prix pour 5 sièges pour un modèle plus ancien
        String expected = "Car Peugeot 208 2014 (5 seats) : " + expectedPrice + "€";
        assertThat(oldCar.toString()).isEqualTo(expected);
    }
}

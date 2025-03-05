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
        // Voiture avec un mod�le r�cent (2020)
        carNewModel = new Car("Toyota", "Corolla", 2020, 5);

        // Voiture avec un mod�le plus ancien (2015)
        carOldModel = new Car("Honda", "Civic", 2015, 5);
        
        carWithOneSeat = new Car("Toyota", "Corolla", 2018, 1);
        carWithMultipleSeats = new Car("Honda", "Civic", 2020, 5);
    }

    @Test
    void testCarCreationWithValidSeats() {
        // Cr�e une voiture avec un nombre valide de si�ges
        Car car = new Car("Ford", "Focus", 2019, 4);

        // V�rifie que la voiture est bien cr��e avec les si�ges sp�cifi�s
        assertThat(car).isNotNull();
        assertThat(car.dailyRentalPrice()).isEqualTo(80);  
    }

    @Test
    void testCarCreationWithInvalidSeats() {
        // Teste la cr�ation d'une voiture avec un nombre de si�ges invalide
        assertThatThrownBy(() -> new Car("Ford", "Focus", 2020, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Le nombre de si�ges 0 est invalide.");
    }

    @Test
    void testDailyRentalPriceNewModel() {
        // V�rifie que le prix pour un mod�le r�cent (2020) est correct
        assertThat(carNewModel.dailyRentalPrice()).isEqualTo(200); // 40� * 5 si�ges
    }

    @Test
    void testDailyRentalPriceOldModel() {
        // V�rifie que le prix pour un mod�le ancien (2015) est correct
        assertThat(carOldModel.dailyRentalPrice()).isEqualTo(100); // 20� * 5 si�ges
    }

    @Test
    void testToStringSingleSeat() {
        // V�rifie que la m�thode toString() retourne la bonne cha�ne pour un v�hicule avec 1 si�ge
        String expected = "Car Toyota Corolla 2018 (1 seat) : " + carWithOneSeat.dailyRentalPrice() + "�";
        assertThat(carWithOneSeat.toString()).isEqualTo(expected);
    }

    @Test
    void testToStringMultipleSeats() {
        // V�rifie que la m�thode toString() retourne la bonne cha�ne pour un v�hicule avec plusieurs si�ges
        String expected = "Car Honda Civic 2020 (5 seats) : " + carWithMultipleSeats.dailyRentalPrice() + "�";
        assertThat(carWithMultipleSeats.toString()).isEqualTo(expected);
    }

    @Test
    void testToStringWithNewModel() {
        // V�rifie que la m�thode toString() inclut bien le prix de location pour un mod�le r�cent
        double expectedPrice = 40 * 5; // Prix pour 5 si�ges pour un mod�le r�cent
        String expected = "Car Honda Civic 2020 (5 seats) : " + expectedPrice + "�";
        assertThat(carWithMultipleSeats.toString()).isEqualTo(expected);
    }

    @Test
    void testToStringWithOldModel() {
        // Simule une ancienne ann�e de production (ex : avant 2015) pour avoir un prix r�duit
        Car oldCar = new Car("Peugeot", "208", 2014, 5);
        double expectedPrice = 20 * 5; // Prix pour 5 si�ges pour un mod�le plus ancien
        String expected = "Car Peugeot 208 2014 (5 seats) : " + expectedPrice + "�";
        assertThat(oldCar.toString()).isEqualTo(expected);
    }
}

package utilTests;

import org.junit.jupiter.api.Test;

import util.TimeProvider;

import static org.assertj.core.api.Assertions.*;

class TimeProviderTest {

    @Test
    void testCurrentYearValue() {
    	TimeProvider timeProvider = new TimeProvider();
        int currentYear = timeProvider.currentYearValue();
        
        // Vérification que l'année actuelle est celle renvoyée par TimeProvider
        int actualYear = java.time.LocalDate.now().getYear();
        
        assertThat(currentYear).isEqualTo(actualYear);
    }
}

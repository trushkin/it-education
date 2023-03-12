import by.bsuir.client.service.ScheduleCheck;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleCheckTest {

    @Test
    void calculateEndWorkingTimeEqualsCurrentDay() {
        LocalDateTime testStartDateTime = LocalDateTime.of(2022, 12, 12, 9, 0);
        int duration = 4;
        LocalDateTime expectedFinalWorkingTime = LocalDateTime.of(2022, 12, 12, 13, 0);
        assertEquals(expectedFinalWorkingTime, ScheduleCheck.calculateFinalWorkingTime(testStartDateTime, duration));
    }

    @Test
    void calculateEndWorkingTimeEqualsNextDay() {
        LocalDateTime testStartDateTime = LocalDateTime.of(2022, 12, 12, 9, 0);
        int duration = 11;
        LocalDateTime expectedFinalWorkingTime = LocalDateTime.of(2022, 12, 13, 11, 0);
        assertEquals(expectedFinalWorkingTime, ScheduleCheck.calculateFinalWorkingTime(testStartDateTime, duration));
    }

    @Test
    void invalidTimeIntervalTest() {
        LocalDateTime testStartDateTime = LocalDateTime.of(2022, 12, 12, 9, 0);
        assertFalse(ScheduleCheck.checkTimeInterval(testStartDateTime, 3, 1));
    }

    @Test
    void validTimeIntervalTest() {
        LocalDateTime testStartDateTime = LocalDateTime.of(2022, 12, 12, 10, 0);
        assertTrue(ScheduleCheck.checkTimeInterval(testStartDateTime, 3, 1));
    }

    @Test
    void test() {
        assertEquals(LocalDateTime.now(), ScheduleCheck.calculateFinalWorkingTime(LocalDateTime.of(2022, 12, 17, 18, 0), 0.5));
    }
}
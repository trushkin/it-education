package by.bsuir.client.service;

import by.pojo.Properties;
import by.pojo.Schedule;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static by.bsuir.client.ClientApp.connection;

public class ScheduleCheck {
    public static LocalDateTime calculateFinalWorkingTime(LocalDateTime startDate, double duration) {
        //LocalDateTime finalWorkingTime = startDate;
        Properties properties = connection.getWorkingHours();
        LocalDateTime endWorkTime = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), properties.getEndWorkTime().getHour(), 0);
        if (!startDate.plusMinutes((int) (duration * 60)).isAfter(endWorkTime)) {
            return startDate.plusMinutes((int) (duration * 60));
        }
        if ((int) duration == 0) {
            return LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), properties.getStartWorkTime().getHour(), (int) (duration * 60));
        }
        duration -= (properties.getEndWorkTime().getHour() - startDate.getHour()); // остаток от длительности работы после вычета свободных часов в первый день записи
        startDate = startDate.plusDays(1);

        while (properties.getStartWorkTime().getHour() + duration > properties.getEndWorkTime().getHour()) {
            duration -= (properties.getEndWorkTime().getHour() - properties.getStartWorkTime().getHour());// отнимаем целый рабочий день в часах
            startDate = startDate.plusDays(1);

        }
        return LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), properties.getStartWorkTime().getHour() + (int) duration, startDate.getMinute());
    }

    public static Schedule findSchedule(LocalDateTime startDate, double duration, List<Schedule> scheduleList, int liftID){
        LocalDateTime finalWorkingTime = calculateFinalWorkingTime(startDate, duration);
        for (Schedule l : scheduleList) {
            LocalDateTime finalWorkingTime2 = calculateFinalWorkingTime(l.getStartDate(), l.getDuration());
            if (!(finalWorkingTime.minusNanos(1).isBefore(l.getStartDate()) || startDate.plusNanos(1).isAfter(finalWorkingTime2)) && l.getLiftID() == liftID) //проверка на пересечение временного интервала
            {
                return l;
            }
        }
        return null;
    }

    public static boolean checkTimeInterval(LocalDateTime startDate, double duration, int liftID)  {
        return checkTimeInterval(startDate, duration, connection.getAllSchedules(), liftID);
    }

    public static boolean checkTimeInterval(LocalDateTime startDate, double duration, List<Schedule> scheduleList, int liftID){
        return (findSchedule(startDate, duration, scheduleList, liftID) == null ? true : false);
    }
}

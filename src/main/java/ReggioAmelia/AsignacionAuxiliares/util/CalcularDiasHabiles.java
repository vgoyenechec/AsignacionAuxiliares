package ReggioAmelia.AsignacionAuxiliares.util;

import de.focus_shift.*;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CalcularDiasHabiles {

    public List<LocalDate> calcularSabadosHabiles() {
        LocalDate inicio = LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 20);
        LocalDate fin = LocalDate.of(LocalDate.now().getYear(), Month.DECEMBER, 1);
        List<LocalDate> sabadosHabiles = new ArrayList<>();

        HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(
                HolidayCalendar.COLOMBIA));

        for (LocalDate fecha = inicio; fecha.isBefore(fin); fecha = fecha.plusDays(1)) {
            if (fecha.getDayOfWeek().getValue() == 6 && !esFestivo(fecha, holidayManager) && !esVacaciones(fecha) ) {
                sabadosHabiles.add(fecha);
            }
        }
        return sabadosHabiles;
    }

    private boolean esVacaciones(LocalDate fecha){
        return (fecha.getMonth().equals(Month.JUNE) && fecha.getDayOfMonth() > 15)
                || (fecha.getMonth().equals(Month.JULY) && fecha.getDayOfMonth() < 10);
    }
    private boolean esFestivo(LocalDate fecha, HolidayManager holidayManager) {
        LocalDate sabadoSanto = calcularDomingoSanto().minusDays(1);
        Set<Holiday> festivos = holidayManager.getHolidays(fecha.getYear());
        festivos.add(new Holiday(sabadoSanto, "Sabado Santo", HolidayType.UNOFFICIAL_HOLIDAY));
        return festivos.stream().anyMatch(h -> h.getDate().isEqual(fecha));
    }

    public static LocalDate calcularDomingoSanto() {
        int year = LocalDate.now().getYear();
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int month = (h + l - 7 * m + 114) / 31;
        int day = ((h + l - 7 * m + 114) % 31) + 1;

        return LocalDate.of(year, month, day);
    }
}

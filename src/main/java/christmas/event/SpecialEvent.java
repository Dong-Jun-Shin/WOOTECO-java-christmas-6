package christmas.event;

import java.util.ArrayList;
import java.util.List;

public class SpecialEvent {
    private static final Integer BENEFIT = 1000;
    private static final ArrayList<Integer> SPECIAL_DAYS = new ArrayList<>(List.of(3, 10, 17, 24, 25, 31));
    public static final String EVENT_NAME = "특별 할인";

    public static boolean isApply(int visitDay) {
        if (!SPECIAL_DAYS.contains(visitDay)) {
            return false;
        }

        return true;
    }

    public static Integer getBenefit(int visitDay) {
        if (!isApply(visitDay)) {
            return 0;
        }

        return BENEFIT;
    }
}

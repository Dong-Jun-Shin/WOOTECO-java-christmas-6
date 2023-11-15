package christmas.event;

public class DdayEvent {
    private static final Integer BENEFIT = 3400;
    private static final Integer UNIT_DISCOUNT = 100;
    private static final Integer START_DAY = 1;
    private static final Integer END_DAY = 25;
    public static final String EVENT_NAME = "크리스마스 디데이 할인";

    public static boolean isApply(int visitDay) {
        if (visitDay < START_DAY || visitDay > END_DAY) {
            return false;
        }

        return true;
    }

    public static Integer getBenefit(int visitDay) {
        if (!isApply(visitDay)) {
            return 0;
        }

        int dDay = END_DAY - visitDay;

        return BENEFIT - (dDay * UNIT_DISCOUNT);
    }
    
}

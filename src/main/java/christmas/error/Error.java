package christmas.error;

public class Error {
    public static final String ERROR_MESSAGE = "[ERROR]";

    public static final String getMessage(String message) {
        return ERROR_MESSAGE + " " + message;
    }

    public static final String invalidOrder() {
        return getMessage("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    public static final String invalidDate() {
        return getMessage("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    public static final String invalidBenefits() {
        return getMessage("유효하지 않은 혜택입니다. 다시 입력해 주세요.");
    }
}

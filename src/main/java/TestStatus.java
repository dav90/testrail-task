package main.java;

/**
 * Created by davits on 7/20/17.
 */
enum TestStatus {
    PASSED(1),
    BLOCKED(2),
    RETEST(3),
    FAILED(4);

    /**
     * The value
     */
    private final int value;

    /**
     * The regular constructor.
     *
     * @param value the value
     */
    TestStatus(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * The factory method, returns the enum constant by the given value.
     *
     * @param value the value
     * @return the enum type constant
     */
    public static TestStatus valueOf(final int value) {
        for (TestStatus e : TestStatus.values()) {
            if (e.value == value) {
                return e;
            }
        }
        return null;
    }
}

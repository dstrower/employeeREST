package com.employee.objects;

/**
 * This class is the Result from an attempted action.
 * The error variable is set to TRUE if something goes wrong.
 * The reason variable will contain a description of the problem.
 */
public class Result {

    private boolean error = false;
    private String reason = "";

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

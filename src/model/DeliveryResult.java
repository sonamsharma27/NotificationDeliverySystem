package model;


import enums.Enums.*;

public class DeliveryResult {
    private final Status status;
    private final ErrorCode errorCode;
    private final String errorMessage;
    private final String providerReference;

    public DeliveryResult(Status status, ErrorCode errorCode, String errorMessage, String providerReference) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.providerReference = providerReference;
    }

    public Status getStatus() {
        return status;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getProviderReference() {
        return providerReference;
    }
}

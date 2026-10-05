package enums;

public  class Enums {

    public enum Status {
        SUCCESS,
        FAILED,
        UNKNOWN
    }

    public enum ErrorCode {
        PROVIDER_REJECTED,
        PROVIDER_TIMEOUT,
        PROVIDER_UNAVAILABLE,
        UNKNOWN
    }

    public enum ChannelType {
        SMS,
        PUSH,
        EMAIL
    }
}

package enums;

public  enum Enums {

    public  enum Status {
        SUCCESS,
        FAILED
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

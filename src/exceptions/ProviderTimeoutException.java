package exceptions;

public class ProviderTimeoutException extends  RuntimeException{
    private final String providerReference;
    public  ProviderTimeoutException(String message, String providerReference){
        super(message);
        this.providerReference=providerReference;
    }
    public String getProviderReference(){
        return providerReference;
    }
}

package exceptions;

public class ProviderRejectedException extends  RuntimeException{
    private final String providerReference;
    public  ProviderRejectedException(String message, String providerReference){
        super(message);
        this.providerReference=providerReference;
    }
    public String getProviderReference(){
        return providerReference;
    }
}



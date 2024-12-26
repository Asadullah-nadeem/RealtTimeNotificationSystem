package nadeem.in.realttimenotification;

public class notification {

    final String message;
    final int timestamp;

    public notification(String message){
        this.message = message;
        this.timestamp = (int) System.currentTimeMillis();
    }

    public String getMessage() {
        return message;
    }

    public int getTimestamp() {
        return timestamp;
    }

}


package models;

public class Message {
    private String message;
    private String userName;
    private String file;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Message{" + "message=" + message + ", userName=" + userName + ", file=" + file + '}';
    }  
}

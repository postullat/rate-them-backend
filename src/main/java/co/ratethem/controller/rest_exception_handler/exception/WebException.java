package co.ratethem.controller.rest_exception_handler.exception;

public class WebException extends Exception {

    private static final long serialVersionUID = 5494339415617534286L;
    private String pointer;
    private String parameter;
    private Integer code;

    public WebException(String message) {
        super(message);
    }

    public WebException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public WebException(String message, Integer code, String pointer, String parameter) {
        super(message);
        this.code = code;
        this.pointer = pointer;
        this.parameter = parameter;
    }

    public WebException(Exception e) {
        super(e.getMessage(), e);
    }

    public String getPointer() {
        return pointer;
    }

    public void setPointer(String pointer) {
        this.pointer = pointer;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}

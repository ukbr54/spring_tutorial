package restapi.exceptionhandling.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ApiError {

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private String message;

    private String debugMessage;

    private List<ApiSubError> subErrors;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    ApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    /**
     * Handling Field Error
     * @param fieldErrors
     */
    void addValidationErrors(List<FieldError> fieldErrors){
       fieldErrors.forEach(this::addValidationError);
    }

    private void addValidationError(FieldError fieldError){
        this.addValidationError(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage()
        );
    }

    /**
     * Handling Global Error
     * @param globalErrors
     */
    void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    private void addValidationError(ObjectError objectError) {
        this.addValidationError(
                objectError.getObjectName(),
                objectError.getDefaultMessage());
    }

    private void addValidationError(String object,String field,Object rejectedValue,String message){
        addSubError(new ApiValidationError(object,field,rejectedValue,message));
    }

    private void addValidationError(String object,String message){
        addSubError(new ApiValidationError(object,message));
    }

    private void addSubError(ApiSubError subError){
        if(subError == null) {
            this.subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }
}

abstract class ApiSubError{ }


@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
class ApiValidationError extends ApiSubError{

    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ApiValidationError(String object,String message){
        this.object = object;
        this.message = message;
    }
}
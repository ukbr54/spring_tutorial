package restapi.exceptionhandling.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.util.*;

@Data
public class ApiError {

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private String message;

    private String debugMessage;

    private List<ApiSubError> subErrors = new ArrayList<>();

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
    void addValidationErrors(List<FieldError> fieldErrors){ fieldErrors.forEach(this::addValidationError); }

    private void addValidationError(FieldError fieldError){
        this.addValidationError(
                Objects.nonNull(fieldError.getObjectName()) ? fieldError.getObjectName() : null,
                Objects.nonNull(fieldError.getField()) ? fieldError.getField() : null,
                Objects.nonNull(fieldError.getRejectedValue()) ? fieldError.getRejectedValue() : null,
                Objects.nonNull(fieldError.getDefaultMessage()) ? fieldError.getDefaultMessage() : null
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
                Objects.nonNull(objectError.getObjectName()) ? objectError.getObjectName() : null,
                Objects.nonNull(objectError.getDefaultMessage()) ? objectError.getDefaultMessage() : null
        );
    }

    private void addValidationError(String object,String field,Object rejectedValue,String message){
        addSubError(new ApiValidationError(object,field,rejectedValue,message));
    }

    private void addValidationError(String object,String message){
        addSubError(new ApiValidationError(object,message));
    }

    private void addSubError(ApiSubError subError){
        if(subError == null) {
            this.subErrors = Collections.emptyList();
        }
        subErrors.add(subError);
    }

    /**
     * Utility method for adding error of ConstraintViolation. Usually when a @Validated validation fails.
     *
     * @param cv the ConstraintViolation
     */
    private void addValidationError(ConstraintViolation<?> cv) {
        this.addValidationError(
                cv.getRootBeanClass().getSimpleName(),
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getInvalidValue(),
                cv.getMessage());
    }

    void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
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
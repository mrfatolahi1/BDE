package response;

import java.io.Serializable;

public class Response implements Serializable {
    private final ResponseStatus status;

    public Response(ResponseStatus status) {
        this.status = status;
    }

    public ResponseStatus getStatus() {
        return status;
    }
}

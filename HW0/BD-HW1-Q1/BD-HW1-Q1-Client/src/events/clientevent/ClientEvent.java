package events.clientevent;

import java.io.Serializable;

public class ClientEvent implements Serializable {
    private final ClientEventStatus status;
    private final int time;

    public ClientEvent(ClientEventStatus status, int time) {
        this.status = status;
        this.time = time;
    }

    public ClientEventStatus getStatus() {
        return status;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "ClientEvent{" +
                "status=" + status +
                ", time=" + time +
                '}';
    }
}

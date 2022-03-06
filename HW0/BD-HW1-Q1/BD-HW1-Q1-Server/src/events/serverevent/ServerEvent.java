package events.serverevent;

import java.io.Serializable;

public class ServerEvent implements Serializable {
    private final ServerEventStatus status;
    private final int time;

    public ServerEvent(ServerEventStatus status, int time) {
        this.status = status;
        this.time = time;
    }

    public ServerEventStatus getStatus() {
        return status;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "ServerEvent{" +
                "status=" + status +
                ", time=" + time +
                '}';
    }
}

package no.jan.rocket.flight;

import no.jan.rocket.comm.RocketCommandReply;

/**
 * Created by jasand on 05.02.2017.
 */
public interface CommandResponseListener {
    void receiveCommandResponse(RocketCommandReply rocketCommandReply);
}

package no.jan.rocket.comm;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by jasand on 16.01.2017.
 */
public class SerialReceiverParserTest {
    private final String cmdResponse1 = "{\"replyFor\":\"HRTB\",\"status\":\"OK\"}";
    private final String cmdResponse2 = "{\"replyFor\":\"STRT\",\"status\":\"OK\"}";
    private final String cmdResponsePart1 = "{\"replyFor";
    private final String cmdResponsePart2 = "\":\"HRTB\",\"status\":\"OK\"}";
    private final String imu1 = "{\"ts\":16679,\"gx\":82,\"gy\":-48,\"gz\":-222,\"ax\":-1330,\"ay\":1499,\"az\":16100,\"mx\":-564,\"my\":1749,\"mz\":-4059}";
    private final String imu2 = "{\"ts\":19011,\"gx\":92,\"gy\":-47,\"gz\":-245,\"ax\":-1397,\"ay\":1492,\"az\":16187,\"mx\":-570,\"my\":1732,\"mz\":-4108}";
    private final String imuPart1 = "{\"ts\":16679,\"gx\":82,\"gy\":-48";
    private final String imuPart2 = ",\"gz\":-222,\"ax\":-1330,\"ay\":1499,\"az\":16100,\"mx\":-564,\"my\":1749,\"mz\":-4059}";
    private final String alt1 = "{\"ts\":56268,\"alt\":172.06,\"temp\":19.62}";


//    {"replyFor":"HRTB","status":"OK"}
//    {"replyFor":"STRT","status":"OK"}
//    {"ts":16679,"gx":82,"gy":-48,"gz":-222,"ax":-1330,"ay":1499,"az":16100,"mx":-564,"my":1749,"mz":-4059}
//    {"ts":16797,"alt":182.06,"temp":20.75}
//    {"ts":17845,"gx":101,"gy":-56,"gz":-239,"ax":-1419,"ay":1452,"az":16228,"mx":-538,"my":1715,"mz":-4065}
//    {"ts":17964,"alt":181.25,"temp":21.06}
//    {"ts":19011,"gx":92,"gy":-47,"gz":-245,"ax":-1397,"ay":1492,"az":16187,"mx":-570,"my":1732,"mz":-4108}
//    {"ts":19129,"alt":181.19,"temp":21.12}
//    {"replyFor":"STOP","status":"OK"}


                    @Test
    public void shouldParseCorrectCmdResponse() {
        SerialReceiverParser parser = new SerialReceiverParser();
        List<RocketDataPacket> dataPackets = parser.receiveAndScanForDataPackets(cmdResponse1);
        assertEquals(1, dataPackets.size());
        assertTrue(dataPackets.get(0) instanceof RocketCommandReply);
        assertEquals("HRTB", ((RocketCommandReply)dataPackets.get(0)).getReplyFor());
    }

    @Test
    public void shouldParseCorrectTelemetryData() {
        SerialReceiverParser parser = new SerialReceiverParser();
        List<RocketDataPacket> dataPackets = parser.receiveAndScanForDataPackets(imu2);
        assertEquals(1, dataPackets.size());
        assertTrue(dataPackets.get(0) instanceof IMUData);
        assertEquals(new Long(19011L), new Long(((IMUData)dataPackets.get(0)).getTs()));
    }

    @Test
    public void shouldHandleBothCmdResponseAndTelemetryData() {
        SerialReceiverParser parser = new SerialReceiverParser();
        List<RocketDataPacket> dataPackets = parser.receiveAndScanForDataPackets(cmdResponse2 + imu1);
        assertEquals(2, dataPackets.size());
        assertTrue(dataPackets.get(0) instanceof RocketCommandReply);
        assertEquals("STRT", ((RocketCommandReply)dataPackets.get(0)).getReplyFor());
        assertTrue(dataPackets.get(1) instanceof IMUData);
        assertEquals(new Long(16679L), new Long(((IMUData)dataPackets.get(1)).getTs()));
    }

    @Test
    public void shouldHandleAndRemoveGarbleBeforePayload() {
        SerialReceiverParser parser = new SerialReceiverParser();
        List<RocketDataPacket> dataPackets = parser.receiveAndScanForDataPackets("garble}" + cmdResponse1);
        assertEquals(1, dataPackets.size());
        assertTrue(dataPackets.get(0) instanceof RocketCommandReply);
        assertEquals("HRTB", ((RocketCommandReply)dataPackets.get(0)).getReplyFor());
    }

    @Test
    public void shouldHandleAndRemoveGarbleAfterPayload() {
        SerialReceiverParser parser = new SerialReceiverParser();
        List<RocketDataPacket> dataPackets = parser.receiveAndScanForDataPackets(imu2 + "qwerty");
        assertEquals(1, dataPackets.size());
        assertTrue(dataPackets.get(0) instanceof IMUData);
        assertEquals(new Long(19011L), new Long(((IMUData)dataPackets.get(0)).getTs()));
    }

    @Test
    public void shouldHandlePayloadsSurroundedByGarble() {
        SerialReceiverParser parser = new SerialReceiverParser();
        List<RocketDataPacket> dataPackets = parser.receiveAndScanForDataPackets("qwerty" +cmdResponse2 + "asdfgh}"+ imu1 + "zxcvbn");
        assertEquals(2, dataPackets.size());
        assertTrue(dataPackets.get(0) instanceof RocketCommandReply);
        assertEquals("STRT", ((RocketCommandReply)dataPackets.get(0)).getReplyFor());
        assertTrue(dataPackets.get(1) instanceof IMUData);
        assertEquals(new Long(16679L), new Long(((IMUData)dataPackets.get(1)).getTs()));
    }

    @Test
    public void shouldHandlePartialResponses() {
        SerialReceiverParser parser = new SerialReceiverParser();
        List<RocketDataPacket> dataPackets = parser.receiveAndScanForDataPackets(cmdResponsePart1);
        assertEquals(0, dataPackets.size());
        dataPackets = parser.receiveAndScanForDataPackets(cmdResponsePart2);
        assertEquals(1, dataPackets.size());
        assertEquals("HRTB", ((RocketCommandReply)dataPackets.get(0)).getReplyFor());
    }

    @Test
    public void shouldHandlePartialResponsesWithGarble() {
        SerialReceiverParser parser = new SerialReceiverParser();
        List<RocketDataPacket> dataPackets = parser.receiveAndScanForDataPackets("qwerty" + cmdResponsePart1);
        assertEquals(0, dataPackets.size());
        dataPackets = parser.receiveAndScanForDataPackets(cmdResponsePart2 + "asdfgh");
        assertEquals(1, dataPackets.size());
        assertEquals("HRTB", ((RocketCommandReply)dataPackets.get(0)).getReplyFor());
    }

    @Test
    public void shouldHandleAltimeterData() {
        SerialReceiverParser parser = new SerialReceiverParser();
        List<RocketDataPacket> dataPackets = parser.receiveAndScanForDataPackets(alt1);
        assertEquals(1, dataPackets.size());
    }

    @Test
    public void shouldHandleNewLineBetweenPackets() {
        SerialReceiverParser parser = new SerialReceiverParser();
        List<RocketDataPacket> dataPackets = parser.receiveAndScanForDataPackets(alt1);
        assertEquals(1, dataPackets.size());
        dataPackets = parser.receiveAndScanForDataPackets("\n");
        assertEquals(0, dataPackets.size());
        dataPackets = parser.receiveAndScanForDataPackets("\r");
        assertEquals(0, dataPackets.size());
        dataPackets = parser.receiveAndScanForDataPackets("");
        assertEquals(0, dataPackets.size());
        dataPackets = parser.receiveAndScanForDataPackets(imuPart1);
        assertEquals(0, dataPackets.size());
        dataPackets = parser.receiveAndScanForDataPackets(imuPart2);
        assertEquals(1, dataPackets.size());
    }

    @Test
    public void shouldHandleOnlyOpeningBracketFirst() {
        String altStart = "{";
        String altRest = "\"ts\":56268,\"alt\":172.06,\"temp\":19.62}";
        SerialReceiverParser parser = new SerialReceiverParser();
        List<RocketDataPacket> dataPackets = parser.receiveAndScanForDataPackets(altStart);
        assertEquals(0, dataPackets.size());
        dataPackets = parser.receiveAndScanForDataPackets(altRest);
        assertEquals(1, dataPackets.size());
    }

    @Test
    public void shouldHandleRemovalOfIncompleteData() {
        String data = "{\"ts\":1096373,\"alt\"{\"ts\":1098423,\"gx\":97,\"gy\":-45,\"gz\":-238,\"ax\":548,\"ay\":-146,\"az\":16255,\"mx\":3290,\"my\":-787,\"mz\":-4766}";
        SerialReceiverParser parser = new SerialReceiverParser();
        List<RocketDataPacket> dataPackets = parser.receiveAndScanForDataPackets(data);
        assertEquals(1, dataPackets.size());
    }
}


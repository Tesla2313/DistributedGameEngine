package serde;

import com.google.gson.Gson;
import message.completed.UserScore;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class PlayScoreSerde implements Serde<UserScore>, Serializer<UserScore>, Deserializer<UserScore> {
    private final Gson gson = new Gson();

    @Override
    public Serializer<UserScore> serializer() {
        return (topic, data) -> gson.toJson(data).getBytes();
    }

    @Override
    public Deserializer<UserScore> deserializer() {
        return ((topic, data) -> gson.fromJson(new String(data), UserScore.class));
    }

    @Override
    public byte[] serialize(String topic, UserScore data) {
        return gson.toJson(data).getBytes();
    }

    @Override
    public UserScore deserialize(String topic, byte[] data) {
        return gson.fromJson(new String(data), UserScore.class);
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public void close() {

    }
}

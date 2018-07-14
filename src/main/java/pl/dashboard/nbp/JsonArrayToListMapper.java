package pl.dashboard.nbp;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.SneakyThrows;
import org.json.JSONArray;

import java.io.IOException;
import java.util.List;

enum JsonArrayToListMapper {
    INSTANCE;
    private final ObjectMapper objectMapper = new ObjectMapper();

    JsonArrayToListMapper() {
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @SneakyThrows(IOException.class)
    public List<ExchangeRate> map(JSONArray jsonArray) {
        CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class, ExchangeRate.class);
        return objectMapper.readValue(jsonArray.toString(), typeReference);
    }
}

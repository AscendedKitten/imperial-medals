package at.imperial;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class TestSkinFetch {

    private static final Logger logger = Logger.getLogger("SkinFetch");

    @Test
    public void ensureCanFetchUUIDByName() throws IOException {
        String lookupName = "cathKitten";
        Map map = new ObjectMapper().readValue(new URL(String.format("https://api.mojang.com/users/profiles/minecraft/%s", lookupName)), Map.class);
        assertThat(map.values().toArray()[1].toString()).isEqualTo("816835608f0749d3a68235f2e6bc6c0d");
    }
}

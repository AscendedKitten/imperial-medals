package at.imperial.utility;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class MinecraftHeadUtility {

    public static String headFromUUID(String uuid) throws IOException {
        URL url = new URL(String.format("https://crafatar.com/avatars/%s", uuid));
        if (doesURLExist(url))
            return url.toString();
        else {
            Logger.getLogger("HeadValidation").warning(String.format("INVALID UUID: %s", uuid));
            return "https://binge.lgbt/idk.png";
        }
    }

    public static boolean doesURLExist(URL url) throws IOException {
        HttpURLConnection.setFollowRedirects(false);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("HEAD");

        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
        int responseCode = httpURLConnection.getResponseCode();

        return responseCode == HttpURLConnection.HTTP_OK;
    }

}

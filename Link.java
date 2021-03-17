import java.net.MalformedURLException;
import java.net.URL;

public class Link {
    public URL url;
    public int depth;

    public Link(URL url, int depth) {
        this.url = url;
        this.depth = depth;
    }

    public Link(String url, int depth) throws MalformedURLException {
        this.url = new URL(url);
        this.depth = depth;
    }


}

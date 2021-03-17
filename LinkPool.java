import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.function.Function;

public class LinkPool {

    private List<Link> linksToScan;
    private List<Link> links;
    private int inProgress;

    public LinkPool(List<Link> linksToScan, List<Link> links) {
        this.linksToScan = linksToScan;
        this.links = links;
        inProgress = 0;
    }

    // Взвращает true, если еще могут быть новые ссылки
    public boolean takeTask(Function<Link, List<String>> executor) {
        Link current;
        synchronized (this) {
            if (linksToScan.size() > 0) {
                current = linksToScan.remove(0);
                inProgress += 1;
            } else if (inProgress > 0) {
                return true;
            } else {
                return false;
            }
        }
        List<String> newLinks = executor.apply(current);
        synchronized (this) {
            for (String urlStr : newLinks) {
                URL url = null;
                try {
                    url = new URL(urlStr);
                } catch (MalformedURLException ignored) {}
                if (url != null) {
                    Link l = new Link(url, current.depth + 1);
                    linksToScan.add(l);
                    links.add(l);
                    //System.out.println(">>> " + url + "\t" + (current.depth + 1));
                }
            }
            inProgress -= 1;
        }
        return true;
    }
}

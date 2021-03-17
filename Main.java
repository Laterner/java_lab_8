import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Main {

    private static void printUsage() {
        System.out.println("lab7 <http(s)://URL> <DEPTH>");
    }

    public static void main(String[] args) throws IOException {
        URL url;
        int depth;

        if (args.length != 2) {
            printUsage();
            return;
        }
        try {
            url = new URL(args[0]);
            if (!(url.getProtocol().equals("http") || url.getProtocol().equals("https"))) {
                System.out.println("Unsupported Protocol");
                return;
            }
        } catch (MalformedURLException ex) {
            System.out.println("Illegal URL.");
            printUsage();
            return;
        }

        try {
            depth = Integer.parseInt(args[1]);
        } catch (NumberFormatException ex) {
            System.out.println("Illegal DEPTH.");
            printUsage();
            return;
        }

        List<Link> list = Crawler.scanLink(url, depth, 4);

        for (Link l : list) {
            System.out.println(l.url + "\t" + l.depth);
        }

    }











}

package net.nightawk.sphex;

import java.net.URI;

/**
 * MysqlURLParser to parse mysql jdbc url
 * <p/>
 * jdbc:mysql://localhost:3306/test?useUnicode=true
 * <or/>
 * jdbc:mysql://localhost:3306/test
 */
public class MysqlURLParser extends AbstractURLParser {

    @Override
    public URLCapsule parse(String url) {
        URLCapsule urlCapsule = URL_CAPSULES.get(url);
        if (urlCapsule == null) {
            URI uri = URI.create(url.substring(5)); // strip "jdbc:"
            String host = uri.getHost();
            int port = uri.getPort();
            String dataBase = uri.getPath().substring(1); // path "/test"
            urlCapsule = new URLCapsule(host, port, dataBase);
            URL_CAPSULES.putIfAbsent(url, urlCapsule);
        }
        return urlCapsule;
    }
}
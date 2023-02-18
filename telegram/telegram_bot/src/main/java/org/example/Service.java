package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Service {
    Document doc;
    Node postsNode;
    public Service() throws IOException{
        init();
    }

    private void init() throws IOException{
            doc = Jsoup.connect("https://www.reddit.com/search/?q=memes").get();
            Elements contentBox = doc.select("[data-testid=posts-list]");
            postsNode = contentBox.get(0).childNode(0);
    }

    public Node getPostsNode(){
        return postsNode;
    }
}

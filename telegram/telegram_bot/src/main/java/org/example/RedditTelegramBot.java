package org.example;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RedditTelegramBot extends TelegramLongPollingBot {
    private Long chatId;
    final private int contentIndex = 1;
    private int position = contentIndex;
    private int maxValue;

    private Service service;
    public RedditTelegramBot(String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        chatId = update.getMessage().getChatId();
        if (update.hasMessage() && update.getMessage().hasText()) {
            Document doc = null;
            try {
                service = new Service();
                maxValue = service.getPostsNode().childNodeSize();
                System.out.println(position);
                if(position>=maxValue-2){
                    SendMessage message = new SendMessage();
                    message.setChatId(chatId);
                    message.setText("Please don`t delete me... I bring you new memes at next time(( ");

                    try {
                        execute(message); // Call method to send the message
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                }
                int displayedAmount = position + 5;

                for (;position < displayedAmount && displayedAmount<maxValue; position++) {
                    RedditPost post;
                    try {
                        post = getPost(service.getPostsNode(), position);
                    } catch (IOException e){
                        continue;
                    }
                    InputFile image = new InputFile(post.getImageStream(), "post");
                    String caption = post.getDescription();
                    SendPhoto msg = SendPhoto.builder()
                            .chatId(chatId)
                            .caption(caption)
                            .photo(image)
                            .build();
                    execute(msg);
                }
            } catch (TelegramApiException | IOException e) {
                position++;
                e.printStackTrace();
            }
        }

    }


    private RedditPost getPost(Node postsNode , int index) throws IOException {
        Element postHref = ((Element) postsNode.childNodes().get(index)).select("a").last();
        String href = postHref.attr("href");
        InputStream imageStream;
        imageStream = getPictureInputStream(href);

        String description = postHref.select("div").attr("aria-label");

        return new RedditPost(description,imageStream);
    }

    private InputStream getPictureInputStream(String href) throws  IOException{
        // Create a neat value object to hold the URL
        URL url = null;

        url = new URL(href );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "text/html");
        return connection.getInputStream();

    }
    @Override
    public String getBotUsername() {
        return "checkUabot";
    }
}

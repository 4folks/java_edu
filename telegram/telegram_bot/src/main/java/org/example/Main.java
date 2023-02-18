package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new RedditTelegramBot("1988306734:AAF62W7WLbIjJtI_GihLdwxJ3Qz3NjcV2OA"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
package com.spring.bot.SpringDemoBot.service;

import com.spring.bot.SpringDemoBot.config.BotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    @Autowired
    public TelegramBot(BotConfig botConfig) {
        this.botConfig = botConfig;
    }


    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage() && update.getMessage().hasText()){
            String message = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String nameOfUser = update.getMessage().getChat().getFirstName();

            switch (message){
                case ("/start"):

                        startCommandReceived(chatId, nameOfUser);
                        break;
                default:
                        sentMessage(chatId, "Sorry, command was not recognize");
            }
        }
    }

    private void startCommandReceived(long chatId, String nameOfUser) {
        String answer = "Hello, " + nameOfUser + ", nice to meet you!";

        sentMessage(chatId,answer);
    }

    private void sentMessage(long chatId, String textToSend){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);

        try{
            execute(sendMessage);
        }catch (TelegramApiException e){

        }
    }
}

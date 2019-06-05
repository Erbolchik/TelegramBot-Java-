import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            sendMessage(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void onUpdateReceived(Update update) {
        List<String> citata = new ArrayList<>();
        citata.add("Моя жизнь, мои правила");
        citata.add("Ошибаюсь, обжигаюсь, иду дальше — улыбаюсь. Хочу плакать, не сдаюсь! Всё в порядке — я прорвусь!");
        citata.add("Думал,дышать без нее не могу,оказалось ,показалось!!! ");
        citata.add("И пусть судьба не справедлива, но жизнь — игра, играй красиво.");
        citata.add("Будь жесток к себе, если не хочешь, что бы другие к тебе были жестоки.");
        citata.add("Слишком люблю твои глаза,чтобы смотреть в чужие");
        citata.add("Люби меня как роза воду,а я тебя как вор свободу");
        citata.add("Давай до старости,и в горе и в радости");
        citata.add("С другими нихочу,с тобой не получается");
        citata.add("А что люди думают о тебе,это их проблемы");
        citata.add("Я ее узнал и не хочу,чтобы другие узнали");
        citata.add("Не на вечер,а на век человеку нужен человек");
        citata.add("Главное,чтобы Мама улыбалась,остальное всё фигня");
        citata.add("Каждый помнит,где и в ком он тонул");
        citata.add("Не идеален,но лучше многих");
        citata.add("В один день, я расскажу тебе,как сильно я тебя люблю,Просто не сегодня ");
        citata.add("Пусть дома будет лучше бардак с детьми,чем порядок без детей");
        citata.add("Не с красотой тебе жить,а с человеком.");


        List<String> anime = new ArrayList<>();
        anime.add("Наруто");
        anime.add("Тетрадь смерти");
        anime.add("Боруто");
        anime.add("Любимый во Франксе");
        anime.add("Форма голоса");
        anime.add("Этот глупый свин не понимает мечту девочки-зайки");
        anime.add("В подземелье я пойду, там красавицу найду");
        anime.add("А ты думал, что твоя жена в онлайн-игре на самом деле не девушка?");
        anime.add("Ангел кровопролития");
        anime.add("Твоё Имя");
        anime.add("Госпожа Кагуя: в любви как на войне");
        anime.add("Токийский Гуль");
        anime.add("Виолетта Эвергарден");
        anime.add("Убийца Акамэ!");
        anime.add("Ходячий замок");
        anime.add("Школьные дни");
        anime.add("Аниме корме");


        String random = citata.get(new Random().nextInt(citata.size()));
        String random1 = anime.get(new Random().nextInt(anime.size()));

        Model model = new Model();

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "Рандомная цитата":
                    sendMsg(message, random);
                    break;
                case "Рандомное аниме":
                    sendMsg(message, random1);
                    break;
                case "Прогноз погоды":
                    sendMsg(message,"Введите название города");
                    break;
                default:
                    try {
                        sendMsg(message, Weather.getWeather(message.getText(), model));
                    } catch (IOException e) {
                        sendMsg(message, "Такой город не найден");
                    }

            }
        }

    }


    public void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);


        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardFirstRows = new KeyboardRow();

        keyboardFirstRows.add(new KeyboardButton("Рандомная цитата"));
        keyboardFirstRows.add(new KeyboardButton("Рандомное аниме"));
        keyboardFirstRows.add(new KeyboardButton("Прогноз погоды"));

        keyboardRows.add(keyboardFirstRows);
        replyKeyboardMarkup.setKeyboard(keyboardRows);

    }

    public String getBotUsername() {
        return "Yerbolchik";
    }

    public String getBotToken() {
        return "705305030:AAFdFBmhS_fSHHR7QcjLam5RSnuyw-I6d8s";
    }
}

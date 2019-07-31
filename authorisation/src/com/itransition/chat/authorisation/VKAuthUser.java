package com.itransition.chat.authorisation;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class VKAuthUser extends Application {
    public static final String REDIRECT_URL = "https://oauth.vk.com/blank.html";
    public static final String VK_AUTH_URL = "https://oauth.vk.com/authorize?client_id=7042774&display=page" +
            "&redirect_uri=" + REDIRECT_URL + "&response_type=token&v=5.101";
    public static String tokenUrl;

    public static void main(String[] args) {
        System.out.println(VKAuthUser.getTokenUrl());
        final WebView object = new WebView();
        final WebEngine engine = object.getEngine();
        engine.load("https://api.vk.com/method/account.getProfileInfo?access_token=ab28feb0223aab49ccb1b2becb970b8da7788d06b6efea7160aea3a1bba8ac8f717ba6018d41dc5baef6d");
        System.out.println(object);
    }

    public static String getTokenUrl(){
        launch(VKAuthUser.class);
        return tokenUrl;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final WebView view = new WebView();
        final WebEngine engine = view.getEngine();
        engine.load(VK_AUTH_URL);


        primaryStage.setScene(new Scene(view));
        primaryStage.show();

        engine.locationProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.startsWith(REDIRECT_URL)){
                    tokenUrl=newValue;
                    primaryStage.close();
                }
            }

        });

    }

}

package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

import java.util.Timer;
import java.util.TimerTask;


public class Controller {

    //@FXML
    //private Event update = new Event(null, 0, null);
    Timer timer;
    TimerTask timerTask;

    @FXML
    public Button button;
    @FXML
    private TextField login;
    @FXML
    private Pane panel1, panel2;
    @FXML
    public Label name0, name1, name2, name3, name4, name5;
    public Label[] names = new Label[6];
    @FXML
    public Label bankroll0, bankroll1, bankroll2, bankroll3, bankroll4, bankroll5;
    public Label[] bankrolls = new Label[6];
    @FXML
    private Label bet0, bet1, bet2, bet3, bet4, bet5;
    public Label[] bets = new Label[6];
    @FXML
    private ImageView card1_0, card2_0, card1_1, card2_1, card1_2, card2_2, card1_3, card2_3, card1_4, card2_4, card1_5, card2_5;
    public ImageView[][] cards = new ImageView[6][2];
    @FXML
    public Label bank;
    @FXML
    private ImageView flop1, flop2, flop3, turn, river;
    public static ImageView[] flop = new ImageView[3];
    @FXML
    private ImageView status0, status1, status2, status3, status4, status5;
    private ImageView[] status = new ImageView[6];
    @FXML
    private ImageView dib0, dib1, dib2, dib3, dib4, dib5;
    public ImageView[] dib = new ImageView[6];
    @FXML
    private ImageView field0, field1, field2, field3, field4, field5;
    private ImageView[] fields = new ImageView[6];


    @FXML
    private Button checkButton, foldButton, raiseButton;
    @FXML
    private Slider slider;
    @FXML
    private Label sliderValue;
    private int sliderValueInt = 0;
    private static int lastDealerPosition, lastHodorPosition = 0;
    //private Command command;


    @FXML
    public void initialize() {
        names[0] = name0;
        names[1] = name1;
        names[2] = name2;
        names[3] = name3;
        names[4] = name4;
        names[5] = name5;
        bankrolls[0] = bankroll0;
        bankrolls[1] = bankroll1;
        bankrolls[2] = bankroll2;
        bankrolls[3] = bankroll3;
        bankrolls[4] = bankroll4;
        bankrolls[5] = bankroll5;
        bets[0] = bet0;
        bets[1] = bet1;
        bets[2] = bet2;
        bets[3] = bet3;
        bets[4] = bet4;
        bets[5] = bet5;
        cards[0][0] = card1_0;
        cards[0][1] = card2_0;
        cards[1][0] = card1_1;
        cards[1][1] = card2_1;
        cards[2][0] = card1_2;
        cards[2][1] = card2_2;
        cards[3][0] = card1_3;
        cards[3][1] = card2_3;
        cards[4][0] = card1_4;
        cards[4][1] = card2_4;
        cards[5][0] = card1_5;
        cards[5][1] = card2_5;
        flop[0] = flop1;
        flop[1] = flop2;
        flop[2] = flop3;
        status[0] = status0;
        status[1] = status1;
        status[2] = status2;
        status[3] = status3;
        status[4] = status4;
        status[5] = status5;
        dib[0] = dib0;
        dib[1] = dib1;
        dib[2] = dib2;
        dib[3] = dib3;
        dib[4] = dib4;
        dib[5] = dib5;
        fields[0] = field0;
        fields[1] = field1;
        fields[2] = field2;
        fields[3] = field3;
        fields[4] = field4;
        fields[5] = field5;
        /*for (int i = 0; i < 3; i++) {
            flop[i] = new ImageView();
        }*/
        //command = new Command();
        //Thread com = new Thread(command);
        //command.AddController(this);
        //com.start();
        panel2.setVisible(false);
        panel1.setLayoutX(0);
        panel1.setLayoutY(0);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Main.client.SendUTF(login.getText());
                panel1.setVisible(false);
                panel2.setLayoutX(0);
                panel2.setLayoutY(0);
                panel2.setVisible(true);
                //action(Main.client.ReadUTF());
                //handleAction();
            }
        });
        checkButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.client.SendInt(0);
                setButtonsDisable();
                //action(Main.client.ReadUTF());
                //handleAction();
                //System.out.println("end of handing check");
            }
        });
        foldButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.client.SendInt(-1);
                setButtonsDisable();
                //action(Main.client.ReadUTF());
                //handleAction();
                //System.out.println("end of handing fold");
            }
        });
        raiseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.client.SendInt(sliderValueInt);
                setButtonsDisable();
                //handleAction();
                //System.out.println("end of handing rize");
            }
        });
        //slider = new Slider();
        //sliderValue = new Label();
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                System.out.println("Slider Value Changed (newValue: " + newValue.intValue() + ")\n");
                sliderValueInt = newValue.intValue();
                sliderValue.setText(Integer.toString(newValue.intValue()));
            }
        });

        //slider.minProperty().setValue(1);
        //slider.maxProperty().setValue(1000);

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handleAction();
            }
        };
        timer.schedule(timerTask, 100, 150);
    }

    public void handleAction() {
        /*boolean exit;
        do {
            exit = action(Main.client.ReadUTF());
        } while (exit);*/

        final String cmd = Main.client.ReadUTF_UnProtected();
        //cmd = "";
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                action(cmd);
            }
        });
        //timer.schedule(timerTask, 100);
    }

    public boolean action(String command) {
        if (command.equals("game start info"))
            gameStartInfo();
        else if (command.equals("you hand"))
            youHand();
        else if (command.equals("change"))
            Change();
        else if (command.equals("flop"))
            flop();
        else if (command.equals("turn"))
            Turn();
        else if (command.equals("river"))
            River();
        else if (command.equals("bank"))
            Bank();
        else if (command.equals("small blind"))
            smallBlind();
        else if (command.equals("big blind"))
            bigBlind();
        else if (command.equals("dealer"))
            dealer();
        else if (command.equals("hodor"))
            hodor();
        else if (command.equals("you turn")) {
            youTurn();
            return false;
        }
        return true;
    }

    public static void ChangeForm() {
        System.out.println("aaa");
    }

    public void gameStartInfo() {
        for (int i = 0; i < 6; i++) {
            int position = Main.client.readInt();
            String login = Main.client.ReadUTF();
            int bankroll = Main.client.readInt();
            names[position].setText(login);
            bankrolls[position].setText(Integer.toString(bankroll));
        }
    }

    private void youHand() {
        String card_1 = Main.client.ReadUTF();
        String card_2 = Main.client.ReadUTF();
    }

    private void smallBlind() {
    }

    private void bigBlind() {
    }

    private void Change() {
        int position = Main.client.readInt();
        String field = Main.client.ReadUTF();
        if (field.equals("bet")) {
            int bet = Main.client.readInt();
            bets[position].setText(String.valueOf(bet));
            if (bet == 0)
                dib[position].setImage(new Image("img/cards/null.png"));
            if (bet <= 50 && bet > 0)
                dib[position].setImage(new Image("img/blue.png"));
            if (bet > 50 && bet <= 100)
                dib[position].setImage(new Image("img/red.png"));
            if (bet > 100 && bet <= 250)
                dib[position].setImage(new Image("img/green.png"));
            if (bet > 250 && bet <= 500)
                dib[position].setImage(new Image("img/black.png"));
            if (bet > 500 && bet <= 1000)
                dib[position].setImage(new Image("img/white.png"));
        }
        if (field.equals("bankroll"))
            bankrolls[position].setText(String.valueOf(Main.client.readInt()));
        if (field.equals("bank"))
            bank.setText(String.valueOf(Main.client.readInt()));
        if (field.equals("hand")) {
            cards[position][0].setImage(new javafx.scene.image.Image(getCard()));
            cards[position][1].setImage(new javafx.scene.image.Image(getCard()));
        }
    }

    private void flop() {
        for (int i = 0; i < 3; i++) {
            String s = getCard();
            flop[i].setImage(new javafx.scene.image.Image(s));
        }
    }

    private String getCard() {
        String card_1 = Main.client.ReadUTF();
        // if (card_1.equals("null"))
        //return "img/cards/back.png";
        return "img/cards/" + card_1 + ".png";
    }

    private void Bank() {
        String bankStr;
        bankStr = new String(String.valueOf(Main.client.readInt()));
        bank.setText(bankStr);
    }

    private void youTurn() {
        int min = Main.client.readInt();
        int max = Main.client.readInt();
        slider.minProperty().setValue(min);
        slider.maxProperty().setValue(max);
        setButtonsEnable();
    }

    private void Turn() {
        turn.setImage(new javafx.scene.image.Image(getCard()));
    }

    private void River() {
        river.setImage(new javafx.scene.image.Image(getCard()));
    }

    private void dealer() {
        int position = Main.client.readInt();
        status[lastDealerPosition].setImage(new javafx.scene.image.Image("img/cards/null.png"));
        status[position].setImage(new javafx.scene.image.Image("img/button.png"));
        lastDealerPosition = position;
    }

    private void hodor() {
        int position = Main.client.readInt();
        fields[lastHodorPosition].setImage(new Image("img/field.png"));
        fields[position].setImage(new Image("img/field_blue.png"));
        lastHodorPosition = position;
    }


    public void setButtonsEnable() {
        checkButton.setDisable(false);
        raiseButton.setDisable(false);
        foldButton.setDisable(false);
    }

    public void setButtonsDisable() {
        checkButton.setDisable(true);
        raiseButton.setDisable(true);
        foldButton.setDisable(true);

    }

}
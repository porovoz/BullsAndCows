package bullsandcows;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    public Spinner<Integer> spinner1;
    public Spinner<Integer> spinner2;
    public Spinner<Integer> spinner3;
    public Spinner<Integer> spinner4;
    public TableView<Turn> history;
    public Button goButton;
    private List<Integer> myNumbers;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        goButton.disableProperty().bind(
                Bindings.createBooleanBinding(() -> {
                            var set = new HashSet<Integer>();
                            set.add(spinner1.getValue());
                            set.add(spinner2.getValue());
                            set.add(spinner3.getValue());
                            set.add(spinner4.getValue());

                            return set.size() < 4;
                        },
                        spinner1.valueProperty(),
                        spinner2.valueProperty(),
                        spinner3.valueProperty(),
                        spinner4.valueProperty()
                )
        );

        resetGame();


    }

    private void resetGame() {
        history.getItems().clear();
        spinner1.getValueFactory().setValue(5);
        spinner2.getValueFactory().setValue(5);
        spinner3.getValueFactory().setValue(5);
        spinner4.getValueFactory().setValue(5);

        var rand = new Random();

        var set = new LinkedHashSet<Integer>();
        while (set.size() < 4) {
            var n = rand.nextInt(10);
            set.add(n);
        }

        myNumbers = List.copyOf(set);

        System.out.println(myNumbers);
    }


    public void doTurn() {
        var n1 = spinner1.getValue();
        var n2 = spinner2.getValue();
        var n3 = spinner3.getValue();
        var n4 = spinner4.getValue();
        var guess = "" + n1 + n2 + n3 + n4;

        var userNumbers = List.of(n1, n2, n3, n4);

        int bulls = 0;
        int cows = 0;


        for (int r = 0; r < 4; r++) {
            int myN = myNumbers.get(r);
            for (int c = 0; c < 4; c++) {
                int usrN = userNumbers.get(c);
                if (myN == usrN) {
                    if (r == c) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }

        var turn = new Turn();
        turn.setGuess(guess);
        turn.setNr(history.getItems().size() + 1);
        turn.setBulls(bulls);
        turn.setCows(cows);

        history.getItems().add(0,turn);

        if (bulls == 4) {
            var winAlert = new Alert(Alert.AlertType.INFORMATION);
            winAlert.setContentText("You won this match! Excellent!");
            winAlert.setTitle("Winner");
            winAlert.setHeaderText("Congratulations");
            winAlert.showAndWait();
            resetGame();
        }
    }


}

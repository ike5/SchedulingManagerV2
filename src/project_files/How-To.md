## Creating a combo box

```java
public class Test {
    public ComboBox<BasketBallPlayer> comboBox;
    ObservableList<BasketBallPlayer> players = ...;
    comboBox.setItems(players);

    // Like a TableView, get items with an index
    BasketBallPlayer b = comboBox.getItems().get(i);
}
```

## Get selection of a ComboBox

```java
public class Test {
    BasketBallPlayer b = comboBox.getValue();

    //or
    BasketBallPlayer b = comboBox.getSelectionModel().getSelectedItem();

    // setting items
    comboBox.setValue(b);   // set with an object
    comboBox.getSelectionModel().

    select(b); // set with model object
    comboBox.getSelectionModel().

    select(i); // set by index
}
```

## Search by player name

```java
public class Test {
    private ObservableList<BasketBallPlayer> searchPlayerName(String partialName) {
        ObservableList<BasketBallPlayer> namedPlayers = FXCollections.observableArrayList();

        ObservableList<BasketBallPlayer> allPlayers = BasketBallPlayer.getAllPlayers();

        for (BasketBallPlayer bp : allPlayers) {
            if (bp.getName().contains(partialName)) {
                namedPlayers.add(bp);
            }
        }
        return namedPlayers;
    }
}

```

## Set up of columns with data

```java
public class Test {
    allJersyCol.setCellValueFactory(new PropertyValueFactory<>("jersy"));
    allNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

    ObservableList<BasketBallPlayer> players = BasketBallPlayer.getAllPlayers();
    allTable.setItems(players);
    resultsLBL.setText(Integer.toString(players.size())+" players returned");
}
```

## Number of things that can be displayed at once with a ComboBox

combo1.setVisibleRowCount(5);

## Set prompt for ComboBox

combo1.setPromptText("You must choose a player...");

## Clear a ComboBox

```java
import java.awt.event.ActionEvent;

public class Test {
    public void onClear(ActionEvent actionEvent) {
        combo1.getSelectionModel().clearSelection();
        combo2.getSelectionModel().clearSelection();
        combo3.getSelectionModel().clearSelection();
    }
}
```

## LocalTime mechanisms java.time package

```java
public class Time {
    LocalTime start = LocalTime.of(6, 0); // 6:00
    LocalTiem end = LocalTime.NOON;

        while(start.isBefore(end.plusSeconds(1)))

    {
        combo3.getItems().add(start);
        start = start.plusMinutes(10);
    }

        combo3.getSelectionModel().

    select(LocalTime.of(8,0));
}
```

## Pull action

```java
import java.awt.event.ActionEvent;

public class Test {
    public void onPull(ActionEvent actionEvent) {
        StringBuilder sb = new StringBuilder("");

        BasketBallPlayer bp = combo1.getSelectionModel().getSelectedItem();

        if (bp == null) {
            sb.append("CB1: null");
        } else {
            sb.append("CB1: " + bp.getName());
        }
        sb.append(" | ");

        if (combo2.getValue() == null) {
            sb.append("CB2: null");
        } else {
            sb.append("CB2: " + combo2.getValue().getName());
        }
        sb.append(" | ");

        if (combo3.getValue() == null) {
            sb.append("CB3: null");
        } else {
            sb.append("CB3: " + combo3.getValue());
        }
        resultsLBL.setText(sb.toString());
    }
}
```

## Set Cell Factory/Button Cell Callbacks

```java
import javax.security.auth.callback.Callback;
import javax.swing.text.html.ListView;


public class Test {

    // Factory for the List Cells
    Callback<ListView<BasketBallPlayer>, ListCell<BasketBallPlayer>> factory = lv -> new ListCell<BasketBallPlayer>() {
        @Override
        protected void updateItem(BasketBallPlayer item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? "" : ("Use : " + item.getName()));
        }
    };

    // Used a different Factory for a button cell
    Callback<ListView<BasketBallPlayer>, ListCell<BasketBallPlayer>> factoryUsed = lv -> new ListCell<BasketBallPlayer>() {
        @Override
        protected void updateItem(BasketBallPlayer item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? "" : ("Your pick : " + item.getName()));
        }
    };


    // Note separate Factories used
    combo1.setCellFactory(factory);
    combo1.setButtonCell(factoryUsed.call(null));
}
```

## using combo boxes selection on table

```java
import java.awt.event.ActionEvent;

public class Test {
    public void onCombo1(ActionEvent actionEvent) {
        onPull(null); // resets data after make a choice
    }
}
```

## Lambda action event for selection on a table

```java
public class Test {
    public static void main(String[] args) {
        allTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                for (int i = 0; i < combo2.getItems().size(); i++) {
                    BasketBallPlayer abp = combo2.getItems().get(i);
                    if (abp.getJersey() == newSelection.getJersy() && abp.getName().equals(newSelection.getName())) {
                        combo2.setValue(abp);=
//                        combo2.getSelectionModel().select(i); 
//                        combo2.getSelectionModel().select(abp);
                        break;
                    }
                }
            }
        });
    }
}
```

## Correct Macos issues

// Use the below code if having font issues
// root.setStyle("-fx-font-family: 'Times New Roman';");

// Use the below code if having font issues with alerts
// Alert alert = new Alert(Alert.AlertType.WARNING);
// alert.getDialogPane().getScene().getRoot().setStyle("-fx-font-family: 'Times New Roman';");
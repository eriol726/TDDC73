/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewer;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.security.ssl.Debug;

/**
 *
 * @author Erik
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private ImageView imageView;
    
    ArrayList<Image> images = new ArrayList<Image>();
    private int currentIndex = 0;
    private float currentScale = 1;
    private Stage dialogStage = new Stage();
    private boolean shown = false;
    
    @FXML
    private void scaleUpImageAction(ActionEvent event) {
        if(imageView.getScaleX() <= 1.5){
            imageView.setScaleX(imageView.getScaleX()*1.1);
            imageView.setScaleY(imageView.getScaleY()*1.1);
            shown = false;
        }
        else if(!shown){
            shown = true;
            dialogStage.show();
        }
    }
    
    @FXML
    private void scaleDownImageAction(ActionEvent event) {
        System.out.println("x-size: " + imageView.getScaleX());
        
        if(imageView.getScaleX() >= 0.5){
            imageView.setScaleX(imageView.getScaleX()*0.90);
            imageView.setScaleY(imageView.getScaleY()*0.90);
            shown = false;
        }
        else if(!shown){
            shown = true;
            dialogStage.show();
        }
        
    }
    
    
    @FXML
    private void rotLImageAction(ActionEvent event) {
        imageView.setRotate(imageView.getRotate() - 90);
    }
    
    @FXML
    private void rotRImageAction(ActionEvent event) {
        imageView.setRotate(imageView.getRotate() + 90);
    }

    @FXML
    private void nextImageAction(ActionEvent event) {
        imageView.setRotate(imageView.getRotate()*0);
        restImageScale();
                
        currentIndex++;
        if( (currentIndex % 4) == 0 ){
            currentIndex = 0;
        }

        imageView.setImage(images.get(currentIndex));
        label.setText("Image: " + (currentIndex + 1));
    }
    
    @FXML
    private void prevImageAction(ActionEvent event) {
        imageView.setRotate(imageView.getRotate()*0);
        restImageScale();
        
        if(currentIndex>0){
            currentIndex--;
        }
        else if( currentIndex  == 0 ){
            currentIndex = 3;
        }

        imageView.setImage(images.get(currentIndex));
        label.setText("Image: " + (currentIndex + 1));
    }
    
    void restImageScale(){
        imageView.setScaleX(1.0);
        imageView.setScaleY(1.0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initialize");
        
        label.setText("Image: " + 1 );
        
        Image imageA = new Image("imageviewer/images/flowerA.jpg");
        Image imageB = new Image("imageviewer/images/flowerB.jpg");
        Image imageC = new Image("imageviewer/images/flowerC.jpg");
        Image imageD = new Image("imageviewer/images/flowerD.jpg");
        images.add(imageA);
        images.add(imageB);
        images.add(imageC);
        images.add(imageD);
        
        
        dialogStage.initModality(Modality.WINDOW_MODAL);

        Button okButton = new Button("Ok.");

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialogStage.close();
            }
        });

        VBox vbox = new VBox(new Text("Can't scale more"), okButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(5, 5, 5, 5));
        vbox.setSpacing(20);

        dialogStage.setScene(new Scene(vbox));
        dialogStage.setAlwaysOnTop(true);
    }    
    
}

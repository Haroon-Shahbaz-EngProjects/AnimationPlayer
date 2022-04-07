/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.animation.AnimationTimer;

/**
 *
 * @author Haroon
 */
public class AnimationPlayer extends Application {

    int frames, speed, elements;
    int r = 40, xc, yc, CcolorR = 0, CcolorG = 0, CcolorB = 0, Cborder, CBorderR, CBorderG, CBorderB; // circle
                                                                                                        // variables
    boolean isCircle = false, isRect = false, isLine = false;
    int length = 50, width = 50, Rborder = 5, RBorderR = 5, RBorderG = 5, RBorderB = 5, xr = 90, yr,
            RcolorR = 0, RcolorG = 0, RcolorB = 0;
    int startX, startY, endX, endY, Lborder, LcolorR, LcolorG, LcolorB; // Line variables

    File file = new File("spam");
    String content;
    double opacity = 1;
    int frameNumber = 0;
    int timeCircleHide = -1, timeCircleShow = -1, timeCircleChangeColor = -1, timeCircleJump=-1, CircleNewX, CircleNewY,
            CircleNewR, CircleNewG, CircleNewB;

    int timeRectHide =-1, timeRectShow =-1, timeRectChangeColor =-1, timeRectJump=-1, RectNewX, RectNewY,
            RectNewR, RectNewG, RectNewB;
    int timeLineHide =-1, timeLineShow =-1, timeLineChangeColor =-1, timeLineJump=-1, LineNewStartX, LineNewStartY,
            LineNewEndX, LineNewEndY,
            LineNewR, LineNewG, LineNewB;
    Circle circle;
    Rectangle rect;
    Line line;

    public AnimationPlayer() {
        super();

        // this.speed = speed;
        // this.elements = elements;
        // this.r = r;
        // this.xc =xc;
        // this.xr = xr;
        // this.yr = yr;
        // this.Rborder = Rborder;
        // this.RcolorR = RcolorR;
        // this.RcolorG = RcolorG;
        // this.RcolorB = RcolorB;
        // this.startX = startX;
        // this.startY = startY;
        // this.RBorderB = RBorderB;
        // this.RBorderR = RBorderR;
        // this.RBorderG = RBorderG;

        // this.endX = endX;
        // this.endY = endY;
        // this.isCircle = isCircle;
        // this.isRect = isRect;
        // this.isLine = isLine;
    }

    public void loadAnimationFromFile(String filename) {

        file = new File(filename);

        try {

            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                if (data.contains("frames:")) { // Assigning data from text to varibles with nested cases fora circle or
                                                // rectangle
                    frames = Integer.parseInt(data.split(":")[1].trim());

                }
                if (data.contains("speed:")) {
                    speed = Integer.parseInt(data.split(":")[1].trim().split("f")[0]);

                }

                if (data.length() == 1) {
                    elements = Integer.parseInt(data);
                }

                if (data.contains("Circle")) {
                    isCircle = true;

                    String data2 = "0";
                    while (!data2.isEmpty() && myReader.hasNextLine()) {

                        data2 = myReader.nextLine();

                        if (data2.contains("r:") && !(data2.contains("color")) && !(data2.contains("border"))) {
                            r = Integer.parseInt(data2.split(":")[1].trim());

                        }
                        if (data2.contains("x:")) {
                            this.xc = Integer.parseInt(data2.split(":")[1].trim());

                        }
                        if (data2.contains("y:")) {

                            yc = Integer.parseInt(data2.split(":")[1].trim());

                        }
                        if (data2.contains("color:") && !(data2.contains("border"))
                                && !(data2.contains("Change"))) {
                            CcolorR = Integer.parseInt(data2.split(":")[1].trim().split(",")[0]);
                            CcolorG = Integer.parseInt(data2.split(",")[1].trim());
                            CcolorB = Integer.parseInt(data2.split(",")[2].trim());
                            System.out.println("Getting circle color");
                            System.out.println("red=" + CcolorR);

                        }
                        if (data2.contains("border:")) {

                            Cborder = Integer.parseInt(data2.split(":")[1].trim());

                        }

                        if (data2.contains("borderColor:")) {

                            CBorderR = Integer.parseInt(data2.split(":")[1].trim().split(",")[0]);
                            CBorderG = Integer.parseInt(data2.split(",")[1].trim());
                            CBorderB = Integer.parseInt(data2.split(",")[2].trim());
                        }

                        if (data2.contains("Hide")) { // Effects if statements
                            String start = myReader.nextLine();
                            timeCircleHide = Integer.parseInt(start.split(":")[1].trim());

                        }
                        if (data2.contains("Show")) {
                            String start2 = myReader.nextLine();
                            timeCircleShow = Integer.parseInt(start2.split(":")[1].trim());

                        }
                        if (data2.contains("Jump")) {
                            String start3 = myReader.nextLine();
                            timeCircleJump = Integer.parseInt(start3.split(":")[1].trim());
                            start3 = myReader.nextLine();
                            CircleNewX = Integer.parseInt(start3.split(":")[1].trim());
                            start3 = myReader.nextLine();
                            CircleNewY = Integer.parseInt(start3.split(":")[1].trim());
                        }
                        if (data2.contains("ChangeColor")) {
                            String start4 = myReader.nextLine();
                            timeCircleChangeColor = Integer.parseInt(start4.split(":")[1].trim());
                            start4 = myReader.nextLine();
                            CircleNewR = Integer.parseInt(start4.split(":")[1].trim().split(",")[0]);
                            CircleNewG = Integer.parseInt(start4.split(",")[1].trim());
                            CircleNewB = Integer.parseInt(start4.split(",")[2].trim());
                        }

                    }

                }

                if (data.contains("Rect")) {
                    isRect = true;

                    String data3 = "0";
                    while (!data3.isEmpty() && myReader.hasNextLine()) {

                        data3 = myReader.nextLine();

                        if (data3.contains("length:")) {
                            length = Integer.parseInt(data3.split(":")[1].trim());

                        }
                        if (data3.contains("width:")) {
                            width = Integer.parseInt(data3.split(":")[1].trim());

                        }

                        if (data3.contains("x:")) {
                            xr = Integer.parseInt(data3.split(":")[1].trim());

                        }
                        if (data3.contains("y:")) {
                            yr = Integer.parseInt(data3.split(":")[1].trim());

                        }

                        if (data3.contains("border:")) {

                            Rborder = Integer.parseInt(data3.split(":")[1].trim());

                        }

                        if (data3.contains("borderColor:")) {

                            RBorderR = Integer.parseInt(data3.split(":")[1].trim().split(",")[0]);
                            RBorderG = Integer.parseInt(data3.split(",")[1].trim());
                            RBorderB = Integer.parseInt(data3.split(",")[2].trim());

                        }

                        if (data3.contains("color:") && !(data3.contains("border"))
                                && !(data3.contains("ChangeColor"))) {
                            RcolorR = Integer.parseInt(data3.split(":")[1].trim().split(",")[0]);
                            RcolorG = Integer.parseInt(data3.split(",")[1].trim());
                            RcolorB = Integer.parseInt(data3.split(",")[2].trim());

                        }

                        if (data3.contains("Hide")) { // Effects if statements
                            String start = myReader.nextLine();
                            timeRectHide = Integer.parseInt(start.split(":")[1].trim());
                        }
                        if (data3.contains("Show")) {
                            String start2 = myReader.nextLine();
                            timeRectShow = Integer.parseInt(start2.split(":")[1].trim());

                        }
                        if (data3.contains("Jump")) {
                            String start3 = myReader.nextLine();
                            timeRectJump = Integer.parseInt(start3.split(":")[1].trim());
                            start3 = myReader.nextLine();
                            RectNewX = Integer.parseInt(start3.split(":")[1].trim());
                            start3 = myReader.nextLine();
                            RectNewY = Integer.parseInt(start3.split(":")[1].trim());
                        }
                        if (data3.contains("ChangeColor")) {
                            String start4 = myReader.nextLine();
                            timeRectChangeColor = Integer.parseInt(start4.split(":")[1].trim());
                            System.out.println(timeCircleChangeColor);
                            start4 = myReader.nextLine();
                            RectNewR = Integer.parseInt(start4.split(":")[1].trim().split(",")[0]);
                            RectNewG = Integer.parseInt(start4.split(",")[1].trim());
                            RectNewB = Integer.parseInt(start4.split(",")[2].trim());
                        }

                    }

                }

                if (data.contains("Line")) {
                    isLine = true;
                    String data4 = "0";
                    while (!data4.isEmpty()&& myReader.hasNextLine()) {

                        data4 = myReader.nextLine();

                        if (data.contains("startX:")) {
                            startX = Integer.parseInt(data4.split(":")[1].trim());
                        }

                        if (data.contains("startY:")) {
                            startX = Integer.parseInt(data4.split(":")[1].trim());
                        }
                        if (data.contains("endX:")) {
                            startX = Integer.parseInt(data4.split(":")[1].trim());
                        }
                        if (data.contains("endY:")) {
                            startX = Integer.parseInt(data4.split(":")[1].trim());
                        }

                        if (data4.contains("color:") && !(data4.contains("border")) && !(data4.contains("Change"))) {
                            LcolorR = Integer.parseInt(data4.split(":")[1].trim().split(",")[0]);
                            LcolorG = Integer.parseInt(data4.split(",")[1].trim());
                            LcolorB = Integer.parseInt(data4.split(",")[2].trim());

                        }

                        if (data4.contains("border:")) {
                            Lborder = Integer.parseInt(data4.split(":")[1].trim());
                        }

                        if (data4.contains("Hide")) { // Effects if statements
                            String start = myReader.nextLine();
                            timeLineHide = Integer.parseInt(start.split(":")[1].trim());
                        }
                        if (data4.contains("Show")) {
                            String start2 = myReader.nextLine();
                            timeLineShow = Integer.parseInt(start2.split(":")[1].trim());
                        }
                        if (data4.contains("Jump")) {
                            String start3 = myReader.nextLine();
                            timeLineJump = Integer.parseInt(start3.split(":")[1].trim());
                            start3 = myReader.nextLine();
                            LineNewStartX = Integer.parseInt(start3.split(":")[1].trim());
                            start3 = myReader.nextLine();
                            LineNewStartY = Integer.parseInt(start3.split(":")[1].trim());
                            start3 = myReader.nextLine();
                            LineNewEndX = Integer.parseInt(start3.split(":")[1].trim());
                            start3 = myReader.nextLine();
                            LineNewEndY = Integer.parseInt(start3.split(":")[1].trim());
                        }
                        if (data4.contains("ChangeColor")) {
                            String start4 = myReader.nextLine();
                            timeLineChangeColor = Integer.parseInt(start4.split(":")[1].trim());
                            start4 = myReader.nextLine();
                            LineNewR = Integer.parseInt(start4.split(":")[1].trim().split(",")[0]);
                            LineNewG = Integer.parseInt(start4.split(",")[1].trim());
                            LineNewB = Integer.parseInt(start4.split(",")[2].trim());
                        }

                    }

                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void run(String args[]) {
        launch(AnimationPlayer.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnimationPlayer player2 = new AnimationPlayer();
        player2.loadAnimationFromFile("animation1.txt");

        AnimationTimer timer = new MyTimer(player2);
        timer.start();

        // Drawing a Circle
        circle = new Circle(player2.xc, player2.yc, player2.r);

        // Setting the properties of the circle

        // System.out.println(num);
        circle.setStrokeWidth(player2.Cborder);
        circle.setStroke(Color.rgb(player2.CBorderB, player2.CBorderG, player2.CBorderB));
        circle.setRadius(player2.r);
        circle.setFill(Color.rgb(player2.CcolorR, player2.CcolorG, player2.CcolorB));
        System.out.println("RED in space" + CcolorR);
        circle.opacityProperty().set(0);

        rect = new Rectangle();

        rect.setStrokeWidth(player2.Rborder);
        rect.setStroke(Color.rgb(player2.RBorderR, player2.RBorderG, player2.RBorderB));
        rect.setX(player2.xr);
        rect.setY(player2.yr);
        rect.setWidth(player2.width);
        rect.setHeight(player2.length);
        rect.setFill(Color.rgb(player2.RcolorR, player2.RcolorG, player2.RcolorB));
        rect.opacityProperty().set(0);

        line = new Line();
        line.setStartX(player2.startX);
        line.setStartY(player2.startY);
        line.setEndX(player2.endX);
        line.setEndY(player2.endY);
        line.setStyle("-fx-stroke:" + Color.rgb(player2.LcolorR, player2.LcolorG, player2.LcolorB));
        line.setStrokeWidth(player2.Lborder);
        line.opacityProperty().set(0);

        // Creating a Group object
        Group root = new Group();
        root.getChildren().add(circle);
        root.getChildren().add(rect);

        // Creating a scene object
        Scene scene = new Scene(root, 400, 300);
        // Setting title to the Stage
        primaryStage.setTitle("Animation Player");

        // Adding scene to the stage
        primaryStage.setScene(scene);

        // Displaying the contents of the stage
        primaryStage.show();

        // System.out.println(player2.frameNumber);

    }

    private class MyTimer extends AnimationTimer {

        AnimationPlayer player2;

        int num = 0;

        MyTimer(AnimationPlayer player2) {
            this.player2 = player2;
        }

        @Override
        public void handle(long now) {

            doHandle(num);
            // System.out.println(num);

            if (num == player2.timeCircleHide && player2.isCircle) {
                doCircleHide();
            }
            if (num == player2.timeCircleShow && player2.isCircle) {
                System.out.println("enter circle show");
                doCircleShow();
            }
            if (num == player2.timeCircleChangeColor && player2.isCircle) {
                doCircleChangeColor();
            }
            if (num == player2.timeCircleJump && player2.isCircle) {
                doCircleJump();
            }

            if (num == player2.timeRectHide && player2.isRect) {
                doRectHide();
            }
            if (num == player2.timeRectShow && player2.isRect) {

                doRectShow();
            }
            if (num == player2.timeRectChangeColor && player2.isRect) {
                doRectChangeColor();
            }
            if (num == player2.timeRectJump && player2.isRect) {
                doRectJump();
            }

            if (num == player2.timeLineHide && player2.isLine) {
                doLineHide();
            }
            if (num == player2.timeLineShow && player2.isLine) {
                doLineShow();
            }
            if (num == player2.timeLineChangeColor && player2.isLine) {
                doLineChangeColor();
            }
            if (num == player2.timeLineJump && player2.isLine) {
                doLineJump();
            }

            num = getFrameNum(num);

        }

        private int getFrameNum(int num) {
            num+=1;
            return num;
        }

        private void doHandle(int num) {
            if (num == player2.frames) {
                stop();
            }

        }

        private void doCircleHide() {
            circle.opacityProperty().set(0);
            System.out.println("Circle hidden");
        }

        private void doCircleShow() {

            circle.opacityProperty().set(1);
            circle.setStrokeWidth(player2.Cborder);
            circle.setStroke(Color.rgb(player2.CBorderB, player2.CBorderG, player2.CBorderB));
            circle.setFill(Color.rgb(player2.CcolorR, player2.CcolorG, player2.CcolorB));
            System.out.println("Circle appears");

        }

        private void doCircleChangeColor() {
            System.out.println("circle changed color");
            circle.setFill(Color.rgb(player2.CircleNewR, player2.CircleNewG, player2.CircleNewB));
        }

        private void doCircleJump() {
            System.out.println("Circle Jump");
            circle.setCenterX(player2.CircleNewX);
            circle.setCenterY(player2.CircleNewY);
        }

        private void doRectHide() {

            rect.opacityProperty().set(0);
            System.out.println("Rect hidden");

        }

        private void doRectShow() {
            rect.opacityProperty().set(1);
            rect.setStrokeWidth(player2.Rborder);
            rect.setStroke(Color.rgb(player2.RBorderR, player2.RBorderG, player2.RBorderB));
            rect.setFill(Color.rgb(player2.RcolorR, player2.RcolorG, player2.RcolorB));
            System.out.println("rect appear");

        }

        private void doRectChangeColor() {
            System.out.println("rect changed color");
            rect.setFill(Color.rgb(player2.RectNewR, player2.RectNewG, player2.RectNewB));
        }

        private void doRectJump() {
            System.out.println("rect Jump");
            rect.setX(player2.RectNewX);
            rect.setY(player2.RectNewY);
        }

        private void doLineHide() {
            line.opacityProperty().set(0);
            System.out.println("Line hidden");
        }

        private void doLineShow() {

            line.opacityProperty().set(1);
            line.setStyle("-fx-stroke:" + Color.rgb(player2.LcolorR, player2.LcolorG, player2.LcolorB));
            line.setStrokeWidth(player2.Lborder);
            System.out.println("Line appears");

        }

        private void doLineChangeColor() {
            System.out.println("Line changed color");
            line.setStyle("-fx-stroke:" + Color.rgb(player2.LineNewR, player2.LineNewG, player2.LineNewB));
        }

        private void doLineJump() {
            System.out.println("Line Jump");
            line.setStartX(player2.LineNewStartX);
            line.setStartY(player2.LineNewStartY);
            line.setEndX(player2.LineNewEndX);
            line.setEndY(player2.LineNewEndY);
        }

    }

}

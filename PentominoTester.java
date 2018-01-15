package com.company;
import javafx.scene.shape.Box;
import javafx.scene.Group;
import javafx.scene.paint.*;
public class PentominoTester {

    private final int BOX_SIZE = 50;

    private int[][][] array;
    private double[] coordList = new double[15];
    private int pentCubeCounter = 0;
    private Group pentomino = new Group();

    public PentominoTester (int[][][] inArray) {
        array = inArray;
    }

    public int[][][] search (int i, int j, int k, int id) {
        coordList = new double[15];
        pentCubeCounter = 0;
        for(int a=-3; a<4 && i+a<array.length; a++) {
            if(a+i>=0) {
                for(int b=-3; b<4 && j+b<array[0].length; b++) {
                    if(b+j>=0) {
                        for(int c=-3; c<4 && k+c<array[0][0].length; c++) {
                            if(c+k>=0) {
                                if(array[a+i][b+j][c+k] == id) {
                                    coordList[pentCubeCounter*3] = a;
                                    coordList[pentCubeCounter*3+1] = b;
                                    coordList[pentCubeCounter*3+2] = c;
                                    array[a+i][b+j][c+k] = 0;
                                    pentCubeCounter++;
                                }
                            }
                        }
                    }
                }
            }
        }
        build(i, j, k);
        return array;
    }
    public void build(int i, int j, int k) {
        Box[] listBoxes = new Box[5];
        PhongMaterial material = new PhongMaterial(new Color(Math.random(), Math.random(), Math.random(),1.0));
        for(int t =0; t<=4; t++) {
            listBoxes[t] = new Box(BOX_SIZE,BOX_SIZE,BOX_SIZE);
            listBoxes[t].setMaterial(material);
            listBoxes[t].setTranslateX(coordList[t*3]*BOX_SIZE);
            listBoxes[t].setTranslateY(coordList[t*3+1]*BOX_SIZE);
            listBoxes[t].setTranslateZ(coordList[t*3+2]*BOX_SIZE);
            pentomino.getChildren().add(listBoxes[t]);
            pentomino.setTranslateX(i*BOX_SIZE);
            pentomino.setTranslateY(j*BOX_SIZE);
            pentomino.setTranslateZ(k*BOX_SIZE);
        }
    }
    public Group getPent() {
        return pentomino;
    }
}

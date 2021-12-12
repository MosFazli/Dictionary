/*

This app created By Danial Bayati, Ali Toosi and Mostafa Fazli
Shahroud University of Technology
1400/03/31

*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class Main extends Application {

    public Button startT;
    public static Optimal_Binary_Search_Tree opt = new Optimal_Binary_Search_Tree();

    public static void main(String[] args) throws IOException {launch(args);}

    public static void input() throws IOException {
        Scanner input = new Scanner(System.in);

        try {
            //Get the Excel File
            FileInputStream file = new FileInputStream(
                    new File("src\\dictionary.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get the Desired sheet
            XSSFSheet sheet = workbook.getSheetAt(0);

            int i = 0;
            //Increment over rows
            for (Row row : sheet) {
                //Iterate and get the cells from the row
                Iterator cellIterator = row.cellIterator();
                // Loop till you read all the data
                if (row.getRowNum() != 0) {
                    cellIterator.next();
                    Cell cell = (Cell) cellIterator.next();
                    String key = cell.getStringCellValue();
                    int KEY = key.hashCode();
                    opt.KEYS[i] = KEY;

                    cell = (Cell) cellIterator.next();
                    String meaning = cell.getStringCellValue();
                    opt.mean[i] = meaning;

                    cell = (Cell) cellIterator.next();
                    double p = cell.getNumericCellValue();
                    opt.p[i] = p;

                    opt.q[i] = 0;

                    i++;
                }
            }
            opt.numberOfKeys = i;
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        opt.OPTIMAL_BINARY_SEARCH_TREE();
//        //opt.ROOT.DISPLAY(opt.ROOT, 0);
//        System.out.println();
//        System.out.println("please inter a phrase: ");
//        String[] phrase;
//        String names = input.nextLine();
//        //names = names.replace("  ", " ");
//        names = names.replaceAll("( )+", " ");
//        phrase = names.split(" ");
//        OBST rr = new OBST();
//        for (int i = 0; i < phrase.length; i++) {
//            rr = opt.ROOT.search_Recursive(opt.ROOT, phrase[i].hashCode());
//            if(rr == null) {
//                System.out.print("not found ");
//            } else {
//                System.out.print(rr.meaning + " ");
//            }
//        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        primaryStage.setTitle("Dictionary");
        input();
        primaryStage.setScene(new Scene(root, 508, 388));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void translatee() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Translating Page");
        opt.OPTIMAL_BINARY_SEARCH_TREE();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("translatePage.fxml")));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) startT.getScene().getWindow();
        stage.close();
    }
}

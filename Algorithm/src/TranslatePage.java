/*

This app created By Danial Bayati, Ali Toosi and Mostafa Fazli
Shahroud University of Technology
1400/03/31

*/

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;

public class TranslatePage {
    public Button transB;
    public TextArea englishWord;
    public Label persianWord;

    public void translate() {
        String[] phrase;
        String names = englishWord.getText();
        //names = names.replace("  ", " ");
        names = names.replaceAll("( )+", " ").toLowerCase();
        phrase = names.split(" ");
        OBST rr = new OBST();
        StringBuilder all= new StringBuilder();
        for (int i = 0; i < phrase.length; i++) {
            rr = Main.opt.ROOT.search_Recursive(Main.opt.ROOT, phrase[i].hashCode());
            if(rr == null) {
                System.out.print("not found ");
            } else {
                all.append(rr.meaning).append(" ");
            }
        }
        persianWord.setText(String.valueOf(all));
    }

    public void play(ActionEvent actionEvent) {

        try {

            // Set property as Kevin Dictionary
            System.setProperty(
                    "freetts.voices",
                    "com.sun.speech.freetts.en.us"
                            + ".cmu_us_kal.KevinVoiceDirectory");

            // Register Engine
            Central.registerEngineCentral(
                    "com.sun.speech.freetts"
                            + ".jsapi.FreeTTSEngineCentral");

            // Create a Synthesizer
            Synthesizer synthesizer
                    = Central.createSynthesizer(
                    new SynthesizerModeDesc(Locale.US));

            // Allocate synthesizer
            synthesizer.allocate();

            // Resume Synthesizer
            synthesizer.resume();

            // Speaks the given text
            // until the queue is empty.

            String names = englishWord.getText();
            //names = names.replace("  ", " ");
            names = names.replaceAll("( )+", " ").toLowerCase();


            synthesizer.speakPlainText(
                    names, null);
            synthesizer.getSynthesizerProperties().setSpeakingRate(100);
     //       synthesizer.getSynthesizerProperties().setPitch(150);
            synthesizer.waitEngineState(
                    Synthesizer.QUEUE_EMPTY);

            // Deallocate the Synthesizer.
            synthesizer.deallocate();

        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }
}

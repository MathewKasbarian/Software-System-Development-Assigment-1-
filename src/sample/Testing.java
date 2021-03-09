package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Testing {
    private static ObservableList<TestFile> files= FXCollections.observableArrayList();

    public static ObservableList<TestFile> getAllFiles() throws FileNotFoundException {
      File ham =new File("src/sample/data/test/ham");
      File spam =new File("src/sample/data/test/spam");
      train.setTrainDetector();
      train.probWofS();
      train.probWofH();
      train.probSofW();
      for(File email: Objects.requireNonNull(ham.listFiles())){
          Scanner input=new Scanner(email);

          double n=0.0;
          while(input.hasNext()){
              String word =input.next();
              if(train.prSofW.containsKey(word)){
                  n+=Math.log(1-train.getProbSOfW(word))-Math.log(train.getProbSOfW(word));

              }

          }
          double prSofF= 1.0/(1.0+Math.pow(Math.E,n));
          files.add(new TestFile(email.getName(),prSofF,"ham"));
      }
        for(File email: Objects.requireNonNull(spam.listFiles())){
            Scanner input=new Scanner(email);

            double n=0.0;
            while(input.hasNext()){
                String word =input.next();
                if(train.prSofW.containsKey(word)){
                    n+=Math.log(1-train.getProbSOfW(word))-Math.log(train.getProbSOfW(word));

                }

            }
            double prSofF= 1.0/(1.0+Math.pow(Math.E,n));
            files.add(new TestFile(email.getName(),prSofF,"spam"));
        }
        return files;
    }
    public static float getAccuracy(){
        float accuracy= 0.0F;
        int numTruePos=0;
        int numTrueNeg=0;
        for(TestFile email:files){
            if(email.getSpamProbability()>=0.9&& email.getActualClass().equals("spam")){
                numTruePos++;
            }
            else if(email.getSpamProbability()<=0.1&& email.getActualClass().equals("ham")){
                numTrueNeg++;
            }

        }
        accuracy=(float)(numTrueNeg+numTruePos)/(float)files.size();
        return accuracy;

    }
    public static float getPrecision(){
        int numTruePos=0;
        int numFalsePos=0;
        for(TestFile email:files){
            if(email.getSpamProbability()>=0.9&& email.getActualClass().equals("spam")){
                numTruePos++;
            }
            else if(email.getSpamProbability()>=0.9&& email.getActualClass().equals("ham")){
                numFalsePos++;
            }

        }
        float precision=(float)numTruePos/(float)(numFalsePos+numTruePos);
        return precision;
    }
}

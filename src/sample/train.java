package sample;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class train {

    private static int numHam=0;
    private static int numSpam=0;

    public static HashMap<String, Float>prSofW=new HashMap<>();
    private static HashMap<String, Float>prWofH=new HashMap<>();
    private static HashMap<String, Float>prWofS=new HashMap<>();
    private static HashMap<String, Integer>trainSpamFreq=new HashMap<>();
    private static HashMap<String, Integer>trainHamFreq=new HashMap<>();
    public static void setTrainDetector() throws FileNotFoundException {
        Scanner input;

        //ham
        File ham=new File ("src/sample/data/train/ham");
        for(File email: Objects.requireNonNull(ham.listFiles())){
            numHam++;
            input=new Scanner (email);
            while( input.hasNext()){
                String x=input.next();
                if(trainHamFreq.containsKey(x)){
                    trainHamFreq.put(x, trainHamFreq.get(x)+1);
                }
                else{
                    trainHamFreq.put(x,1);
                }
            }
        }
        //ham2
        ham=new File ("src/sample/data/train/ham2");
        for(File email: Objects.requireNonNull(ham.listFiles())){
            input=new Scanner (email);
            numHam++;
            while( input.hasNext()){
                String x=input.next();
                if(trainHamFreq.containsKey(x)){
                    trainHamFreq.put(x, trainHamFreq.get(x)+1);
                }
                else{
                    trainHamFreq.put(x,1);
                }

            }
        }
        //spam
        ham=new File ("src/sample/data/train/spam");
        for(File email: Objects.requireNonNull(ham.listFiles())){
            numSpam++;
            input=new Scanner (email);
            while( input.hasNext()){
                String x=input.next();
                if(trainSpamFreq.containsKey(x)){
                    trainSpamFreq.put(x, trainSpamFreq.get(x)+1);
                }
                else{
                    trainSpamFreq.put(x,1);
                }
            }
        }
    }
    public static void probWofS(){

        for(var x: trainSpamFreq.entrySet()){
            prWofS.put(x.getKey(),(float)x.getValue()/ (float)numSpam);

        }
    }
    public static void probWofH(){

        for(var x: trainHamFreq.entrySet()){
            prWofH.put(x.getKey(),(float)x.getValue()/ (float)numHam);

        }
    }
    public static void probSofW(){

        for(var x: prWofS.entrySet()){
            if(prWofH.containsKey(x.getKey())){
                String key= x.getKey();
                prSofW.put(key,prWofS.get(key)/(prWofS.get(key)+prWofH.get(key)));
            }
        }
    }
    public static float getProbSOfW(String key){

        return prSofW.get(key);
    }



}

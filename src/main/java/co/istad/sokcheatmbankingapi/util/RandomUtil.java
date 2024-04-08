package co.istad.sokcheatmbankingapi.util;

public class RandomUtil {
    String result;
    String generate9Digits(){

        for(long i = 1; i<=999999999; i++){
            result = String.format("%09d",i);
        }
        return result;
    }

}

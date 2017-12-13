package program;

public class FastExponential {

    public FastExponential(){}
    
    public int getFastExpo(int base,int power,int mod){
        //Define
        int test = base;
        String bitPow = Integer.toBinaryString(power);
        int lenBitPow = bitPow.length();
        int result = 1;
        int [] fexpo = new int[lenBitPow];
        
        //cal power
        fexpo[0]=test;
        for(int i=1;i<lenBitPow;i++){
            fexpo[i]=Math.floorMod((fexpo[i-1]*fexpo[i-1]),mod);
            
        }
        for(int j =lenBitPow-1;j>=0;j--){
            if(bitPow.substring(j,j+1).equals("1")){
                result=Math.floorMod(result*(fexpo[j]),mod);
            }
        }
        return result;
    }
}

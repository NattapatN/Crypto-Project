package program;

public class FastExponential {

    public FastExponential(){}
    
    public int getFastExpo(int base,int power,int mod){
        //define
        long ibase =base;
        long ipower = power;
        long result=1;
        
        while(ipower!=0){
            if(ipower %2==1){
                result=Math.floorMod((result*ibase),mod);
                //System.out.println("result is "+result);
            }
            ipower>>=1;
            ibase = Math.floorMod((ibase*ibase),mod);
            //System.out.println("base is "+ibase);
        }        
        //System.out.println("base : "+ base+" Result : "+result);
        return (int)result;
    }
}

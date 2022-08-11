import java.lang.Math;


class PrimePrinterThread implements Runnable{
    int number;
    PrimePrinterThread(int num){
        this.number=num;
    }
    PrimePrinterThread(){
        this.number=100;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread());
        for (int i = 2; i <= this.number ; i++) {
            if(isPrime(i)){
                System.out.println(i);
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
            }
        }
    }
  public static Boolean isPrime(int number){
        if(number== 2) return (Boolean)true;
        if(number%2 == 0) return (Boolean)false;
        int i=2;
        while(i <= Math.sqrt(number)){
            if(number % i ==0){
                return (Boolean)false;
            }
            i+=1;
        }
        return (Boolean) true;
    }
}


public class Test11 {
    public static void main(String[] args) throws InterruptedException {
        PrimePrinterThread r= new PrimePrinterThread();
        System.out.println(Thread.currentThread());

        Thread p=new Thread(r);
        p.start();
        p.join();//join the main threads execution wait for the thread to end
        //without .join() main thread wont wait
        System.out.println(Thread.currentThread());

    }
}

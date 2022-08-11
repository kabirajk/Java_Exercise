import java.lang.Math;
import java.util.Random;
import java.util.Vector;

class CUserQueue<E> extends Vector<E> {

    public void enqueue(E e){
        // v.add(e);
        super.add(e);
    }

    @Override
    public synchronized E get(int index) {
        return super.get(index);
    }

    public E dequeue(){
        // v.remove(0);
        E temp=null;
        try {
            temp=super.remove(0);
        }
        catch (ArrayIndexOutOfBoundsException AIOBE){
        }
        return temp;
    }
    public void list(){
        for(int index=0;index<this.size();index+=1){
            System.out.print(this.get(index)+"<=");
        }
        System.out.println("endofUserQueue");
    }
}

class Producer implements  Runnable{

   CUserQueue<Integer> queue;

    Producer(CUserQueue<Integer>  obj){
        queue=obj;
    }

    @Override
    public void run() {
        while(true){
            Random rand= new Random();
            queue.enqueue(Integer.valueOf(rand.nextInt(10000)));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


class Consumer implements  Runnable{
   CUserQueue<Integer> queue;

    Consumer(CUserQueue<Integer> obj){
        queue=obj;
    }

    @Override
    public void run() {
        while(true){
            int number=(int)queue.dequeue();
            if(number == 0)continue;
            if(this.isPrime(number)){
                System.out.println(number+" is Prime");
            }
            else {
                System.out.println(number+" is NotPrime");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public  Boolean isPrime(int number){
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

public class Test12 {
    public static void main(String[] args) throws InterruptedException {
        CUserQueue<Integer> intQueue = new CUserQueue<>();
        Producer producerObject = new Producer(intQueue);
        Consumer consumerObject = new Consumer(intQueue);
        Thread con = new Thread(consumerObject);
        Thread prod= new Thread(producerObject);

        prod.start();
        prod.setPriority(10);
        Thread.sleep(2000);
        con.start();
    }
}


import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Vector;
public class Test9 {
    public static void main(String[] args) {
        UserQueue<String> q = new UserQueue<>();
        // q.enUserQueue(1);
        // q.enUserQueue(2);
        // q.enUserQueue("Hello");
        // q.enUserQueue('H');  

        // for(int i=0;i<q.size();i++){
        //     System.out.println(q.get(i));
        // }
        Scanner myScanner =new Scanner(System.in);
        int option;
        String myString;
        while(true){
            System.out.println("1.EnUserQueue");
            System.out.println("2.DeUserQueue");
            System.out.println("3.List");
            System.out.println("4.exit");

            option=myScanner.nextInt();
            if(option==1){
                System.out.println("What want to insert");
                // System.out.println(myScanner.hasNextLine());
                myString= myScanner.next();
                q.enqueue(myString);
            }
            else if(option==2){
                q.dequeue();
            }
            else if(option==3){
                q.list();
            }
            else if(option==4){
                break;
            }
            }
        myScanner.close();
    }
}



class UserQueue<E> extends Vector<E>{


    public void enqueue(E e){
        // v.add(e);
            super.add(e);
    }

    @Override
    public synchronized E get(int index) {
        return super.get(index);
    }

    public void dequeue(){
        // v.remove(0);
        try{
        super.remove(super.firstElement());
        }
        catch(NoSuchElementException ex){
            System.out.println("Empty UserQueue");
        }
        this.list();
    }
    public void list(){
        for(int index=0;index<this.size();index+=1){
            System.out.print(this.get(index)+"<=");
        }
        System.out.println("endofUserQueue");
    }
}
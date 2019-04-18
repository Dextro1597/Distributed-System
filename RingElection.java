import java.util.Scanner;

class Process {

    int processId;
    boolean active;

    public Process(int processId) {
        this.processId = processId;
        active = true;
    }
}

public class RingElection {
    
    private Scanner consoleInput;
    private Process[] process;
    private int NosOfProcess;

    public RingElection() {
        System.out.println("\n\t\t==:Ring Coordinator Election Algorithm:==\n");
        consoleInput = new Scanner(System.in);
    }

    public void getInput() {
        System.out.println("Enter number of process in ring: ");
        NosOfProcess = consoleInput.nextInt();
        process = new Process[NosOfProcess];

        for (int i = 0; i < NosOfProcess; i++) {
            System.out.print("Enter Process ID of p" + i + ": ");
            int pid = consoleInput.nextInt();
            initializeProcess(i, pid);
        }
        sortProcess();
        putOutput();
    }

    private void initializeProcess(int i, int pid) {
        process[i] = new Process(pid);
    }

    public void conductElection() {
                
        try{
            Thread.sleep(2000);
        }catch(Exception e ){
            System.out.println(e);
        }
        System.out.println("process "+ process[getMax()].processId +" Fail");
        process[NosOfProcess-1].active = false;
        
        while(true){
            System.out.print("Conduct Election?\nyes or exit: ");
            String choice = consoleInput.next();
            if("yes".equals(choice) || "Yes".equals(choice) || "y".equals(choice) || "Y".equals(choice)){
                System.out.println("Election initiated by: ");
                int initiatorProcess = consoleInput.nextInt();
                for(int i = 0; i< NosOfProcess; i++){
                    if(process[i].processId == initiatorProcess){
                        initiatorProcess = i;
                        break;
                    }
                }
                int prev = initiatorProcess;
                int next = prev+1;
               
                while(true){
                    if(process[next].active) {
                        System.out.println("Process "+ process[prev].processId +" pass message to process "+process[next].processId );

                         int[] temp=new int[10];
                           for(int i=0;i<temp.length;i++)
                           { 
                           
                            temp[i]=process[prev].processId;
                            break;
                             
                            
                            }
                          for(int i=0;i<temp.length;i++)
                         { 
                                
                                  if(temp[i]!=0)
                                 {System.out.println("["+temp[i]+"]");}
                                 else break;                            
                            
                         }
                         System.out.println("["+process[next].processId+"]" );
                         prev = next;
                    }
                    next = (next+1) % NosOfProcess;
                    
                    if(next == initiatorProcess) {
                        System.out.println("Process "+ process[prev].processId +" pass message to process "+process[next].processId );
                         System.out.println("["+initiatorProcess+"]" );
                         System.out.println("["+process[next].processId+"]" );
                         
                         
                      
                        break;
                    }
                }
                System.out.println("Process "+ process[getMax()].processId +" becomes coordinator");
            } else {
                System.exit(0);            
            }
        }
    }
    
    public void putOutput(){
        System.out.println("Processes in the ring: ");   
        for(int i = 0; i < NosOfProcess; i++){
            System.out.print(process[i].processId +", active: "+ process[i].active);
            if(i == getMax()){
                System.out.print(", Coordinator\n");
            }else {
                System.out.print("\n");
            }
            
        }
    }
    
    private void sortProcess() {
        for (int i = 0; i < NosOfProcess - 1; i++) {
            for (int j = 0; j < (NosOfProcess - i) -1; j++) {
                if (process[j].processId > process[j + 1].processId) {
                    int temp = process[j].processId;
                    process[j].processId = process[j + 1].processId;
                    process[j + 1].processId = temp;

                }
            }
        }
    }
    
    private int getMax(){
        int max = 0, indexOfMax = 0;
        for(int i = 0; i < NosOfProcess; i++){
            if(process[i].active && max <= process[i].processId ) {
                max = process[i].processId;
                indexOfMax = i;
            }
        }
        return indexOfMax;
    }

    public static void main(String arg[]) {
        System.out.println("BHAGYASHREE MEHTA [0802IT121014]");
        RingElection ringElection = new RingElection();
        ringElection.getInput();
        ringElection.conductElection();
    
    }
} 

/*
ubuntu@ubuntu-VPCEH36EN:~/Desktop$ javac RingElection.java
ubuntu@ubuntu-VPCEH36EN:~/Desktop$ java RingElection
BHAGYASHREE MEHTA [0802IT121014]

		==:Ring Coordinator Election Algorithm:==

Enter number of process in ring: 
8
Enter Process ID of p0: 0
Enter Process ID of p1: 1
Enter Process ID of p2: 2
Enter Process ID of p3: 3
Enter Process ID of p4: 4
Enter Process ID of p5: 5
Enter Process ID of p6: 6
Enter Process ID of p7: 7
Processes in the ring: 
0, active: true
1, active: true
2, active: true
3, active: true
4, active: true
5, active: true
6, active: true
7, active: true, Coordinator
process 7 Fail
Conduct Election?
yes or exit: yes
Election initiated by: 
2
Process 2 pass message to process 3
Process 3 pass message to process 4
Process 4 pass message to process 5
Process 5 pass message to process 6
Process 6 pass message to process 0
Process 0 pass message to process 1
Process 6 becomes coordinator
Conduct Election?
yes or exit: exit
*/

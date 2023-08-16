import java.util.Arrays;
import java.util.Scanner;

class CliBankApp{
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {



              final String CLEAR = "\033[H\033[2J";
              final String COLOR_BLUE_BOLD = "\033[34;1m";
              final String COLOR_RED_BOLD = "\033[31;1m";
              final String COLOR_GREEN_BOLD = "\033[33;1m";
              final String RESET = "\033[0m";

              final String DASHBOARD = "Welcome to Smart Banking";
              final String CREATE_ACCOUNT = "Create new Account";
              final String DEPOSITS = "Deposits";
              final String WITHDRAWSLS = "Withdrawals";
              final String TRANSFER = "Transfer";
              final String CHECK_ACCOUNT_BALANCE = "Check Account Balance";
              final String DELETE_ACCOUNT = "Delete Account";

              final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
              final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);


              String[][] BankDetails = new String[0][3];
              int Account = 1;

             String screen = DASHBOARD;

mainLoop:
            do{
              final String APP_TITLE = String.format("%s%s%s",
              COLOR_BLUE_BOLD, screen, RESET);

              System.out.println(CLEAR);
              System.out.println("\t" + APP_TITLE + "\n");

switch(screen){
    case DASHBOARD: 
             System.out.println("\t[1]. Create new Account");
             System.out.println("\t[2]. Deposits");
             System.out.println("\t[3]. Withdrawals");
             System.out.println("\t[4]. Transfer");
             System.out.println("\t[5]. Check Account Balance");
             System.out.println("\t[6]. Delete Account");
             System.out.println("\t[7]. Exit\n");
             System.out.print("\tEnter an option to continue: ");
             int option = SCANNER.nextInt();
             SCANNER.nextLine();

switch (option){
            case 1: screen = CREATE_ACCOUNT; break;
            case 2: screen = DEPOSITS; break;
            case 3: screen = WITHDRAWSLS; break;
            case 4: screen = TRANSFER; break;
            case 5: screen = CHECK_ACCOUNT_BALANCE; break;
            case 6: screen = DELETE_ACCOUNT; break;
            case 7: System.out.println(CLEAR); System.exit(0);
            default: continue;
            }
            break;

case CREATE_ACCOUNT:
           String id = "";
           String name = "";
           Double initialDeposit = 0.00;
           boolean valid;

           System.out.printf("\tNew Account id: SDB-%05d\n",Account);
           id = String.format("SDB-%05d",Account); 

// Name Validation
           do{

                valid = true;
                System.out.print("\tEnter Customer Name: ");
                name = SCANNER.nextLine().strip();
                if (name.isBlank()){
                System.out.printf(ERROR_MSG, "Customer name can't be empty");
                valid = false;
                continue;
            }

           for (int i = 0; i < name.length(); i++) {
           if (!(Character.isLetter(name.charAt(i)) || 
           Character.isSpaceChar(name.charAt(i))) ) {
           System.out.printf(ERROR_MSG, "Invalid name");
           valid = false;
           break;
          } 
       }   
    }while(!valid);

//Deposit Validation

           do{

               valid = true;
               System.out.print("\tEnter Deposit value: ");
               initialDeposit = SCANNER.nextDouble();
               SCANNER.nextLine();
// System.out.println(initialDeposit);

            if ((initialDeposit<5000.00)){
            System.out.printf(ERROR_MSG, "Insufficient Account");
            valid = false;
          }
    }while(!valid);
          String[][] newBankDetails = new String[BankDetails.length+1][3];

          for (int i = 0; i < BankDetails.length; i++) {
              newBankDetails[i] = BankDetails[i];
            }

          newBankDetails[newBankDetails.length - 1][0] = id;
          newBankDetails[newBankDetails.length - 1][1] = name;
          newBankDetails[newBankDetails.length - 1][2] = Double.toString(initialDeposit);

         newBankDetails = BankDetails;


System.out.println(Arrays.toString(BankDetails));
System.out.println(Arrays.toString(BankDetails));
System.out.println(Arrays.toString(BankDetails));

System.out.println();
System.out.printf(SUCCESS_MSG, 
String.format("%s:%s has been saved successfully", id, name)); Account++;
System.out.print("\tDo you want to continue adding (Y/n)? ");
if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
screen = DASHBOARD;
break;



case DEPOSITS:
int index1 = 0;

// ID Validation
do {
valid = true;
System.out.print("\tEnter the Account No: ");
id = SCANNER.nextLine().toUpperCase().strip();
if (id.isBlank()){
System.out.printf(ERROR_MSG, "ID can't be empty");
valid = false;
}else if (!id.startsWith("C-") || id.length() < 4){
System.out.printf(ERROR_MSG, "Invalid ID format");
valid = false;
}else{
String number = id.substring(4);
for (int i = 0; i < number.length(); i++) {
if (!Character.isDigit(number.charAt(i))){
System.out.printf(ERROR_MSG, "Invalid ID format");
valid = false;
break;
}
}
boolean exists = false;
for (int i = 0; i < BankDetails.length; i++) {
if (BankDetails[i][0].equals(id)){
index1 = i;
exists = true;
break;
}
} 
if (!exists){
valid = false;
System.out.printf(ERROR_MSG, "Account is not Found");
}
}

if (!valid) {
System.out.print("\n\tDo you want to try again? (Y/n)");
if (!SCANNER.nextLine().strip().toUpperCase().equals("Y")){
screen = DASHBOARD;
continue mainLoop;
}
System.out.println();

System.out.printf("Current Balance : %d.2", BankDetails[index1][2]);

System.out.print("Deposit amount : ");
Double depositAmount = SCANNER.nextDouble();
SCANNER.nextLine();

if(!(depositAmount>=500)){System.out.print("Insufficient Amount"); break;
}else{BankDetails[index1][2]=(BankDetails[index1][2] +depositAmount);}

System.out.printf("New Account Balance: %.2d",BankDetails[index1][2]);

System.out.printf(SUCCESS_MSG, 
String.format("%s has been done successfully", "Diposit"));
System.out.print("\tDo you want to continue (Y/n)? ");
if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
screen = DASHBOARD;
break;

}
}while (!valid);




case WITHDRAWSLS: 
int index2 = 0;

// ID Validation
do {
valid = true;
System.out.print("\tEnter the Account Id: ");
id = SCANNER.nextLine().toUpperCase().strip();
if (id.isBlank()){
System.out.printf(ERROR_MSG, "ID can't be empty");
valid = false;
}else if (!id.startsWith("C-") || id.length() < 4){
System.out.printf(ERROR_MSG, "Invalid ID format");
valid = false;
}else{
String number = id.substring(4);
for (int i = 0; i < number.length(); i++) {
if (!Character.isDigit(number.charAt(i))){
System.out.printf(ERROR_MSG, "Invalid ID format");
valid = false;
break;
}
}
boolean exists = false;
for (int i = 0; i < BankDetails.length; i++) {
if (BankDetails[i][0].equals(id)){
index2 = i;
exists = true;
break;
}
} 
if (!exists){
valid = false;
System.out.printf(ERROR_MSG, "Account does not exist");
}
}

if (!valid) {
System.out.print("\n\tDo you want to try again? (Y/n)");
if (!SCANNER.nextLine().strip().toUpperCase().equals("Y")){
screen = DASHBOARD;
continue mainLoop;
}
System.out.println();

System.out.printf("Current Balance : %d.2", BankDetails[index2][2]);

System.out.print("Withdraw amount : ");
Double withdraw = SCANNER.nextDouble();
SCANNER.nextLine();

if(!( withdraw>100 || (AccountBlance[index2]-withdraw)>500)){System.out.print("Insufficient Amount"); break;
}else{AccountBlance[index2]=(AccountBlance[index2]-withdraw);}

System.out.printf("New Account Balance: %.2d",AccountBlance[index2]);

System.out.printf(SUCCESS_MSG, 
String.format("%s has been done successfully", "Withdraw"));
System.out.print("\tDo you want to continue (Y/n)? ");
if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
screen = DASHBOARD;
break;

}
}while (!valid);











case TRANSFER:

int indexFrom = 0;
// ID Validation
do {
valid = true;
System.out.print("\tEnter the Customer ID to delete: ");
id = SCANNER.nextLine().toUpperCase().strip();
if (id.isBlank()){
System.out.printf(ERROR_MSG, "ID can't be empty");
valid = false;
}else if (!id.startsWith("C-") || id.length() < 3){
System.out.printf(ERROR_MSG, "Invalid ID format");
valid = false;
}else{
String number = id.substring(2);
for (int i = 0; i < number.length(); i++) {
if (!Character.isDigit(number.charAt(i))){
System.out.printf(ERROR_MSG, "Invalid ID format");
valid = false;
break;
}
}
boolean exists = false;
for (int i = 0; i < customerId.length; i++) {
if (customerId[i].equals(id)){
indexFrom = i;
exists = true;
break;
}
} 
if (!exists){
valid = false;
System.out.printf(ERROR_MSG, "Customer ID does not exist");
}
}
if (!valid) {
System.out.print("\n\tDo you want to try again? (Y/n)");
if (!SCANNER.nextLine().strip().toUpperCase().equals("Y")){
screen = DASHBOARD;
continue mainLoop;
}
System.out.println();
}
}while (!valid); 

int indexTo = 0;
// ID Validation
do {
valid = true;
System.out.print("\tEnter the Customer ID to delete: ");
id = SCANNER.nextLine().toUpperCase().strip();
if (id.isBlank()){
System.out.printf(ERROR_MSG, "ID can't be empty");
valid = false;
}else if (!id.startsWith("C-") || id.length() < 3){
System.out.printf(ERROR_MSG, "Invalid ID format");
valid = false;
}else{
String number = id.substring(2);
for (int i = 0; i < number.length(); i++) {
if (!Character.isDigit(number.charAt(i))){
System.out.printf(ERROR_MSG, "Invalid ID format");
valid = false;
break;
}
}
boolean exists = false;
for (int i = 0; i < customerId.length; i++) {
if (customerId[i].equals(id)){
indexTo = i;
exists = true;
break;
}
} 
if (!exists){
valid = false;
System.out.printf(ERROR_MSG, "Customer ID does not exist");
}
}
if (!valid) {
System.out.print("\n\tDo you want to try again? (Y/n)");
if (!SCANNER.nextLine().strip().toUpperCase().equals("Y")){
screen = DASHBOARD;
continue mainLoop;
}
System.out.println();
}
}while (!valid);

System.out.println("From Account: "+customerNames[indexFrom]);
System.out.println("To Account: "+customerNames[indexTo]);

System.out.println("From Acoount balance: "+AccountBlance[indexFrom]);
System.out.println("To Acoount balance: "+AccountBlance[indexTo]);

System.out.print("Enter transfer amount: ");
Double transfer = SCANNER.nextDouble();
SCANNER.nextLine();

if(transfer>100 && (AccountBlance[indexFrom]-transfer-(transfer*2/100))>500){
Double newFrom = AccountBlance[indexFrom]-transfer-(transfer*2/100);
Double newTo = AccountBlance[indexTo]+transfer;
AccountBlance[indexFrom] = newFrom;
AccountBlance[indexTo] = newTo;
System.out.println("New Account Balance From: "+newFrom);
System.out.println("New Account Balance From: "+newTo);

System.out.println();
System.out.printf(SUCCESS_MSG, 
String.format("%s has been transfer successfully", transfer));
System.out.print("\tDo you want to continue transfering (Y/n)? ");
if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
screen = DASHBOARD;
break;
}


















case CHECK_ACCOUNT_BALANCE: 
int index = 0;
// ID Validation
do {
valid = true;
System.out.print("\tEnter the From Customer Account number: ");
id = SCANNER.nextLine().toUpperCase().strip();
if (id.isBlank()){
System.out.printf(ERROR_MSG, "ID can't be empty");
valid = false;
}else if (!id.startsWith("C-") || id.length() < 3){
System.out.printf(ERROR_MSG, "Invalid ID format");
valid = false;
}else{
String number = id.substring(2);
for (int i = 0; i < number.length(); i++) {
if (!Character.isDigit(number.charAt(i))){
System.out.printf(ERROR_MSG, "Invalid ID format");
valid = false;
break;
}
}

boolean exists = false;
for (int i = 0; i < customerId.length; i++) {
if (customerId[i].equals(id)){
index = i;
exists = true;
break;
}
} 
if (!exists){
valid = false;
System.out.printf(ERROR_MSG, "Customer ID does not exist");
}
}
if (!valid) {
System.out.print("\n\tDo you want to try again? (Y/n)");
if (!SCANNER.nextLine().strip().toUpperCase().equals("Y")){
screen = DASHBOARD;
continue mainLoop;
}
System.out.println();
}
}while (!valid);




case DELETE_ACCOUNT:
int index3 = 0;

// ID Validation
do {
valid = true;
System.out.print("\tEnter the Account Id: ");
id = SCANNER.nextLine().toUpperCase().strip();
if (id.isBlank()){
System.out.printf(ERROR_MSG, "ID can't be empty");
valid = false;
}else if (!id.startsWith("C-") || id.length() < 4){
System.out.printf(ERROR_MSG, "Invalid ID format");
valid = false;
}else{
String number = id.substring(4);
for (int i = 0; i < number.length(); i++) {
if (!Character.isDigit(number.charAt(i))){
System.out.printf(ERROR_MSG, "Invalid ID format");
valid = false;
break;
}
}
boolean exists = false;
for (int i = 0; i < customerId.length; i++) {
if (customerId[i].equals(id)){
index3 = i;
exists = true;
break;
}
} 
if (!exists){
valid = false;
System.out.printf(ERROR_MSG, "Account does not exist");
}
}

if (!valid) {
System.out.print("\n\tDo you want to try again? (Y/n)");
if (!SCANNER.nextLine().strip().toUpperCase().equals("Y")){
screen = DASHBOARD;
continue mainLoop;
}
System.out.println();
}
}while (!valid);

System.out.print("\tAre you sure to delete this account (Y/n)? ");
if (!SCANNER.nextLine().strip().toUpperCase().equals("Y")) break;

newIdtemp = new String[customerId.length - 1];
newNametemp= new String[newIdtemp.length];

for (int i = 0; i < customerId.length; i++) {
if (i < index3){
newIdtemp[i] = customerId[i];
newNametemp[i] = customerNames[i];
}else if (i == index3){
continue;
}else{
newIdtemp[i - 1] = customerId[i];
newNametemp[i - 1] = customerNames[i];
}
}

customerId = newIdtemp;
customerNames = newNametemp;


System.out.println();
System.out.printf(SUCCESS_MSG, 
String.format("%s has been deleted successfully", id));
System.out.print("\tDo you want to continue adding (Y/n)? ");
if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
screen = DASHBOARD;
break;
}
}while(true);








        
    }
   
    }

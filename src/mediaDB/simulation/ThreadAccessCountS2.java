package mediaDB.simulation;


public class ThreadAccessCountS2 extends Thread {
//    Administration admin;
//    ArrayList<Uploadable> uploadables;
//    int randomInt;
//    Uploadable pickedFile;
//
//    public ThreadAccessCountS2(Administration admin) {
//        this.admin = admin;
//    }
//
//    public void run(){
//        while (true) {
//            uploadables = admin.read();
//            int size = uploadables.size();
//            if (size > 0)
//                randomInt = getRandomNumberInRange(size);
//            else continue;
//            if (randomInt < size)
//                pickedFile = uploadables.get(randomInt);
//            else continue;
//            long oldCount;
//            if (pickedFile != null)
//                oldCount= ((Content) pickedFile).getAccessCount();
//            else continue;
//            String address = ((Content) pickedFile).getAddress();
//            admin.update(address, (oldCount + 1));
//            System.out.println("Tried to update access count of " + address + " from " + oldCount + " to " + (oldCount + 1));
//        }
//    }
//
//    private static int getRandomNumberInRange(int max) {
//        if (0 >= max) {
//            throw new IllegalArgumentException("max must be greater than min");
//        }
//        return (int) (Math.random() * ((max) + 1));
//    }
}

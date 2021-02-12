package mediaDB.simulation;


public class ThreadDeleteS2 extends Thread{
//    Administration admin;
//    BlockingQueue queue;
//    ArrayList<Uploadable> uploadables;
//    Uploadable pickedFile;
//    int size;
//
//
//    public ThreadDeleteS2(Administration admin, BlockingQueue queue) {
//        this.admin = admin;
//        this.queue = queue;
//    }
//
//    public  void run() {
//            while (true) {
//                uploadables = admin.read();
//                size = uploadables.size();
//                if (size > 0)
//                    pickedFile = lowestAccessCount();
//                else continue;
//                if (pickedFile != null) {
//                    try {
//                        queue.take(pickedFile);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                else continue;
//                String address = ((Content) pickedFile).getAddress();
//                System.out.println("Tried to delete file " + address);
//            }
//    }
//
//    private Uploadable lowestAccessCount(){
//        long lowestCount = 50000000000L;
//        Uploadable lowestCountUploadable = null;
//        for (int i = 0; i < size; i++) {
//            Uploadable uploadable = uploadables.get(i);
//            long accessCount = ((Content) uploadable).getAccessCount();
//            if (accessCount <= lowestCount){
//                lowestCountUploadable = uploadable;
//                lowestCount = accessCount;
//            }
//        }
//        return lowestCountUploadable;
//    }
}

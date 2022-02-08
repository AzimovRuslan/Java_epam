public class TestingRunner {
    public static void main(String[] args) {
        final Object object = new Object();
        Thread t1 = new Thread() {
            public void run()
            {
                synchronized (object) {
                    System.out.println("T1 start!");
                    System.out.println("T2 end!");
                }
            }
        };

        Thread t2 = new Thread() {
            public void run()
            {
                synchronized (object) {
                    while (t1.isAlive()) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    object.notify();
                    System.out.println("T2 start!");
                    System.out.println("T2 end!");
                }
            }
        };

        t1.start();
        t2.start();

    }
}
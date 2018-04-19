package foo;

/**
 * Created by tysheng
 * Date: 15/12/17 4:03 PM.
 * Email: tyshengsx@gmail.com
 */

public class Test {
    public static void main(String[] args) {
//        Observable.just(new ArrayList<>())
//                .flatMapIterable(objects -> objects)
//                .map(o -> o)
//                .toList()
//                .subscribe(objects -> System.out.println(objects));

//        System.out.println(Math.pow(62,6));
        System.out.println("main "+Thread.currentThread().getName());
        new Tthread(new Adapter() {
            @Override
            public void onCall() {
                System.out.println("call "+Thread.currentThread().getName());
            }
        }).start();
    }


    interface Adapter{
        void onCall();
    }
    static class Tthread extends Thread{
        Adapter adapter;
        public Tthread(Adapter adapter) {
            this.adapter = adapter;
        }

        @Override
        public void run() {
            super.run();
            System.out.println("run "+Thread.currentThread().getName());
            Thread.yield();
            adapter.onCall();
        }
    }
}

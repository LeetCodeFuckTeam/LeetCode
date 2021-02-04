package juc.piped;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.nio.channels.Pipe;

public class Piped {


    public static void main(String args[]) {
        try{
            PipedReader pipedReader = new PipedReader();
            PipedWriter pipedWriter = new PipedWriter();
            pipedReader.connect(pipedWriter);
            new Thread(new Print(pipedReader),"print").start();
            int recieve = 0;
            while ((recieve = System.in.read()) != -1) {
                pipedWriter.write(recieve) ;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }


    }
    static class Print implements Runnable{

        private PipedReader pipedReader;

        public Print(PipedReader pipedReader) {
            this.pipedReader = pipedReader;
        }

        @Override
        public void run() {
            int recieve = 0;
            while (true) {
                try {
                    if ((recieve = pipedReader.read())!= -1) {
                        System.out.println((char) recieve);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}


package musichub.main;

//import musichub.util.*;

import java.io.IOException;

public class Tmp {
    public static void main(String[] args) {
        // Create two threads:
        Thread thread1 = new Thread() {
            public void run() {
                try {
                    //Process p = Runtime.getRuntime().exec("cmd /c start cmd.exe -c java -cp musichub.util.Server");
                    String[] args = new String[] {"cmd", "/c", "start", "cmd.exe", "-c", "java", "-cp", "musichub.util.server"};
                    Process proc = new ProcessBuilder(args).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread2 = new Thread() {
            public void run() {
                try {
                    Process p = Runtime.getRuntime().exec("java -cp musichub.util.Client");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        //thread1.start();
        //thread2.start();

        /*
        String[] arg1 = new String[] {"cmd", "/c", "start", "cmd.exe", "-c", "java", "-cp", "./src/main/java", "musichub.util.Server"};
        try {
            Process proc = new ProcessBuilder(arg1).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] arg2 = new String[] {"cmd", "/c", "start", "cmd.exe", "-c", "java", "-cp", "./src/main/java", "musichub.util.Client"};
        try {
            Process proc = new ProcessBuilder(arg2).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

         */

        ProcessBuilder pb = new ProcessBuilder("echo hello");
        //pb.command("cmd.exe", "/c", "start cmd.exe | echo hello | java -cp ./src/main/java musichub.util.Server");
        try {
            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ProcessBuilder pb2 = new ProcessBuilder();
        pb2.command("cmd.exe", "/c", "start cmd.exe | java -cp ./src/main/java musichub.util.Client");
        try {
            Thread.sleep(500);
            pb2.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

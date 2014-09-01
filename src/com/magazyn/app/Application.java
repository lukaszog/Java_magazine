package com.magazyn.app;

import com.magazyn.controller.Controller;
import com.magazyn.model.Model;
import com.magazyn.view.View;

import javax.swing.*;
import java.sql.SQLException;
import java.text.NumberFormat;

/**
 * @author Lukasz
 * Main class of application
 *
 */

public class Application {



    private static final long MEGABYTE = 1024L * 1024L;

    /**
     *
     * @param bytes
     * @return
     */
    public static long bytesToMegaBytes(long bytes) {
        return bytes / MEGABYTE;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    runApp();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     *
     * @throws SQLException
     * @throws Exception
     */
    public static void runApp() throws SQLException, Exception {

        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(view, model);

        view.setCategoryListener(controller);
        view.setCompanyListener(controller);
        view.setItemListener(controller);
        view.setOrderListener(controller);


        new Thread(new Runnable(){
            public void run(){

                try {

                    for(int i=0; i<1000; i++){
                        Runtime runtime = Runtime.getRuntime();
                        final NumberFormat format = NumberFormat.getInstance();
                        final StringBuilder sb = new StringBuilder();
                        final long maxMemory = runtime.maxMemory();
                        final long allocatedMemory = runtime.totalMemory();
                        final long freeMemory = runtime.freeMemory();
                        sb.append("free memory: " + format.format(bytesToMegaBytes(freeMemory)) + "MB\n");
                        sb.append("allocated memory: " + format.format(bytesToMegaBytes(allocatedMemory)) + "MB\n");
                        sb.append("max memory: " + format.format(bytesToMegaBytes(maxMemory)) + "MB\n");
                        sb.append("total free memory: " + format.format(bytesToMegaBytes((freeMemory + (maxMemory - allocatedMemory)))) + "MB\n");
                        System.out.println(sb);
                        Thread.sleep(3000);

                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }).start();

    }

}

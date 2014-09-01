package com.magazyn.app;

import java.sql.SQLException;
import javax.swing.SwingUtilities;
import com.magazyn.controller.Controller;
import com.magazyn.model.Model;
import com.magazyn.view.View;

/**
 * @author Łukasz ogan
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
	}

}

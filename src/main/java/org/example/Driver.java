package org.example;

import org.example.controller.MdController;

/**
 * Entry point for the program
 */
public class Driver {
    public static void main(String[] args) {

        if (args.length > 0) {

            MdController controller = new MdController(args[0], args[1], args[2]);
            controller.start();
        }

    }
}
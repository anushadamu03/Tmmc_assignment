package com.tmmc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class VerticalLineCounter {

    // Brightness threshold: pixels darker than this are considered "black"
    private static final int BLACK_THRESHOLD = 128;

    public static void main(String[] args) {

        // --- Argument Validation ---
        if (args.length != 1) {
            System.out.println("Usage: java -jar VerticalLineCounter.jar <absolute_path_to_image>");
            System.out.println("Example: java -jar VerticalLineCounter.jar C:\\TMMC_interview_assignment\\img_1.jpg");
            System.out.println("Error: Exactly 1 argument required. Received: " + args.length);
            return;
        }

        String imagePath = args[0];

        try {
            // --- Load Image ---
            File imageFile = new File(imagePath);

            if (!imageFile.exists()) {
                System.out.println("Error: File not found at path: " + imagePath);
                return;
            }

            if (!imageFile.isFile()) {
                System.out.println("Error: The provided path does not point to a valid file: " + imagePath);
                return;
            }

            BufferedImage image = ImageIO.read(imageFile);

            if (image == null) {
                System.out.println("Error: Could not read image. Ensure the file is a valid JPG image.");
                return;
            }

            // --- Count Vertical Lines ---
            int lineCount = countVerticalLines(image);

            // --- Output Result ---
            System.out.println(lineCount);

        } catch (Exception e) {
            // Application must not crash; output all exceptions to console
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Counts the number of distinct vertical black lines in the image.
     * Strategy: Scan across the vertical midpoint row of the image.
     * Count each transition from a white (non-black) pixel to a black pixel.
     * Each such transition marks the start of a new vertical line.
     */

    private static int countVerticalLines(BufferedImage image) {
        int width  = image.getWidth();
        int height = image.getHeight();

        // Use the vertical midpoint row for scanning.
        // Since lines span both top and bottom halves, the midpoint is always on a line.
        int scanRow = height / 2;

        int lineCount  = 0;
        boolean insideLine = false; // Tracks whether we are currently traversing a black line

        for (int x = 0; x < width; x++) {
            boolean isBlack = isBlackPixel(image.getRGB(x, scanRow));

            if (isBlack && !insideLine) {
                // Transition: white -> black = entering a new vertical line
                lineCount++;
                insideLine = true;
            } else if (!isBlack && insideLine) {
                // Transition: black -> white = exiting the current line
                insideLine = false;
            }
        }

        return lineCount;
    }

    /**
     * Determines if a pixel is "black" based on its brightness.
     * Extracts the red, green, and blue channels from the ARGB integer
     * and computes the average brightness. Pixels below BLACK_THRESHOLD
     * are considered black (line pixels).
     */
    private static boolean isBlackPixel(int rgb) {
        int red   = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8)  & 0xFF;
        int blue  =  rgb        & 0xFF;

        // Average brightness across R, G, B channels
        int brightness = (red + green + blue) / 3;

        return brightness < BLACK_THRESHOLD;
    }
}


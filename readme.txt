
DESCRIPTION:
  A Java console application that counts the number of vertical black
  lines in a black-and-white JPG image created using MS Paint.

  HOW TO RUN:
    Open a Command Prompt (cmd) and run:

      java -jar VerticalLineCounter.jar <absolute_path_to_image>

  EXAMPLE:
    java -jar VerticalLineCounter.jar C:\TMMC_interview_assignment\img_1.jpg

  OUTPUT:
    The application prints a single number representing the count
    of vertical black lines found in the image.

RROR HANDLING:
  - If no argument or more than 1 argument is provided, a usage message is shown.
  - If the file does not exist, an appropriate error message is displayed.
  - Any unexpected exceptions are printed to the console.
  - The application will never crash silently.

EXPECTED RESULTS (based on provided test images):
  img_1.jpg -> 1
  img_2.jpg -> 3
  img_3.jpg -> 1
  img_4.jpg -> 7

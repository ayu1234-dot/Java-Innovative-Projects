import java.util.Scanner;

class Steganography {

    private final String coverText;
    private final String secretMessage;
    private String encodedText;

    public Steganography(String coverText, String secretMessage) {
        this.coverText = coverText;
        this.secretMessage = secretMessage;
    }

    public void encode() {
        encodedText = coverText + "<!--SECRET:" + secretMessage + "-->";
        System.out.println("\nMessage Hidden Successfully!");
    }

    public void decode() {
        int start = encodedText.indexOf("<!--SECRET:") + 11;
        int end = encodedText.indexOf("-->");

        String hidden = encodedText.substring(start, end);

        System.out.println("\nHidden Message: " + hidden);
    }

    public void displayEncodedText() {
        System.out.println("\nEncoded Text:\n" + encodedText);
    }
}

public class TextSteganography {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== Text Steganography System =====");

        System.out.print("Enter Cover Text: ");
        String cover = sc.nextLine();

        System.out.print("Enter Secret Message: ");
        String secret = sc.nextLine();

        Steganography s = new Steganography(cover, secret);

        s.encode();
        s.displayEncodedText();
        s.decode();

        sc.close();
    }
}
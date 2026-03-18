import java.io.*;

public class Main {

   public static String tinhPhiGuiXe(double soGio, String loaiXe) {
    if (soGio <= 0) {
        return "Loi";
    }

    if (!loaiXe.equals("xe_may") && !loaiXe.equals("o_to")) {
        return "Loi";
    }

    if (loaiXe.equals("xe_may")) {
        if (soGio <= 2) return "5000";
        else if (soGio <= 5) return "10000";
        else return "15000";
    }

    if (loaiXe.equals("o_to")) {
        if (soGio <= 2) return "20000";
        else if (soGio <= 5) return "40000";
        else return "60000";
    }

    return "Loi";
}

    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try (
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))
        ) {
            String line;
            int lineNumber = 0;

            bw.write("KET QUA KIEM THU:\n");

            while ((line = br.readLine()) != null) {
                lineNumber++;

                if (lineNumber == 1) continue; // bỏ header

                String[] parts = line.split(",");

                if (parts.length != 4) {
                    bw.write("Dong " + lineNumber + ": Loi dinh dang\n");
                    continue;
                }

                String testType = parts[0];

                try {
                    double soGio = Double.parseDouble(parts[1]);
                    String loaiXe = parts[2];
                    String expected = parts[3];

                    String actual = tinhPhiGuiXe(soGio, loaiXe);

                    String result = actual.equals(expected) ? "PASS" : "FAIL";

                    bw.write(testType + " - Dong " + lineNumber +
                            ": Expected=" + expected +
                            ", Actual=" + actual +
                            " => " + result + "\n");

                } catch (NumberFormatException e) {
                    bw.write("Dong " + lineNumber + ": Loi chuyen doi du lieu\n");
                }
            }

            System.out.println("Da ghi ket qua vao output.txt");

        } catch (IOException e) {
            System.out.println("Loi file: " + e.getMessage());
        }
    }
}
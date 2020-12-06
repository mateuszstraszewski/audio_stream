package MicTest;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        //Mic.getLine();
        Gui newGui = new Gui();

        Mixer.Info[] newAudioMixer = AudioSystem.getMixerInfo();
        log("" + newAudioMixer.length + " mixers detected");

        for (int i = 0; i < newAudioMixer.length; i++) {
            System.out.println("========" + i + "========");
            System.out.println(newAudioMixer[i].getName());
            System.out.println(newAudioMixer[i].getVendor());
            System.out.println(newAudioMixer[i].getVersion());
            System.out.println(newAudioMixer[i].getDescription());
            System.out.println("====================");
        }

        Mixer setMixer = AudioSystem.getMixer(newAudioMixer[5]);
        Line.Info[] sourceInfo = setMixer.getSourceLineInfo();
        Line.Info[] targetInfo = setMixer.getTargetLineInfo();

        AudioFormat format = new AudioFormat(8000f, 16, 2, true, false);

        TargetDataLine line;
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format); // format is an AudioFormat object
        if (!AudioSystem.isLineSupported(info)) {
            // Handle the error ...
        }

        // Obtain and open the line.
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            // Assume that the TargetDataLine, line, has already
            // been obtained and opened.
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int numBytesRead;
            byte[] data = new byte[line.getBufferSize() / 5];

            // Begin audio capture.
            line.start();

            // Here, stopped is a global boolean set by another thread.
            while (!newGui.stopped) {
                // Read the next chunk of data from the TargetDataLine.
                numBytesRead = line.read(data, 0, data.length);
                // Save this chunk of data.
                out.write(data, 0, numBytesRead);
            }

            OutputStream outputStream = new FileOutputStream("TestOutput");
            out.writeTo(outputStream);
            System.out.println(out.size());

            byte[] result = out.toByteArray();
            for (byte b : result) {
                System.out.println(b);
            }

        } catch (LineUnavailableException ex) {
            // Handle the error ...
        }

    }

    static void log(String s) {
        System.out.println(s);
        System.out.flush();
    }


}

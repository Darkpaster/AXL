package axl;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.System.exit;

public class LOGGER {
    private static final StringBuilder log = new StringBuilder();

    public static void log(String text)
    {
        log.append(text).append((char)0x0A);
        System.out.println(text);
    }

    public static void log(String text, boolean exit)
    {
        log(text);
        if(exit)
        {
            save();
            exit(666);
        }
    }

    public static void save()
    {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));

        File output = new File(formatter.format(date) + ".log");
        FileWriter writer;
        try {
            writer = new FileWriter(output);

            writer.write(log.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

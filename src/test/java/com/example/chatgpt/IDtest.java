package com.example.chatgpt;

import com.example.chatgpt.Utils.IdUtils;
import org.junit.jupiter.api.Test;
import net.sourceforge.plantuml.SourceStringReader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IDtest {
    @Test
    void contextLoads() throws IOException {



// Create a PlantUML source string
        String plantUmlSource = "@startuml\nBob -> Alice : hello\n@enduml";

// Generate the PNG image and save it to a file
        SourceStringReader reader = new SourceStringReader(plantUmlSource);
        FileOutputStream outputStream = new FileOutputStream("myimage.png");
        reader.generateImage(outputStream);

    }
}

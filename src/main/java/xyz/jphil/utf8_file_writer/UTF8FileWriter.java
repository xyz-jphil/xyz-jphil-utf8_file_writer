/*
 * Copyright 2021 Ivan Velikanova ivan.velikanova@gmail.com .
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xyz.jphil.utf8_file_writer;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.function.Consumer;

/**
 *
 * @author Ivan
 */
public class UTF8FileWriter {
    private final String toWrite;
    private Exception error;
    public UTF8FileWriter(String toWrite) {
        this.toWrite = toWrite;
    }
    
    public static UTF8FileWriter write(String s){
        return new UTF8FileWriter(s);
    }
    
    public UTF8FileWriter to(Path p){
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(p.toFile()), StandardCharsets.UTF_8)) {
            writer.write(toWrite);
            writer.flush();
        }catch(Exception ioe){
            error = ioe;
        }
        return this;
    }
    
    public UTF8FileWriter onError(Consumer<Exception> handle){
        if(error!=null)handle.accept(error);
        return this;
    }
}

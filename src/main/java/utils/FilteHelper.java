package utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilteHelper {
    protected static Logger log = LogManager.getLogger();

    public static void writeToFile(String content, String fileName) {
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(List<String> content, String fileName) {
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (String line : content) {
                bw.write(line);
                bw.newLine();
            }

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] convertTextFileToBytesArray(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            log.debug("File doest not exist:" + fileName);
            return "".getBytes();
        }

        FileInputStream fl = new FileInputStream(file);
        byte[] arr = new byte[(int) file.length()];
        fl.read(arr);
        fl.close();
        return arr;
    }

    public static String readFileData(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            log.debug("File doest not exist:" + fileName);
            return "";
        }
        String fileContent = new String(Files.readAllBytes(Paths.get(fileName)));
        return fileContent;
    }

    public static List<String> getFileNameFromPath(String filePath) {

        List<String> fileNames = new ArrayList<>();
        try {
            File fileloc = new File(filePath);
            File[] fileArray;

            fileArray = fileloc.listFiles();
            for (File f : fileArray) {
                fileNames.add(f.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileNames;
    }

    public static void copyAllFilesFromSourceToTarget(String sourcePath, String targetPath){
        String fileSourcePath;
        String fileTargetPath;

        List<String> files = getFileNameFromPath(sourcePath);
        try {
            for (String fileName : files) {
                fileSourcePath = getAbsoulutePath(sourcePath) + fileName;
                fileTargetPath = getAbsoulutePath(targetPath);
                copyFile(fileSourcePath,fileTargetPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(String sourcePath, String targetPath) {
        File source = new File(sourcePath);
        File dest = new File(targetPath);
        try {
            FileUtils.copyFileToDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getAbsoulutePath(String relativePath){
        Path currRelativePath = Paths.get("");
        String currAbsolutePathString = currRelativePath.toAbsolutePath().toString();
        String filepath = currAbsolutePathString.replace("\\" , "/")  + relativePath.replace(".", "");
        return filepath;
    }

    public static void deleteAllFilesFromTarget(String targetPath){
        String fileTargetPath;
        List<String> files = getFileNameFromPath(targetPath);
        try {
            for (String fileName : files) {
                fileTargetPath = getAbsoulutePath(targetPath) + fileName;
                deleteFile(fileTargetPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteFile(String targetPath ){
        try {
            File targetFile = new File(targetPath);
            FileUtils.deleteQuietly(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}

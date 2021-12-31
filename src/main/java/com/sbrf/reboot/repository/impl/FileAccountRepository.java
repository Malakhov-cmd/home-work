package com.sbrf.reboot.repository.impl;


import com.sbrf.reboot.repository.AccountRepository;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FileAccountRepository implements AccountRepository {
    @Getter
    @Setter
    private String filePath;

    public FileAccountRepository(@NonNull String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Set<Long> getAllAccountsByClientId(long clientId) throws IOException {
        Set<Long> allAccountsByClientId = new HashSet();

        try (InputStream is = new FileInputStream(filePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            while (br.readLine() != null) {
                String tmpStr = br.readLine();
                if (tmpStr != null && tmpStr.contains("clientId")) {
                    long tmpIdClient = Long.parseLong(tmpStr.substring(15, tmpStr.length() - 1));
                    if (tmpIdClient == clientId) {
                        long tmpAccount = Long.parseLong(br.readLine().substring(14));
                        allAccountsByClientId.add(tmpAccount);
                    }
                }
            }
        }

        return allAccountsByClientId;
    }

    public boolean changeAccountByClientIdAndOldAccountNumber(
            long clientId, long oldAccountNumber, long newAccountNumber
    ) throws IOException {
        boolean isChanged;
        List<String> includesOfFiles = new ArrayList<>();

        try (InputStream is = new FileInputStream(filePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            br.lines().forEachOrdered(includesOfFiles::add);
        }

        isChanged = processing(clientId, oldAccountNumber, newAccountNumber, includesOfFiles);

        try (OutputStream outputStream = new FileOutputStream(filePath);
             Writer outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            includesOfFiles.forEach(str -> {
                try {
                    if (str != null) {
                        outputStreamWriter.write(str + "\n");
                    }
                } catch (IOException e) {
                    System.err.println("Error on writing");
                    e.printStackTrace();
                }
            });
        }

        return isChanged;
    }

    private boolean processing(
            long clientId, long oldAccountNumber, long newAccountNumber, List<String> fileIncludes
    ) {
        boolean isChanged = false;
        ListIterator<String> listIterator = fileIncludes.listIterator();
        while (listIterator.hasNext()) {
            String tmpStr = listIterator.next();
            if (tmpStr != null && tmpStr.contains("clientId")) {
                long tmpIdClient = Long.parseLong(tmpStr.substring(16, tmpStr.length() - 1));
                if (tmpIdClient == clientId) {
                    long tmpAccount = Long.parseLong(listIterator.next().substring(14));
                    if (tmpAccount == oldAccountNumber) {
                        listIterator.set("    \"number\": " + newAccountNumber);
                        isChanged = true;
                    }
                }
            }
        }
        return isChanged;
    }
}

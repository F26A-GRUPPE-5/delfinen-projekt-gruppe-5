package dk.delfinen.gruppe5.adapter.out.persistence;

import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.domain.model.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//TODO: her gemmes member til en fil
public class FileMemberRepository implements MemberRepository {
    FileMemberSerializer memberSerializer;
    String filePath;
    List<String> lines;


    public FileMemberRepository(FileMemberSerializer memberSerializer, String filePath) {
        this.memberSerializer = memberSerializer;
        this.filePath = filePath;

        ensureFileExists();
        parseLines();
    }

    @Override
    public Member find(int id) {
        try {
            for (String line: lines) {
                Member member = memberSerializer.toMember(line);
                if (member.getId() == id) {
                    return member;
                }
            }
        } catch (Exception e) {
            System.out.println("Member blev ikke fundet.");
            e.printStackTrace();
        }
        System.out.println("Member blev ikke fundet.");
        return null;
    }


    @Override
    public boolean exists(int id) {
        for (String line : lines) {
            Member member = memberSerializer.toMember(line);
            if (member.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void save(Member member) {
        lines.add(memberSerializer.toCSV(member));
        writeFile();
    }

    public void saveAll(List<Member> members) {
        lines.clear();

        for (Member m : members) {
            lines.add(memberSerializer.toCSV(m));
        }

        writeFile();
    }

    @Override
    public void delete(int id) {
        Iterator<String> it = lines.iterator();

        while (it.hasNext()) {
            String line = it.next();
            Member member = memberSerializer.toMember(line);

            if (member.getId() == id) {
                it.remove(); // ✅ safe removal
            }
        }
        writeFile();
    }

    @Override
    public ArrayList<Member> findAll() {
        ArrayList<Member> membersList = new ArrayList<Member>();

        for (String line : lines) {
            membersList.add(memberSerializer.toMember(line));
        }
        return membersList;
    }
    public void clearAll() {
        lines = new ArrayList<>();
        writeFile();
    }

    private void parseLines() {
        List<String> result = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Filen blev ikke fundet.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Der opstod en fejl under læsning af filen.");
            e.printStackTrace();
        }
        lines = result;
    }
    private void writeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void ensureFileExists() {
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Members.csv blev oprettet.");
            }

        } catch (IOException e) {
            throw new RuntimeException("Kunne ikke oprette filen: " + filePath, e);
        }
    }
}
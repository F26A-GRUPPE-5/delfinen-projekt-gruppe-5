package dk.delfinen.gruppe5.adapter.out.persistence;

import dk.delfinen.gruppe5.application.port.out.IMemberRepository;

//TODO: her gemmes member til en fil
public class MemberRepository implements IMemberRepository {
    MemberSerializer memberSerializer;
    String filePath;
}

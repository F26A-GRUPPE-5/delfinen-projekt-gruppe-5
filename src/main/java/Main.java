public class Main {
    static void main() {
        Member member1 = new Member("Jørgen", 1977, true, false);
        Member member2 = new Member("Hans Hansen", 2002, true, false);
        Member member3 = new Member("Gert Gertsen", 1920, true, true);
        Member member4 = new Member("Cay", 1975, true, true);

        for (Member member : Member.getMembers()) {
            System.out.println(member);
        }
    }
}

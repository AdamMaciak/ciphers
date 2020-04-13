package testLucasL;

public class TestLucasLehmer {

    public String LucasLehmer(Integer p){
        Long s=4L;
        Long M=((long)Math.pow(2,p)-1L);
        for (int i = 1; i <= p-2; i++) {
            s=((s*s)-2)%M;
        }
        if(s==0)return "Prime";
        else return "Composite";
    }
    public static void main(String[] args) {
        TestLucasLehmer mers=new TestLucasLehmer();
        for (int i = 3; i < 32; i++) {
            System.out.print("2^"+i+" ");
            System.out.println(mers.LucasLehmer(i));
        }
    }
}

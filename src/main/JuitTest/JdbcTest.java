import dao.upfile;
import org.junit.jupiter.api.Test;
import service.Operate;
import service.impl.Operatimpl;

public class JdbcTest {
    @Test
    void test1(){
        upfile f1=new upfile();
        f1.setUid("12233");
        f1.setSize("1213");
        f1.setRealpath("sjsjs");
        f1.setType("jpg");
        f1.setName("hua");
        f1.setPath("c");
        Operate op=new Operatimpl();
        System.out.println(op.savefile(f1));

    }
    @Test
    void test2(){
        Operate op=new Operatimpl();
        String str="fa37238503da410bb719d1869001bdf0.png";
        String []str1=str.split(".");
        System.out.println(str.substring(0,str.lastIndexOf(".")-1));
        System.out.println(op.findpath("383b6f2631084c49abbf4399f7c3e6cf").toString());
    }
}

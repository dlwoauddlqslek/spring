package example.day02.consolemvc.view;

import example.day02.consolemvc.controller.PhoneController;
import example.day02.consolemvc.model.dto.PhoneDto;

import java.util.ArrayList;
import java.util.Scanner;

public class PhoneView {
    //[1] 싱글톤 패턴(패턴:문법X)
    private static PhoneView phoneView=new PhoneView();
    private PhoneView(){}
    public static PhoneView getInstance(){
        return phoneView;
    }

    private Scanner scan=new Scanner(System.in);

    public void run(){
        while(true){
            System.out.println("1.[POST/CREATE/C] 2.[GET/SELECT/R]: ");
            int ch=scan.nextInt();
            switch (ch){
                case 1:postPhone(); break;
                case 2:getPhone(); break;
                default:break;
            }
        }
    }
    // 1. 이름과 번호를 입력받아 두 입력값을 객체(dto)화 해서 컨트롤에게 매개변수로 전달 후 boolean 결과 받아 출력문 처리한다.
    public void postPhone(){
        System.out.print(">> name: "); String name=scan.next();
        System.out.print(">> phone: "); String phone=scan.next();
        PhoneDto phoneDto=new PhoneDto(0,name,phone);
        boolean result= PhoneController.getInstance().postPhone(phoneDto);
        if(result){System.out.println("save");}
        else{System.out.println("fail");}
    }
    // 2.
    public void getPhone(){
        ArrayList<PhoneDto> result=PhoneController.getInstance().getPhone();
        result.forEach(phone->{
            System.out.printf("%5d %20s %20s\n",phone.getId(),phone.getName(),phone.getPhone());
        });
    }
}

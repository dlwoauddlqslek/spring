package example.day02.consolemvc.controller;

import example.day02.consolemvc.model.dao.PhoneDao;
import example.day02.consolemvc.model.dto.PhoneDto;
import example.day02.consolemvc.view.PhoneView;

import java.util.ArrayList;

public class PhoneController {
    private static PhoneController phoneCon=new PhoneController();
    private PhoneController(){}
    public static PhoneController getInstance(){
        return phoneCon;
    }

    public boolean postPhone(PhoneDto phoneDto){
        return PhoneDao.getInstance().postPhone(phoneDto);
    }

    public ArrayList<PhoneDto> getPhone(){
        return PhoneDao.getInstance().getPhone();
    }
}
